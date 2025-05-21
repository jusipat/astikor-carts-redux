package de.mennomax.astikorcarts;


import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;

public interface Initializer {
    void init(final Context mod);

    interface Context {
        ModLoadingContext context();

        IEventBus bus();

        IEventBus modBus();
    }
}