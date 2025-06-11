package de.mennomax.astikorcarts.network.serverbound;

import de.mennomax.astikorcarts.AstikorCarts;
import de.mennomax.astikorcarts.entity.AbstractDrawnEntity;
import de.mennomax.astikorcarts.util.AstikorWorld;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public record ToggleSlowMessage() implements CustomPacketPayload {

    public static final Type<ToggleSlowMessage> TYPE = CustomPacketPayload.createType(AstikorCarts.ID + "_toggle_slow");
    public static final StreamCodec<FriendlyByteBuf, ToggleSlowMessage> STREAM_CODEC = new StreamCodec<>() {
        @Override
        public @NotNull ToggleSlowMessage decode(FriendlyByteBuf object) {
            return new ToggleSlowMessage();
        }

        @Override
        public void encode(FriendlyByteBuf object, ToggleSlowMessage object2) {
        }
    };

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(final ToggleSlowMessage payload, final IPayloadContext context) {
        getCart(context.player()).ifPresent(AbstractDrawnEntity::toggleSlow);
    }

    public static Optional<AbstractDrawnEntity> getCart(final Player player) {
        final Entity ridden = player.getVehicle();
        if (ridden == null) return Optional.empty();
        if (ridden instanceof AbstractDrawnEntity) return Optional.of((AbstractDrawnEntity) ridden);
        return AstikorWorld.get(player.level()).getDrawn(ridden);
    }

}