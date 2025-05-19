package de.mennomax.astikorcarts.item;

import de.mennomax.astikorcarts.AstikorCarts;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
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

import java.util.List;

public final class CartItem extends Item {

    private final WoodType woodType;
    private final String cartType;

    public CartItem(Properties settings, WoodType type, String cartType) {
        super(settings);
        this.woodType = type;
        this.cartType = cartType;
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> list, TooltipFlag pTooltipFlag) {
        list.add(Component.empty());
        list.add(Component.translatable("item." + this.cartType + ".tooltip1").withStyle(ChatFormatting.GRAY));
        list.add(Component.translatable("item." + this.cartType + ".tooltip2").withStyle(ChatFormatting.GRAY));
        super.appendHoverText(pStack, pContext, list, pTooltipFlag);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(final Level world, final Player player, final InteractionHand hand) {
        final ItemStack stack = player.getItemInHand(hand);
        final BlockHitResult result = getPlayerPOVHitResult(world, player, ClipContext.Fluid.ANY);
        if (result.getType() == HitResult.Type.MISS) {
            return InteractionResultHolder.pass(stack);
        } else {
            final Vec3 lookVec = player.getLookAngle();
            final List<Entity> list = world.getEntities(player, player.getBoundingBox().expandTowards(lookVec.scale(5.0D)).inflate(5.0D), EntitySelector.NO_SPECTATORS.and(Entity::canBeCollidedWith));
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
                final EntityType<?> type = BuiltInRegistries.ENTITY_TYPE.get(BuiltInRegistries.ITEM.getKey(this));
                if (type == null) {
                    return InteractionResultHolder.pass(stack);
                }
                final Entity cart = type.create(world);
                if (cart == null) {
                    return InteractionResultHolder.pass(stack);
                }
                cart.moveTo(result.getLocation().x, result.getLocation().y, result.getLocation().z);
                cart.setYRot((player.getYRot() + 180) % 360);
                if (!world.noCollision(cart, cart.getBoundingBox().inflate(0.1F, -0.1F, 0.1F))) {
                    return InteractionResultHolder.fail(stack);
                } else {
                    if (!world.isClientSide()) {
                        world.addFreshEntity(cart);
                        world.playSound(null, cart.getX(), cart.getY(), cart.getZ(), AstikorCarts.SoundEvents.CART_PLACED.get(), SoundSource.BLOCKS, 0.75F, 0.8F);
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
