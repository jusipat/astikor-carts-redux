package com.jusipat.astikorcartsredux.client.renderer.entity;

import com.jusipat.astikorcartsredux.client.renderer.entity.ICargoCartRenderer;
import com.jusipat.astikorcartsredux.entity.AbstractCargoCart;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;

@FunctionalInterface
public interface IContentsRenderer {
    void render(final ICargoCartRenderer renderer, final AbstractCargoCart entity, final PoseStack stack, final MultiBufferSource source, final int packedLight, final NonNullList<ItemStack> cargo);
}