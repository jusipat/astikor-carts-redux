package com.jusipat.astikorcartsredux;

import com.google.common.collect.ImmutableMap;
import com.jusipat.astikorcartsredux.client.datagen.ModItemModelProvider;
import com.jusipat.astikorcartsredux.client.datagen.ModRecipeProvider;
import com.jusipat.astikorcartsredux.client.datagen.lang.AstikorCartsReduxDeDeLanguageProvider;
import com.jusipat.astikorcartsredux.client.datagen.lang.AstikorCartsReduxEnUsLanguageProvider;
import com.jusipat.astikorcartsredux.client.renderer.AstikorCartsReduxModelLayers;
import com.jusipat.astikorcartsredux.client.renderer.entity.*;
import com.jusipat.astikorcartsredux.client.renderer.entity.model.*;
import com.jusipat.astikorcartsredux.client.renderer.texture.AssembledTexture;
import com.jusipat.astikorcartsredux.client.renderer.texture.AssembledTextureFactory;
import com.jusipat.astikorcartsredux.client.renderer.texture.Material;
import com.jusipat.astikorcartsredux.network.clientbound.UpdateDrawnPayload;
import com.jusipat.astikorcartsredux.network.serverbound.ActionKeyPayload;
import com.jusipat.astikorcartsredux.network.serverbound.ToggleSlowPayload;
import com.jusipat.astikorcartsredux.util.NiftyWorld;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.event.TextureAtlasStitchedEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;
import net.neoforged.neoforge.client.network.event.RegisterClientPayloadHandlersEvent;
import net.neoforged.neoforge.common.util.Lazy;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Unique;

import static net.minecraft.world.level.block.state.properties.WoodType.*;

// This class will not load on dedicated servers. Accessing client side code from here is safe.
@Mod(value = AstikorCartsRedux.MODID, dist = Dist.CLIENT)
// You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
@EventBusSubscriber(modid = AstikorCartsRedux.MODID, value = Dist.CLIENT)
public class AstikorCartsReduxClient {

    public AstikorCartsReduxClient(ModContainer container) {
        // Allows NeoForge to create a config screen for this mod's configs.
        // The config screen is accessed by going to the Mods screen > clicking on your mod > clicking on config.
        // Do not forget to add translations for your config options to the en_us.json file.
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    public static final Lazy<KeyMapping> ACTION_KEY_MAPPING = Lazy.of(() -> new KeyMapping(
            "key.niftycarts.action",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_R,
            "key.categories.niftycarts"));

    public static final Lazy<KeyMapping> TOGGLE_SLOW_MAPPING = Lazy.of(() -> new KeyMapping(
            "key.niftycarts.slow",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_Z,
            "key.categories.niftycarts"
    ));

    @SubscribeEvent // on the mod event bus only on the physical client
    public static void registerBindings(RegisterKeyMappingsEvent event) {
        event.register(ACTION_KEY_MAPPING.get());
        event.register(TOGGLE_SLOW_MAPPING.get());
    }

    @SubscribeEvent // on the mod event bus, client-only class
    public static void register(RegisterClientPayloadHandlersEvent event) {
        event.register(UpdateDrawnPayload.TYPE, (payload, context) -> {
            context.enqueueWork(() -> {
                Minecraft mc = Minecraft.getInstance();
                if (mc.level != null) {
                    UpdateDrawnPayload.handle(payload, mc.level);
                }
            });
        });
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        // Some client setup code
        AstikorCartsRedux.LOGGER.info("HELLO FROM CLIENT SETUP");
        AstikorCartsRedux.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
    }

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();

        // handle action key
        while (ACTION_KEY_MAPPING.get().consumeClick()) {
            ClientPacketDistributor.sendToServer(new ActionKeyPayload());
        }

        // handle toggle slow
        var player = mc.player;
        if (player != null && ToggleSlowPayload.getCart(player).isPresent()) {
            while (TOGGLE_SLOW_MAPPING.get().consumeClick()) {
                ClientPacketDistributor.sendToServer(new ToggleSlowPayload());
                KeyMapping.set(TOGGLE_SLOW_MAPPING.get().getDefaultKey(), false);
            }
        }

        // tick nifty world if not paused
        if (!mc.isPaused() && mc.level != null) {
            NiftyWorld.getClient().tick(mc.level);
        }
    }

    @SubscribeEvent // on the mod event bus only on the physical client
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(AstikorCartsRedux.SUPPLY_CART_ENTITY.get(), SupplyCartRenderer::new);
        event.registerEntityRenderer(AstikorCartsRedux.PLOW_ENTITY.get(), PlowRenderer::new);
        event.registerEntityRenderer(AstikorCartsRedux.HAND_CART_ENTITY.get(), HandCartRenderer::new);
        event.registerEntityRenderer(AstikorCartsRedux.SEED_DRILL_ENTITY.get(), SeedDrillRenderer::new);
        event.registerEntityRenderer(AstikorCartsRedux.REAPER_ENTITY.get(), ReaperRenderer::new);
        event.registerEntityRenderer(AstikorCartsRedux.POSTILION_ENTITY.get(), PostilionRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(AstikorCartsReduxModelLayers.PLOW, PlowModel::createLayer);
        event.registerLayerDefinition(AstikorCartsReduxModelLayers.ANIMAL_CART, AnimalCartModel::createLayer);
        event.registerLayerDefinition(AstikorCartsReduxModelLayers.SUPPLY_CART, SupplyCartModel::createLayer);
        event.registerLayerDefinition(AstikorCartsReduxModelLayers.HAND_CART, HandCartModel::createLayer);
        event.registerLayerDefinition(AstikorCartsReduxModelLayers.SEED_DRILL, SeedDrillModel::createLayer);
        event.registerLayerDefinition(AstikorCartsReduxModelLayers.REAPER, ReaperModel::createLayer);
    }

    @SubscribeEvent // on the mod event bus
    public static void gatherData(GatherDataEvent.Client event) {
        event.createProvider(ModRecipeProvider.Runner::new);
        event.createProvider(ModItemModelProvider::new);
        event.createProvider(AstikorCartsReduxEnUsLanguageProvider::new);
        event.createProvider(AstikorCartsReduxDeDeLanguageProvider::new);
    }

    @Unique
    private static final ImmutableMap<WoodType, String> LOG_NAME_OVERRIDE = ImmutableMap.of(
            WoodType.CRIMSON, "stem",
            WoodType.WARPED, "stem",
            WoodType.BAMBOO, "block"
    );

    @SubscribeEvent
    public static void onTextureStitchPost(TextureAtlasStitchedEvent event) {
        if (!event.getAtlas().location().equals(TextureAtlas.LOCATION_BLOCKS)) return;

        // Now the atlas is ready
        AssembledTextureFactory factory = new AssembledTextureFactory();
        Material composterSide = new Material(ResourceLocation.withDefaultNamespace("block/composter_side"), 16)
                .fill(16, 47, 44, 5, Material.R0, -2, 1)
                .fill(16, 54, 38, 5, Material.R0, -2, -6);
        Material composterTop = new Material(ResourceLocation.withDefaultNamespace("block/composter_top"), 16)
                .fill(18, 45, 10, 2, Material.R0, -2, 3)
                .fill(28, 45, 10, 2, Material.R0, 10, 3)
                .fill(18, 52, 8, 2, Material.R0, 0, -4)
                .fill(26, 52, 9, 2, Material.R0, 11, -4);
        Material stone = new Material(ResourceLocation.withDefaultNamespace("block/stone"), 16)
                .fill(62, 55, 2, 9);
        Material dirt = new Material(ResourceLocation.withDefaultNamespace("block/dirt"), 16)
                .fill(0, 45, 16, 17);
        for (WoodType type : AstikorCartsRedux.VANILLA_WOOD_TYPES) {
            String logName = LOG_NAME_OVERRIDE.getOrDefault(type, "log");
            factory.add(new AssembledTexture(AstikorCartsRedux.resLoc("textures/entity/" + type.name() + "_animal_cart.png"),64, 64)
                            .add(new Material(ResourceLocation.withDefaultNamespace("block/" + type.name() + "_planks"), 16)
                                    .fill(0, 0, 60, 38, Material.R0, 0, 2)
                                    .fill(0, 28, 20, 33, Material.R90, 4, -2)
                                    .fill(12, 30, 8, 31, Material.R270, 0, 4)
                            )
                            .add(new Material(ResourceLocation.withDefaultNamespace("block/stripped_" + type.name() + "_" + logName), 16)
                                    .fill(54, 54, 10, 10, Material.R0, 0, 2)
                            )
                            .add(new Material(ResourceLocation.withDefaultNamespace("block/" + type.name() + "_" + logName), 16)
                                    .fill(0, 21, 60, 4, Material.R90)
                                    .fill(46, 60, 8, 4, Material.R90)
                            )
                            .add(stone)
                    )
                    .add(new AssembledTexture(AstikorCartsRedux.resLoc("textures/entity/" + type.name() + "_plow.png"), 64, 64)
                            .add(new Material(ResourceLocation.withDefaultNamespace("block/" + type.name() + "_planks"), 16)
                                    .fill(0, 0, 64, 32, Material.R90)
                                    .fill(0, 8, 42, 3, Material.R0, 0, 1)
                                    .fill(0, 27, 34, 3, Material.R0, 0, 2)
                            )
                            .add(new Material(ResourceLocation.withDefaultNamespace("block/stripped_" + type.name() + "_" + logName), 16)
                                    .fill(54, 54, 10, 10, Material.R0, 2, 0)
                            )
                            .add(new Material(ResourceLocation.withDefaultNamespace("block/" + type.name() + "_" + logName), 16)
                                    .fill(0, 0, 54, 4, Material.R90)
                                    .fill(46, 60, 8, 4, Material.R90)
                            )
                            .add(stone)
                    )
                    .add(new AssembledTexture(AstikorCartsRedux.resLoc("textures/entity/" + type.name() + "_seed_drill.png"), 64, 64)
                            .add(new Material(ResourceLocation.withDefaultNamespace("block/" + type.name() + "_planks"), 16)
                                    .fill(0, 0, 64, 32, Material.R90)
                                    .fill(0, 8, 64, 3, Material.R0, 0, 1)
                                    .fill(0, 27, 34, 3, Material.R0, 0, 2)
                            )
                            .add(new Material(ResourceLocation.withDefaultNamespace("block/stripped_" + type.name() + "_" + logName), 16)
                                    .fill(54, 54, 10, 10, Material.R0, 2, 0)
                            )
                            .add(new Material(ResourceLocation.withDefaultNamespace("block/" + type.name() + "_" + logName), 16)
                                    .fill(0, 0, 64, 4, Material.R90)
                                    .fill(46, 60, 8, 4, Material.R90)
                            )
                            .add(new Material(ResourceLocation.withDefaultNamespace("block/stone"), 16)
                                    .fill(62, 55, 2, 9)
                                    .fill(0, 57, 8, 7, Material.R0)
                            )
                    )
                    .add(new AssembledTexture(AstikorCartsRedux.resLoc("textures/entity/" + type.name() + "_reaper.png"), 64, 64)
                            .add(new Material(ResourceLocation.withDefaultNamespace("block/" + type.name() + "_planks"), 16)
                                    .fill(0, 0, 64, 32, Material.R90)
                                    .fill(0, 8, 46, 4, Material.R0, 0, 1)
                                    .fill(0, 27, 34, 3, Material.R0, 0, 2)
                            )
                            .add(new Material(ResourceLocation.withDefaultNamespace("block/stripped_" + type.name() + "_" + logName), 16)
                                    .fill(54, 54, 10, 10, Material.R0, 2, 0)
                            )
                            .add(new Material(ResourceLocation.withDefaultNamespace("block/" + type.name() + "_" + logName), 16)
                                    .fill(0, 0, 64, 4, Material.R90)
                                    .fill(32, 12, 8, 17, Material.R0)
                                    .fill(46, 60, 8, 4, Material.R90)
                            )
                            .add(new Material(ResourceLocation.withDefaultNamespace("block/stone"), 16)
                                    .fill(62, 55, 2, 9)
                                    .fill(0, 32, 64, 16)
                            )
                    )
                    .add(new AssembledTexture(AstikorCartsRedux.resLoc("textures/entity/" + type.name() + "_supply_cart.png"), 64, 64)
                            .add(new Material(ResourceLocation.withDefaultNamespace("block/" + type.name() + "_planks"), 16)
                                    .fill(0, 0, 60, 45, Material.R0, 0, 2)
                                    .fill(0, 27, 60, 8, Material.R0, 0, 1)
                            )
                            .add(new Material(ResourceLocation.withDefaultNamespace("block/stripped_" + type.name() + "_" + logName), 16)
                                    .fill(54, 54, 10, 10, Material.R0, 0, 2)
                            )
                            .add(new Material(ResourceLocation.withDefaultNamespace("block/" + type.name() + "_" + logName), 16)
                                    .fill(0, 23, 54, 4, Material.R90)
                                    .fill(46, 60, 8, 4, Material.R90)
                            )
                            .add(stone)
                            .add(composterSide)
                            .add(composterTop)
                            .add(dirt)
                    )
                    .add(new AssembledTexture(AstikorCartsRedux.resLoc("textures/entity/" + type.name() + "_hand_cart.png"), 64, 64)
                            .add(new Material(ResourceLocation.withDefaultNamespace("block/" + type.name() + "_planks"), 16)
                                    .fill(0,0, 52, 43, Material.R0, 0, 2)
                                    .fill(0, 24, 42, 4, Material.R0, 0, 1)
                            )
                            .add(new Material(ResourceLocation.withDefaultNamespace("block/stripped_" + type.name() + "_" + logName), 16)
                                    .fill(54, 54, 10, 10, Material.R0, 0, 2)
                            )
                            .add(new Material(ResourceLocation.withDefaultNamespace("block/" + type.name() + "_" + logName), 16)
                                    .fill(0, 20, 46, 4, Material.R90)
                                    .fill(46, 60, 8, 4, Material.R90)
                            )
                            .add(stone)
                            .add(composterSide)
                            .add(composterTop)
                            .add(dirt)
                    );
        }
        factory.bake();
    }
}
