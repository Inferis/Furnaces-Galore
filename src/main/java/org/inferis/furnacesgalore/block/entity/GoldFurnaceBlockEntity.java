package org.inferis.furnacesgalore.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class GoldFurnaceBlockEntity extends AbstractAcceleratedFurnaceBlockEntity {
    public GoldFurnaceBlockEntity(BlockPos pos, BlockState state) {
        super(FurnacesGaloreBlockEntityTypes.GOLD_FURNACE, pos, state);
    }

    @Override
    public double getCookTimeAcceleration() {
        return 1.5;
    }

    @Override
    protected Text getContainerName() {
        return Text.translatable("container.furnaces-galore.gold_furnace");
    }
}
