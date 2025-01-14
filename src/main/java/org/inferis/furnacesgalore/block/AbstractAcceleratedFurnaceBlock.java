package org.inferis.furnacesgalore.block;

import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Direction.Axis;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import org.inferis.furnacesgalore.block.entity.AbstractAcceleratedFurnaceBlockEntity;

public abstract class AbstractAcceleratedFurnaceBlock extends AbstractFurnaceBlock {
    protected AbstractAcceleratedFurnaceBlock(Settings settings) {
        super(settings);
    }

    protected abstract BlockEntityType<? extends AbstractFurnaceBlockEntity> getTickerEntityType();

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state,
            BlockEntityType<T> type) {
        return validateTicker(world, type, getTickerEntityType());
    }
                
    @Override
    protected void openScreen(World world, BlockPos pos, PlayerEntity player) {
        if (world.getBlockEntity(pos) instanceof AbstractAcceleratedFurnaceBlockEntity acceleratedfurnaceBlockEntity) {
            player.openHandledScreen(acceleratedfurnaceBlockEntity);
            player.incrementStat(Stats.INTERACT_WITH_FURNACE);
        }
    }

    // Taken from FurnaceBlock
    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (!state.get(LIT)) {
            return;
        }

        double x = (double)pos.getX() + 0.5;
        double y = (double)pos.getY();
        double z = (double)pos.getZ() + 0.5;
        if (random.nextDouble() < 0.1) {
            world.playSound(x, y, z, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
        }

        Direction direction = (Direction)state.get(FACING);
        Direction.Axis axis = direction.getAxis();
        double g = 0.52;
        double h = random.nextDouble() * 0.6 - 0.3;
        double dx = axis == Axis.X ? (double)direction.getOffsetX() * 0.52 : h;
        double dy = random.nextDouble() * 6.0 / 16.0;
        double dz = axis == Axis.Z ? (double)direction.getOffsetZ() * 0.52 : h;
        world.addParticle(ParticleTypes.SMOKE, x + dx, y + dy, z + dz, 0.0, 0.0, 0.0);
        world.addParticle(ParticleTypes.FLAME, x + dx, y + dy, z + dz, 0.0, 0.0, 0.0);
    }
}
