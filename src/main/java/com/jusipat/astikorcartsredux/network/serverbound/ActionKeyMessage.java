package com.jusipat.astikorcartsredux.network.serverbound;

import com.jusipat.astikorcartsredux.AstikorCartsRedux;
import com.jusipat.astikorcartsredux.entity.AbstractDrawnEntity;
import com.jusipat.astikorcartsredux.network.Message;
import com.jusipat.astikorcartsredux.network.ServerMessageContext;
import com.jusipat.astikorcartsredux.util.NiftyWorld;
import it.unimi.dsi.fastutil.Pair;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;

import java.util.Comparator;

public class ActionKeyMessage implements Message {
    @Override
    public void encode(FriendlyByteBuf buf) {
    }

    @Override
    public void decode(FriendlyByteBuf buf) {
    }

    public static void handle(final ActionKeyMessage ignoredMsg, final ServerMessageContext ctx) {
        final Entity pulling;
        final Level level = ctx.getWorld();
        if (ctx.getPlayer().getVehicle() == null) {
            pulling = ctx.getPlayer();
        } else {
            pulling = ctx.getPlayer().getVehicle();
        }
        var drawn = NiftyWorld.getServer(AstikorCartsRedux.server, level.dimension()).getDrawn(pulling);
        drawn.map(c -> Pair.of(c, (Entity) null))
                .or(() -> level.getEntitiesOfClass(AbstractDrawnEntity.class, pulling.getBoundingBox().inflate(2.0d), entity -> entity != pulling).stream()
                        .min(Comparator.comparing(pulling::distanceTo))
                        .map(c -> Pair.of(c, pulling))
                ).filter(p -> p.key().getConfig().adventureModeInteract.get() || ctx.getPlayer().gameMode.getGameModeForPlayer() != GameType.ADVENTURE)
                .ifPresent(p -> p.key().setPulling(p.value()));
    }

}