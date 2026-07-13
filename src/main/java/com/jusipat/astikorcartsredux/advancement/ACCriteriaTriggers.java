package com.jusipat.astikorcartsredux.advancement;

import com.jusipat.astikorcartsredux.AstikorCartsRedux;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.registries.Registries;
import net.minecraftforge.registries.DeferredRegister;

public class ACCriteriaTriggers {

    public static final DeferredRegister<CriterionTrigger<?>> TRIGGERS =
            DeferredRegister.create(Registries.TRIGGER_TYPE, AstikorCartsRedux.MODID);

    public static final DeferredHolder<CriterionTrigger<?>, CartAddBannerCriterion> CART_ADD_BANNER =
            TRIGGERS.register(
                    "cart_add_banner",
                    CartAddBannerCriterion::new
            );

    public static final DeferredHolder<CriterionTrigger<?>, PlaceCartItemCriterion> PLACE_CART_ITEM =
            TRIGGERS.register(
                    "place_cart_item",
                    PlaceCartItemCriterion::new
            );

    public static final DeferredHolder<CriterionTrigger<?>, PullCartCriterion> PULL_CART =
            TRIGGERS.register(
                    "pull_cart",
                    PullCartCriterion::new
            );

    public static final DeferredHolder<CriterionTrigger<?>, ReaperHarvestCriterion> REAPER_HARVEST =
            TRIGGERS.register(
                    "reaper_harvest",
                    ReaperHarvestCriterion::new
            );

    public static final DeferredHolder<CriterionTrigger<?>, SeedDrillPlaceCriterion> SEED_DRILL_PLACE =
            TRIGGERS.register(
                    "seed_drill_place",
                    SeedDrillPlaceCriterion::new
            );

    public static final DeferredHolder<CriterionTrigger<?>, SteerCartCriterion> STEER_CART =
            TRIGGERS.register(
                    "steer_cart",
                    SteerCartCriterion::new
            );

    public static final DeferredHolder<CriterionTrigger<?>, UsePlowCriterion> USE_PLOW =
            TRIGGERS.register(
                    "use_plow",
                    UsePlowCriterion::new
            );
}