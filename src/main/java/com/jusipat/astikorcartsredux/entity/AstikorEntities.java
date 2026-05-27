package com.jusipat.astikorcartsredux.entity;

import com.jusipat.astikorcartsredux.AstikorCartsRedux;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class AstikorEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, AstikorCartsRedux.ID);

    public static final ResourceKey<EntityType<?>> OAK_SUPPLY_CART_KEY =
            ResourceKey.create(ForgeRegistries.Keys.ENTITY_TYPES, new ResourceLocation(AstikorCartsRedux.ID, "oak_supply_cart"));
    public static final ResourceKey<EntityType<?>> SPRUCE_SUPPLY_CART_KEY =
            ResourceKey.create(ForgeRegistries.Keys.ENTITY_TYPES, new ResourceLocation(AstikorCartsRedux.ID, "spruce_supply_cart"));
    public static final ResourceKey<EntityType<?>> BIRCH_SUPPLY_CART_KEY =
            ResourceKey.create(ForgeRegistries.Keys.ENTITY_TYPES, new ResourceLocation(AstikorCartsRedux.ID, "birch_supply_cart"));
    public static final ResourceKey<EntityType<?>> ACACIA_SUPPLY_CART_KEY =
            ResourceKey.create(ForgeRegistries.Keys.ENTITY_TYPES, new ResourceLocation(AstikorCartsRedux.ID, "acacia_supply_cart"));
    public static final ResourceKey<EntityType<?>> CHERRY_SUPPLY_CART_KEY =
            ResourceKey.create(ForgeRegistries.Keys.ENTITY_TYPES, new ResourceLocation(AstikorCartsRedux.ID, "cherry_supply_cart"));
    public static final ResourceKey<EntityType<?>> JUNGLE_SUPPLY_CART_KEY =
            ResourceKey.create(ForgeRegistries.Keys.ENTITY_TYPES, new ResourceLocation(AstikorCartsRedux.ID, "jungle_supply_cart"));
    public static final ResourceKey<EntityType<?>> DARK_OAK_SUPPLY_CART_KEY =
            ResourceKey.create(ForgeRegistries.Keys.ENTITY_TYPES, new ResourceLocation(AstikorCartsRedux.ID, "dark_oak_supply_cart"));
    public static final ResourceKey<EntityType<?>> CRIMSON_SUPPLY_CART_KEY =
            ResourceKey.create(ForgeRegistries.Keys.ENTITY_TYPES, new ResourceLocation(AstikorCartsRedux.ID, "crimson_supply_cart"));
    public static final ResourceKey<EntityType<?>> WARPED_SUPPLY_CART_KEY =
            ResourceKey.create(ForgeRegistries.Keys.ENTITY_TYPES, new ResourceLocation(AstikorCartsRedux.ID, "warped_supply_cart"));
    public static final ResourceKey<EntityType<?>> MANGROVE_SUPPLY_CART_KEY =
            ResourceKey.create(ForgeRegistries.Keys.ENTITY_TYPES, new ResourceLocation(AstikorCartsRedux.ID, "mangrove_supply_cart"));
    public static final ResourceKey<EntityType<?>> BAMBOO_SUPPLY_CART_KEY =
            ResourceKey.create(ForgeRegistries.Keys.ENTITY_TYPES, new ResourceLocation(AstikorCartsRedux.ID, "bamboo_supply_cart"));

    public static final ResourceKey<EntityType<?>> OAK_ANIMAL_CART_KEY =
            ResourceKey.create(ForgeRegistries.Keys.ENTITY_TYPES, new ResourceLocation(AstikorCartsRedux.ID, "oak_animal_cart"));
    public static final ResourceKey<EntityType<?>> SPRUCE_ANIMAL_CART_KEY =
            ResourceKey.create(ForgeRegistries.Keys.ENTITY_TYPES, new ResourceLocation(AstikorCartsRedux.ID, "spruce_animal_cart"));
    public static final ResourceKey<EntityType<?>> BIRCH_ANIMAL_CART_KEY =
            ResourceKey.create(ForgeRegistries.Keys.ENTITY_TYPES, new ResourceLocation(AstikorCartsRedux.ID, "birch_animal_cart"));
    public static final ResourceKey<EntityType<?>> ACACIA_ANIMAL_CART_KEY =
            ResourceKey.create(ForgeRegistries.Keys.ENTITY_TYPES, new ResourceLocation(AstikorCartsRedux.ID, "acacia_animal_cart"));
    public static final ResourceKey<EntityType<?>> CHERRY_ANIMAL_CART_KEY =
            ResourceKey.create(ForgeRegistries.Keys.ENTITY_TYPES, new ResourceLocation(AstikorCartsRedux.ID, "cherry_animal_cart"));
    public static final ResourceKey<EntityType<?>> JUNGLE_ANIMAL_CART_KEY =
            ResourceKey.create(ForgeRegistries.Keys.ENTITY_TYPES, new ResourceLocation(AstikorCartsRedux.ID, "jungle_animal_cart"));
    public static final ResourceKey<EntityType<?>> DARK_OAK_ANIMAL_CART_KEY =
            ResourceKey.create(ForgeRegistries.Keys.ENTITY_TYPES, new ResourceLocation(AstikorCartsRedux.ID, "dark_oak_animal_cart"));
    public static final ResourceKey<EntityType<?>> CRIMSON_ANIMAL_CART_KEY =
            ResourceKey.create(ForgeRegistries.Keys.ENTITY_TYPES, new ResourceLocation(AstikorCartsRedux.ID, "crimson_animal_cart"));
    public static final ResourceKey<EntityType<?>> WARPED_ANIMAL_CART_KEY =
            ResourceKey.create(ForgeRegistries.Keys.ENTITY_TYPES, new ResourceLocation(AstikorCartsRedux.ID, "warped_animal_cart"));
    public static final ResourceKey<EntityType<?>> MANGROVE_ANIMAL_CART_KEY =
            ResourceKey.create(ForgeRegistries.Keys.ENTITY_TYPES, new ResourceLocation(AstikorCartsRedux.ID, "mangrove_animal_cart"));
    public static final ResourceKey<EntityType<?>> BAMBOO_ANIMAL_CART_KEY =
            ResourceKey.create(ForgeRegistries.Keys.ENTITY_TYPES, new ResourceLocation(AstikorCartsRedux.ID, "bamboo_animal_cart"));

    public static final ResourceKey<EntityType<?>> OAK_PLOW_KEY =
            ResourceKey.create(ForgeRegistries.Keys.ENTITY_TYPES, new ResourceLocation(AstikorCartsRedux.ID, "oak_plow"));
    public static final ResourceKey<EntityType<?>> SPRUCE_PLOW_KEY =
            ResourceKey.create(ForgeRegistries.Keys.ENTITY_TYPES, new ResourceLocation(AstikorCartsRedux.ID, "spruce_plow"));
    public static final ResourceKey<EntityType<?>> BIRCH_PLOW_KEY =
            ResourceKey.create(ForgeRegistries.Keys.ENTITY_TYPES, new ResourceLocation(AstikorCartsRedux.ID, "birch_plow"));
    public static final ResourceKey<EntityType<?>> ACACIA_PLOW_KEY =
            ResourceKey.create(ForgeRegistries.Keys.ENTITY_TYPES, new ResourceLocation(AstikorCartsRedux.ID, "acacia_plow"));
    public static final ResourceKey<EntityType<?>> CHERRY_PLOW_KEY =
            ResourceKey.create(ForgeRegistries.Keys.ENTITY_TYPES, new ResourceLocation(AstikorCartsRedux.ID, "cherry_plow"));
    public static final ResourceKey<EntityType<?>> JUNGLE_PLOW_KEY =
            ResourceKey.create(ForgeRegistries.Keys.ENTITY_TYPES, new ResourceLocation(AstikorCartsRedux.ID, "jungle_plow"));
    public static final ResourceKey<EntityType<?>> DARK_OAK_PLOW_KEY =
            ResourceKey.create(ForgeRegistries.Keys.ENTITY_TYPES, new ResourceLocation(AstikorCartsRedux.ID, "dark_oak_plow"));
    public static final ResourceKey<EntityType<?>> CRIMSON_PLOW_KEY =
            ResourceKey.create(ForgeRegistries.Keys.ENTITY_TYPES, new ResourceLocation(AstikorCartsRedux.ID, "crimson_plow"));
    public static final ResourceKey<EntityType<?>> WARPED_PLOW_KEY =
            ResourceKey.create(ForgeRegistries.Keys.ENTITY_TYPES, new ResourceLocation(AstikorCartsRedux.ID, "warped_plow"));
    public static final ResourceKey<EntityType<?>> MANGROVE_PLOW_KEY =
            ResourceKey.create(ForgeRegistries.Keys.ENTITY_TYPES, new ResourceLocation(AstikorCartsRedux.ID, "mangrove_plow"));
    public static final ResourceKey<EntityType<?>> BAMBOO_PLOW_KEY =
            ResourceKey.create(ForgeRegistries.Keys.ENTITY_TYPES, new ResourceLocation(AstikorCartsRedux.ID, "bamboo_plow"));

    //public static ResourceKey<EntityType<?>> ANIMAL_CART_ENTITY_KEY = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.withDefaultNamespace("animal_cart"));
    //public static ResourceKey<EntityType<?>> PLOW_ENTITY_KEY = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.withDefaultNamespace("plow"));
    public static final ResourceKey<EntityType<?>> POSTILION_ENTITY_KEY =
            ResourceKey.create(
                    ForgeRegistries.Keys.ENTITY_TYPES,
                    new ResourceLocation(AstikorCartsRedux.ID, "postilion")
            );

    // supply carts
    public static final Supplier<EntityType<SupplyCartEntity>> OAK_SUPPLY_CART_ENTITY =
            ENTITY_TYPES.register("oak_supply_cart", () -> EntityType.Builder.of(SupplyCartEntity::new, MobCategory.MISC)
                    .sized(1.5f, 1.4f).build(String.valueOf(OAK_SUPPLY_CART_KEY)));
    public static final Supplier<EntityType<SupplyCartEntity>> SPRUCE_SUPPLY_CART_ENTITY =
            ENTITY_TYPES.register("spruce_supply_cart", () -> EntityType.Builder.of(SupplyCartEntity::new, MobCategory.MISC)
                    .sized(1.5f, 1.4f).build(String.valueOf(SPRUCE_SUPPLY_CART_KEY)));
    public static final Supplier<EntityType<SupplyCartEntity>> BIRCH_SUPPLY_CART_ENTITY =
            ENTITY_TYPES.register("birch_supply_cart", () -> EntityType.Builder.of(SupplyCartEntity::new, MobCategory.MISC)
                    .sized(1.5f, 1.4f).build(String.valueOf(BIRCH_SUPPLY_CART_KEY)));
    public static final Supplier<EntityType<SupplyCartEntity>> ACACIA_SUPPLY_CART_ENTITY =
            ENTITY_TYPES.register("acacia_supply_cart", () -> EntityType.Builder.of(SupplyCartEntity::new, MobCategory.MISC)
                    .sized(1.5f, 1.4f).build(String.valueOf(ACACIA_SUPPLY_CART_KEY)));
    public static final Supplier<EntityType<SupplyCartEntity>> CHERRY_SUPPLY_CART_ENTITY =
            ENTITY_TYPES.register("cherry_supply_cart", () -> EntityType.Builder.of(SupplyCartEntity::new, MobCategory.MISC)
                    .sized(1.5f, 1.4f).build(String.valueOf(CHERRY_SUPPLY_CART_KEY)));
    public static final Supplier<EntityType<SupplyCartEntity>> JUNGLE_SUPPLY_CART_ENTITY =
            ENTITY_TYPES.register("jungle_supply_cart", () -> EntityType.Builder.of(SupplyCartEntity::new, MobCategory.MISC)
                    .sized(1.5f, 1.4f).build(String.valueOf(JUNGLE_SUPPLY_CART_KEY)));
    public static final Supplier<EntityType<SupplyCartEntity>> DARK_OAK_SUPPLY_CART_ENTITY =
            ENTITY_TYPES.register("dark_oak_supply_cart", () -> EntityType.Builder.of(SupplyCartEntity::new, MobCategory.MISC)
                    .sized(1.5f, 1.4f).build(String.valueOf(DARK_OAK_SUPPLY_CART_KEY)));
    public static final Supplier<EntityType<SupplyCartEntity>> CRIMSON_SUPPLY_CART_ENTITY =
            ENTITY_TYPES.register("crimson_supply_cart", () -> EntityType.Builder.of(SupplyCartEntity::new, MobCategory.MISC)
                    .sized(1.5f, 1.4f).build(String.valueOf(CRIMSON_SUPPLY_CART_KEY)));
    public static final Supplier<EntityType<SupplyCartEntity>> WARPED_SUPPLY_CART_ENTITY =
            ENTITY_TYPES.register("warped_supply_cart", () -> EntityType.Builder.of(SupplyCartEntity::new, MobCategory.MISC)
                    .sized(1.5f, 1.4f).build(String.valueOf(WARPED_SUPPLY_CART_KEY)));
    public static final Supplier<EntityType<SupplyCartEntity>> MANGROVE_SUPPLY_CART_ENTITY =
            ENTITY_TYPES.register("mangrove_supply_cart", () -> EntityType.Builder.of(SupplyCartEntity::new, MobCategory.MISC)
                    .sized(1.5f, 1.4f).build(String.valueOf(MANGROVE_SUPPLY_CART_KEY)));
    public static final Supplier<EntityType<SupplyCartEntity>> BAMBOO_SUPPLY_CART_ENTITY =
            ENTITY_TYPES.register("bamboo_supply_cart", () -> EntityType.Builder.of(SupplyCartEntity::new, MobCategory.MISC)
                    .sized(1.5f, 1.4f).build(String.valueOf(BAMBOO_SUPPLY_CART_KEY)));

    // animal carts
    public static final Supplier<EntityType<AnimalCartEntity>> OAK_ANIMAL_CART_ENTITY =
            ENTITY_TYPES.register("oak_animal_cart", () -> EntityType.Builder.of(AnimalCartEntity::new, MobCategory.MISC)
                    .sized(1.5f, 1.4f).build(String.valueOf(OAK_ANIMAL_CART_KEY)));
    public static final Supplier<EntityType<AnimalCartEntity>> SPRUCE_ANIMAL_CART_ENTITY =
            ENTITY_TYPES.register("spruce_animal_cart", () -> EntityType.Builder.of(AnimalCartEntity::new, MobCategory.MISC)
                    .sized(1.5f, 1.4f).build(String.valueOf(SPRUCE_ANIMAL_CART_KEY)));
    public static final Supplier<EntityType<AnimalCartEntity>> BIRCH_ANIMAL_CART_ENTITY =
            ENTITY_TYPES.register("birch_animal_cart", () -> EntityType.Builder.of(AnimalCartEntity::new, MobCategory.MISC)
                    .sized(1.5f, 1.4f).build(String.valueOf(BIRCH_ANIMAL_CART_KEY)));
    public static final Supplier<EntityType<AnimalCartEntity>> ACACIA_ANIMAL_CART_ENTITY =
            ENTITY_TYPES.register("acacia_animal_cart", () -> EntityType.Builder.of(AnimalCartEntity::new, MobCategory.MISC)
                    .sized(1.5f, 1.4f).build(String.valueOf(ACACIA_ANIMAL_CART_KEY)));
    public static final Supplier<EntityType<AnimalCartEntity>> CHERRY_ANIMAL_CART_ENTITY =
            ENTITY_TYPES.register("cherry_animal_cart", () -> EntityType.Builder.of(AnimalCartEntity::new, MobCategory.MISC)
                    .sized(1.5f, 1.4f).build(String.valueOf(CHERRY_ANIMAL_CART_KEY)));
    public static final Supplier<EntityType<AnimalCartEntity>> JUNGLE_ANIMAL_CART_ENTITY =
            ENTITY_TYPES.register("jungle_animal_cart", () -> EntityType.Builder.of(AnimalCartEntity::new, MobCategory.MISC)
                    .sized(1.5f, 1.4f).build(String.valueOf(JUNGLE_ANIMAL_CART_KEY)));
    public static final Supplier<EntityType<AnimalCartEntity>> DARK_OAK_ANIMAL_CART_ENTITY =
            ENTITY_TYPES.register("dark_oak_animal_cart", () -> EntityType.Builder.of(AnimalCartEntity::new, MobCategory.MISC)
                    .sized(1.5f, 1.4f).build(String.valueOf(DARK_OAK_ANIMAL_CART_KEY)));
    public static final Supplier<EntityType<AnimalCartEntity>> CRIMSON_ANIMAL_CART_ENTITY =
            ENTITY_TYPES.register("crimson_animal_cart", () -> EntityType.Builder.of(AnimalCartEntity::new, MobCategory.MISC)
                    .sized(1.5f, 1.4f).build(String.valueOf(CRIMSON_ANIMAL_CART_KEY)));
    public static final Supplier<EntityType<AnimalCartEntity>> WARPED_ANIMAL_CART_ENTITY =
            ENTITY_TYPES.register("warped_animal_cart", () -> EntityType.Builder.of(AnimalCartEntity::new, MobCategory.MISC)
                    .sized(1.5f, 1.4f).build(String.valueOf(WARPED_ANIMAL_CART_KEY)));
    public static final Supplier<EntityType<AnimalCartEntity>> MANGROVE_ANIMAL_CART_ENTITY =
            ENTITY_TYPES.register("mangrove_animal_cart", () -> EntityType.Builder.of(AnimalCartEntity::new, MobCategory.MISC)
                    .sized(1.5f, 1.4f).build(String.valueOf(MANGROVE_ANIMAL_CART_KEY)));
    public static final Supplier<EntityType<AnimalCartEntity>> BAMBOO_ANIMAL_CART_ENTITY =
            ENTITY_TYPES.register("bamboo_animal_cart", () -> EntityType.Builder.of(AnimalCartEntity::new, MobCategory.MISC)
                    .sized(1.5f, 1.4f).build(String.valueOf(BAMBOO_ANIMAL_CART_KEY)));

    // plows
    public static final Supplier<EntityType<PlowEntity>> OAK_PLOW_ENTITY =
            ENTITY_TYPES.register("oak_plow", () -> EntityType.Builder.of(PlowEntity::new, MobCategory.MISC)
                    .sized(1.5f, 1.4f).build(String.valueOf(OAK_PLOW_KEY)));
    public static final Supplier<EntityType<PlowEntity>> SPRUCE_PLOW_ENTITY =
            ENTITY_TYPES.register("spruce_plow", () -> EntityType.Builder.of(PlowEntity::new, MobCategory.MISC)
                    .sized(1.5f, 1.4f).build(String.valueOf(SPRUCE_PLOW_KEY)));
    public static final Supplier<EntityType<PlowEntity>> BIRCH_PLOW_ENTITY =
            ENTITY_TYPES.register("birch_plow", () -> EntityType.Builder.of(PlowEntity::new, MobCategory.MISC)
                    .sized(1.5f, 1.4f).build(String.valueOf(BIRCH_ANIMAL_CART_KEY)));
    public static final Supplier<EntityType<PlowEntity>> ACACIA_PLOW_ENTITY =
            ENTITY_TYPES.register("acacia_plow", () -> EntityType.Builder.of(PlowEntity::new, MobCategory.MISC)
                    .sized(1.5f, 1.4f).build(String.valueOf(ACACIA_PLOW_KEY)));
    public static final Supplier<EntityType<PlowEntity>> CHERRY_PLOW_ENTITY =
            ENTITY_TYPES.register("cherry_plow", () -> EntityType.Builder.of(PlowEntity::new, MobCategory.MISC)
                    .sized(1.5f, 1.4f).build(String.valueOf(CHERRY_PLOW_KEY)));
    public static final Supplier<EntityType<PlowEntity>> JUNGLE_PLOW_ENTITY =
            ENTITY_TYPES.register("jungle_plow", () -> EntityType.Builder.of(PlowEntity::new, MobCategory.MISC)
                    .sized(1.5f, 1.4f).build(String.valueOf(JUNGLE_PLOW_KEY)));
    public static final Supplier<EntityType<PlowEntity>> DARK_OAK_PLOW_ENTITY =
            ENTITY_TYPES.register("dark_oak_plow", () -> EntityType.Builder.of(PlowEntity::new, MobCategory.MISC)
                    .sized(1.5f, 1.4f).build(String.valueOf(DARK_OAK_PLOW_KEY)));
    public static final Supplier<EntityType<PlowEntity>> CRIMSON_PLOW_ENTITY =
            ENTITY_TYPES.register("crimson_plow", () -> EntityType.Builder.of(PlowEntity::new, MobCategory.MISC)
                    .sized(1.5f, 1.4f).build(String.valueOf(CRIMSON_PLOW_KEY)));
    public static final Supplier<EntityType<PlowEntity>> WARPED_PLOW_ENTITY =
            ENTITY_TYPES.register("warped_plow", () -> EntityType.Builder.of(PlowEntity::new, MobCategory.MISC)
                    .sized(1.5f, 1.4f).build(String.valueOf(WARPED_PLOW_KEY)));
    public static final Supplier<EntityType<PlowEntity>> MANGROVE_PLOW_ENTITY =
            ENTITY_TYPES.register("mangrove_plow", () -> EntityType.Builder.of(PlowEntity::new, MobCategory.MISC)
                    .sized(1.5f, 1.4f).build(String.valueOf(MANGROVE_PLOW_KEY)));
    public static final Supplier<EntityType<PlowEntity>> BAMBOO_PLOW_ENTITY =
            ENTITY_TYPES.register("bamboo_plow", () -> EntityType.Builder.of(PlowEntity::new, MobCategory.MISC)
                    .sized(1.5f, 1.4f).build(String.valueOf(BAMBOO_PLOW_KEY)));

//    public static final Supplier<EntityType<AnimalCartEntity>> ANIMAL_CART_ENTITY =
//            ENTITY_TYPES.register("animal_cart", () -> EntityType.Builder.of(AnimalCartEntity::new, MobCategory.MISC)
//                    .sized(1.3f, 1.4f).build(ANIMAL_CART_ENTITY_KEY));
//
//    public static final Supplier<EntityType<PlowEntity>> PLOW_ENTITY =
//            ENTITY_TYPES.register("plow", () -> EntityType.Builder.of(PlowEntity::new, MobCategory.MISC)
//                    .sized(1.3f, 1.4f).build(PLOW_ENTITY_KEY));

    public static final Supplier<EntityType<PostilionEntity>> POSTILION_ENTITY =
            ENTITY_TYPES.register("postilion", () -> EntityType.Builder.of(PostilionEntity::new, MobCategory.MISC)
                    .sized(0.25f, 0.25f).noSummon().noSave().build(String.valueOf(POSTILION_ENTITY_KEY)));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}