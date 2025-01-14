package org.inferis.furnacesgalore.block.entity;

import org.inferis.furnacesgalore.FurnacesGalore;

import net.minecraft.block.BlockState;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class NetheriteFurnaceBlockEntity extends AbstractAcceleratedFurnaceBlockEntity {
    public NetheriteFurnaceBlockEntity(BlockPos pos, BlockState state) {
        super(FurnacesGaloreBlockEntityTypes.NETHERITE_FURNACE, pos, state);
    }

    @Override
    public double getCookTimeAcceleration() {
        return FurnacesGalore.CONFIG.netheriteAcceleration;
    }

    @Override
    protected Text getContainerName() {
        return Text.translatable("container.furnaces-galore.netherite_furnace");
    }
}
