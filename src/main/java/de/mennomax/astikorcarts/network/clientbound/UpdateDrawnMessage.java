package de.mennomax.astikorcarts.network.clientbound;

import de.mennomax.astikorcarts.AstikorCarts;
import de.mennomax.astikorcarts.entity.AbstractDrawnEntity;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jetbrains.annotations.NotNull;

public record UpdateDrawnMessage(int pullingId, int cartId) implements CustomPacketPayload {
    public static final Type<UpdateDrawnMessage> TYPE = CustomPacketPayload.createType(AstikorCarts.ID + "_update_drawn");
    public static final StreamCodec<ByteBuf, UpdateDrawnMessage> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT, UpdateDrawnMessage::pullingId,
            ByteBufCodecs.VAR_INT, UpdateDrawnMessage::cartId,
            UpdateDrawnMessage::new
    );

    public static void handle(final UpdateDrawnMessage payload, final IPayloadContext context) {
        final Level level = context.player().level();
        final Entity e = level.getEntity(payload.cartId);
        if (e instanceof AbstractDrawnEntity) {
            if (payload.pullingId < 0) {
                ((AbstractDrawnEntity) e).setPulling(null);
            } else {
                ((AbstractDrawnEntity) e).setPulling(level.getEntity(payload.pullingId));
            }
        }
    }

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
