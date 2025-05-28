package de.mennomax.astikorcarts.datagen;

import de.mennomax.astikorcarts.item.AstikorItems;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemModelGenerator {

    protected static void run(ItemModelGenerators itemModels) {
        List<Item> singleTextureItems = new ArrayList<>(AstikorItems.ITEMS.getEntries().stream().map(Holder::value).toList());
        for (Item item : singleTextureItems) {
            itemModels.generateFlatItem(item, ModelTemplates.FLAT_ITEM);
        }
    }
}
