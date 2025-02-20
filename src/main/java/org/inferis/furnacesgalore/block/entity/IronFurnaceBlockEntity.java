package org.inferis.furnacesgalore.block.entity;

import org.inferis.furnacesgalore.FurnacesGalore;

import net.minecraft.block.BlockState;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class IronFurnaceBlockEntity extends AbstractAcceleratedFurnaceBlockEntity {
    public IronFurnaceBlockEntity(BlockPos pos, BlockState state) {
        super(FurnacesGaloreBlockEntityTypes.IRON_FURNACE, pos, state);
    }

    @Override
    public double getCookTimeAcceleration() {
        return FurnacesGalore.CONFIG.ironAcceleration;
    }

    @Override
    protected Text getContainerName() {
        return Text.translatable("container.furnaces-galore.iron_furnace");
    }
}
