package de.mennomax.astikorcarts.network.serverbound;

import com.mojang.datafixers.util.Pair;
import de.mennomax.astikorcarts.AstikorCarts;
import de.mennomax.astikorcarts.entity.AbstractDrawnEntity;
import de.mennomax.astikorcarts.util.AstikorWorld;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.Objects;

public record ActionKeyMessage() implements CustomPacketPayload {
    public static final Type<ActionKeyMessage> TYPE = CustomPacketPayload.createType(AstikorCarts.ID + "_action_key");

    public static final StreamCodec<FriendlyByteBuf, ActionKeyMessage> STREAM_CODEC = new StreamCodec<>() {
        @Override
        public @NotNull ActionKeyMessage decode(FriendlyByteBuf object) {
            return new ActionKeyMessage();
        }

        @Override
        public void encode(FriendlyByteBuf object, ActionKeyMessage object2) {}
    };

    public ActionKeyMessage(ByteBuf bb) {
        this();
    }

    private void write(ByteBuf bb) {
    }

    public static void handle(final ActionKeyMessage payload, final IPayloadContext context) {
        final Entity pulling;
        final Level level = context.player().level();
        if (context.player().getVehicle() == null) {
            pulling = context.player();
        } else {
            pulling = context.player().getVehicle();
        }
        var drawn = AstikorWorld.getServer(Objects.requireNonNull(level.getServer()), level.dimension()).getDrawn(pulling);
        drawn.map(c -> Pair.of(c, (Entity) null))
                .or(() -> level.getEntitiesOfClass(AbstractDrawnEntity.class, pulling.getBoundingBox().inflate(2.0d), entity -> entity != pulling).stream()
                        .min(Comparator.comparing(pulling::distanceTo))
                        .map(c -> Pair.of(c, pulling))
                ).ifPresent(p -> p.getFirst().setPulling(p.getFirst()));
    }

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
