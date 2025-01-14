package org.inferis.furnacesgalore.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;

import org.inferis.furnacesgalore.block.entity.FurnacesGaloreBlockEntityTypes;
import org.inferis.furnacesgalore.block.entity.NetheriteFurnaceBlockEntity;

import com.mojang.serialization.MapCodec;

public class NetheriteFurnaceBlock extends AbstractAcceleratedFurnaceBlock {
    public static final MapCodec<NetheriteFurnaceBlock> CODEC = createCodec(NetheriteFurnaceBlock::new);

    public NetheriteFurnaceBlock(Settings settings) {
        super(settings);
    }

    @Override
    public MapCodec<NetheriteFurnaceBlock> getCodec() {
       return CODEC;
    }

    protected BlockEntityType<? extends AbstractFurnaceBlockEntity> getTickerEntityType() {
        return FurnacesGaloreBlockEntityTypes.NETHERITE_FURNACE; 
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new NetheriteFurnaceBlockEntity(pos, state);
    }
}
