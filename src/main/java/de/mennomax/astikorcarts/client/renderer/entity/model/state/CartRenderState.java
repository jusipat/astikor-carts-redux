package de.mennomax.astikorcarts.client.renderer.entity.model.state;

import de.mennomax.astikorcarts.entity.AbstractDrawnEntity;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

public class CartRenderState extends EntityRenderState {
        final float delta;
        Vec3 target;
        float yaw = Float.NaN;
        float pitch = Float.NaN;

    public CartRenderState(float delta) {
        this.delta = delta;
    }

    public Vec3 getTarget() {
            if (this.target == null) {
                if (AbstractDrawnEntity.this.pulling == null) {
                    this.target = AbstractDrawnEntity.this.getViewVector(this.delta);
                } else {
                    this.target = AbstractDrawnEntity.this.getRelativeTargetVec(this.delta);
                }
            }
            return this.target;
        }

        public float getYaw() {
            if (Float.isNaN(this.yaw)) {
                if (AbstractDrawnEntity.this.pulling == null) {
                    this.yaw = Mth.lerp(this.delta, AbstractDrawnEntity.this.yRotO, AbstractDrawnEntity.this.getYRot());
                } else {
                    this.yaw = AbstractDrawnEntity.getYaw(this.getTarget());
                }
            }
            return this.yaw;
        }

        public float getPitch() {
            if (Float.isNaN(this.pitch)) {
                if (AbstractDrawnEntity.this.pulling == null) {
                    this.pitch = Mth.lerp(this.delta, AbstractDrawnEntity.this.xRotO, AbstractDrawnEntity.this.getXRot());
                } else {
                    this.pitch = AbstractDrawnEntity.getPitch(this.target);
                }
            }
            return this.pitch;
        }
}
