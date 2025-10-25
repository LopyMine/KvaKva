package net.lopymine.kvakva.yacl;

import lombok.experimental.ExtensionMethod;
import net.lopymine.kvakva.KvaKva;
import net.lopymine.kvakva.manager.KvaKvaManager;
import net.lopymine.mossylib.yacl.api.*;
import net.lopymine.mossylib.yacl.extension.SimpleOptionExtension;
import net.minecraft.client.gui.screen.Screen;

import net.lopymine.kvakva.config.KvaKvaConfig;

@ExtensionMethod(SimpleOptionExtension.class)
public class YACLConfigurationScreen {

	private YACLConfigurationScreen() {
		throw new IllegalStateException("Screen class");
	}

	public static Screen createScreen(Screen parent) {
		KvaKvaConfig defConfig = KvaKvaConfig.getNewInstance();
		KvaKvaConfig config = KvaKvaConfig.getInstance();

		return SimpleYACLScreen.startBuilder(KvaKva.MOD_ID, parent, KvaKvaYACLScreen::new, config::saveAsync)
				.categories(getGeneralCategory(defConfig, config))
				.build();
	}

	private static SimpleCategory getGeneralCategory(KvaKvaConfig defConfig, KvaKvaConfig config) {
		return SimpleCategory.startBuilder("general")
				.groups(getMainGroup(defConfig, config));
	}

	private static SimpleGroup getMainGroup(KvaKvaConfig defConfig, KvaKvaConfig config) {
		return SimpleGroup.startBuilder("main").options(
				SimpleOption.<Boolean>startBuilder("mod_enabled")
						.withBinding(defConfig.isModEnabled(), config::isModEnabled, config::setModEnabled, true)
						.withController()
						.withDescription(SimpleContent.NONE),
				SimpleOption.<Boolean>startBuilder("russian_variants_enabled")
						.withBinding(defConfig.isRussianVariantsEnabled(), config::isRussianVariantsEnabled, config::setRussianVariantsEnabled, true)
						.withController()
						.withDescription(SimpleContent.NONE),
				SimpleOption.<Boolean>startBuilder("english_variants_enabled")
						.withBinding(defConfig.isEnglishVariantsEnabled(), config::isEnglishVariantsEnabled, config::setEnglishVariantsEnabled, true)
						.withController()
						.withDescription(SimpleContent.NONE),
				SimpleOption.<Boolean>startBuilder("peaceful_mode")
						.withBinding(defConfig.isPeacefulMode(), config::isPeacefulMode, config::setPeacefulMode, true)
						.withController()
						.withDescription(SimpleContent.NONE)
		);
	}

}


