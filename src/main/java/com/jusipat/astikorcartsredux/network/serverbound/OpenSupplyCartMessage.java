package com.jusipat.astikorcartsredux.network.serverbound;

import com.jusipat.astikorcartsredux.entity.SupplyCartEntity;
import com.jusipat.astikorcartsredux.network.Message;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public final class OpenSupplyCartMessage implements Message {
    @Override
    public void encode(final FriendlyByteBuf buf) {
    }

    @Override
    public void decode(final FriendlyByteBuf buf) {
    }

    public static void handle(final OpenSupplyCartMessage msg, final ServerMessageContext ctx) {
        final Player player = ctx.getPlayer();
        final Entity ridden = player.getVehicle();
        if (ridden instanceof SupplyCartEntity) {
            ((SupplyCartEntity) ridden).openContainer(player);
        }
    }
}