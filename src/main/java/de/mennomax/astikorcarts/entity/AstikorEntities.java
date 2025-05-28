package de.mennomax.astikorcarts.entity;

import de.mennomax.astikorcarts.AstikorCarts;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class AstikorEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, AstikorCarts.ID);

    public static ResourceKey<EntityType<?>> OAK_SUPPLY_CART_KEY = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.withDefaultNamespace("oak_supply_cart"));
    public static ResourceKey<EntityType<?>> SPRUCE_SUPPLY_CART_KEY = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.withDefaultNamespace("spruce_supply_cart"));
    public static ResourceKey<EntityType<?>> BIRCH_SUPPLY_CART_KEY = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.withDefaultNamespace("birch_supply_cart"));
    public static ResourceKey<EntityType<?>> ACACIA_SUPPLY_CART_KEY = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.withDefaultNamespace("acacia_supply_cart"));
    public static ResourceKey<EntityType<?>> CHERRY_SUPPLY_CART_KEY = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.withDefaultNamespace("cherry_supply_cart"));
    public static ResourceKey<EntityType<?>> JUNGLE_SUPPLY_CART_KEY = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.withDefaultNamespace("jungle_supply_cart"));
    public static ResourceKey<EntityType<?>> DARK_OAK_SUPPLY_CART_KEY = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.withDefaultNamespace("dark_oak_supply_cart"));
    public static ResourceKey<EntityType<?>> CRIMSON_SUPPLY_CART_KEY = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.withDefaultNamespace("crimson_supply_cart"));
    public static ResourceKey<EntityType<?>> WARPED_SUPPLY_CART_KEY = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.withDefaultNamespace("warped_supply_cart"));
    public static ResourceKey<EntityType<?>> MANGROVE_SUPPLY_CART_KEY = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.withDefaultNamespace("mangrove_supply_cart"));
    public static ResourceKey<EntityType<?>> BAMBOO_SUPPLY_CART_KEY = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.withDefaultNamespace("bamboo_supply_cart"));

    public static ResourceKey<EntityType<?>> OAK_ANIMAL_CART_KEY = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.withDefaultNamespace("oak_animal_cart"));
    public static ResourceKey<EntityType<?>> SPRUCE_ANIMAL_CART_KEY = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.withDefaultNamespace("spruce_animal_cart"));
    public static ResourceKey<EntityType<?>> BIRCH_ANIMAL_CART_KEY = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.withDefaultNamespace("birch_animal_cart"));
    public static ResourceKey<EntityType<?>> ACACIA_ANIMAL_CART_KEY = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.withDefaultNamespace("acacia_animal_cart"));
    public static ResourceKey<EntityType<?>> CHERRY_ANIMAL_CART_KEY = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.withDefaultNamespace("cherry_animal_cart"));
    public static ResourceKey<EntityType<?>> JUNGLE_ANIMAL_CART_KEY = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.withDefaultNamespace("jungle_animal_cart"));
    public static ResourceKey<EntityType<?>> DARK_OAK_ANIMAL_CART_KEY = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.withDefaultNamespace("dark_oak_animal_cart"));
    public static ResourceKey<EntityType<?>> CRIMSON_ANIMAL_CART_KEY = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.withDefaultNamespace("crimson_animal_cart"));
    public static ResourceKey<EntityType<?>> WARPED_ANIMAL_CART_KEY = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.withDefaultNamespace("warped_animal_cart"));
    public static ResourceKey<EntityType<?>> MANGROVE_ANIMAL_CART_KEY = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.withDefaultNamespace("mangrove_animal_cart"));
    public static ResourceKey<EntityType<?>> BAMBOO_ANIMAL_CART_KEY = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.withDefaultNamespace("bamboo_animal_cart"));

    public static ResourceKey<EntityType<?>> OAK_PLOW_KEY = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.withDefaultNamespace("oak_plow"));
    public static ResourceKey<EntityType<?>> SPRUCE_PLOW_KEY = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.withDefaultNamespace("spruce_plow"));
    public static ResourceKey<EntityType<?>> BIRCH_PLOW_KEY = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.withDefaultNamespace("birch_plow"));
    public static ResourceKey<EntityType<?>> ACACIA_PLOW_KEY = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.withDefaultNamespace("acacia_plow"));
    public static ResourceKey<EntityType<?>> CHERRY_PLOW_KEY = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.withDefaultNamespace("cherry_plow"));
    public static ResourceKey<EntityType<?>> JUNGLE_PLOW_KEY = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.withDefaultNamespace("jungle_plow"));
    public static ResourceKey<EntityType<?>> DARK_OAK_PLOW_KEY = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.withDefaultNamespace("dark_oak_plow"));
    public static ResourceKey<EntityType<?>> CRIMSON_PLOW_KEY = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.withDefaultNamespace("crimson_plow"));
    public static ResourceKey<EntityType<?>> WARPED_PLOW_KEY = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.withDefaultNamespace("warpedplowt"));
    public static ResourceKey<EntityType<?>> MANGROVE_PLOW_KEY = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.withDefaultNamespace("mangrove_plow"));
    public static ResourceKey<EntityType<?>> BAMBOO_PLOW_KEY = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.withDefaultNamespace("bamboo_plow"));


    //public static ResourceKey<EntityType<?>> ANIMAL_CART_ENTITY_KEY = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.withDefaultNamespace("animal_cart"));
    //public static ResourceKey<EntityType<?>> PLOW_ENTITY_KEY = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.withDefaultNamespace("plow"));
    public static ResourceKey<EntityType<?>> POSTILION_ENTITY_KEY = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.withDefaultNamespace("postilion"));


    // supply carts
    public static final Supplier<EntityType<SupplyCartEntity>> OAK_SUPPLY_CART_ENTITY =
            ENTITY_TYPES.register("oak_supply_cart", () -> EntityType.Builder.of(SupplyCartEntity::new, MobCategory.MISC)
                    .sized(1.5f, 1.4f).build(OAK_SUPPLY_CART_KEY));
    public static final Supplier<EntityType<SupplyCartEntity>> SPRUCE_SUPPLY_CART_ENTITY =
            ENTITY_TYPES.register("spruce_supply_cart", () -> EntityType.Builder.of(SupplyCartEntity::new, MobCategory.MISC)
                    .sized(1.5f, 1.4f).build(SPRUCE_SUPPLY_CART_KEY));
    public static final Supplier<EntityType<SupplyCartEntity>> BIRCH_SUPPLY_CART_ENTITY =
            ENTITY_TYPES.register("birch_supply_cart", () -> EntityType.Builder.of(SupplyCartEntity::new, MobCategory.MISC)
                    .sized(1.5f, 1.4f).build(BIRCH_SUPPLY_CART_KEY));
    public static final Supplier<EntityType<SupplyCartEntity>> ACACIA_SUPPLY_CART_ENTITY =
            ENTITY_TYPES.register("acacia_supply_cart", () -> EntityType.Builder.of(SupplyCartEntity::new, MobCategory.MISC)
                    .sized(1.5f, 1.4f).build(ACACIA_SUPPLY_CART_KEY));
    public static final Supplier<EntityType<SupplyCartEntity>> CHERRY_SUPPLY_CART_ENTITY =
            ENTITY_TYPES.register("cherry_supply_cart", () -> EntityType.Builder.of(SupplyCartEntity::new, MobCategory.MISC)
                    .sized(1.5f, 1.4f).build(CHERRY_SUPPLY_CART_KEY));
    public static final Supplier<EntityType<SupplyCartEntity>> JUNGLE_SUPPLY_CART_ENTITY =
            ENTITY_TYPES.register("jungle_supply_cart", () -> EntityType.Builder.of(SupplyCartEntity::new, MobCategory.MISC)
                    .sized(1.5f, 1.4f).build(JUNGLE_SUPPLY_CART_KEY));
    public static final Supplier<EntityType<SupplyCartEntity>> DARK_OAK_SUPPLY_CART_ENTITY =
            ENTITY_TYPES.register("dark_oak_supply_cart", () -> EntityType.Builder.of(SupplyCartEntity::new, MobCategory.MISC)
                    .sized(1.5f, 1.4f).build(DARK_OAK_SUPPLY_CART_KEY));
    public static final Supplier<EntityType<SupplyCartEntity>> CRIMSON_SUPPLY_CART_ENTITY =
            ENTITY_TYPES.register("crimson_supply_cart", () -> EntityType.Builder.of(SupplyCartEntity::new, MobCategory.MISC)
                    .sized(1.5f, 1.4f).build(CRIMSON_SUPPLY_CART_KEY));
    public static final Supplier<EntityType<SupplyCartEntity>> WARPED_SUPPLY_CART_ENTITY =
            ENTITY_TYPES.register("warped_supply_cart", () -> EntityType.Builder.of(SupplyCartEntity::new, MobCategory.MISC)
                    .sized(1.5f, 1.4f).build(WARPED_SUPPLY_CART_KEY));
    public static final Supplier<EntityType<SupplyCartEntity>> MANGROVE_SUPPLY_CART_ENTITY =
            ENTITY_TYPES.register("mangrove_supply_cart", () -> EntityType.Builder.of(SupplyCartEntity::new, MobCategory.MISC)
                    .sized(1.5f, 1.4f).build(MANGROVE_SUPPLY_CART_KEY));
    public static final Supplier<EntityType<SupplyCartEntity>> BAMBOO_SUPPLY_CART_ENTITY =
            ENTITY_TYPES.register("bamboo_supply_cart", () -> EntityType.Builder.of(SupplyCartEntity::new, MobCategory.MISC)
                    .sized(1.5f, 1.4f).build(BAMBOO_SUPPLY_CART_KEY));

    // animal carts
    public static final Supplier<EntityType<AnimalCartEntity>> OAK_ANIMAL_CART_ENTITY =
            ENTITY_TYPES.register("oak_animal_cart", () -> EntityType.Builder.of(AnimalCartEntity::new, MobCategory.MISC)
                    .sized(1.5f, 1.4f).build(OAK_ANIMAL_CART_KEY));
    public static final Supplier<EntityType<AnimalCartEntity>> SPRUCE_ANIMAL_CART_ENTITY =
            ENTITY_TYPES.register("spruce_animal_cart", () -> EntityType.Builder.of(AnimalCartEntity::new, MobCategory.MISC)
                    .sized(1.5f, 1.4f).build(SPRUCE_ANIMAL_CART_KEY));
    public static final Supplier<EntityType<AnimalCartEntity>> BIRCH_ANIMAL_CART_ENTITY =
            ENTITY_TYPES.register("birch_animal_cart", () -> EntityType.Builder.of(AnimalCartEntity::new, MobCategory.MISC)
                    .sized(1.5f, 1.4f).build(BIRCH_ANIMAL_CART_KEY));
    public static final Supplier<EntityType<AnimalCartEntity>> ACACIA_ANIMAL_CART_ENTITY =
            ENTITY_TYPES.register("acacia_animal_cart", () -> EntityType.Builder.of(AnimalCartEntity::new, MobCategory.MISC)
                    .sized(1.5f, 1.4f).build(ACACIA_ANIMAL_CART_KEY));
    public static final Supplier<EntityType<AnimalCartEntity>> CHERRY_ANIMAL_CART_ENTITY =
            ENTITY_TYPES.register("cherry_animal_cart", () -> EntityType.Builder.of(AnimalCartEntity::new, MobCategory.MISC)
                    .sized(1.5f, 1.4f).build(CHERRY_ANIMAL_CART_KEY));
    public static final Supplier<EntityType<AnimalCartEntity>> JUNGLE_ANIMAL_CART_ENTITY =
            ENTITY_TYPES.register("jungle_animal_cart", () -> EntityType.Builder.of(AnimalCartEntity::new, MobCategory.MISC)
                    .sized(1.5f, 1.4f).build(JUNGLE_ANIMAL_CART_KEY));
    public static final Supplier<EntityType<AnimalCartEntity>> DARK_OAK_ANIMAL_CART_ENTITY =
            ENTITY_TYPES.register("dark_oak_animal_cart", () -> EntityType.Builder.of(AnimalCartEntity::new, MobCategory.MISC)
                    .sized(1.5f, 1.4f).build(DARK_OAK_ANIMAL_CART_KEY));
    public static final Supplier<EntityType<AnimalCartEntity>> CRIMSON_ANIMAL_CART_ENTITY =
            ENTITY_TYPES.register("crimson_animal_cart", () -> EntityType.Builder.of(AnimalCartEntity::new, MobCategory.MISC)
                    .sized(1.5f, 1.4f).build(CRIMSON_ANIMAL_CART_KEY));
    public static final Supplier<EntityType<AnimalCartEntity>> WARPED_ANIMAL_CART_ENTITY =
            ENTITY_TYPES.register("warped_animal_cart", () -> EntityType.Builder.of(AnimalCartEntity::new, MobCategory.MISC)
                    .sized(1.5f, 1.4f).build(WARPED_ANIMAL_CART_KEY));
    public static final Supplier<EntityType<AnimalCartEntity>> MANGROVE_ANIMAL_CART_ENTITY =
            ENTITY_TYPES.register("mangrove_animal_cart", () -> EntityType.Builder.of(AnimalCartEntity::new, MobCategory.MISC)
                    .sized(1.5f, 1.4f).build(MANGROVE_ANIMAL_CART_KEY));
    public static final Supplier<EntityType<AnimalCartEntity>> BAMBOO_ANIMAL_CART_ENTITY =
            ENTITY_TYPES.register("bamboo_animal_cart", () -> EntityType.Builder.of(AnimalCartEntity::new, MobCategory.MISC)
                    .sized(1.5f, 1.4f).build(BAMBOO_ANIMAL_CART_KEY));

// plows
public static final Supplier<EntityType<PlowEntity>> OAK_PLOW_ENTITY =
        ENTITY_TYPES.register("oak_plow", () -> EntityType.Builder.of(PlowEntity::new, MobCategory.MISC)
                .sized(1.5f, 1.4f).build(OAK_PLOW_KEY));
    public static final Supplier<EntityType<PlowEntity>> SPRUCE_PLOW_ENTITY =
            ENTITY_TYPES.register("spruce_plow", () -> EntityType.Builder.of(PlowEntity::new, MobCategory.MISC)
                    .sized(1.5f, 1.4f).build(SPRUCE_PLOW_KEY));
    public static final Supplier<EntityType<PlowEntity>> BIRCH_PLOW_ENTITY =
            ENTITY_TYPES.register("birch_plow", () -> EntityType.Builder.of(PlowEntity::new, MobCategory.MISC)
                    .sized(1.5f, 1.4f).build(BIRCH_ANIMAL_CART_KEY));
    public static final Supplier<EntityType<PlowEntity>> ACACIA_PLOW_ENTITY =
            ENTITY_TYPES.register("acacia_plow", () -> EntityType.Builder.of(PlowEntity::new, MobCategory.MISC)
                    .sized(1.5f, 1.4f).build(ACACIA_PLOW_KEY));
    public static final Supplier<EntityType<PlowEntity>> CHERRY_PLOW_ENTITY =
            ENTITY_TYPES.register("cherry_plow", () -> EntityType.Builder.of(PlowEntity::new, MobCategory.MISC)
                    .sized(1.5f, 1.4f).build(CHERRY_PLOW_KEY));
    public static final Supplier<EntityType<PlowEntity>> JUNGLE_PLOW_ENTITY =
            ENTITY_TYPES.register("jungle_plow", () -> EntityType.Builder.of(PlowEntity::new, MobCategory.MISC)
                    .sized(1.5f, 1.4f).build(JUNGLE_PLOW_KEY));
    public static final Supplier<EntityType<PlowEntity>> DARK_OAK_PLOW_ENTITY =
            ENTITY_TYPES.register("dark_oak_plow", () -> EntityType.Builder.of(PlowEntity::new, MobCategory.MISC)
                    .sized(1.5f, 1.4f).build(DARK_OAK_PLOW_KEY));
    public static final Supplier<EntityType<PlowEntity>> CRIMSON_PLOW_ENTITY =
            ENTITY_TYPES.register("crimson_plow", () -> EntityType.Builder.of(PlowEntity::new, MobCategory.MISC)
                    .sized(1.5f, 1.4f).build(CRIMSON_PLOW_KEY));
    public static final Supplier<EntityType<PlowEntity>> WARPED_PLOW_ENTITY =
            ENTITY_TYPES.register("warped_plow", () -> EntityType.Builder.of(PlowEntity::new, MobCategory.MISC)
                    .sized(1.5f, 1.4f).build(WARPED_PLOW_KEY));
    public static final Supplier<EntityType<PlowEntity>> MANGROVE_PLOW_ENTITY =
            ENTITY_TYPES.register("mangrove_plow", () -> EntityType.Builder.of(PlowEntity::new, MobCategory.MISC)
                    .sized(1.5f, 1.4f).build(MANGROVE_PLOW_KEY));
    public static final Supplier<EntityType<PlowEntity>> BAMBOO_PLOW_ENTITY =
            ENTITY_TYPES.register("bamboo_plow", () -> EntityType.Builder.of(PlowEntity::new, MobCategory.MISC)
                    .sized(1.5f, 1.4f).build(BAMBOO_PLOW_KEY));

//    public static final Supplier<EntityType<AnimalCartEntity>> ANIMAL_CART_ENTITY =
//            ENTITY_TYPES.register("animal_cart", () -> EntityType.Builder.of(AnimalCartEntity::new, MobCategory.MISC)
//                    .sized(1.3f, 1.4f).build(ANIMAL_CART_ENTITY_KEY));
//
//    public static final Supplier<EntityType<PlowEntity>> PLOW_ENTITY =
//            ENTITY_TYPES.register("plow", () -> EntityType.Builder.of(PlowEntity::new, MobCategory.MISC)
//                    .sized(1.3f, 1.4f).build(PLOW_ENTITY_KEY));

    public static final Supplier<EntityType<PostilionEntity>> POSTILION_ENTITY =
            ENTITY_TYPES.register("postilion", () -> EntityType.Builder.of(PostilionEntity::new, MobCategory.MISC)
                    .sized(0.25f, 0.25f).noSummon().noSave().build(POSTILION_ENTITY_KEY));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
