package com.jusipat.astikorcartsredux.client.renderer.entity;

import com.jusipat.astikorcartsredux.AstikorCartsRedux;
import com.jusipat.astikorcartsredux.AstikorCartsReduxConfig;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import javax.annotation.Nullable;
import java.util.function.Predicate;

enum Contents {
    FLOWERS(s -> s.getItem() instanceof BlockItem && s.is(ItemTags.FLOWERS) && AstikorCartsReduxConfig.getClient().renderSupplyFlowers.get(), ICargoCartRenderer::renderFlowers),
    PAINTINGS(s -> s.getItem() == Items.PAINTING && AstikorCartsReduxConfig.getClient().renderSupplyPaintings.get(), ICargoCartRenderer::renderPaintings),
    WHEEL(s -> s.getItem() == AstikorCartsRedux.WHEEL.get() && AstikorCartsReduxConfig.getClient().renderSupplyWheel.get(), ICargoCartRenderer::renderWheel),
    SUPPLIES(s -> AstikorCartsReduxConfig.getClient().renderSupplies.get(), ICargoCartRenderer::renderSupplies),
    NONE(s -> true, null);

    private final Predicate<? super ItemStack> predicate;
    private final IContentsRenderer renderer;

    Contents(final Predicate<? super ItemStack> predicate, @Nullable final IContentsRenderer renderer) {
        this.predicate = predicate;
        this.renderer = renderer;
    }

    public Predicate<? super ItemStack> getPredicate() {
        return predicate;
    }

    public IContentsRenderer getRenderer() {
        return renderer;
    }
}