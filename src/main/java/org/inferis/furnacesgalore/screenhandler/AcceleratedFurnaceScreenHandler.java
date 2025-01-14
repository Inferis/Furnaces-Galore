package org.inferis.furnacesgalore.screenhandler;

import org.inferis.furnacesgalore.block.entity.AugmentsInventory;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipePropertySet;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.book.RecipeBookType;
import net.minecraft.screen.AbstractFurnaceScreenHandler;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.slot.Slot;

public class AcceleratedFurnaceScreenHandler extends AbstractFurnaceScreenHandler {
   private Inventory augmentInventory;

   public AcceleratedFurnaceScreenHandler(int syncId, PlayerInventory playerInventory) {
      this(syncId, playerInventory, new SimpleInventory(3), new AugmentsInventory(), new ArrayPropertyDelegate(4));
   }

   public AcceleratedFurnaceScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, Inventory augmentInventory, PropertyDelegate propertyDelegate) {
      super(FurnacesGaloreScreenHandlers.ACCELERATED_FURNACE, RecipeType.SMELTING, RecipePropertySet.FURNACE_INPUT, RecipeBookType.FURNACE, syncId, playerInventory, inventory, propertyDelegate);
      this.augmentInventory = augmentInventory;
      addSlot(new AugmentationSlot(augmentInventory, 0, 174, 6));
   }  

   @Override
   public ItemStack quickMove(PlayerEntity player, int slot) {
      ItemStack itemStack = ItemStack.EMPTY;
      if (this.slots.get(slot) instanceof Slot slotInstance) {
         if (slotInstance.hasStack()) {
            ItemStack itemStack2 = slotInstance.getStack();
            if (slot != 39) {
               itemStack = itemStack2.copy();
               if (insertItem(itemStack2, 39, 40, false)) {
                  augmentInventory.markDirty();
               } 
               else {
                  return super.quickMove(player, slot);
               }
               return itemStack;
            }
            else if (this.insertItem(itemStack2, 3, 30, false)) {
               augmentInventory.markDirty();
            }
            else {
               return ItemStack.EMPTY;
            }
         }
      }

      return super.quickMove(player, slot);
   }
}
