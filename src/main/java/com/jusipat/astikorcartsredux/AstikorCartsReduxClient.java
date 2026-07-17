package com.jusipat.astikorcartsredux;

import com.google.common.collect.ImmutableMap;
import com.jusipat.astikorcartsredux.client.renderer.AstikorCartsReduxModelLayers;
import com.jusipat.astikorcartsredux.client.renderer.entity.*;
import com.jusipat.astikorcartsredux.client.renderer.entity.model.*;
import com.jusipat.astikorcartsredux.client.renderer.texture.AssembledTexture;
import com.jusipat.astikorcartsredux.client.renderer.texture.AssembledTextureFactory;
import com.jusipat.astikorcartsredux.client.renderer.texture.Material;
import com.jusipat.astikorcartsredux.client.screen.PlowScreen;
import com.jusipat.astikorcartsredux.client.screen.SeedDrillScreen;
import com.jusipat.astikorcartsredux.datagen.ModItemModelProvider;
import com.jusipat.astikorcartsredux.datagen.lang.DeDeLanguageProvider;
import com.jusipat.astikorcartsredux.datagen.lang.EnUsLanguageProvider;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.spongepowered.asm.mixin.Unique;

import java.util.concurrent.CompletableFuture;

import static com.jusipat.astikorcartsredux.AstikorCartsReduxClientRuntime.ACTION_KEY_MAPPING;
import static com.jusipat.astikorcartsredux.AstikorCartsReduxClientRuntime.TOGGLE_SLOW_MAPPING;

// This class will not load on dedicated servers. Accessing client side code from here is safe.
@Mod(value = AstikorCartsRedux.MODID)
// You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
@Mod.EventBusSubscriber(modid = AstikorCartsRedux.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AstikorCartsReduxClient {

    public AstikorCartsReduxClient(ModContainer container) {
        // Allows NeoForge to create a config screen for this mod's configs.
        // The config screen is accessed by going to the Mods screen > clicking on your mod > clicking on config.
        // Do not forget to add translations for your config options to the en_us_existing.json file.
        //container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
        //container.registerConfig(ModConfig.Type.CLIENT, AstikorCartsReduxConfig.clientSpec());
    }

    // Event is listened to on the mod event bus
    @SubscribeEvent
    public static void registerScreens(FMLClientSetupEvent event) {
        event.enqueueWork(
                // Assume RegistryObject<MenuType<MyMenu>> MY_MENU
                // Assume MyContainerScreen<MyMenu> which takes in three parameters
                () -> MenuScreens.register(AstikorCartsRedux.PLOW_MENU_TYPE.get(), PlowScreen::new)
        );
        event.enqueueWork(
                // Assume RegistryObject<MenuType<MyMenu>> MY_MENU
                // Assume MyContainerScreen<MyMenu> which takes in three parameters
                () -> MenuScreens.register(AstikorCartsRedux.SEED_DRILL_MENU_TYPE.get(), SeedDrillScreen::new)
        );

    }

    // on the mod event bus only on the physical client
    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(AstikorCartsRedux.SUPPLY_CART_ENTITY.get(), SupplyCartRenderer::new);
        event.registerEntityRenderer(AstikorCartsRedux.PLOW_ENTITY.get(), PlowRenderer::new);
        event.registerEntityRenderer(AstikorCartsRedux.ANIMAL_CART_ENTITY.get(), AnimalCartRenderer::new);
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

    // on the mod event bus only on the physical client
    @SubscribeEvent
    public static void registerBindings(RegisterKeyMappingsEvent event) {
        event.register(ACTION_KEY_MAPPING.get());
        event.register(TOGGLE_SLOW_MAPPING.get());
    }

     // on the mod event bus
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        generator.addProvider(
                event.includeClient(),
                new ModItemModelProvider(output, AstikorCartsRedux.MODID, event.getExistingFileHelper())
        );
        generator.addProvider(
                event.includeClient(),
                new DeDeLanguageProvider(output, AstikorCartsRedux.MODID, "de_de")
        );
        generator.addProvider(
                event.includeClient(),
                new EnUsLanguageProvider(output, AstikorCartsRedux.MODID, "en_us")
        );
    }

    @Unique
    private static final ImmutableMap<WoodType, String> LOG_NAME_OVERRIDE = ImmutableMap.of(
            WoodType.CRIMSON, "stem",
            WoodType.WARPED, "stem",
            WoodType.BAMBOO, "block"
    );

    @SubscribeEvent
    public static void onTextureStitchPost(TextureStitchEvent event) {
        if (!event.getAtlas().location().equals(TextureAtlas.LOCATION_BLOCKS)) return;

        // Now the atlas is ready
        AssembledTextureFactory factory = new AssembledTextureFactory();
        Material composterSide = new Material(new ResourceLocation("block/composter_side"), 16)
                .fill(16, 47, 44, 5, Material.R0, -2, 1)
                .fill(16, 54, 38, 5, Material.R0, -2, -6);
        Material composterTop = new Material(new ResourceLocation("block/composter_top"), 16)
                .fill(18, 45, 10, 2, Material.R0, -2, 3)
                .fill(28, 45, 10, 2, Material.R0, 10, 3)
                .fill(18, 52, 8, 2, Material.R0, 0, -4)
                .fill(26, 52, 9, 2, Material.R0, 11, -4);
        Material stone = new Material(new ResourceLocation("block/stone"), 16)
                .fill(62, 55, 2, 9);
        Material dirt = new Material(new ResourceLocation("block/dirt"), 16)
                .fill(0, 45, 16, 17);
        for (WoodType type : AstikorCartsRedux.VANILLA_WOOD_TYPES) {
            String logName = LOG_NAME_OVERRIDE.getOrDefault(type, "log");
            factory.add(new AssembledTexture(AstikorCartsRedux.resLoc("textures/entity/" + type.name() + "_animal_cart.png"),64, 64)
                            .add(new Material(new ResourceLocation("block/" + type.name() + "_planks"), 16)
                                    .fill(0, 0, 60, 38, Material.R0, 0, 2)
                                    .fill(0, 28, 20, 33, Material.R90, 4, -2)
                                    .fill(12, 30, 8, 31, Material.R270, 0, 4)
                            )
                            .add(new Material(new ResourceLocation("block/stripped_" + type.name() + "_" + logName), 16)
                                    .fill(54, 54, 10, 10, Material.R0, 0, 2)
                            )
                            .add(new Material(new ResourceLocation("block/" + type.name() + "_" + logName), 16)
                                    .fill(0, 21, 60, 4, Material.R90)
                                    .fill(46, 60, 8, 4, Material.R90)
                            )
                            .add(stone)
                    )
                    .add(new AssembledTexture(AstikorCartsRedux.resLoc("textures/entity/" + type.name() + "_plow.png"), 64, 64)
                            .add(new Material(new ResourceLocation("block/" + type.name() + "_planks"), 16)
                                    .fill(0, 0, 64, 32, Material.R90)
                                    .fill(0, 8, 42, 3, Material.R0, 0, 1)
                                    .fill(0, 27, 34, 3, Material.R0, 0, 2)
                            )
                            .add(new Material(new ResourceLocation("block/stripped_" + type.name() + "_" + logName), 16)
                                    .fill(54, 54, 10, 10, Material.R0, 2, 0)
                            )
                            .add(new Material(new ResourceLocation("block/" + type.name() + "_" + logName), 16)
                                    .fill(0, 0, 54, 4, Material.R90)
                                    .fill(46, 60, 8, 4, Material.R90)
                            )
                            .add(stone)
                    )
                    .add(new AssembledTexture(AstikorCartsRedux.resLoc("textures/entity/" + type.name() + "_seed_drill.png"), 64, 64)
                            .add(new Material(new ResourceLocation("block/" + type.name() + "_planks"), 16)
                                    .fill(0, 0, 64, 32, Material.R90)
                                    .fill(0, 8, 64, 3, Material.R0, 0, 1)
                                    .fill(0, 27, 34, 3, Material.R0, 0, 2)
                            )
                            .add(new Material(new ResourceLocation("block/stripped_" + type.name() + "_" + logName), 16)
                                    .fill(54, 54, 10, 10, Material.R0, 2, 0)
                            )
                            .add(new Material(new ResourceLocation("block/" + type.name() + "_" + logName), 16)
                                    .fill(0, 0, 64, 4, Material.R90)
                                    .fill(46, 60, 8, 4, Material.R90)
                            )
                            .add(new Material(new ResourceLocation("block/stone"), 16)
                                    .fill(62, 55, 2, 9)
                                    .fill(0, 57, 8, 7, Material.R0)
                            )
                    )
                    .add(new AssembledTexture(AstikorCartsRedux.resLoc("textures/entity/" + type.name() + "_reaper.png"), 64, 64)
                            .add(new Material(new ResourceLocation("block/" + type.name() + "_planks"), 16)
                                    .fill(0, 0, 64, 32, Material.R90)
                                    .fill(0, 8, 46, 4, Material.R0, 0, 1)
                                    .fill(0, 27, 34, 3, Material.R0, 0, 2)
                            )
                            .add(new Material(new ResourceLocation("block/stripped_" + type.name() + "_" + logName), 16)
                                    .fill(54, 54, 10, 10, Material.R0, 2, 0)
                            )
                            .add(new Material(new ResourceLocation("block/" + type.name() + "_" + logName), 16)
                                    .fill(0, 0, 64, 4, Material.R90)
                                    .fill(32, 12, 8, 17, Material.R0)
                                    .fill(46, 60, 8, 4, Material.R90)
                            )
                            .add(new Material(new ResourceLocation("block/stone"), 16)
                                    .fill(62, 55, 2, 9)
                                    .fill(0, 32, 64, 16)
                            )
                    )
                    .add(new AssembledTexture(AstikorCartsRedux.resLoc("textures/entity/" + type.name() + "_supply_cart.png"), 64, 64)
                            .add(new Material(new ResourceLocation("block/" + type.name() + "_planks"), 16)
                                    .fill(0, 0, 60, 45, Material.R0, 0, 2)
                                    .fill(0, 27, 60, 8, Material.R0, 0, 1)
                            )
                            .add(new Material(new ResourceLocation("block/stripped_" + type.name() + "_" + logName), 16)
                                    .fill(54, 54, 10, 10, Material.R0, 0, 2)
                            )
                            .add(new Material(new ResourceLocation("block/" + type.name() + "_" + logName), 16)
                                    .fill(0, 23, 54, 4, Material.R90)
                                    .fill(46, 60, 8, 4, Material.R90)
                            )
                            .add(stone)
                            .add(composterSide)
                            .add(composterTop)
                            .add(dirt)
                    )
                    .add(new AssembledTexture(AstikorCartsRedux.resLoc("textures/entity/" + type.name() + "_hand_cart.png"), 64, 64)
                            .add(new Material(new ResourceLocation("block/" + type.name() + "_planks"), 16)
                                    .fill(0,0, 52, 43, Material.R0, 0, 2)
                                    .fill(0, 24, 42, 4, Material.R0, 0, 1)
                            )
                            .add(new Material(new ResourceLocation("block/stripped_" + type.name() + "_" + logName), 16)
                                    .fill(54, 54, 10, 10, Material.R0, 0, 2)
                            )
                            .add(new Material(new ResourceLocation("block/" + type.name() + "_" + logName), 16)
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

