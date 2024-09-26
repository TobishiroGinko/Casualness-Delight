package com.va11halla.casualness_delight.effect;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class RottenEffect extends MobEffect {
    public final RandomSource random = RandomSource.create();
    public RottenEffect() {
        super(MobEffectCategory.HARMFUL, 0);
    }

    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        boolean SunSensitive = false;
        if (entity instanceof Player player) {
            if (player.isAlive()) {
                if (player.level().isDay() && !player.level().isClientSide) {
                    float f = player.getLightLevelDependentMagicValue();
                    BlockPos blockpos = BlockPos.containing(player.getX(), player.getEyeY(), player.getZ());
                    boolean flag = player.isInWaterRainOrBubble() || player.isInPowderSnow || player.wasInPowderSnow;
                    if (f > 0.5F && this.random.nextFloat() * 30.0F < (f - 0.4F) * 2.0F && !flag && player.level().canSeeSky(blockpos)) {
                        SunSensitive = true;
                    }
                }
            }
            boolean flags = SunSensitive;
            if (flags) {
                ItemStack itemstack = player.getItemBySlot(EquipmentSlot.HEAD);
                if (!itemstack.isEmpty()) {
                    if (itemstack.isDamageableItem()) {
                        Item item = itemstack.getItem();
                        itemstack.setDamageValue(itemstack.getDamageValue() + this.random.nextInt(2));
                        if (itemstack.getDamageValue() >= itemstack.getMaxDamage()) {
                            player.onEquippedItemBroken(item, EquipmentSlot.HEAD);;
                            player.setItemSlot(EquipmentSlot.HEAD, ItemStack.EMPTY);
                        }
                    }

                    flags = false;
                }

                if (flags) {
                    player.igniteForSeconds(8.0F);
                }
            }
        }
        return true;
    }
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}