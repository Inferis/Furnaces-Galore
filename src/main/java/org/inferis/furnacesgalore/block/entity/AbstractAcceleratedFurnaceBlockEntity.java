package org.inferis.furnacesgalore.block.entity;

import org.inferis.furnacesgalore.item.CatalystItem;
import org.inferis.furnacesgalore.screenhandler.AcceleratedFurnaceScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.FuelRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

public abstract class AbstractAcceleratedFurnaceBlockEntity extends AbstractFurnaceBlockEntity {
    private AugmentsInventory augmentsInventory;
    
    protected AbstractAcceleratedFurnaceBlockEntity(BlockEntityType<?> blockEntityType, BlockPos pos, BlockState state) {
        super(blockEntityType, pos, state, RecipeType.SMELTING);
        this.augmentsInventory = new AugmentsInventory(() -> this.markDirty());
        this.inventory = DefaultedList.ofSize(4, ItemStack.EMPTY);
    }

    @Override
    protected int getFuelTime(FuelRegistry fuelRegistry, ItemStack stack) {
        if (!augmentsInventory.containsAny(s -> s.getItem() instanceof CatalystItem)) {
            return (int)((double)super.getFuelTime(fuelRegistry, stack) / (getCookTimeAcceleration() * 1.25));
        }
        return super.getFuelTime(fuelRegistry, stack);
    }

    public abstract double getCookTimeAcceleration();

    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new AcceleratedFurnaceScreenHandler(syncId, playerInventory, this, augmentsInventory, propertyDelegate);
    }

    @Override
    public void markDirty() {
        super.markDirty();
    }

    @Override
    protected void writeNbt(NbtCompound nbt, WrapperLookup registries) {
        super.writeNbt(nbt, registries);
        Inventories.writeNbt(nbt, augmentsInventory.getItems(), registries);
    }

    @Override
    protected void readNbt(NbtCompound nbt, WrapperLookup registries) {
        super.readNbt(nbt, registries);
        var inventory = AugmentsInventory.createInventory();
        Inventories.readNbt(nbt, inventory, registries);
        augmentsInventory.setItems(inventory);
      }
}
