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

    // supply carts

    public static final DeferredItem<Item> OAK_SUPPLY_CART = ITEMS.registerItem("oak_supply_cart",
            (properties) -> new CartItem(properties.stacksTo(16), WoodType.OAK, "supply_cart"));
    public static final DeferredItem<Item> SPRUCE_SUPPLY_CART = ITEMS.registerItem("spruce_supply_cart",
            (properties) -> new CartItem(properties.stacksTo(16), WoodType.OAK, "supply_cart"));
    public static final DeferredItem<Item> BIRCH_SUPPLY_CART = ITEMS.registerItem("birch_supply_cart",
            (properties) -> new CartItem(properties.stacksTo(16), WoodType.OAK, "supply_cart"));
    public static final DeferredItem<Item> ACACIA_SUPPLY_CART = ITEMS.registerItem("acacia_supply_cart",
            (properties) -> new CartItem(properties.stacksTo(16), WoodType.OAK, "supply_cart"));
    public static final DeferredItem<Item> CHERRY_SUPPLY_CART = ITEMS.registerItem("cherry_supply_cart",
            (properties) -> new CartItem(properties.stacksTo(16), WoodType.OAK, "supply_cart"));
    public static final DeferredItem<Item> JUNGLE_SUPPLY_CART = ITEMS.registerItem("jungle_supply_cart",
            (properties) -> new CartItem(properties.stacksTo(16), WoodType.OAK, "supply_cart"));
    public static final DeferredItem<Item> DARK_OAK_SUPPLY_CART = ITEMS.registerItem("dark_oak_supply_cart",
            (properties) -> new CartItem(properties.stacksTo(16), WoodType.OAK, "supply_cart"));
    public static final DeferredItem<Item> CRIMSON_SUPPLY_CART = ITEMS.registerItem("crimson_supply_cart",
            (properties) -> new CartItem(properties.stacksTo(16), WoodType.OAK, "supply_cart"));
    public static final DeferredItem<Item> WARPED_SUPPLY_CART = ITEMS.registerItem("warped_supply_cart",
            (properties) -> new CartItem(properties.stacksTo(16), WoodType.OAK, "supply_cart"));
    public static final DeferredItem<Item> MANGROVE_SUPPLY_CART = ITEMS.registerItem("mangrove_supply_cart",
            (properties) -> new CartItem(properties.stacksTo(16), WoodType.OAK, "supply_cart"));
    public static final DeferredItem<Item> BAMBOO_SUPPLY_CART = ITEMS.registerItem("bamboo_supply_cart",
            (properties) -> new CartItem(properties.stacksTo(16), WoodType.OAK, "supply_cart"));

    // animal carts

    public static final DeferredItem<Item> OAK_ANIMAL_CART = ITEMS.registerItem("oak_animal_cart",
            (properties) -> new CartItem(properties.stacksTo(16), WoodType.OAK, "animal_cart"));
    public static final DeferredItem<Item> SPRUCE_ANIMAL_CART = ITEMS.registerItem("spruce_animal_cart",
            (properties) -> new CartItem(properties.stacksTo(16), WoodType.OAK, "animal_cart"));
    public static final DeferredItem<Item> BIRCH_ANIMAL_CART = ITEMS.registerItem("birch_animal_cart",
            (properties) -> new CartItem(properties.stacksTo(16), WoodType.OAK, "animal_cart"));
    public static final DeferredItem<Item> ACACIA_ANIMAL_CART = ITEMS.registerItem("acacia_animal_cart",
            (properties) -> new CartItem(properties.stacksTo(16), WoodType.OAK, "animal_cart"));
    public static final DeferredItem<Item> CHERRY_ANIMAL_CART = ITEMS.registerItem("cherry_animal_cart",
            (properties) -> new CartItem(properties.stacksTo(16), WoodType.OAK, "animal_cart"));
    public static final DeferredItem<Item> JUNGLE_ANIMAL_CART = ITEMS.registerItem("jungle_animal_cart",
            (properties) -> new CartItem(properties.stacksTo(16), WoodType.OAK, "animal_cart"));
    public static final DeferredItem<Item> DARK_OAK_ANIMAL_CART = ITEMS.registerItem("dark_oak_animal_cart",
            (properties) -> new CartItem(properties.stacksTo(16), WoodType.OAK, "animal_cart"));
    public static final DeferredItem<Item> CRIMSON_ANIMAL_CART = ITEMS.registerItem("crimson_animal_cart",
            (properties) -> new CartItem(properties.stacksTo(16), WoodType.OAK, "animal_cart"));
    public static final DeferredItem<Item> WARPED_ANIMAL_CART = ITEMS.registerItem("warped_animal_cart",
            (properties) -> new CartItem(properties.stacksTo(16), WoodType.OAK, "animal_cart"));
    public static final DeferredItem<Item> MANGROVE_ANIMAL_CART = ITEMS.registerItem("mangrove_animal_cart",
            (properties) -> new CartItem(properties.stacksTo(16), WoodType.OAK, "animal_cart"));
    public static final DeferredItem<Item> BAMBOO_ANIMAL_CART = ITEMS.registerItem("bamboo_animal_cart",
            (properties) -> new CartItem(properties.stacksTo(16), WoodType.OAK, "animal_cart"));

    // plow

    public static final DeferredItem<Item> OAK_PLOW = ITEMS.registerItem("oak_plow",
            (properties) -> new CartItem(properties.stacksTo(16), WoodType.OAK, "plow"));
    public static final DeferredItem<Item> SPRUCE_PLOW = ITEMS.registerItem("spruce_plow",
            (properties) -> new CartItem(properties.stacksTo(16), WoodType.OAK, "plow"));
    public static final DeferredItem<Item> BIRCH_PLOW = ITEMS.registerItem("birch_plow",
            (properties) -> new CartItem(properties.stacksTo(16), WoodType.OAK, "plow"));
    public static final DeferredItem<Item> ACACIA_PLOW = ITEMS.registerItem("acacia_plow",
            (properties) -> new CartItem(properties.stacksTo(16), WoodType.OAK, "plow"));
    public static final DeferredItem<Item> CHERRY_PLOW = ITEMS.registerItem("cherry_plow",
            (properties) -> new CartItem(properties.stacksTo(16), WoodType.OAK, "plow"));
    public static final DeferredItem<Item> JUNGLE_PLOW = ITEMS.registerItem("jungle_plow",
            (properties) -> new CartItem(properties.stacksTo(16), WoodType.OAK, "plow"));
    public static final DeferredItem<Item> DARK_OAK_PLOW = ITEMS.registerItem("dark_oak_plow",
            (properties) -> new CartItem(properties.stacksTo(16), WoodType.OAK, "plow"));
    public static final DeferredItem<Item> CRIMSON_PLOW = ITEMS.registerItem("crimson_plow",
            (properties) -> new CartItem(properties.stacksTo(16), WoodType.OAK, "plow"));
    public static final DeferredItem<Item> WARPED_PLOW = ITEMS.registerItem("warped_plow",
            (properties) -> new CartItem(properties.stacksTo(16), WoodType.OAK, "plow"));
    public static final DeferredItem<Item> MANGROVE_PLOW = ITEMS.registerItem("mangrove_plow",
            (properties) -> new CartItem(properties.stacksTo(16), WoodType.OAK, "plow"));
    public static final DeferredItem<Item> BAMBOO_PLOW = ITEMS.registerItem("bamboo_plow",
            (properties) -> new CartItem(properties.stacksTo(16), WoodType.OAK, "plow"));


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