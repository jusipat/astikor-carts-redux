package com.jusipat.astikorcartsredux.client.datagen;

import com.jusipat.astikorcartsredux.AstikorCartsRedux;
import com.jusipat.astikorcartsredux.item.CartItem;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.Arrays;

public class AstikorCartsReduxModelProvider extends ModelProvider {


    public AstikorCartsReduxModelProvider(PackOutput output) {
        super(output, AstikorCartsRedux.MODID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {

       // itemModels.generateFlatItem(AstikorCartsRedux.WHEEL.asItem(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(AstikorCartsRedux.WHEEL.get(), ModelTemplates.FLAT_ITEM);

        AstikorCartsRedux.CARTS.forEach((cartType, perWoodMap) -> {
            Arrays.stream(AstikorCartsRedux.VANILLA_WOOD_TYPES).distinct().forEach(woodType -> {
                DeferredItem<CartItem> item = perWoodMap.get(woodType);
                if (item != null) {
                    itemModels.generateFlatItem(item.get(), ModelTemplates.FLAT_ITEM);
                }
            });
        });
    }
}