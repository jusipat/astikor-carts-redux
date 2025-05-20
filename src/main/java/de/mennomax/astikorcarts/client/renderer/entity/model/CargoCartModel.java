package de.mennomax.astikorcarts.client.renderer.entity.model;

import de.mennomax.astikorcarts.client.renderer.entity.CargoCartRenderState;
import net.minecraft.client.model.geom.ModelPart;

public abstract class CargoCartModel<T extends CargoCartRenderState> extends CartModel<T> {

    protected final ModelPart flowerBasket;

    protected CargoCartModel(ModelPart root) {
        super(root);
        this.flowerBasket = root.getChild("flowerBasket");
        this.flowerBasket.visible = false;
    }

    public ModelPart getFlowerBasket() {
        return flowerBasket;
    }
}