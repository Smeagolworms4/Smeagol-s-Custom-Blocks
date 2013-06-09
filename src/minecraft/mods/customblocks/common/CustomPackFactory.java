package mods.customblocks.common;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.google.common.base.Charsets;
import com.google.common.io.Files;


public class CustomPackFactory {
	
	static ArrayList<String> listNamePack = new ArrayList<String>();
	
	/**
	 * Crée un Custom pack
	 * @param packFiles Zip file or Dir
	 * @return
	 * @throws Exception
	 */
	public static CustomPack getByPath(File packFiles) throws Exception {
		if (packFiles.isDirectory()) {
			return getByDir(packFiles);
		}
		return getByZip(packFiles);
	}
	
	/**
	 * Crée un Custom pack grace à un fichier zip
	 * @param packFiles
	 * @return
	 * @throws Exception
	 */
	private static CustomPack getByZip(File packFiles) throws Exception{
		System.out.println ("Not implement load by zip");
		return null;
	}
	
	/**
	 * Crée un Custom pack grace à un dossier
	 * @param packFiles
	 * @return
	 * @throws Exception
	 */
	private static CustomPack getByDir(File packFiles) throws Exception {
		
		
		// Load info.json
		
		File infosPath   = new File (packFiles, "infos.json");
		String jsonInfos = Files.toString(infosPath, Charsets.UTF_8);
		JSONObject root  = (JSONObject) JSONValue.parse(jsonInfos);

		String engineVersion = Util.toString (root.get("engine_version"));
		String packVersion   = Util.toString (root.get("pack_version"));
		String name          = Util.toString (root.get("name"));
		String description   = Util.toString (root.get("description"));
		
		if (listNamePack.contains(name)) { throw new Exception ("The custom pack \"" + packFiles.getName() + "\" has a name already used by other pack"); }
		if (name          == null)       { throw new Exception ("The custom pack \"" + packFiles.getName() + "\" has not name in infos.json"); }
		if (engineVersion == null)       { throw new Exception ("The custom pack \"" + packFiles.getName() + "\" has not engine_version in infos.json"); }
		if (packVersion   == null)       { throw new Exception ("The custom pack \"" + packFiles.getName() + "\" has not pack_version in infos.json"); }
		if (description   == null)       { description = ""; }
		
		
		// convertion en cas de besoin TODO 
		
		CustomPack pack = new CustomPack(name, packVersion, description, engineVersion);
		_loadBLock (pack, packFiles);
		
		listNamePack.add (name);
		return pack;
		
	}
	
	/**
	 * Charghe le json de chaque Block
	 * @param pack
	 * @param packFiles
	 */
	private static void _loadBLock (CustomPack pack, File packFiles) {
		
		File blockDir = new File (packFiles, "blocks");
		
		if (blockDir.exists() && blockDir.isDirectory()) {
			for (String pathFileBlock: blockDir.list()) {
				
				try {
					File fileBlock = new File (blockDir, pathFileBlock);
					String jsonBlock = Files.toString(fileBlock, Charsets.UTF_8);
					JSONObject root = (JSONObject) JSONValue.parse(jsonBlock);
					if (root.get("initial_id") == null) { throw new Exception ("The block file \"" + pathFileBlock + "\" has not an initial_id"); }
					
					pack.addBlockInfos(pathFileBlock, root);
					
				} catch (Exception e) {
					System.out.println ("The file block \""+pathFileBlock+"\" is'nt valid.");
				}
			}
		}
	}

}
