package com.jusipat.astikorcartsredux;

import com.jusipat.astikorcartsredux.client.datagen.AstikorCartsReduxModelProvider;
import com.jusipat.astikorcartsredux.client.datagen.AstikorCartsReduxRecipeProvider;
import com.jusipat.astikorcartsredux.client.datagen.lang.AstikorCartsReduxDeDeLanguageProvider;
import com.jusipat.astikorcartsredux.client.datagen.lang.AstikorCartsReduxEnUsLanguageProvider;
import com.jusipat.astikorcartsredux.client.renderer.AstikorCartsReduxModelLayers;
import com.jusipat.astikorcartsredux.client.renderer.entity.*;
import com.jusipat.astikorcartsredux.client.renderer.entity.model.*;
import com.jusipat.astikorcartsredux.client.screen.PlowScreen;
import com.jusipat.astikorcartsredux.client.screen.SeedDrillScreen;
import com.jusipat.astikorcartsredux.network.clientbound.UpdateDrawnPayload;
import com.jusipat.astikorcartsredux.network.serverbound.ActionKeyPayload;
import com.jusipat.astikorcartsredux.network.serverbound.ToggleSlowPayload;
import com.jusipat.astikorcartsredux.util.NiftyWorld;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;
import net.neoforged.neoforge.client.network.event.RegisterClientPayloadHandlersEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.util.Lazy;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import org.lwjgl.glfw.GLFW;

import java.util.Objects;

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

        //NeoForge.EVENT_BUS.addListener(AstikorCartsReduxClient::register);
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
        // Call event.createDatapackRegistryObjects(...) first if adding datapack objects
        event.createProvider(AstikorCartsReduxRecipeProvider.Runner::new);
        event.createProvider(AstikorCartsReduxModelProvider::new);
        event.createProvider(AstikorCartsReduxEnUsLanguageProvider::new);
        event.createProvider(AstikorCartsReduxDeDeLanguageProvider::new);
    }

}
