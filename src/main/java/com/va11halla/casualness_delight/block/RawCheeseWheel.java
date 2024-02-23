package com.va11halla.casualness_delight.block;

import java.util.Iterator;

import com.va11halla.casualness_delight.registry.BlocksRegistry;
import com.va11halla.casualness_delight.registry.TagsRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

public class RawCheeseWheel extends Block {
    protected static final VoxelShape SHAPE = Block.createCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D);
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }
    public static final IntProperty FERMENT = IntProperty.of("ferment", 0, 3);

    public RawCheeseWheel() {
        super(FabricBlockSettings.copyOf(Blocks.CAKE).sounds(BlockSoundGroup.WOOL));
        this.setDefaultState((BlockState)((BlockState)this.getStateManager().getDefaultState()).with(FERMENT, 0));
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(new Property[]{FERMENT});
    }

    public boolean hasRandomTicks(BlockState state) {
        return true;
    }

    @Environment(EnvType.CLIENT)
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        super.randomDisplayTick(state, world, pos, random);
        if (random.nextInt(10) == 0) {
            world.addParticle(ParticleTypes.MYCELIUM, (double)pos.getX() + (double)random.nextFloat(), (double)pos.getY() + 1.1, (double)pos.getZ() + (double)random.nextFloat(), 0.0, 0.0, 0.0);
        }

    }

    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!world.isClient()) {
            float chance = 0.0F;
            boolean hasWater = false;
            int maxLight = 0;
            Iterator var8 = BlockPos.iterate(pos.add(-1, -1, -1), pos.add(1, 1, 1)).iterator();

            while(var8.hasNext()) {
                BlockPos neighborPos = (BlockPos)var8.next();
                BlockState neighborState = world.getBlockState(neighborPos);
                if (neighborState.isIn(TagsRegistry.FERMENT_ACTIVATORS)) {
                    chance += 0.02F;
                }
                int light = world.getLightLevel(LightType.SKY, neighborPos.up());
                if (light > maxLight) {
                    maxLight = light;
                }
            }
            chance += maxLight < 9 ? 0.2F : 0.15F;
            if (world.getRandom().nextFloat() <= chance) {
                if ((Integer)state.get(FERMENT) == 3) {
                    world.setBlockState(pos, BlocksRegistry.CheeseWheel.get().getDefaultState(), 2);
                } else {
                    world.setBlockState(pos, (BlockState)state.with(FERMENT, (Integer)state.get(FERMENT) + 1), 2);
                }
            }

        }
    }

    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return 8 - (Integer)state.get(FERMENT);
    }
}
