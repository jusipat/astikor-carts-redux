package com.jusipat.astikorcartsredux.mixin;

import com.jusipat.astikorcartsredux.entity.AbstractDrawnEntity;
import com.jusipat.astikorcartsredux.network.serverbound.CoachmanMovePayload;
import com.jusipat.astikorcartsredux.util.NiftyWorld;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(LocalPlayer.class)
public abstract class LocalPlayerMixin extends Player {

    public LocalPlayerMixin(Level level, GameProfile gameProfile) {
        super(level, gameProfile);
    }

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;getRootVehicle()Lnet/minecraft/world/entity/Entity;"))
    public void tick(CallbackInfo ci) {
        Entity entity = this.getRootVehicle();
        if (entity != this && entity.getControllingPassenger() == this && entity instanceof AbstractDrawnEntity drawnEntity) {
            NiftyWorld.getClient().getCurrentlyPulling(drawnEntity).ifPresent(pulling ->
                    Objects.requireNonNull(Minecraft.getInstance().getConnection()).send(new CoachmanMovePayload(this.zza)));
        }
    }

}