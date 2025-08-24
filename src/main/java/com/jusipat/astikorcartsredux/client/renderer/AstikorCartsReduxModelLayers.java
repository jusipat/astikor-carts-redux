package com.jusipat.astikorcartsredux.client.renderer;

import com.jusipat.astikorcartsredux.AstikorCartsRedux;
import net.minecraft.client.model.geom.ModelLayerLocation;

public class AstikorCartsReduxModelLayers {
    public static final ModelLayerLocation ANIMAL_CART = main("animal_cart");
    public static final ModelLayerLocation PLOW = main("plow");
    public static final ModelLayerLocation SUPPLY_CART = main("supply_cart");
    public static final ModelLayerLocation HAND_CART = main("hand_cart");
    public static final ModelLayerLocation SEED_DRILL = main("seed_drill");
    public static final ModelLayerLocation REAPER = main("reaper");

    @SuppressWarnings("ConfusingMainMethod")
    private static ModelLayerLocation main(String name) {
        return layer(name, "main");
    }

    @SuppressWarnings("SameParameterValue")
    private static ModelLayerLocation layer(String name, String layer) {
        return new ModelLayerLocation(AstikorCartsRedux.resLoc(name), layer);
    }
}
