package com.jusipat.astikorcartsredux.mixin;

import com.jusipat.astikorcartsredux.entity.AbstractDrawnEntity;
import com.jusipat.astikorcartsredux.network.serverbound.RequestCartUpdatePayload;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(ClientLevel.class)
public class ClientLevelMixin {

    @Inject(method = "addEntity", at = @At("TAIL"))
    public void addEntity(Entity entity, CallbackInfo ci) {
        if (entity instanceof AbstractDrawnEntity d) {
            Objects.requireNonNull(Minecraft.getInstance().getConnection()).send(new RequestCartUpdatePayload(d.getId()));
        }
    }

}