package com.jusipat.astikorcartsredux.client;

import com.jusipat.astikorcartsredux.AstikorCartsRedux;
import com.jusipat.astikorcartsredux.CommonInitializer;
import com.jusipat.astikorcartsredux.client.gui.screen.inventory.PlowScreen;
import com.jusipat.astikorcartsredux.client.renderer.AstikorCartsModelLayers;
import com.jusipat.astikorcartsredux.client.renderer.entity.AnimalCartRenderer;
import com.jusipat.astikorcartsredux.client.renderer.entity.PlowRenderer;
import com.jusipat.astikorcartsredux.client.renderer.entity.PostilionRenderer;
import com.jusipat.astikorcartsredux.client.renderer.entity.SupplyCartRenderer;
import com.jusipat.astikorcartsredux.client.renderer.entity.model.AnimalCartModel;
import com.jusipat.astikorcartsredux.client.renderer.entity.model.PlowModel;
import com.jusipat.astikorcartsredux.client.renderer.entity.model.SupplyCartModel;
import com.jusipat.astikorcartsredux.client.renderer.texture.AssembledTexture;
import com.jusipat.astikorcartsredux.client.renderer.texture.AssembledTextureFactory;
import com.jusipat.astikorcartsredux.client.renderer.texture.Material;
import com.jusipat.astikorcartsredux.entity.SupplyCartEntity;
import com.jusipat.astikorcartsredux.network.serverbound.ActionKeyMessage;
import com.jusipat.astikorcartsredux.network.serverbound.OpenSupplyCartMessage;
import com.jusipat.astikorcartsredux.network.serverbound.ToggleSlowMessage;
import com.jusipat.astikorcartsredux.world.AstikorWorld;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.lwjgl.glfw.GLFW;

public final class ClientInitializer extends CommonInitializer {
    private final KeyMapping action = new KeyMapping("key.astikorcartsredux.desc", GLFW.GLFW_KEY_R, "key.categories.astikorcartsredux");

    @Override
    public void init(final Context mod) {
        super.init(mod);
        mod.bus().<TickEvent.ClientTickEvent>addListener(e -> {
            if (e.phase == TickEvent.Phase.END) {
                final Minecraft mc = Minecraft.getInstance();
                final Level world = mc.level;
                if (world != null) {
                    while (this.action.consumeClick()) {
                        AstikorCartsRedux.CHANNEL.sendToServer(new ActionKeyMessage());
                    }
                    if (!mc.isPaused()) {
                        AstikorWorld.get(world).ifPresent(AstikorWorld::tick);
                    }
                }
            }
        });
        mod.bus().<InputEvent.Key>addListener(e -> {
            final Minecraft mc = Minecraft.getInstance();
            final Player player = mc.player;
            if (player != null) {
                if (ToggleSlowMessage.getCart(player).isPresent()) {
                    final KeyMapping binding = mc.options.keySprint;
                    while (binding.consumeClick()) {
                        AstikorCartsRedux.CHANNEL.sendToServer(new ToggleSlowMessage());
                        KeyMapping.set(binding.getKey(), false);
                    }
                }
            }
        });
        mod.bus().<ScreenEvent.Opening>addListener(e -> {
            if (e.getScreen() instanceof InventoryScreen) {
                final LocalPlayer player = Minecraft.getInstance().player;
                if (player != null && player.getVehicle() instanceof SupplyCartEntity) {
                    e.setCanceled(true);
                    AstikorCartsRedux.CHANNEL.sendToServer(new OpenSupplyCartMessage());
                }
            }
        });
        mod.modBus().<FMLClientSetupEvent>addListener(e -> {
            MenuScreens.register(AstikorCartsRedux.ContainerTypes.PLOW_CART.get(), PlowScreen::new);
        });
        mod.modBus().<RegisterKeyMappingsEvent>addListener(e -> {
            e.register(this.action);
        });
        mod.modBus().<EntityRenderersEvent.RegisterRenderers>addListener(e -> {
            e.registerEntityRenderer(AstikorCartsRedux.EntityTypes.SUPPLY_CART.get(), SupplyCartRenderer::new);
            e.registerEntityRenderer(AstikorCartsRedux.EntityTypes.PLOW.get(), PlowRenderer::new);
            e.registerEntityRenderer(AstikorCartsRedux.EntityTypes.ANIMAL_CART.get(), AnimalCartRenderer::new);
            e.registerEntityRenderer(AstikorCartsRedux.EntityTypes.POSTILION.get(), PostilionRenderer::new);
        });
        mod.modBus().<EntityRenderersEvent.RegisterLayerDefinitions>addListener(e -> {
            e.registerLayerDefinition(AstikorCartsModelLayers.PLOW, PlowModel::createLayer);
            e.registerLayerDefinition(AstikorCartsModelLayers.ANIMAL_CART, AnimalCartModel::createLayer);
            e.registerLayerDefinition(AstikorCartsModelLayers.SUPPLY_CART, SupplyCartModel::createLayer);
        });
        new AssembledTextureFactory()
            .add(new ResourceLocation(AstikorCartsRedux.ID, "textures/entity/animal_cart.png"), new AssembledTexture(64, 64)
                .add(new Material(new ResourceLocation("block/oak_planks"), 16)
                    .fill(0, 0, 60, 38, Material.R0, 0, 2)
                    .fill(0, 28, 20, 33, Material.R90, 4, -2)
                    .fill(12, 30, 8, 31, Material.R270, 0, 4)
                )
                .add(new Material(new ResourceLocation("block/stripped_spruce_log"), 16)
                    .fill(54, 54, 10, 10, Material.R0, 0, 2)
                )
                .add(new Material(new ResourceLocation("block/oak_log"), 16)
                    .fill(0, 21, 60, 4, Material.R90)
                    .fill(46, 60, 8, 4, Material.R90)
                )
                .add(new Material(new ResourceLocation("block/stone"), 16)
                    .fill(62, 55, 2, 9)
                )
            )
            .add(new ResourceLocation(AstikorCartsRedux.ID, "textures/entity/plow.png"), new AssembledTexture(64, 64)
                .add(new Material(new ResourceLocation("block/oak_planks"), 16)
                    .fill(0, 0, 64, 32, Material.R90)
                    .fill(0, 8, 42, 3, Material.R0, 0, 1)
                    .fill(0, 27, 34, 3, Material.R0, 0, 2)
                )
                .add(new Material(new ResourceLocation("block/stripped_spruce_log"), 16)
                    .fill(54, 54, 10, 10, Material.R0, 2, 0)
                )
                .add(new Material(new ResourceLocation("block/oak_log"), 16)
                    .fill(0, 0, 54, 4, Material.R90)
                    .fill(46, 60, 8, 4, Material.R90)
                )
                .add(new Material(new ResourceLocation("block/stone"), 16)
                    .fill(62, 55, 2, 9)
                )
            )
            .add(new ResourceLocation(AstikorCartsRedux.ID, "textures/entity/supply_cart.png"), new AssembledTexture(64, 64)
                .add(new Material(new ResourceLocation("block/oak_planks"), 16)
                    .fill(0, 0, 60, 45, Material.R0, 0, 2)
                    .fill(0, 27, 60, 8, Material.R0, 0, 1)
                )
                .add(new Material(new ResourceLocation("block/stripped_spruce_log"), 16)
                    .fill(54, 54, 10, 10, Material.R0, 0, 2)
                )
                .add(new Material(new ResourceLocation("block/oak_log"), 16)
                    .fill(0, 23, 54, 4, Material.R90)
                    .fill(46, 60, 8, 4, Material.R90)
                )
                .add(new Material(new ResourceLocation("block/stone"), 16)
                    .fill(62, 55, 2, 9)
                )
                .add(new Material(new ResourceLocation("block/composter_side"), 16)
                    .fill(16, 47, 44, 5, Material.R0, -2, 1)
                    .fill(16, 54, 38, 5, Material.R0, -2, -6)
                )
                .add(new Material(new ResourceLocation("block/composter_top"), 16)
                    .fill(18, 45, 10, 2, Material.R0, -2, 3)
                    .fill(28, 45, 10, 2, Material.R0, 10, 3)
                    .fill(18, 52, 8, 2, Material.R0, 0, -4)
                    .fill(26, 52, 9, 2, Material.R0, 11, -4)
                )
                .add(new Material(new ResourceLocation("block/dirt"), 16)
                    .fill(0, 45, 16, 17)
                )
            )
            .register(mod.modBus());
    }
}
