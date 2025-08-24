package com.jusipat.astikorcartsredux;

import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;

public final class AstikorCartsReduxConfig {
    public static Common get() {
        return Holder.COMMON;
    }

    public static ModConfigSpec spec() {
        return Holder.COMMON_SPEC;
    }

    public static Client getClient() {
        return Holder.CLIENT;
    }

    public static ModConfigSpec clientSpec() {
        return Holder.CLIENT_SPEC;
    }

    private static final class Holder {
        private static final Common COMMON;

        private static final ModConfigSpec COMMON_SPEC;

        private static final Client CLIENT;
        private static final ModConfigSpec CLIENT_SPEC;

        static {
            final Pair<Common, ModConfigSpec> specPair = new ModConfigSpec.Builder().configure(Common::new);
            COMMON = specPair.getLeft();
            COMMON_SPEC = specPair.getRight();
            final Pair<Client, ModConfigSpec> clientSpecPair = new ModConfigSpec.Builder().configure(Client::new);
            CLIENT = clientSpecPair.getLeft();
            CLIENT_SPEC = clientSpecPair.getRight();
        }
    }

    public static class Client {
        public final ModConfigSpec.BooleanValue renderSupplies;
        public final ModConfigSpec.BooleanValue renderSupplyGear;
        public final ModConfigSpec.BooleanValue renderSupplyFlowers;
        public final ModConfigSpec.BooleanValue renderSupplyPaintings;
        public final ModConfigSpec.BooleanValue renderSupplyWheel;
        public final ModConfigSpec.ConfigValue<ArrayList<String>> renderBlacklist;

        Client(final ModConfigSpec.Builder builder) {
            builder.comment("Configuration to disable the rendering of certain supplies in the supply cart");
            this.renderSupplies = builder.comment("Enables/Disables the rendering of all supplies")
                    .define("render_supplies", true);
            this.renderSupplyGear = builder.comment("Falls back to rendering as items if false").define("render_supply_gear", true);
            this.renderSupplyFlowers = builder.comment("Falls back to rendering as items if false").define("render_supply_flowers", true);
            this.renderSupplyPaintings = builder.comment("Falls back to rendering as items if false").define("render_supply_paintings", true);
            this.renderSupplyWheel = builder.comment("Falls back to rendering as items if false").define("render_supply_wheel", true);
            ArrayList<String> blacklist = new ArrayList<>();
            blacklist.add("minecraft:trident");
            blacklist.add("minecraft:decorated_pot");
            blacklist.add("#minecraft:buttons");
            blacklist.add("#minecraft:banners");
            this.renderBlacklist = builder.comment("Disables rendering for these blocks and items").define("render_item_blacklist", blacklist);
        }

    }

    public static class Common {
        public final CartConfig supplyCart;
        public final CartConfig animalCart;
        public final CartConfig plow;
        public final CartConfig handCart;
        public final CartConfig seedDrill;
        public final CartConfig reaper;

        Common(final ModConfigSpec.Builder builder) {
            builder.comment("Configuration for all carts and cart-like vehicles, check log for automatic \"pull_animals\" list.").push("carts");
            this.supplyCart = new CartConfig(builder, "supply_cart", "The Supply Cart, a type of cart that stores items");
            this.animalCart = new CartConfig(builder, "animal_cart", "The Animal Cart, a type of cart to haul other animals");
            this.plow = new CartConfig(builder, "plow", "The Plow, an animal pulled machine for tilling soil and creating paths");
            ArrayList<String> list = new ArrayList<>();
            list.add("minecraft:player");
            this.handCart = new CartConfig(builder, "handCart", "The Hand Cart, a player pulled cart that stores items", list, 0);
            this.seedDrill = new CartConfig(builder, "seedDrill", "The Seed Drill, a type of cart that plants crops");
            this.reaper = new CartConfig(builder, "reaper", "The Reaper, a type of cart that harvests crops");
            builder.pop();
        }
    }

    public static class CartConfig {
        public final ModConfigSpec.ConfigValue<ArrayList<String>> pullEntities;
        public final ModConfigSpec.DoubleValue slowSpeed;
        public final ModConfigSpec.DoubleValue pullSpeed;

        CartConfig(final ModConfigSpec.Builder builder, final String name, final String description) {
            this(builder, name, description, new ArrayList<>(), 0);
        }

        CartConfig(final ModConfigSpec.Builder builder, final String name, final String description, ArrayList<String> defaultEntityList, double defaultPullSpeed) {
            builder.comment(description).push(name);
            this.pullEntities = builder
                    .comment(
                            "Entity that are able to pull this cart, such as [\"minecraft:horse\"]\n" +
                                    "An empty list defaults to all which may wear a saddle but not steered by an item"
                    )
                    .define("pull_animals", defaultEntityList);
            this.slowSpeed = builder.comment("Slow speed modifier toggled by the sprint key")
                    .defineInRange("slow_speed", -0.65D, -1.0D, 0.0D);
            this.pullSpeed = builder.comment("Base speed modifier applied to animals (-0.5 = half normal speed)")
                    .defineInRange("pull_speed", 0.0D, -1.0D, defaultPullSpeed);
            builder.pop();
        }
    }
}