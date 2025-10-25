package net.lopymine.kvakva.manager;

import net.lopymine.kvakva.KvaKva;
import net.lopymine.kvakva.config.KvaKvaConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.registry.*;
import net.minecraft.sound.*;
import net.minecraft.util.Identifier;

public class KvaKvaManager {

	private static final String[] LAST_CHARACTERS = new String[]{"", "", "", "", ""};
	private static SoundEvent KVAKVA_SOUND_EVENT;
	private static SoundEvent KVAKVA_PEACEFUL_SOUND_EVENT;

	public static void register() {
		Identifier soundId = KvaKva.id("kvakva");
		KVAKVA_SOUND_EVENT =  Registry.register(
				Registries.SOUND_EVENT,
				soundId,
				SoundEvent.of(soundId)
		);
		Identifier peacefulSoundId = KvaKva.id("kvakva_peaceful");
		KVAKVA_PEACEFUL_SOUND_EVENT =  Registry.register(
				Registries.SOUND_EVENT,
				peacefulSoundId,
				SoundEvent.of(peacefulSoundId)
		);
	}

	public static void input(int ch) {
		if (KVAKVA_SOUND_EVENT == null) {
			throw new IllegalArgumentException("KvaKva mod sound is not registered!");
		}

		KvaKvaConfig config = KvaKvaConfig.getInstance();

		String k0 = LAST_CHARACTERS[0];
		LAST_CHARACTERS[0] = String.valueOf((char) ch);

		String k1 = LAST_CHARACTERS[1];
		LAST_CHARACTERS[1] = k0;

		String k2 = LAST_CHARACTERS[2];
		LAST_CHARACTERS[2] = k1;

		String k3 = LAST_CHARACTERS[3];
		LAST_CHARACTERS[3] = k2;

		LAST_CHARACTERS[4] = k3;

		if (!config.isModEnabled()) {
			return;
		}
		if (config.isRussianVariantsEnabled()) {
			boolean bl = LAST_CHARACTERS[2].equalsIgnoreCase("к") &&
					LAST_CHARACTERS[1].equalsIgnoreCase("в") &&
					LAST_CHARACTERS[0].equalsIgnoreCase("а");
			boolean bl2 = LAST_CHARACTERS[2].equalsIgnoreCase("r") &&
					LAST_CHARACTERS[1].equalsIgnoreCase("d") &&
					LAST_CHARACTERS[0].equalsIgnoreCase("f");
			if (bl || bl2) {
				playRandomCroakingSound();
				return;
			}
		}
		if (config.isEnglishVariantsEnabled()) {
			boolean bl3 = LAST_CHARACTERS[2].equalsIgnoreCase("л") &&
					LAST_CHARACTERS[1].equalsIgnoreCase("м") &&
					LAST_CHARACTERS[0].equalsIgnoreCase("ф");
			boolean bl4 = LAST_CHARACTERS[2].equalsIgnoreCase("k") &&
					LAST_CHARACTERS[1].equalsIgnoreCase("v") &&
					LAST_CHARACTERS[0].equalsIgnoreCase("a");
			boolean bl5 = LAST_CHARACTERS[4].equalsIgnoreCase("c") &&
					LAST_CHARACTERS[3].equalsIgnoreCase("r") &&
					LAST_CHARACTERS[2].equalsIgnoreCase("o") &&
					LAST_CHARACTERS[1].equalsIgnoreCase("a") &&
					LAST_CHARACTERS[0].equalsIgnoreCase("k");
			boolean bl6 = LAST_CHARACTERS[4].equalsIgnoreCase("с") &&
					LAST_CHARACTERS[3].equalsIgnoreCase("к") &&
					LAST_CHARACTERS[2].equalsIgnoreCase("щ") &&
					LAST_CHARACTERS[1].equalsIgnoreCase("ф") &&
					LAST_CHARACTERS[0].equalsIgnoreCase("л");

			if (bl3 || bl4 || bl5 || bl6) {
				playRandomCroakingSound();
			}
		}
	}

	public static void playRandomCroakingSound() {
		if (KvaKvaConfig.getInstance().isPeacefulMode()) {
			MinecraftClient.getInstance().getSoundManager().play(PositionedSoundInstance.master(KVAKVA_PEACEFUL_SOUND_EVENT, 1.0F));
		} else {
			MinecraftClient.getInstance().getSoundManager().play(PositionedSoundInstance.master(KVAKVA_SOUND_EVENT, 1.0F));
		}
	}

}
