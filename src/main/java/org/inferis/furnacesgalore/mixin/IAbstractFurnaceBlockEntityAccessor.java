package org.inferis.furnacesgalore.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import it.unimi.dsi.fastutil.objects.Reference2IntOpenHashMap;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.recipe.Recipe;
import net.minecraft.registry.RegistryKey;

@Mixin(AbstractFurnaceBlockEntity.class)
public interface IAbstractFurnaceBlockEntityAccessor {
    @Accessor
    Reference2IntOpenHashMap<RegistryKey<Recipe<?>>> getRecipesUsed();
}
