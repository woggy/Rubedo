package rubedo;

import rubedo.integration.atg.ATGIntegration;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = RubedoIntegration.modid, name = RubedoIntegration.name, version = RubedoIntegration.version, dependencies = "after:ATG; after:enhancedbiomes;")
public class RubedoIntegration {
	public static final String modid = "rubedoIntegration";
	public static final String name = "@NAME@";
	public static final String version = "@VERSION@";

	// The instance of your mod that Forge uses.
	@Instance(value = "rubedoIntegration")
	public static RubedoIntegration instance;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		if (Loader.isModLoaded("ATG")) {
			ATGIntegration.preInit();
		}
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		if (Loader.isModLoaded("ATG"))
			ATGIntegration.init();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		if (Loader.isModLoaded("ATG"))
			ATGIntegration.postInit();
	}
}
