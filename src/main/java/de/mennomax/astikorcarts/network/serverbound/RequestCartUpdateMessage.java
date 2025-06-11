package de.mennomax.astikorcarts.network.serverbound;

import de.mennomax.astikorcarts.AstikorCarts;
import de.mennomax.astikorcarts.network.clientbound.UpdateDrawnMessage;
import de.mennomax.astikorcarts.util.AstikorWorld;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.NotNull;

public record RequestCartUpdateMessage(int cartId) implements CustomPacketPayload {

    public static final Type<RequestCartUpdateMessage> TYPE = CustomPacketPayload.createType(AstikorCarts.ID + "_request_cart_update");
    public static final StreamCodec<FriendlyByteBuf, RequestCartUpdateMessage> CODEC = new StreamCodec<>() {
        @Override
        public @NotNull RequestCartUpdateMessage decode(FriendlyByteBuf buf) {
            return new RequestCartUpdateMessage(buf.readVarInt());
        }

        @Override
        public void encode(FriendlyByteBuf buf, RequestCartUpdateMessage payload) {
            buf.writeVarInt(payload.cartId());
        }
    };

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(RequestCartUpdateMessage msg, ServerPlayer player) {
        var level = player.level();
        var pulling = AstikorWorld.get(level).getPulling();
        pulling.keySet().intStream()
                .filter(pullId -> pulling.get(pullId).getId() == msg.cartId)
                .findFirst().ifPresent(pullId -> PacketDistributor.sendToPlayer(player, new UpdateDrawnMessage(pullId, msg.cartId)));
    }
}