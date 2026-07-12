package com.jusipat.astikorcartsredux.datagen.lang;

import com.google.common.collect.ImmutableMap;
import com.jusipat.astikorcartsredux.AstikorCartsRedux;
import com.jusipat.astikorcartsredux.item.CartItem;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.Map;

public class DeDeLanguageProvider extends LanguageProvider {

    private static final Map<WoodType, String> NAMES = ImmutableMap.<WoodType, String>builderWithExpectedSize(11)
            .put(WoodType.ACACIA, "Akazienholz")
            .put(WoodType.DARK_OAK, "Schwarzeichenholz")
            .put(WoodType.BAMBOO, "Bambus")
            .put(WoodType.CHERRY, "Kirschholz")
            .put(WoodType.OAK, "Eichenholz")
            .put(WoodType.SPRUCE, "Fichtenholz")
            .put(WoodType.BIRCH, "Birkenholz")
            .put(WoodType.JUNGLE, "Tropenholz")
            .put(WoodType.MANGROVE, "Mangrovenholz")
            .put(WoodType.CRIMSON, "Karmesin")
            .put(WoodType.WARPED, "Wirr").build();

    public DeDeLanguageProvider(PackOutput output, String modid, String locale) {
        super(output, modid, locale);
    }

    @Override
    protected void addTranslations() {
        add(AstikorCartsRedux.WHEEL.get(), "Rad");
        for (String type : AstikorCartsRedux.CART_TYPES) {
            for (WoodType woodType : WoodType.values().toList()) {
                DeferredItem<CartItem> item = AstikorCartsRedux.CARTS.get(type).get(woodType);

                String woodName = NAMES.getOrDefault(woodType, (woodType.name()));
                String typeName = NAMES.getOrDefault(type, (type.replace("_", " ")));
                String displayName = woodName + " " + typeName;

                add(item.get(), displayName);
            }
        }
        add(AstikorCartsRedux.SUPPLY_CART_ENTITY.get(), "Transportkarren");
        add(AstikorCartsRedux.ANIMAL_CART_ENTITY.get(), "Tierkarren");
        add(AstikorCartsRedux.HAND_CART_ENTITY.get(), "Handkarren");
        add(AstikorCartsRedux.PLOW_ENTITY.get(), "Pflug");
        add(AstikorCartsRedux.SEED_DRILL_ENTITY.get(), "Sämaschine");
        add(AstikorCartsRedux.REAPER_ENTITY.get(), "Mähmaschine");
        add("stat.astikorcartsredux.cart_one_cm", "Strecke auf Karren gefahren");
        add("itemGroup.astikorcartsredux", "AstikorCartsRedux");
        add("key.categories.astikorcartsredux", "AstikorCartsRedux");
        add("key.astikorcartsredux.action", "Karren an-/abhängen");
        add("key.astikorcartsredux.slow", "Langsammodus an-/ausschalten");
        add("subtitles.astikorcartsredux.cart.attached", "Karren wird angehängt");
        add("subtitles.astikorcartsredux.cart.detached", "Karren wird abgehängt");
        add("subtitles.astikorcartsredux.cart.placed", "Karren wird platziert");
        add("tutorial.slow.message", "Drücke %1$s um den Langsammodus zu aktivieren");
        add("item.supply_cart.tooltip1", "Dieser Karren kann bis zu 54 Stapel lagern");
        add("item.supply_cart.tooltip2", "Er hat einen Sitzplatz und kann mit einem Banner dekoriert werden");
        add("item.hand_cart.tooltip1", "Dieser Karren kann bis zu 27 Stapel lagern");
        add("item.hand_cart.tooltip2", "Er kann nur vom Spieler gezogen werden");
        add("item.animal_cart.tooltip1", "Dieser Karren hat zwei Sitzplätze für Tiere oder Spieler, und kann mit einem Banner dekoriert werden");
        add("item.animal_cart.tooltip2", "Er kann auch vom vorderen Sitz aus gesteuert werden");
        add("item.plow.tooltip1", "Diese Maschine kann den Boden pflügen, Wege planieren oder Rinde abschaben");
        add("item.plow.tooltip2", "Sie braucht das jeweilige Werkzeug und kann mit Rechtsklick aktiviert werden");
        add("item.seed_drill.tooltip1", "Diese Maschine kann Samen pflanzen");
        add("item.seed_drill.tooltip2", "Sie hat Platz für 9 Stapel Samen");
        add("item.reaper.tooltip1", "Diese Maschine kann Felder abernten");
        add("item.reaper.tooltip2", "Sie funktioniert nur, wenn ein Spieler sie bedient");
        add("advancements.astikorcartsredux.animal_cart_steer.description", "Steuere einen Tierkarren in welchem du sitzt");
        add("advancements.astikorcartsredux.animal_cart_steer.title", "Die Zügel halten");
        add("advancements.astikorcartsredux.attach_banner.description", "Bring einen Banner an einen Transportkarren, Tierkarren oder Wagen an");
        add("advancements.astikorcartsredux.attach_banner.title", "Karren mit Banner");
        add("advancements.astikorcartsredux.hand_cart_one_k.description", "Ziehe einen Handkarren 1000 Blöcke");
        add("advancements.astikorcartsredux.hand_cart_one_k.title", "Erweiterter Inventar");
        add("advancements.astikorcartsredux.place_cart.description", "Bau einen Karren und platziere ihn");
        add("advancements.astikorcartsredux.place_cart.title", "Herumkarren");
        add("advancements.astikorcartsredux.plow_create_path.description", "Verwende eine Schaufel in einem Pflug um einen Weg zu schaffen");
        add("advancements.astikorcartsredux.plow_create_path.title", "Straße Ins Nirgendwo");
        add("advancements.astikorcartsredux.plow_strip_log.description", "Verwende eine Axt in einem Pflug um Holz abzuschaben");
        add("advancements.astikorcartsredux.plow_strip_log.title", "Hochgeschwindigkeitsentrindung");
        add("advancements.astikorcartsredux.plow_till_ground.description", "Verwende eine Hacke in einem Pflug um den Boden zu pflügen");
        add("advancements.astikorcartsredux.plow_till_ground.title", "Bodenbrechende Innovation");
        add("advancements.astikorcartsredux.reaper_harvest.description", "Bring die Ernte mit einer Mähmaschine ein");
        add("advancements.astikorcartsredux.reaper_harvest.title", "Mähmaschine");
        add("advancements.astikorcartsredux.seed_drill_plant.description", "Pflanze ein Feld mit einer Sämaschine");
        add("advancements.astikorcartsredux.seed_drill_plant.title", "Hochgeschwindigkeitbepflanzung");
        add("advancements.astikorcartsredux.supply_cart_filled.description", "Zieh einen Transportwagen der voll ist");
        add("advancements.astikorcartsredux.supply_cart_filled.title", "Bis zum Rand gefüllt");
        add("advancements.astikorcartsredux.wagon_cart_full.description", "Ziehe einen Wagen mit vier Passagiere");
        add("advancements.astikorcartsredux.wagon_cart_full.title", "Alle an Bord!");
    }
}
