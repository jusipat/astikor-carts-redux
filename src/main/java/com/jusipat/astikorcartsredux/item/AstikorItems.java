package com.jusipat.astikorcartsredux.item;

import com.jusipat.astikorcartsredux.AstikorCartsRedux;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;

public class AstikorItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, AstikorCartsRedux.ID);

    public static final Map<WoodType, RegistryObject<Item>> SUPPLY_CART = new HashMap<>();
    //public static final Map<WoodType, CartItem> HAND_CART = new HashMap<>();
    public static final Map<WoodType, CartItem> PLOW = new HashMap<>();
    public static final Map<WoodType, CartItem> ANIMAL_CART = new HashMap<>();
    //public static final Map<WoodType, CartItem> REAPER = new HashMap<>();
    //public static final Map<WoodType, CartItem> SEED_DRILL = new HashMap<>();

    public static final RegistryObject<Item> WHEEL = ITEMS.register("wheel",
            () -> new Item(new Item.Properties()));

    // supply carts

    public static final RegistryObject<Item> OAK_SUPPLY_CART = ITEMS.register("oak_supply_cart",
            () -> new CartItem(WoodType.OAK, "supply_cart", new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> SPRUCE_SUPPLY_CART = ITEMS.register("spruce_supply_cart",
            () -> new CartItem(WoodType.SPRUCE, "supply_cart", new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> BIRCH_SUPPLY_CART = ITEMS.register("birch_supply_cart",
            () -> new CartItem(WoodType.BIRCH, "supply_cart", new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> ACACIA_SUPPLY_CART = ITEMS.register("acacia_supply_cart",
            () -> new CartItem(WoodType.ACACIA, "supply_cart", new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> CHERRY_SUPPLY_CART = ITEMS.register("cherry_supply_cart",
            () -> new CartItem(WoodType.CHERRY, "supply_cart", new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> JUNGLE_SUPPLY_CART = ITEMS.register("jungle_supply_cart",
            () -> new CartItem(WoodType.JUNGLE, "supply_cart", new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> DARK_OAK_SUPPLY_CART = ITEMS.register("dark_oak_supply_cart",
            () -> new CartItem(WoodType.DARK_OAK, "supply_cart", new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> CRIMSON_SUPPLY_CART = ITEMS.register("crimson_supply_cart",
            () -> new CartItem(WoodType.CRIMSON, "supply_cart", new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> WARPED_SUPPLY_CART = ITEMS.register("warped_supply_cart",
            () -> new CartItem(WoodType.WARPED, "supply_cart", new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> MANGROVE_SUPPLY_CART = ITEMS.register("mangrove_supply_cart",
            () -> new CartItem(WoodType.MANGROVE, "supply_cart", new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> BAMBOO_SUPPLY_CART = ITEMS.register("bamboo_supply_cart",
            () -> new CartItem(WoodType.BAMBOO, "supply_cart", new Item.Properties().stacksTo(16)));

    // animal carts

    public static final RegistryObject<Item> OAK_ANIMAL_CART = ITEMS.register("oak_animal_cart",
            () -> new CartItem(WoodType.OAK, "animal_cart", new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> SPRUCE_ANIMAL_CART = ITEMS.register("spruce_animal_cart",
            () -> new CartItem(WoodType.SPRUCE, "animal_cart", new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> BIRCH_ANIMAL_CART = ITEMS.register("birch_animal_cart",
            () -> new CartItem(WoodType.BIRCH, "animal_cart", new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> ACACIA_ANIMAL_CART = ITEMS.register("acacia_animal_cart",
            () -> new CartItem(WoodType.ACACIA, "animal_cart", new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> CHERRY_ANIMAL_CART = ITEMS.register("cherry_animal_cart",
            () -> new CartItem(WoodType.CHERRY, "animal_cart", new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> JUNGLE_ANIMAL_CART = ITEMS.register("jungle_animal_cart",
            () -> new CartItem(WoodType.JUNGLE, "animal_cart", new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> DARK_OAK_ANIMAL_CART = ITEMS.register("dark_oak_animal_cart",
            () -> new CartItem(WoodType.DARK_OAK, "animal_cart", new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> CRIMSON_ANIMAL_CART = ITEMS.register("crimson_animal_cart",
            () -> new CartItem(WoodType.CRIMSON, "animal_cart", new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> WARPED_ANIMAL_CART = ITEMS.register("warped_animal_cart",
            () -> new CartItem(WoodType.WARPED, "animal_cart", new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> MANGROVE_ANIMAL_CART = ITEMS.register("mangrove_animal_cart",
            () -> new CartItem(WoodType.MANGROVE, "animal_cart", new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> BAMBOO_ANIMAL_CART = ITEMS.register("bamboo_animal_cart",
            () -> new CartItem(WoodType.BAMBOO, "animal_cart", new Item.Properties().stacksTo(16)));

    // plow

    public static final RegistryObject<Item> OAK_PLOW = ITEMS.register("oak_plow",
            () -> new CartItem(WoodType.OAK, "plow", new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> SPRUCE_PLOW = ITEMS.register("spruce_plow",
            () -> new CartItem(WoodType.SPRUCE, "plow", new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> BIRCH_PLOW = ITEMS.register("birch_plow",
            () -> new CartItem(WoodType.BIRCH, "plow", new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> ACACIA_PLOW = ITEMS.register("acacia_plow",
            () -> new CartItem(WoodType.ACACIA, "plow", new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> CHERRY_PLOW = ITEMS.register("cherry_plow",
            () -> new CartItem(WoodType.CHERRY, "plow", new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> JUNGLE_PLOW = ITEMS.register("jungle_plow",
            () -> new CartItem(WoodType.JUNGLE, "plow", new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> DARK_OAK_PLOW = ITEMS.register("dark_oak_plow",
            () -> new CartItem(WoodType.DARK_OAK, "plow", new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> CRIMSON_PLOW = ITEMS.register("crimson_plow",
            () -> new CartItem(WoodType.CRIMSON, "plow", new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> WARPED_PLOW = ITEMS.register("warped_plow",
            () -> new CartItem(WoodType.WARPED, "plow", new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> MANGROVE_PLOW = ITEMS.register("mangrove_plow",
            () -> new CartItem(WoodType.MANGROVE, "plow", new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> BAMBOO_PLOW = ITEMS.register("bamboo_plow",
            () -> new CartItem(WoodType.BAMBOO, "plow", new Item.Properties().stacksTo(16)));

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