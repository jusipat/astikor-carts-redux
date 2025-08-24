package com.jusipat.astikorcartsredux.client.renderer.entity;

import com.jusipat.astikorcartsredux.AstikorCartsRedux;
import com.jusipat.astikorcartsredux.client.renderer.AstikorCartsReduxModelLayers;
import com.jusipat.astikorcartsredux.client.renderer.entity.model.ReaperModel;
import com.jusipat.astikorcartsredux.entity.ReaperCartEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public final class ReaperRenderer extends DrawnRenderer<ReaperCartEntity, CartRenderState, ReaperModel> {

    public ReaperRenderer(final EntityRendererProvider.Context renderManager) {
        super(renderManager, new ReaperModel(renderManager.bakeLayer(AstikorCartsReduxModelLayers.REAPER)));
        this.shadowRadius = 1.0F;
    }

    @Override
    public @NotNull CartRenderState createRenderState() {
        return new CartRenderState();
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(CartRenderState state) {
        return AstikorCartsRedux.resLoc("textures/entity/" + state.woodType.name() + "_reaper.png");
    }

    @Override
    protected void renderContents(CartRenderState state, PoseStack stack, MultiBufferSource source, int packedLight) {}
}