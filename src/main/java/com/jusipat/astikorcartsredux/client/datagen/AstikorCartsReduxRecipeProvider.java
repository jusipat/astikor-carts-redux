package com.jusipat.astikorcartsredux.client.datagen;

import com.jusipat.astikorcartsredux.AstikorCartsRedux;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.WoodType;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class AstikorCartsReduxRecipeProvider extends RecipeProvider {

    // The runner to add to the data generator
    public static class Runner extends RecipeProvider.Runner {
        // Get the parameters from the `GatherDataEvent`s.
        public Runner(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
            super(output, lookupProvider);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput output) {
            return new AstikorCartsReduxRecipeProvider(provider, output);
        }

        @Override
        public String getName() {
            return "AstikorCartsRedux Recipe Provider";
        }
    }

    protected AstikorCartsReduxRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }


    @Override
    protected void buildRecipes() {
        var items = registries.lookupOrThrow(Registries.ITEM);
        ShapedRecipeBuilder.shaped(items, RecipeCategory.MISC, AstikorCartsRedux.WHEEL)
                .define('p', ItemTags.PLANKS)
                .define('s', Items.STICK)
                .unlockedBy(RecipeProvider.getHasName(Items.STICK), RecipeProvider.inventoryTrigger(ItemPredicate.Builder.item().of(items, Items.STICK)))
                .pattern("sss")
                .pattern("sps")
                .pattern("sss")
                .save(output);

        WoodType.values().forEach(woodType -> {
            ResourceLocation supplyCartId = AstikorCartsRedux.resLoc(woodType.name() + "_supply_cart");
            Optional<Holder.Reference<Item>> supplyCart = BuiltInRegistries.ITEM.get(supplyCartId);
            Item planks = BuiltInRegistries.ITEM.getValue(ResourceLocation.withDefaultNamespace(woodType.name() + "_planks"));
            var recipeTrigger = RecipeProvider.inventoryTrigger(ItemPredicate.Builder.item().of(items, AstikorCartsRedux.WHEEL), ItemPredicate.Builder.item().of(items, planks));
            ShapedRecipeBuilder.shaped(items, RecipeCategory.TRANSPORTATION, supplyCart.get().value())
                    .define('p', BuiltInRegistries.ITEM.get(ResourceLocation.withDefaultNamespace(woodType.name() + "_planks")).get().value())
                    .define('w', AstikorCartsRedux.WHEEL)
                    .define('c', Blocks.CHEST)
                    .unlockedBy("has_wheel_and_planks", recipeTrigger)
                    .pattern("pcp")
                    .pattern("pcp")
                    .pattern("wpw")
                    .save(output);

            ResourceLocation animalCartId = AstikorCartsRedux.resLoc(woodType.name() + "_animal_cart");
            Optional<Holder.Reference<Item>>  animalCart = BuiltInRegistries.ITEM.get(animalCartId);
            ShapedRecipeBuilder.shaped(items, RecipeCategory.TRANSPORTATION, animalCart.get().value())
                    .define('p', BuiltInRegistries.ITEM.get(ResourceLocation.withDefaultNamespace(woodType.name() + "_planks")).get().value())
                    .define('w', AstikorCartsRedux.WHEEL)
                    .unlockedBy("has_wheel_and_planks", recipeTrigger)
                    .pattern("ppp")
                    .pattern("ppp")
                    .pattern("wpw")
                    .save(output);

            ResourceLocation handCartId = AstikorCartsRedux.resLoc(woodType.name() + "_hand_cart");
            Optional<Holder.Reference<Item>>  handCart = BuiltInRegistries.ITEM.get(handCartId);
            ShapedRecipeBuilder.shaped(items, RecipeCategory.TRANSPORTATION, handCart.get().value())
                    .define('p', BuiltInRegistries.ITEM.get(ResourceLocation.withDefaultNamespace(woodType.name() + "_planks")).get().value())
                    .define('w', AstikorCartsRedux.WHEEL)
                    .define('c', Blocks.CHEST)
                    .unlockedBy("has_wheel_and_planks", recipeTrigger)
                    .pattern("pcp")
                    .pattern("wpw")
                    .save(output);

            ResourceLocation plowId = AstikorCartsRedux.resLoc(woodType.name() + "_plow");
            Optional<Holder.Reference<Item>>  plow = BuiltInRegistries.ITEM.get(plowId);
            ShapedRecipeBuilder.shaped(items, RecipeCategory.TRANSPORTATION, plow.get().value())
                    .define('p', BuiltInRegistries.ITEM.get(ResourceLocation.withDefaultNamespace(woodType.name() + "_planks")).get().value())
                    .define('w', AstikorCartsRedux.WHEEL)
                    .define('s', Items.STICK)
                    .unlockedBy("has_wheel_and_planks", recipeTrigger)
                    .pattern("sss")
                    .pattern("psp")
                    .pattern("wpw")
                    .save(output);

            ResourceLocation reaperId = AstikorCartsRedux.resLoc(woodType.name() + "_reaper");
            Optional<Holder.Reference<Item>>  reaper = BuiltInRegistries.ITEM.get(reaperId);
            ShapedRecipeBuilder.shaped(items, RecipeCategory.TRANSPORTATION, reaper.get().value())
                    .define('p', BuiltInRegistries.ITEM.get(ResourceLocation.withDefaultNamespace(woodType.name() + "_planks")).get().value())
                    .define('l', BuiltInRegistries.ITEM.get(ResourceLocation.withDefaultNamespace(woodType.name() + "_slab")).get().value())
                    .define('w', AstikorCartsRedux.WHEEL)
                    .define('s', Items.STICK)
                    .define('i', Items.IRON_INGOT)
                    .unlockedBy("has_wheel_and_planks", recipeTrigger)
                    .pattern(" sl")
                    .pattern("spp")
                    .pattern("iww")
                    .save(output);

            ResourceLocation seedDrillId = AstikorCartsRedux.resLoc(woodType.name() + "_seed_drill");
            Optional<Holder.Reference<Item>>  seedDrill = BuiltInRegistries.ITEM.get(seedDrillId);
            ShapedRecipeBuilder.shaped(items, RecipeCategory.TRANSPORTATION, seedDrill.get().value())
                    .define('p', BuiltInRegistries.ITEM.get(ResourceLocation.withDefaultNamespace(woodType.name() + "_planks")).get().value())
                    .define('w', AstikorCartsRedux.WHEEL)
                    .define('c', Blocks.CHEST)
                    .define('h', Blocks.HOPPER)
                    .unlockedBy("has_wheel_and_planks", recipeTrigger)
                    .pattern("pcp")
                    .pattern("php")
                    .pattern("wpw")
                    .save(output);
        });
    }
}
