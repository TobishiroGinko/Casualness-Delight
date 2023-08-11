package com.va11halla.casualness_delight.effect;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.level.GameRules;

public class RottenEffect extends MobEffect {
    public final RandomSource random = RandomSource.create();
    public RottenEffect() {
        super(MobEffectCategory.HARMFUL, 0);
    }

    public void applyEffectTick(LivingEntity entity, int amplifier) {
        boolean SunSensitive = false;
        if (!entity.getCommandSenderWorld().isClientSide && entity instanceof Player player) {
            if (player.level.isDay() && !player.level.isClientSide) {
                float f = player.getLightLevelDependentMagicValue();
                BlockPos blockpos = new BlockPos(player.getX(), player.getEyeY(), player.getZ());
                boolean flag = player.isInWaterRainOrBubble() || player.isInPowderSnow || player.wasInPowderSnow;
                if (f > 0.5F && random.nextFloat() * 30.0F < (f - 0.4F) * 2.0F && !flag && player.level.canSeeSky(blockpos)) {
                    SunSensitive = true;
                }
            }
            if(SunSensitive){
                player.setSecondsOnFire(8);
            }
        }
    }

    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
