package com.jusipat.astikorcartsredux;

import com.jusipat.astikorcartsredux.config.AstikorCartsConfig;
import com.jusipat.astikorcartsredux.entity.PostilionEntity;
import com.jusipat.astikorcartsredux.entity.ai.goal.PullCartGoal;
import com.jusipat.astikorcartsredux.entity.ai.goal.RideCartGoal;
import com.jusipat.astikorcartsredux.util.GoalAdder;
import com.jusipat.astikorcartsredux.world.AstikorWorld;
import com.jusipat.astikorcartsredux.world.SimpleAstikorWorld;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;

public class CommonInitializer implements Initializer {
    @Override
    public void init(final Context mod) {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, AstikorCartsConfig.spec());
        mod.modBus().<FMLLoadCompleteEvent>addListener(e -> {
            AstikorCartsRedux.LOGGER.info("Automatic pull animal configuration:\n{}", AstikorCartsConfig.Common.getComment());
        });
        mod.modBus().<EntityAttributeCreationEvent>addListener(e -> {
            e.put(AstikorCartsRedux.EntityTypes.POSTILION.get(), LivingEntity.createLivingAttributes().build());
        });
        mod.bus().<AttachCapabilitiesEvent<Level>, Level>addGenericListener(Level.class, e ->
            e.addCapability(new ResourceLocation(AstikorCartsRedux.ID, "astikor"), AstikorWorld.createProvider(SimpleAstikorWorld::new))
        );
        GoalAdder.mobGoal(Mob.class)
            .add(1, PullCartGoal::new)
            .add(1, RideCartGoal::new)
            .build()
            .register(mod.bus());
        mod.bus().<PlayerInteractEvent.EntityInteract>addListener(e -> {
            final Entity rider = e.getTarget().getControllingPassenger();
            if (rider instanceof PostilionEntity) {
                rider.stopRiding();
            }
        });
        mod.bus().<TickEvent.LevelTickEvent>addListener(e -> {
            if (e.phase == TickEvent.Phase.END) {
                AstikorWorld.get(e.level).ifPresent(AstikorWorld::tick);
            }
        });
    }
}
