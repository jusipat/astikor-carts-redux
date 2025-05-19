package de.mennomax.astikorcarts.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import de.mennomax.astikorcarts.AstikorCarts;
import de.mennomax.astikorcarts.client.renderer.AstikorCartsModelLayers;
import de.mennomax.astikorcarts.client.renderer.entity.model.AnimalCartModel;
import de.mennomax.astikorcarts.entity.AnimalCartEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public final class AnimalCartRenderer extends DrawnRenderer<AnimalCartEntity, EntityRenderState, AnimalCartModel> {

    public AnimalCartRenderer(final EntityRendererProvider.Context renderManager) {
        super(renderManager, new AnimalCartModel(renderManager.bakeLayer(AstikorCartsModelLayers.ANIMAL_CART)));
        this.shadowRadius = 1.0F;
    }

    @Override
    public AnimalCartRenderState createRenderState() {
        return null;
    }

    @Override
    protected void renderContents(EntityRenderState renderState, PoseStack stack, MultiBufferSource source, int packedLight) {
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
        return ResourceLocation.fromNamespaceAndPath(AstikorCarts.ID, "textures/entity/" + entity.getWoodType().name() + "_animal_cart.png");
    }
}