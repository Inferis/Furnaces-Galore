package org.inferis.furnacesgalore.screenhandler;

import org.inferis.furnacesgalore.item.CatalystItem;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

public class AugmentationSlot extends Slot {
    public AugmentationSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    } 
    
    @Override
    public boolean canInsert(ItemStack stack) {
        return super.canInsert(stack) && stack.getItem() instanceof CatalystItem;
    }

    @Override
    public int getMaxItemCount() {
        return 1;
    }
}
