package com.jusipat.astikorcartsredux.client.renderer.entity;

import com.jusipat.astikorcartsredux.AstikorCartsRedux;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.datafixers.util.Pair;
import com.jusipat.astikorcartsredux.client.renderer.AstikorCartsModelLayers;
import com.jusipat.astikorcartsredux.client.renderer.entity.model.AnimalCartModel;
import com.jusipat.astikorcartsredux.entity.AnimalCartEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.entity.BannerPattern;

import java.util.List;

public final class AnimalCartRenderer extends DrawnRenderer<AnimalCartEntity, AnimalCartModel> {

    public AnimalCartRenderer(final EntityRendererProvider.Context renderManager) {
        super(renderManager, new AnimalCartModel(renderManager.bakeLayer(AstikorCartsModelLayers.ANIMAL_CART)));
        this.shadowRadius = 1.0F;
    }

    @Override
    protected void renderContents(final AnimalCartEntity entity, final float delta, final PoseStack stack, final MultiBufferSource source, final int packedLight) {
        super.renderContents(entity, delta, stack, source, packedLight);
        final List<Pair<Holder<BannerPattern>, DyeColor>> list = entity.getBannerPattern();
        if (!list.isEmpty()) {
            stack.pushPose();
            this.model.getBody().translateAndRotate(stack);
            stack.translate(0.0D, -0.6D, 1.56D);
            this.renderBanner(stack, source, packedLight, list);
            stack.popPose();
        }
    }

    @Override
    public ResourceLocation getTextureLocation(final AnimalCartEntity entity) {
        return new ResourceLocation(AstikorCartsRedux.ID, "textures/entity/" + entity.getWoodType() + "_animal_cart.png");
    }
}
