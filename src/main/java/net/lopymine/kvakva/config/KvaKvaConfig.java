package net.lopymine.kvakva.config;

import lombok.*;
import net.lopymine.mossylib.utils.*;
import org.slf4j.*;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fabricmc.loader.api.FabricLoader;

import net.lopymine.kvakva.KvaKva;

import java.io.*;
import java.util.concurrent.CompletableFuture;

import static net.lopymine.mossylib.utils.CodecUtils.option;

@Getter
@Setter
@AllArgsConstructor
public class KvaKvaConfig {

	public static final Codec<KvaKvaConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
			option("mod_enabled", true, Codec.BOOL, KvaKvaConfig::isModEnabled),
			option("russian_variants_enabled", true, Codec.BOOL, KvaKvaConfig::isRussianVariantsEnabled),
			option("english_variants_enabled", false, Codec.BOOL, KvaKvaConfig::isEnglishVariantsEnabled),
			option("peaceful_mode", false, Codec.BOOL, KvaKvaConfig::isPeacefulMode)
	).apply(instance, KvaKvaConfig::new));

	private static final File CONFIG_FILE = FabricLoader.getInstance().getConfigDir().resolve(KvaKva.MOD_ID + ".json5").toFile();
	private static final Logger LOGGER = LoggerFactory.getLogger(KvaKva.MOD_NAME + "/Config");
	private static KvaKvaConfig INSTANCE;
	
	private boolean modEnabled;
	private boolean russianVariantsEnabled;
	private boolean englishVariantsEnabled;
	private boolean peacefulMode;

	@SuppressWarnings("unused")
	private KvaKvaConfig() {
		throw new IllegalArgumentException();
	}

	public static KvaKvaConfig getInstance() {
		return INSTANCE == null ? reload() : INSTANCE;
	}

	public static KvaKvaConfig reload() {
		return INSTANCE = KvaKvaConfig.read();
	}

	public static KvaKvaConfig getNewInstance() {
		return CodecUtils.parseNewInstanceHacky(CODEC);
	}

	private static KvaKvaConfig read() {
		return ConfigUtils.readConfig(CODEC, CONFIG_FILE, LOGGER);
	}

	public void saveAsync() {
		CompletableFuture.runAsync(this::save);
	}

	public void save() {
		ConfigUtils.saveConfig(this, CODEC, CONFIG_FILE, LOGGER);
	}
}
