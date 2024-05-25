package com.va11halla.casualness_delight.registry;

import com.va11halla.casualness_delight.CasualnessDelight;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ItemTab {
    public static final ItemGroup CasualnessDelightTab = Registry.register(Registries.ITEM_GROUP,
            new Identifier(CasualnessDelight.MODID, "casualness_delight"),
            FabricItemGroup.builder().displayName(Text.translatable("itemGroup.casualness_delight"))
                    .icon(() -> new ItemStack(ItemRegistry.StargazyPie.get()))
                    .entries((displayContext, entries) -> {
                        entries.add(ItemRegistry.FishAndChips.get());
                        entries.add(ItemRegistry.YorkshirePudding.get());
                        entries.add(ItemRegistry.BeefNoodles.get());
                        entries.add(ItemRegistry.QuicheLorraine.get());
                        entries.add(ItemRegistry.QuicheLorraineSlice.get());
                        entries.add(ItemRegistry.StargazyPie.get());
                        entries.add(ItemRegistry.StargazyPieSlice.get());
                        entries.add(ItemRegistry.RawCheeseWheel.get());
                        entries.add(ItemRegistry.CheeseWheel.get());
                        entries.add(ItemRegistry.CheeseWheelSlice.get());
                        entries.add(ItemRegistry.PhantomDumplings.get());
                        entries.add(ItemRegistry.PhantomPuff.get());
                        entries.add(ItemRegistry.SpicyStrips.get());
                        entries.add(ItemRegistry.GreenTongue.get());
                        entries.add(ItemRegistry.RawGluten.get());
                        entries.add(ItemRegistry.Gluten.get());
                        entries.add(ItemRegistry.GlutenSkewer.get());
                        entries.add(ItemRegistry.RoastGluten.get());
                        entries.add(ItemRegistry.RawDonkeyMeat.get());
                        entries.add(ItemRegistry.CookedDonkeyMeat.get());
                        entries.add(ItemRegistry.DonkeyBurger.get());
                        entries.add(ItemRegistry.PaperWrappedFish.get());
                        entries.add(ItemRegistry.BowlOfPaperWrappedFish.get());
                        entries.add(ItemRegistry.BoboChicken.get());
                        entries.add(ItemRegistry.PotatoBoboChicken.get());
                        entries.add(ItemRegistry.CabbageBoboChicken.get());
                        entries.add(ItemRegistry.ChickenBoboChicken.get());
                        entries.add(ItemRegistry.RawPotatoBoboChicken.get());
                        entries.add(ItemRegistry.RawCabbageBoboChicken.get());
                        entries.add(ItemRegistry.RawChickenBoboChicken.get());
                        entries.add(ItemRegistry.DeepFryingPan.get());
                        entries.add(ItemRegistry.PotatoSlice.get());
                        entries.add(ItemRegistry.PotatoChip.get());
                        entries.add(ItemRegistry.RawSpringRoll.get());
                        entries.add(ItemRegistry.SpringRoll.get());
                        entries.add(ItemRegistry.SpringRollMedley.get());
                        entries.add(ItemRegistry.FriedFish.get());
                        entries.add(ItemRegistry.FriedChickenChip.get());
                        entries.add(ItemRegistry.Tonkatsu.get());
                        entries.add(ItemRegistry.RawFriedDumpling.get());
                        entries.add(ItemRegistry.FriedDumpling.get());
                        entries.add(ItemRegistry.PlateOFFriedDumpling.get());
                        entries.add(ItemRegistry.BowlOfFriedDumpling.get());
                        entries.add(ItemRegistry.SweetRice.get());
                        entries.add(ItemRegistry.BowlOfSweetRice.get());
                    }).build());

    public static void register() {
        CasualnessDelight.LOGGER.info("Registering Item Group for " + CasualnessDelight.MODID);
    }
}
