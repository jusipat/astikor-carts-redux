package com.jusipat.astikorcartsredux.network.serverbound;

import com.jusipat.astikorcartsredux.AstikorCartsRedux;
import com.jusipat.astikorcartsredux.entity.AbstractDrawnEntity;
import com.jusipat.astikorcartsredux.entity.PostilionEntity;
import com.jusipat.astikorcartsredux.util.NiftyWorld;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jetbrains.annotations.NotNull;

public record CoachmanMovePayload(float zza) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<CoachmanMovePayload> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(AstikorCartsRedux.MODID, AstikorCartsRedux.MODID + "_coachman_move"));

    public static final StreamCodec<FriendlyByteBuf, CoachmanMovePayload> CODEC = new StreamCodec<>() {
        @Override
        public @NotNull CoachmanMovePayload decode(FriendlyByteBuf buf) {
            return new CoachmanMovePayload(buf.readFloat());
        }

        @Override
        public void encode(FriendlyByteBuf buf, CoachmanMovePayload msg) {
            buf.writeFloat(msg.zza());
        }
    };

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(CoachmanMovePayload payload, IPayloadContext ctx) { // needs CoachmanMovePayload msg,
        ServerLevel level = (ServerLevel) ctx.player().level();
        Entity vehicle = ctx.player().getRootVehicle();
        if (vehicle != ctx.player() && vehicle.getControllingPassenger() == ctx.player() && vehicle instanceof AbstractDrawnEntity drawnEntity) {
            Entity pulling = NiftyWorld.get(level).getCurrentlyPulling(drawnEntity).orElse(null);
            if (pulling != null) {
                LivingEntity passenger = pulling.getControllingPassenger();
                if (passenger instanceof PostilionEntity postilion) {
                    postilion.setYRot(ctx.player().getYRot());
                    postilion.yRotO = postilion.getYRot();
                    postilion.setXRot(ctx.player().getXRot() * 0.5F);
                    postilion.zza = payload.zza();
                    postilion.xxa = 0.0F;
                }
            }
        }
    }
}
