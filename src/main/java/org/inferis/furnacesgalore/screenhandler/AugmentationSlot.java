package org.inferis.furnacesgalore.screenhandler;

import org.inferis.furnacesgalore.item.CatalystItem;
import org.inferis.furnacesgalore.item.ExperienceDoublerItem;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

public class AugmentationSlot extends Slot {
    public AugmentationSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    } 
    
    @Override
    public boolean canInsert(ItemStack stack) {
        var item = stack.getItem();
        return super.canInsert(stack) && (item instanceof CatalystItem || item instanceof ExperienceDoublerItem);
    }

    @Override
    public int getMaxItemCount() {
        return 1;
    }
}
