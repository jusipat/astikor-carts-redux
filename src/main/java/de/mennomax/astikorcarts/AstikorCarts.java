package de.mennomax.astikorcarts;

import de.mennomax.astikorcarts.container.PlowMenu;
import de.mennomax.astikorcarts.entity.AnimalCartEntity;
import de.mennomax.astikorcarts.entity.PlowEntity;
import de.mennomax.astikorcarts.entity.PostilionEntity;
import de.mennomax.astikorcarts.entity.SupplyCartEntity;
import de.mennomax.astikorcarts.item.CartItem;
import de.mennomax.astikorcarts.network.clientbound.UpdateDrawnMessage;
import de.mennomax.astikorcarts.network.serverbound.ActionKeyMessage;
import de.mennomax.astikorcarts.network.serverbound.OpenSupplyCartMessage;
import de.mennomax.astikorcarts.network.serverbound.ToggleSlowMessage;
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

    private static final BiFunction<WoodType, String, Supplier<CartItem>> CART_ITEM_SUPPLIER =
            (woodType, cartType) -> () -> new CartItem(new Item.Properties().stacksTo(1), woodType, cartType);
    public static final Map<WoodType, DeferredItem<CartItem>> SUPPLY_CART = new HashMap<>();
    public static final Map<WoodType, DeferredItem<CartItem>> HAND_CART = new HashMap<>();
    public static final Map<WoodType, DeferredItem<CartItem>> PLOW = new HashMap<>();
    public static final Map<WoodType, DeferredItem<CartItem>> ANIMAL_CART = new HashMap<>();
    public static final Map<WoodType, DeferredItem<CartItem>> REAPER = new HashMap<>();
    public static final Map<WoodType, DeferredItem<CartItem>> SEED_DRILL = new HashMap<>();

    public static final WoodType[] VANILLA_WOOD_TYPES = {
            WoodType.OAK,
            WoodType.SPRUCE,
            WoodType.BIRCH,
            WoodType.ACACIA,
            WoodType.CHERRY,
            WoodType.JUNGLE,
            WoodType.DARK_OAK,
            WoodType.CRIMSON,
            WoodType.WARPED,
            WoodType.MANGROVE,
            WoodType.BAMBOO
    };

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ID);
    public static final DeferredItem<Item> WHEEL = ITEMS.register("wheel", () -> new Item(new Item.Properties()));
    static {
        for (WoodType woodType : VANILLA_WOOD_TYPES) {
            SUPPLY_CART.put(woodType,
                    ITEMS.register(woodType.name() + "_supply_cart",
                            CART_ITEM_SUPPLIER.apply(woodType, "supply_cart")));
            PLOW.put(woodType,
                    ITEMS.register(woodType.name() + "_plow",
                            CART_ITEM_SUPPLIER.apply(woodType, "_plow")));
            ANIMAL_CART.put(woodType,
                    ITEMS.register(woodType.name() + "_animal_cart",
                            CART_ITEM_SUPPLIER.apply(woodType, "_animal_cart")));

//            ITEMS.register(woodType.name() + "_supply_cart", SUPPLY_CART.get(woodType));
//            ITEMS.register(woodType.name() + "_plow", PLOW.get(woodType));
//            ITEMS.register(woodType.name() + "_animal_cart", ANIMAL_CART.get(woodType));
            //ITEMS.register(woodType.name() + "_hand_cart"), HAND_CART.get(woodType));
            //R.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(ID, woodType.name() + "_reaper"), REAPER.get(woodType));
            //R.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(ID, woodType.name() + "_seed_drill"), SEED_DRILL.get(woodType));
        }
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(WHEEL.get());
            SUPPLY_CART.values().forEach(deferredItem -> event.accept(deferredItem.get()));
            ANIMAL_CART.values().forEach(deferredItem -> event.accept(deferredItem.get()));
            PLOW.values().forEach(deferredItem -> event.accept(deferredItem.get()));
            HAND_CART.values().forEach(deferredItem -> event.accept(deferredItem.get()));
            REAPER.values().forEach(deferredItem -> event.accept(deferredItem.get()));
            SEED_DRILL.values().forEach(deferredItem -> event.accept(deferredItem.get()));
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

    public static final class EntityTypes {
        private EntityTypes() {
        }
        public static final DeferredRegister<EntityType<?>> R = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, ID);

        public static final Supplier<EntityType<SupplyCartEntity>> SUPPLY_CART;
        public static final Supplier<EntityType<PlowEntity>> PLOW;
        public static final Supplier<EntityType<AnimalCartEntity>> ANIMAL_CART;
        public static final Supplier<EntityType<PostilionEntity>> POSTILION;

        static {
            SUPPLY_CART = R.register("supply_cart", () -> EntityType.Builder.of(SupplyCartEntity::new, MobCategory.MISC)
                    .sized(1.5F, 1.4F)
                    .build(ID + ":supply_cart"));
            PLOW = R.register("plow", () -> EntityType.Builder.of(PlowEntity::new, MobCategory.MISC)
                    .sized(1.3F, 1.4F)
                    .build(ID + ":plow"));
            ANIMAL_CART = R.register("animal_cart", () -> EntityType.Builder.of(AnimalCartEntity::new, MobCategory.MISC)
                    .sized(1.3F, 1.4F)
                    .build(ID + ":animal_cart"));
            POSTILION = R.register("postilion", () -> EntityType.Builder.of(PostilionEntity::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .noSummon()
                    .noSave()
                    .build(ID + ":postilion"));
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
        bus.addListener(EventPriority.NORMAL, this::setup);
        ITEMS.register(bus);
        EntityTypes.R.register(bus);
        SoundEvents.SOUND_EVENTS.register(bus);
        //ContainerTypes.R.register(bus);
        ACStats.AC_STATS.register(bus);
        bus.<EntityAttributeCreationEvent>addListener(e -> {e.put(EntityTypes.POSTILION.get(), LivingEntity.createLivingAttributes().build());});
        bus.addListener(this::addCreative);
    }
    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(ACStats::initStats);
    }
}