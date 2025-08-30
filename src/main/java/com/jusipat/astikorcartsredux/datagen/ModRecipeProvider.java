package com.jusipat.astikorcartsredux.datagen;

import com.jusipat.astikorcartsredux.AstikorCartsRedux;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
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

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {


    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    public void buildRecipes(RecipeOutput exporter) {
        ResourceLocation wheelId = ResourceLocation.fromNamespaceAndPath(AstikorCartsRedux.MODID, "wheel");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AstikorCartsRedux.WHEEL)
                .define('p', ItemTags.PLANKS)
                .define('s', Items.STICK)
                .unlockedBy(RecipeProvider.getHasName(Items.STICK), RecipeProvider.has(Items.STICK))
                .pattern("sss")
                .pattern("sps")
                .pattern("sss")
                .save(exporter, wheelId);

        WoodType.values().forEach(woodType -> {
            ResourceLocation supplyCartId = ResourceLocation.fromNamespaceAndPath(AstikorCartsRedux.MODID, woodType.name() + "_supply_cart");
            Item supplyCart = BuiltInRegistries.ITEM.get(supplyCartId);
            Item planks = BuiltInRegistries.ITEM.get(ResourceLocation.withDefaultNamespace(woodType.name() + "_planks"));
            var recipeTrigger = RecipeProvider.inventoryTrigger(ItemPredicate.Builder.item().of(AstikorCartsRedux.WHEEL), ItemPredicate.Builder.item().of(planks));
            ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, supplyCart)
                    .define('p', planks)
                    .define('w', AstikorCartsRedux.WHEEL)
                    .define('c', Blocks.CHEST)
                    .unlockedBy("has_wheel_and_planks", recipeTrigger)
                    .pattern("pcp")
                    .pattern("pcp")
                    .pattern("wpw")
                    .save(exporter, supplyCartId);

            ResourceLocation animalCartId = ResourceLocation.fromNamespaceAndPath(AstikorCartsRedux.MODID, woodType.name() + "_animal_cart");
            Item animalCart = BuiltInRegistries.ITEM.get(animalCartId);
            ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, animalCart)
                    .define('p', planks)
                    .define('w', AstikorCartsRedux.WHEEL)
                    .unlockedBy("has_wheel_and_planks", recipeTrigger)
                    .pattern("ppp")
                    .pattern("ppp")
                    .pattern("wpw")
                    .save(exporter, animalCartId);

            ResourceLocation handCartId = ResourceLocation.fromNamespaceAndPath(AstikorCartsRedux.MODID, woodType.name() + "_hand_cart");
            Item handCart = BuiltInRegistries.ITEM.get(handCartId);
            ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, handCart)
                    .define('p', planks)
                    .define('w', AstikorCartsRedux.WHEEL)
                    .define('c', Blocks.CHEST)
                    .unlockedBy("has_wheel_and_planks", recipeTrigger)
                    .pattern("pcp")
                    .pattern("wpw")
                    .save(exporter, handCartId);

            ResourceLocation plowId = ResourceLocation.fromNamespaceAndPath(AstikorCartsRedux.MODID, woodType.name() + "_plow");
            Item plow = BuiltInRegistries.ITEM.get(plowId);
            ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, plow)
                    .define('p', planks)
                    .define('w', AstikorCartsRedux.WHEEL)
                    .define('s', Items.STICK)
                    .unlockedBy("has_wheel_and_planks", recipeTrigger)
                    .pattern("sss")
                    .pattern("psp")
                    .pattern("wpw")
                    .save(exporter, plowId);

            ResourceLocation reaperId = ResourceLocation.fromNamespaceAndPath(AstikorCartsRedux.MODID, woodType.name() + "_reaper");
            Item reaper = BuiltInRegistries.ITEM.get(reaperId);
            ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, reaper)
                    .define('p', planks)
                    .define('l', BuiltInRegistries.ITEM.get(ResourceLocation.withDefaultNamespace(woodType.name() + "_slab")))
                    .define('w', AstikorCartsRedux.WHEEL)
                    .define('s', Items.STICK)
                    .define('i', Items.IRON_INGOT)
                    .unlockedBy("has_wheel_and_planks", recipeTrigger)
                    .pattern(" sl")
                    .pattern("spp")
                    .pattern("iww")
                    .save(exporter, reaperId);

            ResourceLocation seedDrillId = ResourceLocation.fromNamespaceAndPath(AstikorCartsRedux.MODID, woodType.name() + "_seed_drill");
            Item seedDrill = BuiltInRegistries.ITEM.get(seedDrillId);
            ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, seedDrill)
                    .define('p', planks)
                    .define('w', AstikorCartsRedux.WHEEL)
                    .define('c', Blocks.CHEST)
                    .define('h', Blocks.HOPPER)
                    .unlockedBy("has_wheel_and_planks", recipeTrigger)
                    .pattern("pcp")
                    .pattern("php")
                    .pattern("wpw")
                    .save(exporter, seedDrillId);
        });
    }
}