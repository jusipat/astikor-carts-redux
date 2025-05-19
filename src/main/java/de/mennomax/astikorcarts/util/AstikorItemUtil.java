package de.mennomax.astikorcarts.util;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class AstikorItemUtil {

//    public static boolean isHumanoidArmor(ItemStack stack) {
//        return isHumanoidArmor(stack.getItem());
//    }

//    public static boolean isHumanoidArmor(Item item) {
//        if (item.components().has(DataComponents.EQUIPPABLE)) {
//            Equipabble equippable = item.components().get(DataComponents.EQUIPPABLE);
//            return equippable.slot().getType() == EquipmentSlot.Type.HUMANOID_ARMOR;
//        }
//        return false;
//    }

    public static boolean isTool(ItemStack itemStack) {
        return isTool(itemStack.getItem());
    }

    public static boolean isTool(Item item) {
        return item.components().has(DataComponents.TOOL);
    }

}