package org.inferis.furnacesgalore.block.entity;

import org.inferis.furnacesgalore.FurnacesGalore;

import net.minecraft.block.BlockState;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class DiamondFurnaceBlockEntity extends AbstractAcceleratedFurnaceBlockEntity {
    public DiamondFurnaceBlockEntity(BlockPos pos, BlockState state) {
        super(FurnacesGaloreBlockEntityTypes.DIAMOND_FURNACE, pos, state);
    }

    @Override
    public double getCookTimeAcceleration() {
        return FurnacesGalore.CONFIG.diamondAcceleration;
    }

    @Override
    protected Text getContainerName() {
        return Text.translatable("container.furnaces-galore.diamond_furnace");
    }
}
