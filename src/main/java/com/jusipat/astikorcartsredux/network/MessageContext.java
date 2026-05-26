package com.jusipat.astikorcartsredux.network;

import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkEvent;

public abstract class MessageContext {
    protected final NetworkEvent.Context context;

    public MessageContext(final NetworkEvent.Context context) {
        this.context = context;
    }

    public abstract LogicalSide getSide();
}
