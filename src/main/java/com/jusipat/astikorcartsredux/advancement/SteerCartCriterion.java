package com.jusipat.astikorcartsredux.advancement;

import com.jusipat.astikorcartsredux.entity.AbstractDrawnEntity;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.*;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class SteerCartCriterion extends SimpleCriterionTrigger<SteerCartCriterion.TriggerInstance> {

    @Override
    public @NotNull Codec<TriggerInstance> codec() {
        return TriggerInstance.CODEC;
    }

    public void trigger(ServerPlayer serverPlayer, AbstractDrawnEntity cart, float distance) {
        super.trigger(serverPlayer, t -> t.matches(cart, distance));
    }

    public record TriggerInstance(Optional<ContextAwarePredicate> player, Optional<EntityTypePredicate> type, Optional<Float> minDist) implements SimpleInstance {

        private static final Codec<TriggerInstance> CODEC = RecordCodecBuilder.create(
                instance -> instance.group(
                        EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(TriggerInstance::player),
                        EntityTypePredicate.CODEC.optionalFieldOf("type").forGetter(TriggerInstance::type),
                        Codec.FLOAT.optionalFieldOf("distance").forGetter(TriggerInstance::minDist)
                ).apply(instance, TriggerInstance::new));

        public static Criterion<TriggerInstance> steerCart() {
            return ACCriteriaTriggers.STEER_CART.get().createCriterion(new TriggerInstance(Optional.empty(), Optional.empty(), Optional.empty()));
        }

        public static Criterion<TriggerInstance> steerCart(EntityTypePredicate type, float minDist) {
            return ACCriteriaTriggers.STEER_CART.get().createCriterion(new TriggerInstance(Optional.empty(), Optional.of(type), Optional.of(minDist)));
        }

        public boolean matches(AbstractDrawnEntity cart, float distance) {
            return (this.minDist.isEmpty() || distance >= this.minDist.get()) && (this.type.isEmpty() || this.type.get().matches(cart.getType()));
        }
    }

}