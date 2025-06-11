package de.mennomax.astikorcarts.client.mixin;

import de.mennomax.astikorcarts.entity.AbstractDrawnEntity;
import de.mennomax.astikorcarts.network.serverbound.RequestCartUpdateMessage;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.Entity;
import net.neoforged.neoforge.network.PacketDistributor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientLevel.class)
public class ClientLevelMixin {

    @Inject(method = "addEntity", at = @At("TAIL"))
    public void onAddEntity(Entity entity, CallbackInfo ci) {
        if (entity instanceof AbstractDrawnEntity d) {
            //PacketDistributor.sendToServer(new RequestCartUpdateMessage(d.getId())); todo: fix
        }
    }
}