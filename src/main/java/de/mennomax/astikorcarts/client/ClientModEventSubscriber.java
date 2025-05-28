package de.mennomax.astikorcarts.client;

import de.mennomax.astikorcarts.AstikorCarts;
import de.mennomax.astikorcarts.client.gui.screen.inventory.PlowScreen;
import de.mennomax.astikorcarts.client.renderer.AstikorCartsModelLayers;
import de.mennomax.astikorcarts.client.renderer.entity.AnimalCartRenderer;
import de.mennomax.astikorcarts.client.renderer.entity.PlowRenderer;
import de.mennomax.astikorcarts.client.renderer.entity.PostilionRenderer;
import de.mennomax.astikorcarts.client.renderer.entity.SupplyCartRenderer;
import de.mennomax.astikorcarts.client.renderer.entity.model.AnimalCartModel;
import de.mennomax.astikorcarts.client.renderer.entity.model.PlowModel;
import de.mennomax.astikorcarts.client.renderer.entity.model.SupplyCartModel;
import de.mennomax.astikorcarts.entity.AstikorEntities;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

@EventBusSubscriber(
        bus = EventBusSubscriber.Bus.MOD,
        modid = AstikorCarts.ID)
public class ClientModEventSubscriber {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {

        // supply cart entities
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
        event.register(AstikorCarts.PLOW_MENU_TYPE, PlowScreen::new);
    }
}
