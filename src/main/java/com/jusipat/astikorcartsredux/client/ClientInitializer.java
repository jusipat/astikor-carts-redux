package com.jusipat.astikorcartsredux.client;

import com.google.common.collect.ImmutableMap;

import com.jusipat.astikorcartsredux.AstikorCartsRedux;
import com.jusipat.astikorcartsredux.client.gui.screen.inventory.PlowScreen;
import com.jusipat.astikorcartsredux.client.renderer.AstikorCartsModelLayers;
import com.jusipat.astikorcartsredux.client.renderer.entity.model.AnimalCartModel;
import com.jusipat.astikorcartsredux.client.renderer.entity.model.PlowModel;
import com.jusipat.astikorcartsredux.client.renderer.entity.model.SupplyCartModel;
import com.jusipat.astikorcartsredux.entity.AstikorEntities;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;


@Mod.EventBusSubscriber(
        bus = Mod.EventBusSubscriber.Bus.MOD,
        modid = AstikorCartsRedux.ID)
public class ClientModEventSubscriber {
    private static final ImmutableMap<WoodType, String> LOG_NAME_OVERRIDE = ImmutableMap.of(
            WoodType.CRIMSON, "stem",
            WoodType.WARPED, "stem",
            WoodType.BAMBOO, "block"
    );

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        //ClientPlayNetworking.registerGlobalReceiver(UpdateDrawnMessage.TYPE, (payload, ctx) -> ctx.client().execute(() -> UpdateDrawnMessage.handle(payload, Objects.requireNonNull(ctx.client().level))));
        // todo: port to neoforge syntax
        EntityRenderers.register(
                AstikorEntities.OAK_SUPPLY_CART_ENTITY.get(),
                SupplyCartRenderer::new
        );
        EntityRenderers.register(
                AstikorEntities.SPRUCE_SUPPLY_CART_ENTITY.get(),
                SupplyCartRenderer::new
        );
        EntityRenderers.register(
                AstikorEntities.BIRCH_SUPPLY_CART_ENTITY.get(),
                SupplyCartRenderer::new
        );
        EntityRenderers.register(
                AstikorEntities.ACACIA_SUPPLY_CART_ENTITY.get(),
                SupplyCartRenderer::new
        );
        EntityRenderers.register(
                AstikorEntities.CHERRY_SUPPLY_CART_ENTITY.get(),
                SupplyCartRenderer::new
        );
        EntityRenderers.register(
                AstikorEntities.JUNGLE_SUPPLY_CART_ENTITY.get(),
                SupplyCartRenderer::new
        );
        EntityRenderers.register(
                AstikorEntities.DARK_OAK_SUPPLY_CART_ENTITY.get(),
                SupplyCartRenderer::new
        );
        EntityRenderers.register(
                AstikorEntities.CRIMSON_SUPPLY_CART_ENTITY.get(),
                SupplyCartRenderer::new
        );
        EntityRenderers.register(
                AstikorEntities.WARPED_SUPPLY_CART_ENTITY.get(),
                SupplyCartRenderer::new
        );
        EntityRenderers.register(
                AstikorEntities.MANGROVE_SUPPLY_CART_ENTITY.get(),
                SupplyCartRenderer::new
        );
        EntityRenderers.register(
                AstikorEntities.BAMBOO_SUPPLY_CART_ENTITY.get(),
                SupplyCartRenderer::new
        );

        // animal cart entities

        EntityRenderers.register(
                AstikorEntities.OAK_ANIMAL_CART_ENTITY.get(),
                AnimalCartRenderer::new
        );
        EntityRenderers.register(
                AstikorEntities.SPRUCE_ANIMAL_CART_ENTITY.get(),
                AnimalCartRenderer::new
        );
        EntityRenderers.register(
                AstikorEntities.BIRCH_ANIMAL_CART_ENTITY.get(),
                AnimalCartRenderer::new
        );
        EntityRenderers.register(
                AstikorEntities.ACACIA_ANIMAL_CART_ENTITY.get(),
                AnimalCartRenderer::new
        );
        EntityRenderers.register(
                AstikorEntities.CHERRY_ANIMAL_CART_ENTITY.get(),
                AnimalCartRenderer::new
        );
        EntityRenderers.register(
                AstikorEntities.JUNGLE_ANIMAL_CART_ENTITY.get(),
                AnimalCartRenderer::new
        );
        EntityRenderers.register(
                AstikorEntities.DARK_OAK_ANIMAL_CART_ENTITY.get(),
                AnimalCartRenderer::new
        );
        EntityRenderers.register(
                AstikorEntities.CRIMSON_ANIMAL_CART_ENTITY.get(),
                AnimalCartRenderer::new
        );
        EntityRenderers.register(
                AstikorEntities.WARPED_ANIMAL_CART_ENTITY.get(),
                AnimalCartRenderer::new
        );
        EntityRenderers.register(
                AstikorEntities.MANGROVE_ANIMAL_CART_ENTITY.get(),
                AnimalCartRenderer::new
        );
        EntityRenderers.register(
                AstikorEntities.BAMBOO_ANIMAL_CART_ENTITY.get(),
                AnimalCartRenderer::new
        );

        // plow entities

        EntityRenderers.register(
                AstikorEntities.OAK_PLOW_ENTITY.get(),
                PlowRenderer::new
        );
        EntityRenderers.register(
                AstikorEntities.SPRUCE_PLOW_ENTITY.get(),
                PlowRenderer::new
        );
        EntityRenderers.register(
                AstikorEntities.BIRCH_PLOW_ENTITY.get(),
                PlowRenderer::new
        );
        EntityRenderers.register(
                AstikorEntities.ACACIA_PLOW_ENTITY.get(),
                PlowRenderer::new
        );
        EntityRenderers.register(
                AstikorEntities.CHERRY_PLOW_ENTITY.get(),
                PlowRenderer::new
        );
        EntityRenderers.register(
                AstikorEntities.JUNGLE_PLOW_ENTITY.get(),
                PlowRenderer::new
        );
        EntityRenderers.register(
                AstikorEntities.DARK_OAK_PLOW_ENTITY.get(),
                PlowRenderer::new
        );
        EntityRenderers.register(
                AstikorEntities.CRIMSON_PLOW_ENTITY.get(),
                PlowRenderer::new
        );
        EntityRenderers.register(
                AstikorEntities.WARPED_PLOW_ENTITY.get(),
                PlowRenderer::new
        );
        EntityRenderers.register(
                AstikorEntities.MANGROVE_PLOW_ENTITY.get(),
                PlowRenderer::new
        );
        EntityRenderers.register(
                AstikorEntities.BAMBOO_PLOW_ENTITY.get(),
                PlowRenderer::new
        );


        EntityRenderers.register(
                AstikorEntities.POSTILION_ENTITY.get(),
                PostilionRenderer::new
        );
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(AstikorCartsModelLayers.PLOW, PlowModel::createLayer);
        event.registerLayerDefinition(AstikorCartsModelLayers.ANIMAL_CART, AnimalCartModel::createLayer);
        event.registerLayerDefinition(AstikorCartsModelLayers.SUPPLY_CART, SupplyCartModel::createLayer);
    }

    @SubscribeEvent
    private static void registerScreens(RegisterMenuScreensEvent event) {
        // register plow screen
        event.register(AstikorCartsRedux.PLOW_MENU_TYPE, PlowScreen::new);
    }
}