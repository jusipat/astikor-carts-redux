package com.jusipat.astikorcartsredux.client.datagen.lang;

import com.jusipat.astikorcartsredux.AstikorCartsRedux;
import com.jusipat.astikorcartsredux.item.CartItem;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredItem;

public class AstikorCartsReduxEnUsLanguageProvider extends LanguageProvider {


    public AstikorCartsReduxEnUsLanguageProvider(PackOutput output) {
        super(output, AstikorCartsRedux.MODID, "de_de");
    }

    private static String capitalizeWordStart(String s) {
        StringBuilder builder = new StringBuilder();
        for (String sub : s.split("_")) {
            builder.append(sub.substring(0, 1).toUpperCase());
            builder.append(sub.substring(1));
            builder.append(" ");
        }
        return builder.toString();
    }

    @Override
    protected void addTranslations() {
        add(AstikorCartsRedux.WHEEL.toStack(), "Wheel");

        for (String type : AstikorCartsRedux.CART_TYPES) {
            for (WoodType woodType : WoodType.values().toList()) {
                DeferredItem<CartItem> item = AstikorCartsRedux.CARTS.get(type).get(woodType);
                String displayName = capitalizeWordStart(woodType.name()) + " " + capitalizeWordStart(type.replace("_", " "));
                add(item.get(), displayName);
                System.out.println(displayName + '\n');
            }
        }

        add(AstikorCartsRedux.SUPPLY_CART_ENTITY.get(), "Supply Cart");
        add(AstikorCartsRedux.ANIMAL_CART_ENTITY.get(), "Animal Cart");
        add(AstikorCartsRedux.HAND_CART_ENTITY.get(), "Hand Cart");
        add(AstikorCartsRedux.PLOW_ENTITY.get(), "Plow");
        add(AstikorCartsRedux.SEED_DRILL_ENTITY.get(), "Seed Drill");
        add(AstikorCartsRedux.REAPER_ENTITY.get(), "Reaper");
        add(AstikorCartsRedux.CART_ONE_CM.toLanguageKey(), "Distance by Cart");
        add("key.categories.niftycarts", "NiftyCarts");
        add("key.niftycarts.action", "Attach/Detach Cart");
        add("key.niftycarts.slow", "Toggle Slow");
        add("subtitles.niftycarts.cart.attached", "Cart attaches");
        add("subtitles.niftycarts.cart.detached", "Cart detaches");
        add("subtitles.niftycarts.cart.placed", "Cart placed");
        add("item.supply_cart.tooltip1", "This cart can hold up to 54 stacks of items");
        add("item.supply_cart.tooltip2", "It has one seat and can be decorated with a banner");
        add("item.hand_cart.tooltip1", "This cart can hold up to 27 stacks of items");
        add("item.hand_cart.tooltip2", "It can only be pulled by the player");
        add("item.animal_cart.tooltip1", "This cart has two seats for animals or players and can be decorated with a banner");
        add("item.animal_cart.tooltip2", "It can be also controlled from the front seat");
        add("item.plow.tooltip1", "This contraption can till the ground, make dirt paths or strip logs");
        add("item.plow.tooltip2", "It needs the respective tools to work and can be toggled by right-clicking");
        add("item.seed_drill.tooltip1", "This contraption plants seeds on farmland");
        add("item.seed_drill.tooltip2", "It has room for 9 stacks of seeds");
        add("item.reaper.tooltip1", "This contraption can harvest crops");
        add("item.reaper.tooltip2", "Only works if the player is sitting on it");
    }
}