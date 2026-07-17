package com.jusipat.astikorcartsredux.item;

import com.jusipat.astikorcartsredux.AstikorCartsRedux;
import com.jusipat.astikorcartsredux.entity.AbstractDrawnEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.List;

public class CartItem extends Item {

    private final WoodType woodType;
    private final String cartType;

    public CartItem(WoodType type, String cartType, Properties settings) {
        super(settings);
        this.woodType = type;
        this.cartType = cartType;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        tooltipComponents.add(Component.empty());
        tooltipComponents.add(Component.translatable("item." + this.cartType + ".tooltip1").withStyle(ChatFormatting.GRAY));
        tooltipComponents.add(Component.translatable("item." + this.cartType + ".tooltip2").withStyle(ChatFormatting.GRAY));
        super.appendHoverText(stack, level, tooltipComponents, isAdvanced);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        final ItemStack stack = player.getItemInHand(interactionHand);
        final BlockHitResult result = getPlayerPOVHitResult(level, player, ClipContext.Fluid.ANY);
        if (result.getType() == HitResult.Type.MISS) {
            return InteractionResultHolder.pass(stack);
        } else {
            final Vec3 lookVec = player.getLookAngle();
            final List<Entity> list = level.getEntities(player, player.getBoundingBox().expandTowards(lookVec.scale(5.0D)).inflate(5.0D), EntitySelector.NO_SPECTATORS.and(Entity::canBeCollidedWith));
            if (!list.isEmpty()) {
                final Vec3 eyePos = player.getEyePosition(1.0F);
                for (final Entity entity : list) {
                    final AABB axisalignedbb = entity.getBoundingBox().inflate(entity.getPickRadius());
                    if (axisalignedbb.contains(eyePos)) {
                        return InteractionResultHolder.pass(stack);
                    }
                }
            }

            if (result.getType() == HitResult.Type.BLOCK) {
                final EntityType<?> type = BuiltInRegistries.ENTITY_TYPE.get(new ResourceLocation(AstikorCartsRedux.MODID, this.cartType));
                final Entity cart = type.create(level);
                if (cart == null) {
                    return InteractionResultHolder.pass(stack);
                }
                if (cart instanceof AbstractDrawnEntity drawn) {
                    drawn.setWoodType(this.woodType);
                }
                cart.moveTo(result.getLocation().x, result.getLocation().y, result.getLocation().z);
                cart.setYRot((player.getYRot() + 180) % 360);
                if (!level.noCollision(cart, cart.getBoundingBox().inflate(0.1F, -0.1F, 0.1F))) {
                    return InteractionResultHolder.fail(stack);
                } else {
                    if (!level.isClientSide()) {
                        level.addFreshEntity(cart);
                        level.playSound(null, cart.getX(), cart.getY(), cart.getZ(), AstikorCartsRedux.PLACE_SOUND.get(), SoundSource.BLOCKS, 0.75F, 0.8F);
                    }
                    if (!player.getAbilities().instabuild) {
                        stack.shrink(1);
                    }
                    player.awardStat(Stats.ITEM_USED.get(this));
                    return InteractionResultHolder.success(stack);
                }
            } else {
                return InteractionResultHolder.pass(stack);
            }
        }
    }
}
