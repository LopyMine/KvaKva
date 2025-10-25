package net.lopymine.kvakva.client;

import net.lopymine.kvakva.KvaKva;
import net.lopymine.kvakva.manager.KvaKvaManager;
import net.lopymine.mossylib.logger.MossyLogger;

import net.fabricmc.api.ClientModInitializer;

public class KvaKvaClient implements ClientModInitializer {

	public static MossyLogger LOGGER = KvaKva.LOGGER.extend("Client");

	@Override
	public void onInitializeClient() {
		LOGGER.info("{} Client Initialized", KvaKva.MOD_NAME);
		KvaKvaManager.register();
	}

}
