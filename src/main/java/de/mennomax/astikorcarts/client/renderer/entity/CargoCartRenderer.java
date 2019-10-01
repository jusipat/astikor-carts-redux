package de.mennomax.astikorcarts.client.renderer.entity;

import com.mojang.blaze3d.platform.GlStateManager;

import de.mennomax.astikorcarts.AstikorCarts;
import de.mennomax.astikorcarts.client.renderer.entity.model.CargoCartModel;
import de.mennomax.astikorcarts.entity.CargoCartEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;

public class CargoCartRenderer extends EntityRenderer<CargoCartEntity>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(AstikorCarts.MODID, "textures/entity/cargocart.png");
    protected EntityModel<CargoCartEntity> model = new CargoCartModel();

    public CargoCartRenderer(EntityRendererManager renderManager)
    {
        super(renderManager);
        this.shadowSize = 1.0F;
    }

    @Override
    protected ResourceLocation getEntityTexture(CargoCartEntity entity) {
        return TEXTURE;
    }

    @Override
    public void doRender(CargoCartEntity entity, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.pushMatrix();
        this.setupTranslation(x, y, z);
        this.setupRotation(entityYaw);
        this.bindEntityTexture(entity);

        if (this.renderOutlines) {
            GlStateManager.enableColorMaterial();
            GlStateManager.setupSolidRenderingTextureCombine(this.getTeamColor(entity));
        }

        this.model.render(entity, partialTicks, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);

        if (this.renderOutlines) {
            GlStateManager.tearDownSolidRenderingTextureCombine();
            GlStateManager.disableColorMaterial();
        }
        GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    public void setupRotation(float entityYaw) {
        GlStateManager.rotatef(180.0F - entityYaw, 0.0F, 1.0F, 0.0F);
        GlStateManager.scalef(-1.0F, -1.0F, 1.0F);
    }

    public void setupTranslation(double x, double y, double z) {
        GlStateManager.translated(x, y + 1.0F, z);
    }

}
