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

    public static ResourceKey<EntityType<?>> SUPPLY_CART_KEY = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.withDefaultNamespace("supply_cart"));
    public static ResourceKey<EntityType<?>> ANIMAL_CART_ENTITY_KEY = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.withDefaultNamespace("animal_cart"));
    public static ResourceKey<EntityType<?>> PLOW_ENTITY_KEY = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.withDefaultNamespace("plow"));
    public static ResourceKey<EntityType<?>> POSTILION_ENTITY_KEY = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.withDefaultNamespace("postilion"));


    public static final Supplier<EntityType<SupplyCartEntity>> SUPPLY_CART_ENTITY =
            ENTITY_TYPES.register("supply_cart", () -> EntityType.Builder.of(SupplyCartEntity::new, MobCategory.MISC)
                    .sized(1.5f, 1.4f).build(SUPPLY_CART_KEY));

    public static final Supplier<EntityType<AnimalCartEntity>> ANIMAL_CART_ENTITY =
            ENTITY_TYPES.register("animal_cart", () -> EntityType.Builder.of(AnimalCartEntity::new, MobCategory.MISC)
                    .sized(1.3f, 1.4f).build(ANIMAL_CART_ENTITY_KEY));

    public static final Supplier<EntityType<PlowEntity>> PLOW_ENTITY =
            ENTITY_TYPES.register("plow", () -> EntityType.Builder.of(PlowEntity::new, MobCategory.MISC)
                    .sized(1.3f, 1.4f).build(PLOW_ENTITY_KEY));

    public static final Supplier<EntityType<PostilionEntity>> POSTILION_ENTITY =
            ENTITY_TYPES.register("postilion", () -> EntityType.Builder.of(PostilionEntity::new, MobCategory.MISC)
                    .sized(0.25f, 0.25f).noSummon().noSave().build(POSTILION_ENTITY_KEY));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
