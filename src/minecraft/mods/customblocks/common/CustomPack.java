package mods.customblocks.common;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.Configuration;

import org.json.simple.JSONObject;

public class CustomPack {

	private String _name;
	private String _packVersion;
	private String _description;
	private String _engineVersion;
	
	private HashMap<String, JSONObject> _jsonBlocks;
	public ArrayList<CustomBlock> blocks;
	
	public CustomPack(String name, String packVersion, String description, String engineVersion) {

		this._name          = name;
		this._packVersion   = packVersion;
		this._description   = description;
		this._engineVersion = engineVersion;
		this._jsonBlocks    = new HashMap<String, JSONObject>();
		this.blocks         = new ArrayList<CustomBlock> ();
		
	}
	
	
	public void addBlockInfos (String name, JSONObject json) {
		this._jsonBlocks.put(name, json);
	}


	/**
	 * Crée la liste des éléments du pack
	 */
	public void init() {
		this._initBlocks ();
	}
	
	/**
	 * Initialise tous les blocks du pack
	 */
	private void _initBlocks() {
	
		File rootConfig = new File(new File(Minecraft.getMinecraftDir(), "config"), "SmeagolCustomBlock");
		Configuration config = new Configuration(new File(rootConfig, this._name));
		config.load();
		
		for (Map.Entry<String,JSONObject> entry : _jsonBlocks.entrySet()) {
			try {

				String name     = entry.getKey();
				JSONObject json = entry.getValue();
				
				int initialId = Integer.parseInt ((String) json.get("initial_id"));
				String label  = (String) json.get("label");

				if (name == "")     { throw new Exception ("The name "); }
				if (initialId == 0) { throw new Exception ("Id"); }
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println ("Block problem in \""+_name+"\" pack.");
			}
		}
		
		config.save();
	}
	
}
