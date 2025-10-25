package net.lopymine.kvakva.mixin;

import net.lopymine.kvakva.manager.KvaKvaManager;
import net.lopymine.kvakva.yacl.KvaKvaYACLScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.*;
import net.minecraft.sound.SoundEvents;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

//? if >=1.21.6 {
import net.minecraft.client.sound.SoundSystem.PlayResult;
//?}

@Mixin(SoundManager.class)
public class SoundManagerMixin {

	//? if >=1.21.6 {
	@Inject(at = @At(value = "HEAD"), method = "play(Lnet/minecraft/client/sound/SoundInstance;)Lnet/minecraft/client/sound/SoundSystem$PlayResult;", cancellable = true)
	private void swapSound(SoundInstance instance, CallbackInfoReturnable<PlayResult> cir) {
		this.swapToCroakSound(instance, () -> cir.setReturnValue(PlayResult.STARTED));
	}
	//?} else {
	/*@Inject(at = @At(value = "HEAD"), method = "play(Lnet/minecraft/client/sound/SoundInstance;)V", cancellable = true)
	private void swapSound(SoundInstance instance, CallbackInfo ci) {
		this.swapToCroakSound(instance, () -> ci.cancel());
	}
	*///?}

	@Unique
	private void swapToCroakSound(SoundInstance instance, Runnable cancel) {
		if (!(MinecraftClient.getInstance().currentScreen instanceof KvaKvaYACLScreen)) {
			return;
		}
		if (instance == null || !SoundEvents.UI_BUTTON_CLICK.value()./*? if >=1.21.2 {*/ id() /*?} else {*//*getId()*//*?}*/.equals(instance.getId())) {
			return;
		}
		KvaKvaManager.playRandomCroakingSound();
		cancel.run();
	}

}
