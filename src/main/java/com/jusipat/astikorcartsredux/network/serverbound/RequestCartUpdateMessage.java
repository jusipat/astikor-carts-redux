package com.jusipat.astikorcartsredux.network.serverbound;


import com.jusipat.astikorcartsredux.AstikorCartsRedux;
import com.jusipat.astikorcartsredux.network.Message;
import com.jusipat.astikorcartsredux.network.ServerMessageContext;
import com.jusipat.astikorcartsredux.network.clientbound.UpdateDrawnMessage;
import com.jusipat.astikorcartsredux.util.NiftyWorld;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.PacketDistributor;

import java.util.function.Supplier;

public class RequestCartUpdateMessage implements Message {

    private int cartId;

    public RequestCartUpdateMessage() {
    }

    public RequestCartUpdateMessage(int cartId) {
        this.cartId = cartId;
    }

    @Override
    public void encode(FriendlyByteBuf buf) {
        buf.writeVarInt(cartId);
    }

    @Override
    public void decode(FriendlyByteBuf buf) {
        this.cartId = buf.readVarInt();
    }

    public static void handle(RequestCartUpdateMessage msg, ServerMessageContext ctx) {
        var level = ctx.getPlayer().level();
        var pulling = NiftyWorld.get(level).getPulling();

        pulling.keySet().intStream()
                .filter(pullId -> NiftyWorld.get(level).getDrawn(level.getEntity(pullId)).map(Entity::getId).orElse(-1) == msg.cartId)
                .findFirst().ifPresent(pullId -> AstikorCartsRedux.CHANNEL.send(PacketDistributor.PLAYER.with((Supplier<ServerPlayer>) ctx.getPlayer()), new UpdateDrawnMessage(pullId, msg.cartId)));
    }

}