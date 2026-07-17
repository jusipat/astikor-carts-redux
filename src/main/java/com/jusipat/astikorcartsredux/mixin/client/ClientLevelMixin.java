package com.jusipat.astikorcartsredux.mixin.client;

import com.jusipat.astikorcartsredux.AstikorCartsRedux;
import com.jusipat.astikorcartsredux.entity.AbstractDrawnEntity;
import com.jusipat.astikorcartsredux.network.serverbound.RequestCartUpdateMessage;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientLevel.class)
public class ClientLevelMixin {

    @Inject(method = "addEntity", at = @At("TAIL"))
    public void addEntity(int i, Entity entity, CallbackInfo ci) {
        if (entity instanceof AbstractDrawnEntity d) {
            AstikorCartsRedux.CHANNEL.sendToServer(new RequestCartUpdateMessage(d.getId()));
        }
    }

}