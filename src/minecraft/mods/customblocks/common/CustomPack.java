package mods.customblocks.common;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.material.Material;
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
				
				String name     = entry.getKey(); name = name.substring(0, name.indexOf(".json"));
				JSONObject json = entry.getValue();
				
				int initialId     = Util.toInt (json.get("initial_id")); 
				if (initialId == 0)     { throw new Exception ("The initial id = 0"); }
				
				String label      = Util.toString (json.get("label"));
				int id            = config.getBlock(name, initialId, label).getInt();
				String  material  = Util.toString (json.get("material"));
				
				
				System.out.println ("New custom block \""+name+"\" in pack \""+this._name+"\" : [initia_id="+initialId+", id="+id+", material="+material+"]");
				CustomBlock blobk = new CustomBlock(id, this._parseMaterial(material));
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println ("Block problem in \""+_name+"\" pack.");
			}
		}
		
		config.save();
	}
	
	private Material _parseMaterial (String str) {
		Material material = Material.grass;
		
		if (("air")          .equals(str)) { material = Material.air; }
		if (("ground")       .equals(str)) { material = Material.ground; }
		if (("wood")         .equals(str)) { material = Material.wood; }
		if (("rock")         .equals(str)) { material = Material.rock; }
		if (("iron")         .equals(str)) { material = Material.iron; }
		if (("anvil")        .equals(str)) { material = Material.anvil; }
		if (("water")        .equals(str)) { material = Material.water; }
		if (("lava")         .equals(str)) { material = Material.lava; }
		if (("leaves")       .equals(str)) { material = Material.leaves; }
		if (("plants")       .equals(str)) { material = Material.plants; }
		if (("vine")         .equals(str)) { material = Material.vine; }
		if (("sponge")       .equals(str)) { material = Material.sponge; }
		if (("cloth")        .equals(str)) { material = Material.cloth; }
		if (("fire")         .equals(str)) { material = Material.fire; }
		if (("sand")         .equals(str)) { material = Material.sand; }
		if (("circuits")     .equals(str)) { material = Material.circuits; }
		if (("glass")        .equals(str)) { material = Material.glass; }
		if (("redstoneLight").equals(str)) { material = Material.redstoneLight; }
		if (("tnt")          .equals(str)) { material = Material.tnt; }
		if (("coral")        .equals(str)) { material = Material.coral; }
		if (("ice")          .equals(str)) { material = Material.ice; }
		if (("snow")         .equals(str)) { material = Material.snow; }
		if (("craftedSnow")  .equals(str)) { material = Material.craftedSnow; }
		if (("cactus")       .equals(str)) { material = Material.cactus; }
		if (("clay")         .equals(str)) { material = Material.clay; }
		if (("pumpkin")      .equals(str)) { material = Material.pumpkin; }
		if (("dragonEgg")    .equals(str)) { material = Material.dragonEgg; }
		if (("portal")       .equals(str)) { material = Material.portal; }
		if (("cake")         .equals(str)) { material = Material.cake; }
		if (("web")          .equals(str)) { material = Material.web; }
		if (("piston")       .equals(str)) { material = Material.piston; }
		
		return material;
	}
	
}
