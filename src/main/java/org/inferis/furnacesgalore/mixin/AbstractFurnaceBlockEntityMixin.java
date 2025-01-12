package org.inferis.furnacesgalore.mixin;

import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.server.world.ServerWorld;

import org.inferis.furnacesgalore.block.entity.AbstractAcceleratedFurnaceBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractFurnaceBlockEntity.class)
public class AbstractFurnaceBlockEntityMixin {
	@Inject(at = @At("RETURN"), method = "getCookTime(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/block/entity/AbstractFurnaceBlockEntity;)I", cancellable = true)
	private static void getCookTime(ServerWorld world, AbstractFurnaceBlockEntity furnace, CallbackInfoReturnable<Integer> info) {
		if (furnace instanceof AbstractAcceleratedFurnaceBlockEntity acceleratedFurnace) {
			var cookTime = (int)((double)info.getReturnValue() / acceleratedFurnace.getCookTimeAcceleration());
			if (cookTime < 2) {
				cookTime = 2;
			}
			info.setReturnValue(cookTime);
		}
	}
}