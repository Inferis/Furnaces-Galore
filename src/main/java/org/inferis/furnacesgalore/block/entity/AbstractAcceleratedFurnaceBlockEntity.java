package org.inferis.furnacesgalore.block.entity;

import java.util.List;

import org.inferis.furnacesgalore.item.CatalystItem;
import org.inferis.furnacesgalore.item.ExperienceDoublerItem;
import org.inferis.furnacesgalore.screenhandler.AcceleratedFurnaceScreenHandler;

import com.google.common.collect.Lists;

import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.Reference2IntMap;
import it.unimi.dsi.fastutil.objects.Reference2IntOpenHashMap;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.FuelRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

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

        NbtCompound furnaceGaloreNbt = new NbtCompound();
        Inventories.writeNbt(furnaceGaloreNbt, augmentsInventory.getItems(), registries);
        nbt.put("furnace_galore", furnaceGaloreNbt);
    }

    @Override
    protected void readNbt(NbtCompound nbt, WrapperLookup registries) {
        super.readNbt(nbt, registries);

        if (nbt.contains("furnace_galore")) {
            NbtCompound furnaceGaloreNbt = nbt.getCompound("furnace_galore");
            var inventory = AugmentsInventory.createInventory();
            Inventories.readNbt(furnaceGaloreNbt, inventory, registries);
            augmentsInventory.setItems(inventory);
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List<RecipeEntry<?>> getRecipesUsedAndDropExperience(ServerWorld world, Vec3d pos) {
        List<RecipeEntry<?>> list = Lists.newArrayList();
        Class<?> entityClass = AbstractFurnaceBlockEntity.class;
        ObjectIterator iterator = null;
        try {
            var field = entityClass.getDeclaredField("recipesUsed");
            field.setAccessible(true);
            var recipesUsed = (Reference2IntOpenHashMap<RegistryKey<Recipe<?>>>)field.get(this);
            iterator = recipesUsed.reference2IntEntrySet().iterator();
        } catch (Exception e) {
            iterator = null;
        }

        if (iterator != null) {
            while (iterator.hasNext()) {
                Reference2IntMap.Entry<RegistryKey<Recipe<?>>> entry = (Reference2IntMap.Entry)iterator.next();
                world.getRecipeManager().get((RegistryKey)entry.getKey()).ifPresent(r -> {
                    var recipe = (RecipeEntry<?>)r;
                    list.add(recipe);
                    dropExperience(world, pos, this, entry.getIntValue(), ((AbstractCookingRecipe)recipe.value()).getExperience());
                });
            }
        }

        return list;
    }

    private static void dropExperience(ServerWorld world, Vec3d pos, AbstractAcceleratedFurnaceBlockEntity entity, int multiplier, float experience) {
        int droppedExperience = MathHelper.floor((float)multiplier * experience);
        float f = MathHelper.fractionalPart((float)multiplier * experience);
        if (f != 0.0F && Math.random() < (double)f) {
            ++droppedExperience;
        }

        if (entity.augmentsInventory.containsAny(s -> s.getItem() instanceof ExperienceDoublerItem)) {
            droppedExperience *= 1.5 + Math.random(); // between x1.5 and x3.
        }

        ExperienceOrbEntity.spawn(world, pos, droppedExperience);
    }
}
