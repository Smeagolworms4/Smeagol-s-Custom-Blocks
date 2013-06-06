package mods.customblocks.common;

import java.io.File;
import java.util.List;

import net.minecraft.client.Minecraft;

public class CustomPackLoader {

	static String PATH_PACK = "custom";
	private File baseDir;
	private List<CustomPack> packs;

	private File _getBaseDir() {
		if (this.baseDir == null) {
			this.baseDir = new File(Minecraft.getMinecraftDir(), PATH_PACK);
		}
		return this.baseDir;
	}

	public void load() {
		
		File dir = this._getBaseDir();
		for(String path : dir.list()) {
			
			CustomPack pack = null;
			try {
				File packFiles = new File(dir, path);
				pack = CustomPackFactory.getByPath (packFiles);
				
			} catch (Exception e) {
				System.out.println (e.getMessage());
				System.out.println ("Load \""+path+"\" pack is not possible");
			}
			
			if (pack != null) {
				this.packs.add (pack);
			}
		}
		
	}
}
