package com.jusipat.astikorcartsredux.server.event;

import com.jusipat.astikorcartsredux.AstikorCartsRedux;
import com.jusipat.astikorcartsredux.config.AstikorCartsConfig;
import com.jusipat.astikorcartsredux.entity.AbstractDrawnEntity;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, modid = AstikorCartsRedux.ID)
public class ForgeServerEvents {
    @SubscribeEvent
    public static void entityStruckByLightningEvent(EntityStruckByLightningEvent event) {
            if (event.getEntity() instanceof AbstractDrawnEntity && AstikorCartsConfig.get().lightningInvulnerable.get()) {
                event.setCanceled(true);
        }
    }
}
