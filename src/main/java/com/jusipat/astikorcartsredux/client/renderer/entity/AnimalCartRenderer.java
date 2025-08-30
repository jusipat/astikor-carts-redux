package com.jusipat.astikorcartsredux.client.renderer.entity;

import com.jusipat.astikorcartsredux.AstikorCartsRedux;
import com.jusipat.astikorcartsredux.client.renderer.AstikorCartsReduxModelLayers;
import com.jusipat.astikorcartsredux.client.renderer.entity.model.AnimalCartModel;
import com.jusipat.astikorcartsredux.entity.AnimalCartEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public final class AnimalCartRenderer extends DrawnRenderer<AnimalCartEntity, AnimalCartModel> {

    public AnimalCartRenderer(final EntityRendererProvider.Context renderManager) {
        super(renderManager, new AnimalCartModel(renderManager.bakeLayer(AstikorCartsReduxModelLayers.ANIMAL_CART)));
        this.shadowRadius = 1.0F;
    }

    @Override
    protected void renderContents(final AnimalCartEntity entity, final float delta, final PoseStack stack, final MultiBufferSource source, final int packedLight) {
        if (entity.getBannerColor() != null) {
            stack.pushPose();
            this.model.getBody().translateAndRotate(stack);
            stack.translate(0.0D, -0.6D, 1.56D);
            this.renderBanner(entity, stack, source, delta, packedLight, entity.getBannerColor(), entity.getBannerPattern());
            stack.popPose();
        }
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(final AnimalCartEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(AstikorCartsRedux.MODID, "textures/entity/" + entity.getWoodType().name() + "_animal_cart.png");
    }
}
