package com.jusipat.astikorcartsredux.client.renderer.entity;

import com.jusipat.astikorcartsredux.AstikorCartsRedux;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.jusipat.astikorcartsredux.client.renderer.AstikorCartsModelLayers;
import com.jusipat.astikorcartsredux.client.renderer.entity.model.PlowModel;
import com.jusipat.astikorcartsredux.entity.PlowEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;


public final class PlowRenderer extends DrawnRenderer<PlowEntity, PlowModel> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(AstikorCartsRedux.ID, "textures/entity/plow.png");

    public PlowRenderer(final EntityRendererProvider.Context renderManager) {
        super(renderManager, new PlowModel(renderManager.bakeLayer(AstikorCartsModelLayers.PLOW)));
        this.shadowRadius = 1.0F;
    }

    @Override
    public ResourceLocation getTextureLocation(final PlowEntity entity) {
        return TEXTURE;
    }

    @Override
    protected void renderContents(final PlowEntity entity, final float delta, final PoseStack stack, final MultiBufferSource source, final int packedLight) {
        super.renderContents(entity, delta, stack, source, packedLight);
        for (int i = 0; i < entity.inventory.getSlots(); i++) {
            final ItemStack itemStack = entity.getStackInSlot(i);
            if (itemStack.isEmpty()) {
                continue;
            }
            this.attach(this.model.getBody(), this.model.getShaft(i), s -> {
                s.mulPose(Axis.XP.rotationDegrees(-90.0F));
                s.mulPose(Axis.YP.rotationDegrees(90.0F));
                s.translate(-4.0D / 16.0D, 1.0D / 16.0D, 0.0D);
                if (itemStack.getItem() instanceof BlockItem) {
                    s.translate(0.0D, -0.1D, 0.0D);
                    s.mulPose(Axis.ZP.rotationDegrees(180.0F));
                }
                Minecraft.getInstance().getItemRenderer().renderStatic(itemStack, ItemDisplayContext.FIXED, packedLight, OverlayTexture.NO_OVERLAY, s, source, entity.level(), 0);
            }, stack);
        }
    }
}
