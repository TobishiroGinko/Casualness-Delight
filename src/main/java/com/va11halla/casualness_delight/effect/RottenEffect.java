package com.va11halla.casualness_delight.effect;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.item.ItemStack;

public class RottenEffect extends MobEffect {
    public RottenEffect() {
        super(MobEffectCategory.HARMFUL, 0x6F4D1B);
    }

    // Adapted from Mob#isSunBurnTick
    @SuppressWarnings("deprecation")
    private boolean isBurnTick(Entity entity, RandomSource random) {
        if (entity.getCommandSenderWorld().isDay()) {
            float lightVal = entity.getLightLevelDependentMagicValue();
            return (
                lightVal > 0.5F &&
                random.nextFloat() * 30.0F < (lightVal - 0.4F) * 2.0F &&
                !(entity.isInWaterRainOrBubble() || entity.isInPowderSnow || entity.wasInPowderSnow) &&
                entity.getCommandSenderWorld().canSeeSky(BlockPos.containing(entity.getX(), entity.getEyeY(), entity.getZ()))
            );
        }
        return false;
    }

    // Adapted from Zombie#aiStep
    // Can be applied to any LivingEntity that isn't fire immune or a Zombie
    // amplifier ignored
    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (
            !entity.getCommandSenderWorld().isClientSide() &&
            entity.isAlive() &&
            entity.getHealth() > 0.0F &&
            !entity.fireImmune() &&
            !(entity instanceof Zombie)
        ) {
            RandomSource random = entity.getRandom();
            boolean sunSensitive = (entity instanceof Mob mob) ? mob.isSunBurnTick() : isBurnTick(entity, random);
            if (sunSensitive && entity.hasItemInSlot(EquipmentSlot.HEAD)) {
                ItemStack itemstack = entity.getItemBySlot(EquipmentSlot.HEAD);
                if (!itemstack.isEmpty()) {
                    if (itemstack.isDamageableItem()) {
                        itemstack.setDamageValue(itemstack.getDamageValue() + random.nextInt(2));
                        if (itemstack.getDamageValue() >= itemstack.getMaxDamage()) {
                            entity.broadcastBreakEvent(EquipmentSlot.HEAD);
                            entity.setItemSlot(EquipmentSlot.HEAD, ItemStack.EMPTY);
                        }
                    }
                    sunSensitive = false;
                }
            }
            if (sunSensitive) {
                entity.setSecondsOnFire(8);
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}