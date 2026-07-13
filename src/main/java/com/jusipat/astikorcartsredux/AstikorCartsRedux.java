package com.jusipat.astikorcartsredux;

import com.google.common.collect.ImmutableMap;
import com.jusipat.astikorcartsredux.advancement.ACCriteriaTriggers;
import com.jusipat.astikorcartsredux.container.PlowMenu;
import com.jusipat.astikorcartsredux.container.SeedDrillMenu;
import com.jusipat.astikorcartsredux.entity.*;
import com.jusipat.astikorcartsredux.entity.ai.goal.AvoidCartGoal;
import com.jusipat.astikorcartsredux.entity.ai.goal.PullCartGoal;
import com.jusipat.astikorcartsredux.entity.ai.goal.RideCartGoal;
import com.jusipat.astikorcartsredux.item.CartItem;
import com.jusipat.astikorcartsredux.network.clientbound.UpdateDrawnMessage;
import com.jusipat.astikorcartsredux.network.serverbound.*;
import com.jusipat.astikorcartsredux.util.GoalAdder;
import com.jusipat.astikorcartsredux.util.NiftyWorld;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.stats.StatFormatter;
import net.minecraft.stats.Stats;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.event.server.ServerStoppedEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.apache.commons.lang3.function.TriFunction;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

// The value here should match an entry in the META-INF/neoforge.neoforge.mods.toml file
@Mod(AstikorCartsRedux.MODID)
public class AstikorCartsRedux {
	// Define mod id in a common place for everything to reference
	public static final String MODID = "astikorcartsredux";
	// Directly reference a slf4j logger
	public static final Logger LOGGER = LogUtils.getLogger();
	// Create a Deferred Register to hold Blocks which will all be registered under the "astikorcartsredux" namespace
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, AstikorCartsRedux.MODID);
	// Create a Deferred Register to hold Items which will all be registered under the "astikorcartsredux" namespace
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE ,AstikorCartsRedux.MODID);
	// Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "astikorcartsredux" namespace
	public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
	public static final DeferredRegister<ResourceLocation> AC_STATS = DeferredRegister.create(Registries.CUSTOM_STAT, AstikorCartsRedux.MODID);
	public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(Registries.MENU, AstikorCartsRedux.MODID);
	private static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MODID);
	private static final List<Runnable> STAT_SETUP = new ArrayList<>();

	public static final RegistryObject<ResourceLocation> CART_ONE_CM = makeACStat("cart_one_cm");
	public static final RegistryObject<ResourceLocation> RIDE_CART_CM = makeACStat("ride_cart_cm");
	public static final RegistryObject<ResourceLocation> STEER_ANIMAL_CART_CM = makeACStat("steer_animal_cart_cm");
	public static final RegistryObject<ResourceLocation> STEER_REAPER_CM = makeACStat("steer_reaper_cm");

	private static RegistryObject<ResourceLocation> makeACStat(String key) {
		ResourceLocation resourcelocation = new ResourceLocation(AstikorCartsRedux.MODID, "cart_one_cm");
		STAT_SETUP.add(() -> Stats.CUSTOM.get(resourcelocation, StatFormatter.DEFAULT));
		return AC_STATS.register(key, () -> resourcelocation);
	}

	public static final RegistryObject<Item> WHEEL = ITEMS.register("wheel", () -> new Item(new Item.Properties()));

	private static final TriFunction<WoodType, String, FeatureFlag[], RegistryObject<CartItem>> CART_ITEM_SUPPLIER =
			(wood, type, flags) ->
					ITEMS.register(
							wood.name() + "_" + type,
							() -> new CartItem(
									wood,
									type,
									new Item.Properties().stacksTo(1).requiredFeatures(flags)
							)
					);


	public static final Map<String, Map<WoodType, RegistryObject<CartItem>>> CARTS = new HashMap<>();

	public static final String[] CART_TYPES = {
			"supply_cart",
			"hand_cart",
			"plow",
			"seed_drill",
			"reaper",
			"animal_cart"
	};

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

	// Creates a creative tab with the id "astikorcartsredux:example_tab" for the example item, that is placed after the combat tab
	public static final RegistryObject<CreativeModeTab> ASTIKORCARTSREDUX_TAB = CREATIVE_MODE_TABS.register("astikorcartsredux_tab", () -> CreativeModeTab.builder()
			.title(Component.translatable("itemGroup.astikorcartsredux")) // The language key for the title of your CreativeModeTab
			.withTabsBefore(CreativeModeTabs.COMBAT)
			.icon(() -> WHEEL.get().getDefaultInstance())
			.displayItems((parameters, output) -> {
				output.accept(WHEEL.get());
				CARTS.values().forEach(map -> { // add all carts to creative tab
					map.values().forEach(cart -> {output.accept(cart.get());});
				});
			}).build());

	static {
		FeatureFlag[] flags = {};

		for (String type : CART_TYPES) {
			Map<WoodType, RegistryObject<CartItem>> perWoodMap = new HashMap<>();

			for (WoodType wood : VANILLA_WOOD_TYPES) {
				String registryName = wood.name() + "_" + type;

				RegistryObject<CartItem> item = ITEMS.register(
						registryName,
						() -> new CartItem(
								wood,
								type,
								new Item.Properties().stacksTo(1).requiredFeatures(flags))
				);

				perWoodMap.put(wood, item);
			}

			CARTS.put(type, perWoodMap);
		}
	}

	public static MinecraftServer server = null;

	public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
			new ResourceLocation(MODID, "main"),
			() -> "1",
			"1"::equals,
			"1"::equals
	);

	private static int packetId = 0;

	private void registerPackets() {
		// Bidirectional - UpdateDrawnPayload
		CHANNEL.registerMessage(packetId++, UpdateDrawnMessage.class,
				UpdateDrawnMessage::encode,
				UpdateDrawnMessage::decode,
				(payload, contextSupplier) -> {
					NetworkEvent.Context context = contextSupplier.get();
					context.enqueueWork(() ->
							UpdateDrawnMessage.handle(payload, context.getSender() != null
									? context.getSender().level()        // server-side: sender is the player
									: Minecraft.getInstance().level));    // client-side: use local level
					context.setPacketHandled(true);
				});

		// Server-bound - ActionKeyPayload
		CHANNEL.registerMessage(packetId++, ActionKeyMessage.class,
				ActionKeyMessage::encode,
				ActionKeyMessage::decode,
				(payload, contextSupplier) -> {
					NetworkEvent.Context context = contextSupplier.get();
					context.enqueueWork(() -> ActionKeyMessage.handle(context));
					context.setPacketHandled(true);
				});

		// Server-bound - OpenSupplyCartPayload
		CHANNEL.registerMessage(packetId++, OpenSupplyCartMessage.class,
				OpenSupplyCartMessage::encode,
				OpenSupplyCartMessage::decode,
				(payload, contextSupplier) -> {
					NetworkEvent.Context context = contextSupplier.get();
					context.enqueueWork(() -> OpenSupplyCartMessage.handle(context));
					context.setPacketHandled(true);
				});

		// Server-bound - ToggleSlowPayload
		CHANNEL.registerMessage(packetId++, ToggleSlowMessage.class,
				ToggleSlowMessage::encode,
				ToggleSlowMessage::decode,
				(payload, contextSupplier) -> {
					NetworkEvent.Context context = contextSupplier.get();
					context.enqueueWork(() ->
							ToggleSlowMessage.handle(context.getSender()));
					context.setPacketHandled(true);
				});

		// Server-bound - RequestCartUpdatePayload
		CHANNEL.registerMessage(packetId++, RequestCartUpdatePayload.class,
				RequestCartUpdatePayload::encode,
				RequestCartUpdatePayload::decode,
				(payload, contextSupplier) -> {
					NetworkEvent.Context context = contextSupplier.get();
					context.enqueueWork(() -> RequestCartUpdatePayload.handle(payload, context));
					context.setPacketHandled(true);
				});
	}

	private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
		ResourceLocation id = new ResourceLocation(MODID, name);
		return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
	}

	public static final RegistryObject<SoundEvent> ATTACH_SOUND = registerSoundEvent("entity.cart.attach");
	public static final RegistryObject<SoundEvent> DETACH_SOUND = registerSoundEvent("entity.cart.detach");
	public static final RegistryObject<SoundEvent> PLACE_SOUND = registerSoundEvent("entity.cart.place");

	public static final Supplier<EntityType<SupplyCartEntity>> SUPPLY_CART_ENTITY =
			ENTITY_TYPES.register( "supply_cart", () -> EntityType.Builder.of(SupplyCartEntity::new, MobCategory.MISC)
					.sized(1.5f, 1.4f)
					.build("supply_cart"));

	public static final Supplier<EntityType<AnimalCartEntity>> ANIMAL_CART_ENTITY = ENTITY_TYPES.register(
			"animal_cart", () -> EntityType.Builder.of(AnimalCartEntity::new, MobCategory.MISC)
					.sized(1.3f, 1.4f)
					.build("animal_cart"));

	public static final Supplier<EntityType<PlowEntity>> PLOW_ENTITY = ENTITY_TYPES.register(
			"plow", () -> EntityType.Builder.of(PlowEntity::new, MobCategory.MISC)
					.sized(1.3f, 1.4f)
					.build("plow"));

	public static final Supplier<EntityType<HandCartEntity>> HAND_CART_ENTITY = ENTITY_TYPES.register(
			"hand_cart", () -> EntityType.Builder.of(HandCartEntity::new, MobCategory.MISC)
					.sized(1.3f, 1.1f)
					.build("hand_cart"));

	public static final Supplier<EntityType<SeedDrillEntity>> SEED_DRILL_ENTITY = ENTITY_TYPES.register(
			"seed_drill", () -> EntityType.Builder.of(SeedDrillEntity::new, MobCategory.MISC)
					.sized(1.3f, 1.4f)
					.build("seed_drill"));

	public static final Supplier<EntityType<ReaperEntity>> REAPER_ENTITY = ENTITY_TYPES.register(
			"reaper", () -> EntityType.Builder.of(ReaperEntity::new, MobCategory.MISC)
					.sized(1.3f, 1.4f)
					.build("reaper"));

	public static final Supplier<EntityType<PostilionEntity>> POSTILION_ENTITY = ENTITY_TYPES.register(
			"postilion", () -> EntityType.Builder.of(PostilionEntity::new, MobCategory.MISC)
					.sized(0.25f, 0.25f)
					.noSummon()
					.noSave()
					.build("postilion"));


	public static final GoalAdder<Mob> MOB_GOAL_ADDER = GoalAdder.mobGoal(Mob.class)
			.add(1, PullCartGoal::new)
			.add(1, RideCartGoal::new)
			.build();

	public static final GoalAdder<PathfinderMob> PATHFINDER_GOAL_ADDER = GoalAdder.mobGoal(PathfinderMob.class)
			.add(3, mob -> new AvoidCartGoal<>(mob, SupplyCartEntity.class, 3.0f, 0.5f))
			.add(3, mob -> new AvoidCartGoal<>(mob, PlowEntity.class, 3.0f, 0.5f))
			.add(3, mob -> new AvoidCartGoal<>(mob, ReaperEntity.class, 3.0f, 0.5f))
			.add(3, mob -> new AvoidCartGoal<>(mob, SeedDrillEntity.class, 3.0f, 0.5f))
			.build();

	public static final Supplier<MenuType<PlowMenu>> PLOW_MENU_TYPE = MENUS.register("plow", () -> new MenuType<>(PlowMenu::new, FeatureFlags.DEFAULT_FLAGS));
	public static final Supplier<MenuType<SeedDrillMenu>> SEED_DRILL_MENU_TYPE = MENUS.register("seed_drill", () -> new MenuType<>(SeedDrillMenu::new, FeatureFlags.DEFAULT_FLAGS));

	public static final TagKey<Block> PLOW_BREAKABLE_HOE = TagKey.create(Registries.BLOCK, AstikorCartsRedux.resLoc("plow_breakable/hoe"));
	public static final TagKey<Block> PLOW_BREAKABLE_SHOVEL = TagKey.create(Registries.BLOCK, AstikorCartsRedux.resLoc("plow_breakable/shovel"));
	public static final TagKey<Block> PLOW_BREAKABLE_AXE = TagKey.create(Registries.BLOCK, AstikorCartsRedux.resLoc("plow_breakable/axe"));
	public static final TagKey<Item> SEED_DRILL_PLANTABLE = TagKey.create(Registries.ITEM, AstikorCartsRedux.resLoc("seed_drill_plantable"));


	// The constructor for the mod class is the first code that is run when your mod is loaded.
	// FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
	public AstikorCartsRedux(IEventBus modEventBus, ModContainer modContainer) {
		// Register the commonSetup method for modloading
		modEventBus.addListener(this::commonSetup);
		modEventBus.addListener(this::registerPayloads);

		modEventBus.addListener(AstikorCartsRedux::onEntityJoinWorld);
		modEventBus.addListener(AstikorCartsRedux::onServerTick);
		modEventBus.addListener(AstikorCartsRedux::onEntityInteract);
		modEventBus.addListener(AstikorCartsRedux::onServerStarted);
		modEventBus.addListener(AstikorCartsRedux::onServerStopped);

		modEventBus.<EntityAttributeCreationEvent>addListener(e -> {e.put(POSTILION_ENTITY.get(), LivingEntity.createLivingAttributes().build());});

		// Register the Deferred Register to the mod event bus so blocks get registered
		BLOCKS.register(modEventBus);
		// Register the Deferred Register to the mod event bus so items get registered
		ITEMS.register(modEventBus);
		// Register the Deferred Register to the mod event bus so tabs get registered
		CREATIVE_MODE_TABS.register(modEventBus);

		MENUS.register(modEventBus);

		ENTITY_TYPES.register(modEventBus);

		AC_STATS.register(modEventBus);

		SOUND_EVENTS.register(modEventBus);

		ACCriteriaTriggers.TRIGGERS.register(modEventBus);

		// Register the item to a creative tab
		modEventBus.addListener(this::addCreative);

		// Register our mod's ModConfigSpec so that FML can create and load the config file for us
		modContainer.registerConfig(ModConfig.Type.COMMON, AstikorCartsReduxConfig.spec());
	}

	public static <T extends Entity> Supplier<EntityType<T>> register(String id, Supplier<EntityType<T>> supplier) {
		return ENTITY_TYPES.register(id, supplier);
	}

	public static <I extends Item> DeferredItem<I> register(String id, Function<Item.Properties, I> function) {
		return ITEMS.register(id, () -> function.apply(new Item.Properties()));
	}

	public static ResourceLocation resLoc(String name) {
		return ResourceLocation.fromNamespaceAndPath(MODID, name);
	}

	private void commonSetup(final FMLCommonSetupEvent event) {
		// Some common setup code
		LOGGER.info("HELLO FROM COMMON SETUP");
		event.enqueueWork(() -> {
			STAT_SETUP.forEach(Runnable::run);
		});
	}

	private static void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
		Entity entity = event.getTarget();
		Entity rider = entity.getControllingPassenger();

		if (rider instanceof PostilionEntity) {
			rider.stopRiding();
		}

		event.setCancellationResult(InteractionResult.PASS);
	}

	private static void onEntityJoinWorld(EntityJoinLevelEvent event) {
		Entity entity = event.getEntity();

		MOB_GOAL_ADDER.onEntityJoinWorld(entity);
		PATHFINDER_GOAL_ADDER.onEntityJoinWorld(entity);
	}

	private static void onServerTick(TickEvent.ServerTickEvent event) {
		MinecraftServer server = event.getServer();

		for (ResourceKey<Level> levelKey : server.levelKeys()) {
			NiftyWorld.getServer(server, levelKey).tick();
		}
	}

	private static void onServerStarted(ServerStartedEvent event) {
		server = event.getServer();
	}

	private static void onServerStopped(ServerStoppedEvent event) {
		server = null;
	}

	// add to creative menus
	private void addCreative(BuildCreativeModeTabContentsEvent event) {
		if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
			event.accept(WHEEL);
		}
		if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
			CARTS.values().forEach(byWood -> {
				Arrays.stream(VANILLA_WOOD_TYPES).forEach(woodType -> {
					event.accept(byWood.get(woodType));
				});
			});
		}
	}

	private void registerPayloads(RegisterPayloadHandlersEvent event) {
		// Sets the current network version
		final PayloadRegistrar registrar = event.registrar("1");

		registrar.playBidirectional(UpdateDrawnPayload.TYPE, UpdateDrawnPayload.CODEC,
				(payload, context) -> UpdateDrawnPayload.handle(payload, context.player().level())); // sketchy

		registrar.playToServer(ActionKeyPayload.TYPE, ActionKeyPayload.CODEC,
				(payload, context) -> ActionKeyPayload.handle(context));
		registrar.playToServer(OpenSupplyCartPayload.TYPE, OpenSupplyCartPayload.CODEC,
				(payload, context) -> OpenSupplyCartPayload.handle(context));
		registrar.playToServer(ToggleSlowPayload.TYPE, ToggleSlowPayload.CODEC,
				(payload, context) -> ToggleSlowPayload.handle(context.player()));

		registrar.playToServer(RequestCartUpdatePayload.TYPE, RequestCartUpdatePayload.CODEC,
				RequestCartUpdatePayload::handle);
		//registrar.playToServer(CoachmanMovePayload.TYPE, CoachmanMovePayload.CODEC,
		//		CoachmanMovePayload::handle); todo: look into coachman
	}
}
