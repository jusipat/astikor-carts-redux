package com.jusipat.astikorcartsredux.datagen.lang;

import com.jusipat.astikorcartsredux.AstikorCartsRedux;
import com.jusipat.astikorcartsredux.item.CartItem;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.Arrays;
import java.util.stream.Collectors;

public class EnUsLanguageProvider extends LanguageProvider {


    public EnUsLanguageProvider(PackOutput output, String modid, String locale) {
        super(output, modid, locale);
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

    public static String toDisplayName(String input) {
        return Arrays.stream(input.split("_"))
                .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase())
                .collect(Collectors.joining(" "));
    }

    @Override
    protected void addTranslations() {
        add(AstikorCartsRedux.WHEEL.get(), "Wheel");
        for (String type : AstikorCartsRedux.CART_TYPES) {
            for (WoodType woodType : WoodType.values().toList()) {
                DeferredItem<CartItem> item = AstikorCartsRedux.CARTS.get(type).get(woodType);
                String displayName = capitalizeWordStart(woodType.name()) + toDisplayName(type);
                add(item.get(), displayName);
            }
        }
        add(AstikorCartsRedux.SUPPLY_CART_ENTITY.get(), "Supply Cart");
        add(AstikorCartsRedux.ANIMAL_CART_ENTITY.get(), "Animal Cart");
        add(AstikorCartsRedux.HAND_CART_ENTITY.get(), "Hand Cart");
        add(AstikorCartsRedux.PLOW_ENTITY.get(), "Plow");
        add(AstikorCartsRedux.SEED_DRILL_ENTITY.get(), "Seed Drill");
        add(AstikorCartsRedux.REAPER_ENTITY.get(), "Reaper");
        add("stat.astikorcartsredux.cart_one_cm", "Distance by Cart");
        add("itemGroup.astikorcartsredux", "AstikorCartsRedux");
        add("key.categories.astikorcartsredux", "AstikorCartsRedux");
        add("key.astikorcartsredux.action", "Attach/Detach Cart");
        add("key.astikorcartsredux.slow", "Toggle Slow");
        add("subtitles.astikorcartsredux.cart.attached", "Cart attaches");
        add("subtitles.astikorcartsredux.cart.detached", "Cart detaches");
        add("subtitles.astikorcartsredux.cart.placed", "Cart placed");
        add("tutorial.slow.message", "Press %1$s to toggle slow mode");
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
        add("item.reaper.tooltip1", "This contraption can harvest crops when attached to a horse");
        add("item.reaper.tooltip2", "Only works if the player is sitting on it");
        add("advancements.astikorcartsredux.animal_cart_steer.description", "Steer an animal cart while sitting in it");
        add("advancements.astikorcartsredux.animal_cart_steer.title", "Holding The Reins");
        add("advancements.astikorcartsredux.attach_banner.description", "Attach a banner to a supply cart, animal cart or a wagon");
        add("advancements.astikorcartsredux.attach_banner.title", "That's A Red Flag");
        add("advancements.astikorcartsredux.hand_cart_one_k.description", "Pull a hand cart 1000 blocks");
        add("advancements.astikorcartsredux.hand_cart_one_k.title", "Expanded Inventory");
        add("advancements.astikorcartsredux.place_cart.description", "Build any cart and place it");
        add("advancements.astikorcartsredux.place_cart.title", "Carting around");
        add("advancements.astikorcartsredux.plow_create_path.description", "Use a shovel with the plow to create a path");
        add("advancements.astikorcartsredux.plow_create_path.title", "Road To Nowhere");
        add("advancements.astikorcartsredux.plow_strip_log.description", "Use a axe with the plow to strip logs");
        add("advancements.astikorcartsredux.plow_strip_log.title", "May I Axe You A Question?");
        add("advancements.astikorcartsredux.plow_till_ground.description", "Use a hoe with the plow to till the ground");
        add("advancements.astikorcartsredux.plow_till_ground.title", "Groundbreaking Innovation");
        add("advancements.astikorcartsredux.reaper_harvest.description", "Harvest some crops using a reaper");
        add("advancements.astikorcartsredux.reaper_harvest.title", "The Reaper's Due");
        add("advancements.astikorcartsredux.seed_drill_plant.description", "Plant crops using the seed drill");
        add("advancements.astikorcartsredux.seed_drill_plant.title", "Planting With Speed");
        add("advancements.astikorcartsredux.supply_cart_filled.description", "Pulled a supply cart that has an item in every slot");
        add("advancements.astikorcartsredux.supply_cart_filled.title", "Filled to the brim");
        add("advancements.astikorcartsredux.wagon_cart_full.description", "Pull a wagon with four passengers");
        add("advancements.astikorcartsredux.wagon_cart_full.title", "All aboard!");
    }
}
