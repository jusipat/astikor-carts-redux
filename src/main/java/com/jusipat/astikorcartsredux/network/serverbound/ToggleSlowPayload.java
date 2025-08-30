package com.jusipat.astikorcartsredux.network.serverbound;

import com.jusipat.astikorcartsredux.AstikorCartsRedux;
import com.jusipat.astikorcartsredux.entity.AbstractDrawnEntity;
import com.jusipat.astikorcartsredux.util.NiftyWorld;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public record ToggleSlowPayload() implements CustomPacketPayload {


    public static final CustomPacketPayload.Type<ToggleSlowPayload> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(AstikorCartsRedux.MODID, AstikorCartsRedux.MODID + "_toggle_slow"));
    public static final StreamCodec<FriendlyByteBuf, ToggleSlowPayload> CODEC = new StreamCodec<>() {
        @Override
        public @NotNull ToggleSlowPayload decode(FriendlyByteBuf object) {
            return new ToggleSlowPayload();
        }

        @Override
        public void encode(FriendlyByteBuf object, ToggleSlowPayload object2) {
        }
    };

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(final Player player) {
        getCart(player).ifPresent(AbstractDrawnEntity::toggleSlow);
    }

    public static Optional<AbstractDrawnEntity> getCart(final Player player) {
        final Entity ridden = player.getVehicle();
        if (ridden == null) return Optional.empty();
        if (ridden instanceof AbstractDrawnEntity) return Optional.of((AbstractDrawnEntity) ridden);
        return NiftyWorld.get(player.level()).getDrawn(ridden);
    }

}