package com.jusipat.astikorcartsredux.mixin;

import com.jusipat.astikorcartsredux.AstikorCartsReduxClientRuntime;
import com.jusipat.astikorcartsredux.entity.ReaperEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetPassengersPacket;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPacketListener.class)
public abstract class ClientPacketListenerMixin {

    @Shadow private ClientLevel level;

    @Inject(method = "handleSetEntityPassengersPacket", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;setOverlayMessage(Lnet/minecraft/network/chat/Component;Z)V"), cancellable = true)
    private void handleSetEntityPassengersPacket(ClientboundSetPassengersPacket clientboundSetPassengersPacket, CallbackInfo ci) {
        Entity vehicle = this.level.getEntity(clientboundSetPassengersPacket.getVehicle());
        if (vehicle instanceof ReaperEntity) {
            Component slowModeMessage = Component.translatable("tutorial.slow.message", AstikorCartsReduxClientRuntime.TOGGLE_SLOW_MAPPING.get().getTranslatedKeyMessage());
            Component component = Component.translatable("mount.onboard", Minecraft.getInstance().options.keyShift.getTranslatedKeyMessage()).append("; ").append(slowModeMessage);
            Minecraft.getInstance().gui.setOverlayMessage(component, false);
            Minecraft.getInstance().getNarrator().sayNow(component);
            ci.cancel();
        }
    }

}