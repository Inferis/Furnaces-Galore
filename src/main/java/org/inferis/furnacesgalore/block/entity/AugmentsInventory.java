package org.inferis.furnacesgalore.block.entity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;;

public class AugmentsInventory implements Inventory {
    private DefaultedList<ItemStack> inventory = createInventory();
    private IDirtyMarker marker;
        
    public interface IDirtyMarker {
        void markDirty();
    }
    
    public AugmentsInventory() {
        this(null);
    }

    public AugmentsInventory(IDirtyMarker marker) {
        this.marker = marker;
    }

    public static DefaultedList<ItemStack> createInventory() {
        return DefaultedList.ofSize(1, ItemStack.EMPTY);
    }

    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    public void setItems(DefaultedList<ItemStack> inventory) {
        this.inventory = inventory;
    }

    @Override
    public void clear() {
        inventory.clear();
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return true;
    }

    @Override
    public ItemStack getStack(int slot) {
        return inventory.get(slot);
    }

    @Override
    public boolean isEmpty() {
        return inventory.isEmpty();
    }

    @Override
    public void markDirty() {
        if (marker != null) {
            marker.markDirty();
        }
    }

    @Override
    public ItemStack removeStack(int slot) {
        return Inventories.removeStack(inventory, slot);
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        ItemStack result = Inventories.splitStack(inventory, slot, amount);
        if (!result.isEmpty()) {
            markDirty();
        }
        return result;
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        inventory.set(slot, stack);
        if (stack.getCount() > stack.getMaxCount()) {
            stack.setCount(stack.getMaxCount());
        }   
    }

    @Override
    public int size() {
        return inventory.size();
    }
}
