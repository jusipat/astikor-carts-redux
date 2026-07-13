package com.jusipat.astikorcartsredux.datagen;
import com.jusipat.astikorcartsredux.AstikorCartsRedux;
import com.jusipat.astikorcartsredux.advancement.*;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.critereon.EntityTypePredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancementProvider extends AdvancementProvider {


    public ModAdvancementProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, existingFileHelper, List.of(new ModAdvancementGenerator()));    }

    private static final class ModAdvancementGenerator implements AdvancementGenerator {

        @Override
        public void generate(HolderLookup.Provider provider, Consumer<AdvancementHolder> consumer, ExistingFileHelper existingFileHelper) {
            AdvancementHolder root = Advancement.Builder.advancement()
                    .display(
                            AstikorCartsRedux.WHEEL,
                            Component.translatable("advancements.astikorcartsredux.place_cart.title"),
                            Component.translatable("advancements.astikorcartsredux.place_cart.description"),
                            ResourceLocation.withDefaultNamespace("textures/gui/advancements/backgrounds/husbandry.png"),
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .addCriterion("place_cart", PlaceCartItemCriterion.TriggerInstance.placeCart())
                    .save(consumer, AstikorCartsRedux.MODID + "/root");
            Advancement.Builder.advancement()
                    .parent(root)
                    .display(
                            AstikorCartsRedux.CARTS.get("seed_drill").get(WoodType.OAK),
                            Component.translatable("advancements.astikorcartsredux.seed_drill_plant.title"),
                            Component.translatable("advancements.astikorcartsredux.seed_drill_plant.description"),
                            null,
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .addCriterion("seed_drill_plant", SeedDrillPlaceCriterion.TriggerInstance.seedDrillPlace())
                    .save(consumer, AstikorCartsRedux.MODID + "/seed_drill_plant_seed");

            Advancement.Builder.advancement()
                    .parent(root)
                    .display(
                            AstikorCartsRedux.CARTS.get("plow").get(WoodType.OAK),
                            Component.translatable("advancements.astikorcartsredux.plow_till_ground.title"),
                            Component.translatable("advancements.astikorcartsredux.plow_till_ground.description"),
                            null,
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .addCriterion(
                            "plow_till_ground",
                            UsePlowCriterion.TriggerInstance.usePlow(
                                    ItemPredicate.Builder.item().of(ItemTags.HOES)))
                    .save(consumer, AstikorCartsRedux.MODID + "/plow_till_ground");

            Advancement.Builder.advancement()
                    .parent(root)
                    .display(
                            Items.DIRT_PATH,
                            Component.translatable("advancements.astikorcartsredux.plow_create_path.title"),
                            Component.translatable("advancements.astikorcartsredux.plow_create_path.description"),
                            null,
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .addCriterion(
                            "plow_create_path",
                            UsePlowCriterion.TriggerInstance.usePlow(
                                    ItemPredicate.Builder.item().of(ItemTags.SHOVELS)))
                    .save(consumer, AstikorCartsRedux.MODID + "/plow_create_path");

            Advancement.Builder.advancement()
                    .parent(root)
                    .display(
                            Items.STRIPPED_OAK_LOG,
                            Component.translatable("advancements.astikorcartsredux.plow_strip_log.title"),
                            Component.translatable("advancements.astikorcartsredux.plow_strip_log.description"),
                            null,
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .addCriterion(
                            "plow_strip_log",
                            UsePlowCriterion.TriggerInstance.usePlow(
                                    ItemPredicate.Builder.item().of(ItemTags.AXES)))
                    .save(consumer, AstikorCartsRedux.MODID + "/plow_strip_log");

            Advancement.Builder.advancement()
                    .parent(root)
                    .display(
                            Items.RED_BANNER,
                            Component.translatable("advancements.astikorcartsredux.attach_banner.title"),
                            Component.translatable("advancements.astikorcartsredux.attach_banner.description"),
                            null,
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .addCriterion(
                            "attach_banner",
                            CartAddBannerCriterion.TriggerInstance.usedBanner(
                                    ItemPredicate.Builder.item().of(ItemTags.BANNERS)))
                    .save(consumer, AstikorCartsRedux.MODID + "/attach_banner");

            Advancement.Builder.advancement()
                    .parent(root)
                    .display(
                            AstikorCartsRedux.CARTS.get("reaper").get(WoodType.OAK),
                            Component.translatable("advancements.astikorcartsredux.reaper_harvest.title"),
                            Component.translatable("advancements.astikorcartsredux.reaper_harvest.description"),
                            null,
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .addCriterion(
                            "reaper_harvest",
                            ReaperHarvestCriterion.TriggerInstance.reaperHarvest(
                                    SimpleBlockPredicate.Builder.block().of(BlockTags.CROPS)))
                    .save(consumer, AstikorCartsRedux.MODID + "/reaper_harvest");

            Advancement.Builder.advancement()
                    .parent(root)
                    .display(
                            AstikorCartsRedux.CARTS.get("hand_cart").get(WoodType.OAK),
                            Component.translatable("advancements.astikorcartsredux.hand_cart_one_k.title"),
                            Component.translatable("advancements.astikorcartsredux.hand_cart_one_k.description"),
                            null,
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .addCriterion(
                            "hand_cart_one_k",
                            PullCartCriterion.TriggerInstance.pullCart(
                                    EntityTypePredicate.of(AstikorCartsRedux.HAND_CART_ENTITY.get()),
                                    1000_00,
                                    0))
                    .save(consumer, AstikorCartsRedux.MODID + "/hand_cart_one_k");

//            Advancement.Builder.advancement()
//                    .parent(root)
//                    .display(
//                            AstikorCartsRedux.CARTS.get("wagon").get(WoodType.OAK),
//                            Component.translatable("advancements.astikorcartsredux.wagon_cart_full.title"),
//                            Component.translatable("advancements.astikorcartsredux.wagon_cart_full.description"),
//                            null,
//                            AdvancementType.TASK,
//                            true,
//                            true,
//                            false
//                    )
//                    .addCriterion(
//                            "wagon_cart_full",
//                            PullCartCriterion.TriggerInstance.pullCart(
//                                    EntityTypePredicate.of(AstikorCartsRedux.WAGON_ENTITY.get()),
//                                    1,
//                                    4))
//                    .save(consumer, AstikorCartsRedux.MODID + "/wagon_cart_full");

            Advancement.Builder.advancement()
                    .parent(root)
                    .display(
                            AstikorCartsRedux.CARTS.get("animal_cart").get(WoodType.OAK),
                            Component.translatable("advancements.astikorcartsredux.animal_cart_steer.title"),
                            Component.translatable("advancements.astikorcartsredux.animal_cart_steer.description"),
                            null,
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .addCriterion(
                            "animal_cart_steer",
                            SteerCartCriterion.TriggerInstance.steerCart(
                                    EntityTypePredicate.of(AstikorCartsRedux.ANIMAL_CART_ENTITY.get()),
                                    1))
                    .save(consumer, AstikorCartsRedux.MODID + "/animal_cart_steer");

            Advancement.Builder.advancement()
                    .parent(root)
                    .display(
                            AstikorCartsRedux.CARTS.get("supply_cart").get(WoodType.OAK),
                            Component.translatable("advancements.astikorcartsredux.supply_cart_filled.title"),
                            Component.translatable("advancements.astikorcartsredux.supply_cart_filled.description"),
                            null,
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .addCriterion(
                            "supply_cart_filled",
                            PullCartCriterion.TriggerInstance.pullCartFill(
                                    EntityTypePredicate.of(AstikorCartsRedux.SUPPLY_CART_ENTITY.get()),
                                    1.0f))
                    .save(consumer, AstikorCartsRedux.MODID + "/supply_cart_filled");
        }
    }
}