package com.jusipat.astikorcartsredux.datagen;

import com.jusipat.astikorcartsredux.AstikorCartsRedux;
import com.jusipat.astikorcartsredux.item.CartItem;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.Arrays;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
        super(output, AstikorCartsRedux.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        withExistingParent(AstikorCartsRedux.WHEEL.getId().toString(), mcLoc("item/generated")).texture("layer0", "item/wheel");
        basicItem(AstikorCartsRedux.WHEEL.get());

        AstikorCartsRedux.CARTS.forEach((cartType, perWoodMap) -> {
            Arrays.stream(AstikorCartsRedux.VANILLA_WOOD_TYPES).distinct().forEach(woodType -> {
                DeferredItem<CartItem> item = perWoodMap.get(woodType);
                if (item != null) {
                    basicItem(item.get());

                }
            });
        });
    }

    @Override
    public String getName() {
        return "";
    }
}