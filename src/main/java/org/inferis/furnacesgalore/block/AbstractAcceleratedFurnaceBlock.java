package org.inferis.furnacesgalore.block;

import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.util.math.BlockPos;
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
}
