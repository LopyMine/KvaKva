package net.lopymine.kvakva.mixin;

import net.lopymine.kvakva.manager.KvaKvaManager;
import net.minecraft.client.Keyboard;
import net.minecraft.client.input.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public class KeyboardMixin {

	//? if >=1.21.9 {
	/*@Inject(at = @At("HEAD"), method = "onChar")
	private void init(long window, CharInput input, CallbackInfo ci) {
		KvaKvaManager.input(input.codepoint());
	}
	*///?} else {
	@Inject(at = @At("HEAD"), method = "onChar")
	private void init(long window, int codePoint, int modifiers, CallbackInfo ci) {
		KvaKvaManager.input(codePoint);
	}
	//?}

}