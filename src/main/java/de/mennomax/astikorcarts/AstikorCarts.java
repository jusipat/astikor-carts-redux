package de.mennomax.astikorcarts;

import de.mennomax.astikorcarts.container.PlowMenu;
import de.mennomax.astikorcarts.entity.*;
import de.mennomax.astikorcarts.item.AstikorItems;
import de.mennomax.astikorcarts.item.CartItem;
import de.mennomax.astikorcarts.network.clientbound.UpdateDrawnMessage;
import de.mennomax.astikorcarts.network.serverbound.ActionKeyMessage;
import de.mennomax.astikorcarts.network.serverbound.OpenSupplyCartMessage;
import de.mennomax.astikorcarts.network.serverbound.ToggleSlowMessage;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.stats.StatFormatter;
import net.minecraft.stats.Stats;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Supplier;

@Mod(AstikorCarts.ID)
public final class AstikorCarts {
    public static final String ID = "astikorcarts";

    @SubscribeEvent
    public static void register(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar(ID);
        registrar.playToClient(UpdateDrawnMessage.TYPE, UpdateDrawnMessage.STREAM_CODEC, UpdateDrawnMessage::handle);

        registrar.playToServer(ActionKeyMessage.TYPE, ActionKeyMessage.STREAM_CODEC, ActionKeyMessage::handle);
        registrar.playToServer(OpenSupplyCartMessage.TYPE, OpenSupplyCartMessage.STREAM_CODEC, OpenSupplyCartMessage::handle);
        registrar.playToServer(ToggleSlowMessage.TYPE, ToggleSlowMessage.STREAM_CODEC, ToggleSlowMessage::handle);
        // registrar.playToServer(RequestCartUpdateMessage.TYPE, RequestCartUpdateMessage.STREAM_CODEC, RequestCartUpdateMessage::handle); TODO: add this packet
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(AstikorItems.WHEEL);
            SUPPLY_CART.values().forEach(event::accept);
            ANIMAL_CART.values().forEach(event::accept);
            PLOW.values().forEach(event::accept);
            HAND_CART.values().forEach(event::accept);
            REAPER.values().forEach(event::accept);
            SEED_DRILL.values().forEach(event::accept);
        }
    }

    public static final MenuType<PlowMenu> PLOW_MENU_TYPE = new MenuType<>(PlowMenu::new, FeatureFlags.DEFAULT_FLAGS);

    public static final TagKey<Block> PLOW_BREAKABLE_HOE = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(AstikorCarts.ID,"plow_breakable/hoe"));
    public static final TagKey<Block> PLOW_BREAKABLE_SHOVEL = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(AstikorCarts.ID,"plow_breakable/shovel"));
    public static final TagKey<Block> PLOW_BREAKABLE_AXE = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(AstikorCarts.ID,"plow_breakable/axe"));

    public static class ACStats {

        public static final DeferredRegister<ResourceLocation> AC_STATS = DeferredRegister.create(Registries.CUSTOM_STAT, ID);
        public static final Supplier<ResourceLocation> CART_ONE_CM = AC_STATS.register("cart_one_cm", () -> makeStat("cart_one_cm"));
        private static ResourceLocation makeStat(String key) {
            return ResourceLocation.fromNamespaceAndPath(ID, key);
        }
        public static void initStats() {
            Stats.CUSTOM.get(CART_ONE_CM.get(), StatFormatter.DISTANCE);
        }
    }

    public static final class SoundEvents {

        private static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, ID);

        public static final Supplier<SoundEvent> CART_ATTACHED = registerSoundEvent("entity.cart.attach");
        public static final Supplier<SoundEvent> CART_DETACHED = registerSoundEvent("entity.cart.detach");
        public static final Supplier<SoundEvent> CART_PLACED = registerSoundEvent("entity.cart.place");

        private static Supplier<SoundEvent> registerSoundEvent(String name) {
            ResourceLocation id = ResourceLocation.fromNamespaceAndPath(ID, name);
            return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
        }
    }

    public AstikorCarts(IEventBus bus) {
        bus.addListener(this::setup);

        AstikorEntities.register(bus);
        AstikorItems.register(bus);
        init(); // todo: busted af, fix
        SoundEvents.SOUND_EVENTS.register(bus);
        ACStats.AC_STATS.register(bus);
        bus.<EntityAttributeCreationEvent>addListener(e -> {e.put(AstikorEntities.POSTILION_ENTITY, LivingEntity.createLivingAttributes().build());});
        bus.addListener(this::addCreative);
    }
    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(ACStats::initStats);
    }
}