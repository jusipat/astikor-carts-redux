package com.jusipat.astikorcartsredux;

import com.jusipat.astikorcartsredux.network.serverbound.ToggleSlowMessage;
import com.jusipat.astikorcartsredux.util.NiftyWorld;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(modid = AstikorCartsRedux.MODID, value = Dist.CLIENT)
public class AstikorCartsReduxClientRuntime {

    public static final Lazy<KeyMapping> ACTION_KEY_MAPPING = Lazy.of(() -> new KeyMapping(
            "key.astikorcartsredux.action",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_R,
            "key.categories.astikorcartsredux"));

    public static final Lazy<KeyMapping> TOGGLE_SLOW_MAPPING = Lazy.of(() -> new KeyMapping(
            "key.astikorcartsredux.slow",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_Z,
            "key.categories.astikorcartsredux"
    ));
    
    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        Minecraft mc = Minecraft.getInstance();

        // handle action key
        while (ACTION_KEY_MAPPING.get().consumeClick()) {
            PacketDistributor.sendToServer(new ActionKeyPayload());
        }

        // handle toggle slow
        var player = mc.player;
        if (player != null && ToggleSlowMessage.getCart(player).isPresent()) {
            while (TOGGLE_SLOW_MAPPING.get().consumeClick()) {
                PacketDistributor.sendToServer(new ToggleSlowPayload());
                KeyMapping.set(TOGGLE_SLOW_MAPPING.get().getDefaultKey(), false);
            }
        }

        // tick nifty world if not paused
        if (!mc.isPaused() && mc.level != null) {
            NiftyWorld.getClient().tick();
        }
    }
}
