package io.github.aorlov05.firstforgemod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {

    // Consuming a turnip fills 3 half hunger bars, gives saturation, and a 25% chance to get Speed 1 for 10 seconds
    public static final FoodProperties TURNIP = new FoodProperties.Builder()
            .nutrition(3)
            .saturationMod(0.6F)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200), 0.25F)
            .build();

}
