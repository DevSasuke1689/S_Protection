package me.sasuke.protection;

public class ProtectionAPI {

	public static String getPrefixConfig() {
		return Main.plugin.getConfig().getString("Prefix").replace("&", "§");
	}
	
	public static String getStringConfig(String string) {
		return Main.plugin.getConfig().getString(string).replace("&", "§").replace("{prefix}", getPrefixConfig());
	}
	
	public static Boolean getBooleanConfig(String string) {
		return Main.plugin.getConfig().getBoolean(string);
	}
	
}
