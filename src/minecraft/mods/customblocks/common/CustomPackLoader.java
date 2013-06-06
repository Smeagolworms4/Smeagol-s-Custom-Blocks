package mods.customblocks.common;

import java.io.File;
import java.util.ArrayList;
import java.util.ArrayList;

import net.minecraft.client.Minecraft;

public class CustomPackLoader {

	static String PATH_PACK = "custom";
	private File baseDir;
	private ArrayList<CustomPack> packs = new ArrayList<CustomPack>();
	
	
	private File _getBaseDir() {
		if (this.baseDir == null) {
			this.baseDir = new File(Minecraft.getMinecraftDir(), PATH_PACK);
		}
		return this.baseDir;
	}
	
	/**
	 * Changre tou les packs
	 */
	public void load() {
		
		File dir = this._getBaseDir();
		for(String path : dir.list()) {
			
			CustomPack pack = null;
			try {
				File packFiles = new File(dir, path);
				pack = CustomPackFactory.getByPath (packFiles);
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println ("Load \""+path+"\" pack is not possible");
			}
			
			if (pack != null) {
				this.packs.add (pack);
			}
		}
		
	}
	
	/**
	 * Crée la liste des éléments de tous les packs
	 */
	public void init() {
		for (CustomPack pack : packs) {
			pack.init ();
		}
	}
}
