package com.jusipat.astikorcartsredux.entity;

import com.jusipat.astikorcartsredux.AstikorCartsRedux;
import com.jusipat.astikorcartsredux.AstikorCartsReduxConfig;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import org.checkerframework.checker.nullness.qual.Nullable;

public class HandCartEntity extends AbstractCargoCart {

    public HandCartEntity(EntityType<? extends Entity> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn, 27);
        this.spacing = 1.0;
    }

    @Override
    public Item getCartItem() {
        return AstikorCartsRedux.CARTS
                .get("hand_cart")
                .get(getWoodType())
                .get().asItem();
    }

    @Override
    public AstikorCartsReduxConfig.CartConfig getConfig() {
        return AstikorCartsReduxConfig.get().handCart;
    }

    @Override
    protected boolean canInteractNotOpen() {
        return false;
    }

    @Override
    protected InteractionResult onInteractNotOpen(Player player, InteractionHand hand) {
        return InteractionResult.SUCCESS;
    }

    @Override
    protected AbstractContainerMenu createMenuLootUnpacked(int i, Inventory inventory, Player player) {
        return ChestMenu.threeRows(i, inventory, this);
    }

    @Override
    public void setLootTable(@Nullable ResourceLocation resourceLocation) {

    }
}
