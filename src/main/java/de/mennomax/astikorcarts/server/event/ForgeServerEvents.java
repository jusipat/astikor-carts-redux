package de.mennomax.astikorcarts.server.event;

import de.mennomax.astikorcarts.AstikorCarts;
import de.mennomax.astikorcarts.config.AstikorCartsConfig;
import de.mennomax.astikorcarts.entity.AbstractDrawnEntity;

//@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, modid = AstikorCarts.ID)
//public class ForgeServerEvents {
//    @SubscribeEvent
//    public static void entityStruckByLightningEvent(EntityStruckByLightningEvent event) {
//        if (event.getEntity() instanceof AbstractDrawnEntity && AstikorCartsConfig.get().lightningInvulnerable.get()) {
//            event.setCanceled(true);
//        }
//    }
//}