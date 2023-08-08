package com.va11halla.casualness_delight.block;

import com.va11halla.casualness_delight.registry.BlockRegistry;
import com.va11halla.casualness_delight.tag.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import java.util.Iterator;

public class RawCheeseWheel extends Block{
    protected static final VoxelShape SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D);;
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
    public static IntegerProperty FERMENT = IntegerProperty.create("ferment", 0, 3);

    public RawCheeseWheel(Properties properties) {
        super(properties);
        this.registerDefaultState((BlockState)super.defaultBlockState().setValue(FERMENT, 0));
    }
    public boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{FERMENT});
        super.createBlockStateDefinition(builder);
    }
    public int getMaxCompostingStage() {
        return 3;
    }
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!level.isClientSide) {
            float chance = 0.0F;
            boolean hasWater = false;
            int maxLight = 0;
            Iterator<BlockPos> var8 = BlockPos.betweenClosed(pos.offset(-1, -1, -1), pos.offset(1, 1, 1)).iterator();

            while(var8.hasNext()) {
                BlockPos neighborPos = (BlockPos)var8.next();
                BlockState neighborState = level.getBlockState(neighborPos);
                if (neighborState.is(ModTags.FERMENT_ACTIVATORS)) {
                    chance += 0.02F;
                }
                int light = level.getBrightness(LightLayer.SKY, neighborPos.above());
                if (light > maxLight) {
                    maxLight = light;
                }
            }

            chance += maxLight < 9 ? 0.2F : 0.15F;
            if (level.getRandom().nextFloat() <= chance) {
                if ((Integer)state.getValue(FERMENT) == this.getMaxCompostingStage()) {
                level.setBlock(pos, ((Block) BlockRegistry.CheeseWheel.get()).defaultBlockState(), 3);
                } else {
                    level.setBlock(pos, (BlockState)state.setValue(FERMENT, (Integer)state.getValue(FERMENT) + 1), 3);
                }
            }

        }
    }

    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    public int getAnalogOutputSignal(BlockState blockState, Level level, BlockPos pos) {
        return this.getMaxCompostingStage() + 1 - (Integer)blockState.getValue(FERMENT);
    }
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        super.animateTick(state, level, pos, random);
        if (random.nextInt(10) == 0) {
            level.addParticle(ParticleTypes.MYCELIUM, (double)pos.getX() + (double)random.nextFloat(), (double)pos.getY() + 1.1, (double)pos.getZ() + (double)random.nextFloat(), 0.0, 0.0, 0.0);
        }
    }
}