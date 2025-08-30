package com.jusipat.astikorcartsredux.network.serverbound;

import com.jusipat.astikorcartsredux.AstikorCartsRedux;
import com.jusipat.astikorcartsredux.entity.SupplyCartEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jetbrains.annotations.NotNull;

public record OpenSupplyCartPayload() implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<OpenSupplyCartPayload> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(AstikorCartsRedux.MODID, AstikorCartsRedux.MODID + "_open_supply_cart"));

    public static final StreamCodec<FriendlyByteBuf, OpenSupplyCartPayload> CODEC = new StreamCodec<>() {
        @Override
        public @NotNull OpenSupplyCartPayload decode(FriendlyByteBuf object) {
            return new OpenSupplyCartPayload();
        }

        @Override
        public void encode(FriendlyByteBuf object, OpenSupplyCartPayload object2) {
        }
    };

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(IPayloadContext ctx) {
        final Entity ridden = ctx.player().getVehicle();
        if (ridden instanceof SupplyCartEntity) {
            ((SupplyCartEntity) ridden).openCustomInventoryScreen(ctx.player());
        }
    }
}