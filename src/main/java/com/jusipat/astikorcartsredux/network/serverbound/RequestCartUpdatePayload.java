package com.jusipat.astikorcartsredux.network.serverbound;

import com.jusipat.astikorcartsredux.AstikorCartsRedux;
import com.jusipat.astikorcartsredux.network.clientbound.UpdateDrawnPayload;
import com.jusipat.astikorcartsredux.util.NiftyWorld;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jetbrains.annotations.NotNull;

public record RequestCartUpdatePayload(int cartId) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<RequestCartUpdatePayload> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(AstikorCartsRedux.MODID, AstikorCartsRedux.MODID + "_request_cart_update"));

    public static final StreamCodec<FriendlyByteBuf, RequestCartUpdatePayload> CODEC = new StreamCodec<>() {
        @Override
        public @NotNull RequestCartUpdatePayload decode(FriendlyByteBuf buf) {
            return new RequestCartUpdatePayload(buf.readVarInt());
        }

        @Override
        public void encode(FriendlyByteBuf buf, RequestCartUpdatePayload payload) {
            buf.writeVarInt(payload.cartId());
        }
    };


    public static void handle(RequestCartUpdatePayload payload, IPayloadContext ctx) { // needs RequestCartUpdatePayload msg
        var level = ctx.player().level();
        var pulling = NiftyWorld.get(level).getPulling();

        pulling.keySet().intStream()
                .filter(pullId -> NiftyWorld.get(level).getDrawn(level.getEntity(pullId)).map(Entity::getId).orElse(-1) == payload.cartId)
                .findFirst().ifPresent(pullId -> PacketDistributor.sendToPlayer((ServerPlayer) ctx.player(), new UpdateDrawnPayload(pullId, payload.cartId)));
    }

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}