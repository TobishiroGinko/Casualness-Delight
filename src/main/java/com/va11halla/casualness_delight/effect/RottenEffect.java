package com.va11halla.casualness_delight.effect;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public class RottenEffect extends StatusEffect {
    public RottenEffect() {
        super(StatusEffectCategory.HARMFUL, 0);
    }
    protected final Random random = Random.create();
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        boolean bl;
        if (!entity.getEntityWorld().isClient() && entity instanceof PlayerEntity player) {
            if (player.getWorld().isDay() && !player.getWorld().isClient) {
                float f = player.getBrightnessAtEyes();
                BlockPos blockPos = new BlockPos(player.getX(), player.getEyeY(), player.getZ());
                boolean bl2 = bl = player.isWet() || player.inPowderSnow || player.wasInPowderSnow;
                if (f > 0.5f && random.nextFloat() * 30.0f < (f - 0.4f) * 2.0f && !bl && player.getWorld().isSkyVisible(blockPos)) {
                    bl=true;
                }
            }
            else{
                bl = false;
            }
            if (bl) {
                ItemStack itemStack = player.getEquippedStack(EquipmentSlot.HEAD);
                if (!itemStack.isEmpty()) {
                    if (itemStack.isDamageable()) {
                        itemStack.setDamage(itemStack.getDamage() + random.nextInt(2));
                        if (itemStack.getDamage() >= itemStack.getMaxDamage()) {
                            player.sendEquipmentBreakStatus(EquipmentSlot.HEAD);
                            player.equipStack(EquipmentSlot.HEAD, ItemStack.EMPTY);
                        }
                    }
                    bl = false;
                }
                if (bl) {
                    player.setOnFireFor(8);
                }
            }
        }
    }

    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}

