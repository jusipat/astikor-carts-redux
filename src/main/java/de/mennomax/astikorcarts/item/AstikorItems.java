package de.mennomax.astikorcarts.item;

import de.mennomax.astikorcarts.AstikorCarts;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.Map;

public class AstikorItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(AstikorCarts.ID);

    public static final Map<WoodType, DeferredItem<Item>> SUPPLY_CART = new HashMap<>();
    //public static final Map<WoodType, CartItem> HAND_CART = new HashMap<>();
    public static final Map<WoodType, CartItem> PLOW = new HashMap<>();
    public static final Map<WoodType, CartItem> ANIMAL_CART = new HashMap<>();
    //public static final Map<WoodType, CartItem> REAPER = new HashMap<>();
    //public static final Map<WoodType, CartItem> SEED_DRILL = new HashMap<>();

    public static final DeferredItem<Item> WHEEL = ITEMS.registerItem("wheel", Item::new, new Item.Properties());

    public static final DeferredItem<Item> OAK_SUPPLY_CART = ITEMS.registerItem("oak_supply_cart",
            (properties) -> new CartItem(properties.stacksTo(16), WoodType.OAK, "supply_cart"));

    static {
    SUPPLY_CART.put(WoodType.OAK, OAK_SUPPLY_CART);
    }

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

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}