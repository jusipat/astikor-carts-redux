package com.jusipat.astikorcartsredux.network.serverbound;

import com.jusipat.astikorcartsredux.AstikorCartsRedux;
import com.jusipat.astikorcartsredux.entity.AbstractDrawnEntity;
import com.jusipat.astikorcartsredux.util.NiftyWorld;
import it.unimi.dsi.fastutil.Pair;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

public record ActionKeyPayload() implements CustomPacketPayload {


    public static final CustomPacketPayload.Type<ActionKeyPayload> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(AstikorCartsRedux.MODID, AstikorCartsRedux.MODID + "_action_key"));
    public static final StreamCodec<FriendlyByteBuf, ActionKeyPayload> CODEC = new StreamCodec<>() {
        @Override
        public @NotNull ActionKeyPayload decode(FriendlyByteBuf object) {
            return new ActionKeyPayload();
        }

        @Override
        public void encode(FriendlyByteBuf object, ActionKeyPayload object2) {}
    };


    public static void handle(IPayloadContext ctx) {
        final Entity pulling;
        final Level level = ctx.player().level();
        if (ctx.player().getVehicle() == null) {
            pulling = ctx.player();
        } else {
            pulling = ctx.player().getVehicle();
        }
        var drawn = NiftyWorld.getServer(AstikorCartsRedux.server, level.dimension()).getDrawn(pulling);
        drawn.map(c -> Pair.of(c, (Entity) null))
                .or(() -> level.getEntitiesOfClass(AbstractDrawnEntity.class, pulling.getBoundingBox().inflate(2.0d), entity -> entity != pulling).stream()
                        .min(Comparator.comparing(pulling::distanceTo))
                        .map(c -> Pair.of(c, pulling))
                ).ifPresent(p -> p.key().setPulling(p.value()));
    }

    @Override
    public CustomPacketPayload.Type<ActionKeyPayload> type()
    {
        return TYPE;
    }
}