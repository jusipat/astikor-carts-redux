package de.mennomax.astikorcarts.item;

import de.mennomax.astikorcarts.AstikorCarts;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class AstikorItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(AstikorCarts.ID);

    public static final DeferredItem<Item> WHEEL = ITEMS.registerItem("wheel",
            Item::new, new Item.Properties());

    private static final BiFunction<WoodType, String, DeferredItem<CartItem>> CART_ITEM_SUPPLIER =
            (woodType, cartType) -> {
                String id = (woodType.name() + "_" + cartType).toLowerCase();
                System.out.println("TEST: " + id);
                return ITEMS.register(id, () -> new CartItem(new Item.Properties().stacksTo(1), woodType, cartType));
            };

    public static final Map<WoodType, DeferredItem<CartItem>> SUPPLY_CART = new HashMap<>();
    public static final Map<WoodType, DeferredItem<CartItem>> PLOW = new HashMap<>();
    public static final Map<WoodType, DeferredItem<CartItem>> ANIMAL_CART = new HashMap<>();
    //public static final Map<WoodType, CartItem> HAND_CART = new HashMap<>();
    //public static final Map<WoodType, CartItem> REAPER = new HashMap<>();
    //public static final Map<WoodType, CartItem> SEED_DRILL = new HashMap<>();

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

    static {
        for (WoodType woodType : VANILLA_WOOD_TYPES) {
            SUPPLY_CART.put(woodType, CART_ITEM_SUPPLIER.apply(woodType, "supply_cart"));
            //HAND_CART.put(woodType, CART_ITEM_SUPPLIER.apply(woodType, "hand_cart"));
            PLOW.put(woodType, CART_ITEM_SUPPLIER.apply(woodType, "plow"));
            //SEED_DRILL.put(woodType, CART_ITEM_SUPPLIER.apply(woodType, "seed_drill"));
            //REAPER.put(woodType, CART_ITEM_SUPPLIER.apply(woodType, "reaper"));
            ANIMAL_CART.put(woodType, CART_ITEM_SUPPLIER.apply(woodType, "animal_cart"));
        }
    }

//    static  {
//        for (WoodType woodType : VANILLA_WOOD_TYPES) {
//            ITEMS.registerItem(woodType.name() + "_supply_cart", properties -> SUPPLY_CART.get(woodType).asItem());
//            ITEMS.registerItem(woodType.name() + "_plow", properties -> PLOW.get(woodType).asItem());
//            ITEMS.registerItem(woodType.name() + "_animal_cart", properties -> ANIMAL_CART.get(woodType).asItem());
//            //Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(AstikorCarts.ID, woodType.name() + "_supply_cart"), SUPPLY_CART.get(woodType));
//            //Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(AstikorCarts.ID, woodType.name() + "_plow"), PLOW.get(woodType));
//            //Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(AstikorCarts.ID, woodType.name() + "_animal_cart"), ANIMAL_CART.get(woodType));
//            //Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(AstikorCarts.ID, woodType.name() + "_hand_cart"), HAND_CART.get(woodType));
//            //Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(AstikorCarts.ID, woodType.name() + "_reaper"), REAPER.get(woodType));
//            //Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(AstikorCarts.ID, woodType.name() + "_seed_drill"), SEED_DRILL.get(woodType));
//        }
//    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
