package de.mennomax.astikorcarts.client.renderer;

import de.mennomax.astikorcarts.AstikorCarts;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class AstikorCartsModelLayers {
    public static final ModelLayerLocation ANIMAL_CART = main("animal_cart");
    public static final ModelLayerLocation PLOW = main("plow");
    public static final ModelLayerLocation SUPPLY_CART = main("supply_cart");
    //public static final ModelLayerLocation HAND_CART = main("hand_cart");
    //public static final ModelLayerLocation REAPER = main("reaper");
    //public static final ModelLayerLocation SEED_DRILL = main("seed_drill");

    @SuppressWarnings("ConfusingMainMethod")
    private static ModelLayerLocation main(String name) {
        return layer(name, "main");
    }

    @SuppressWarnings("SameParameterValue")
    private static ModelLayerLocation layer(String name, String layer) {
        return new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(AstikorCarts.ID, name), layer);
    }
}