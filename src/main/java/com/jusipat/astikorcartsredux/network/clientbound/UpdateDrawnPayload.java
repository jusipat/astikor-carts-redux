package com.jusipat.astikorcartsredux.network.clientbound;

import com.jusipat.astikorcartsredux.AstikorCartsRedux;
import com.jusipat.astikorcartsredux.entity.AbstractDrawnEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public record UpdateDrawnPayload(int pullingId, int cartId) implements CustomPacketPayload {


    public static final CustomPacketPayload.Type<UpdateDrawnPayload> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(AstikorCartsRedux.MODID,AstikorCartsRedux.MODID + "_update_drawn"));

    public static final StreamCodec<FriendlyByteBuf, UpdateDrawnPayload> CODEC = new StreamCodec<>() {
        @Override
        public @NotNull UpdateDrawnPayload decode(FriendlyByteBuf buf) {
            int pullingId = buf.readVarInt();
            int cartId = buf.readVarInt();
            return new UpdateDrawnPayload(pullingId, cartId);
        }

        @Override
        public void encode(FriendlyByteBuf buf, UpdateDrawnPayload payload) {
            buf.writeVarInt(payload.pullingId);
            buf.writeVarInt(payload.cartId);
        }
    };

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(UpdateDrawnPayload msg, Level level) {
        final Entity e = level.getEntity(msg.cartId);
        if (e instanceof AbstractDrawnEntity drawn) {
            if (msg.pullingId < 0) {
                drawn.setPulling(null);
            } else {
                drawn.setPulling(level.getEntity(msg.pullingId));
            }
        }
    }
}
