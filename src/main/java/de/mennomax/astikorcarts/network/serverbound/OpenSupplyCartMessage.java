package de.mennomax.astikorcarts.network.serverbound;

import de.mennomax.astikorcarts.AstikorCarts;
import de.mennomax.astikorcarts.entity.SupplyCartEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.Entity;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jetbrains.annotations.NotNull;

public record OpenSupplyCartMessage() implements CustomPacketPayload {

    public static final Type<OpenSupplyCartMessage> TYPE = CustomPacketPayload.createType(AstikorCarts.ID + "_open_supply_cart");
    public static final StreamCodec<FriendlyByteBuf, OpenSupplyCartMessage> STREAM_CODEC = new StreamCodec<>() {
        @Override
        public @NotNull OpenSupplyCartMessage decode(FriendlyByteBuf object) {
            return new OpenSupplyCartMessage();
        }

        @Override
        public void encode(FriendlyByteBuf object, OpenSupplyCartMessage object2) {
        }
    };

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(final OpenSupplyCartMessage payload, final IPayloadContext context) {
        final Entity ridden = context.player().getVehicle();
        if (ridden instanceof SupplyCartEntity) {
            ((SupplyCartEntity) ridden).openCustomInventoryScreen(context.player());
        }
    }
}