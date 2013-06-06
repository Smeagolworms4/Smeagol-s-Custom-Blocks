package mods.customblocks.common;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.google.common.base.Charsets;
import com.google.common.io.Files;


public class CustomPackFactory {
	
	static List<String> listNamePack;
	
	public static CustomPack getByPath(File packFiles) throws Exception {
		if (packFiles.isDirectory()) {
			return getByDir(packFiles);
		}
		return getByZip(packFiles);
	}

	private static CustomPack getByZip(File packFiles) throws Exception{
		System.out.println ("Not implement load by zip");
		return null;
	}

	private static CustomPack getByDir(File packFiles) throws Exception {
		
		File packPath = new File (packFiles, "infos.json");
		String jsonInfos = Files.toString(packPath, Charsets.UTF_8);
		JSONObject root = (JSONObject) JSONValue.parse(jsonInfos);

		String engineVersion = (String) root.get("engine_version");
		String packVersion   = (String) root.get("pack_version");
		String name          = (String) root.get("name");
		String description   = (String) root.get("description");
		
		if (listNamePack.contains(name)) { throw new Exception ("The custom pack \"" + packFiles.getName() + "\" has a name already used by other pack"); }
		if (name          == null)       { throw new Exception ("The custom pack \"" + packFiles.getName() + "\" has not name in infos.json"); }
		if (engineVersion == null)       { throw new Exception ("The custom pack \"" + packFiles.getName() + "\" has not engine_version in infos.json"); }
		if (packVersion   == null)       { throw new Exception ("The custom pack \"" + packFiles.getName() + "\" has not pack_version in infos.json"); }
		if (description   == null)       { description = ""; }
		
		CustomPack pack = new CustomPack(name, packVersion, description, engineVersion);
		
		listNamePack.add (name);
		
		return pack;
		
	}

}
