/**
* Adds a GUI for the essentials command /warp
* https://www.spigotmc.org/resources/essentials-warp-gui-opensource.13571/
*
* @author  Marcely1199
* @website https://marcely.de/ 
*/

package de.marcely.warpgui.config;

import de.marcely.configmanager2.EZConfigManager;
import de.marcely.warpgui.EssentialsWarpGUI;
import de.marcely.warpgui.util.StringUtil;
import de.marcely.warpgui.util.Util;

public class BaseConfig {
	
	public static EZConfigManager manager = new EZConfigManager(Util.FILE_CONFIG_BASE, false);
	
	public static void load(){
		if(!manager.exists()){
			save();
			return;
		}
		
		manager.load();
		
		final String version = manager.getConfigString("config-version");
		final String invtitle = manager.getConfigString("inv-title");
		final Boolean firstcharcaps = manager.getConfigBoolean("first-char-caps");
		final Boolean incl_warps = manager.getConfigBoolean("includecmd-warps");
		
		{
			if(invtitle != null)
				ConfigValue.inventory_title = StringUtil.readableStringToFormattedChatColor(invtitle);
			if(firstcharcaps != null)
				ConfigValue.first_char_caps = firstcharcaps;
			if(incl_warps != null)
				ConfigValue.include_command_warps = incl_warps;
		}
		
		if(version == null || version != null && !version.equals(EssentialsWarpGUI.getVersion()))
			save();
	}
	
	public static void save(){
		manager.clear();
		manager.addComment("Don't change this");
		manager.addConfig("config-version", EssentialsWarpGUI.getVersion());
		
		manager.addEmptyLine();
		
		manager.addComment("Set the title from the inventory");
		manager.addConfig("inv-title", StringUtil.formattedChatColorStringToReadable(ConfigValue.inventory_title));
		
		manager.addEmptyLine();
		
		manager.addComment("If it's enabled, the first character in the name of the warp is in caps");
		manager.addConfig("first-char-caps", ConfigValue.first_char_caps);
		
		manager.addEmptyLine();
		
		manager.addComment("If it's enabled, /warps will open the GUI too");
		manager.addConfig("includecmd-warps", ConfigValue.include_command_warps);
		
		manager.save();
	}
}
