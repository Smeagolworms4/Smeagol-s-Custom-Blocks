package mods.customblocks.common;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import net.minecraftforge.common.Configuration;
import net.minecraft.tileentity.TileEntity;// any;

@Mod(modid = "CustomBlocksCommon", name = "Smeagol's Custom Blocks Common", version = "1.0 for 1.5.2")
@NetworkMod(clientSideRequired = true, serverSideRequired = true)
public class ModCustomBlocks {

	@Instance("ModCustomBlocks")
	public static ModCustomBlocks instance;
	
	@SidedProxy(clientSide = "mods.customblocks.common.ClientProxyCustomBlocks", serverSide = "mods.customblocks.common.CommonProxyCustomBlocks")
	public static CommonProxyCustomBlocks proxy;
	
	/** 1 **/
	@Mod.PreInit
	public void preInit(FMLPreInitializationEvent event) {
		CustomPackLoader loadder = new CustomPackLoader ();
		loadder.load ();
	}
	
	/** 2 **/
	@Init
	public void load(FMLInitializationEvent event) {
	}

	/** 3 **/
	@PostInit
	public void postInit(FMLPostInitializationEvent event) {

	}
	
}
