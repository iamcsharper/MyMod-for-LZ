package ru.XuPoH.shaders;

import net.minecraft.creativetab.CreativeTabs;

import org.apache.logging.log4j.Logger;

import ru.XuPoH.shaders.lib.Reference;
import ru.XuPoH.shaders.proxies.ClientProxy;
import ru.XuPoH.shaders.proxies.CommonProxy;
import ru.XuPoH.shaders.tabs.AnomalyCreativeTab;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(name = Reference.modName, modid = Reference.modID, version = Reference.modVersion)
public class ShadersMod {
	/**
	 * ќбъ€вл€ем прокси.
	 */
	@SidedProxy(clientSide="ru.XuPoH.shaders.proxies.ClientProxy", serverSide="ru.XuPoH.shaders.proxies.CommonProxy")
	public static CommonProxy proxy;
	public static ClientProxy clientProxy;
	
	public static CreativeTabs anomalyTab = new AnomalyCreativeTab(CreativeTabs.getNextID(), "anomalyTab"); 
	
	@Instance(Reference.modID)
	public static ShadersMod instance;
	
	public static Logger logger = FMLLog.getLogger();
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		ItemsBlocksCollection.initBlocks();
		ItemsBlocksCollection.initItems();
		ItemsBlocksCollection.initTiles();
		
		logger.info("PreInitialization...");
	}
 
	@Mod.EventHandler
	public void init(FMLInitializationEvent e) {
		logger.info("Initialization...");
		
		proxy.registerRenders();
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		logger.info("Applying other after initialization...");
	}
}