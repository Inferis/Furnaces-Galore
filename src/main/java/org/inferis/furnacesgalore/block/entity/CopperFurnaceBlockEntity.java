package org.inferis.furnacesgalore.block.entity;

import org.inferis.furnacesgalore.FurnacesGalore;

import net.minecraft.block.BlockState;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class CopperFurnaceBlockEntity extends AbstractAcceleratedFurnaceBlockEntity {
    public CopperFurnaceBlockEntity(BlockPos pos, BlockState state) {
        super(FurnacesGaloreBlockEntityTypes.COPPER_FURNACE, pos, state);
    }

    @Override
    public double getCookTimeAcceleration() {
        return FurnacesGalore.CONFIG.copperAcceleration;
    }

    @Override
    protected Text getContainerName() {
        return Text.translatable("container.furnaces-galore.copper_furnace");
    }
}
