package com.jusipat.astikorcartsredux.client.renderer.entity;

import com.jusipat.astikorcartsredux.AstikorCartsRedux;
import com.jusipat.astikorcartsredux.client.renderer.entity.model.ReaperModel;
import com.jusipat.astikorcartsredux.entity.ReaperEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.jusipat.astikorcartsredux.client.renderer.AstikorCartsReduxModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ReaperRenderer extends DrawnRenderer<ReaperEntity, ReaperModel> {

    public ReaperRenderer(final EntityRendererProvider.Context renderManager) {
        super(renderManager, new ReaperModel(renderManager.bakeLayer(AstikorCartsReduxModelLayers.REAPER)));
        this.shadowRadius = 1.0F;
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(final ReaperEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(AstikorCartsRedux.MODID, "textures/entity/" + entity.getWoodType().name() + "_reaper.png");
    }

    @Override
    protected void renderContents(ReaperEntity entity, float delta, PoseStack stack, MultiBufferSource source, int packedLight) {}
}