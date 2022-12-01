package me.sasuke.protection.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.sasuke.protection.Main;
import me.sasuke.protection.ProtectionAPI;

public class sProtection implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		
		if (cmd.getName().equalsIgnoreCase("sprotection")) {
			
			if (sender instanceof Player) {
				Player p = (Player) sender;
				p.sendMessage(ProtectionAPI.getStringConfig("Mensagems.ComandoparaConsole"));
			}else {
				
				if (args.length == 0) {
					
					Bukkit.getConsoleSender().sendMessage("");
					Bukkit.getConsoleSender().sendMessage(Main.prefix + "/sprotection reload");
					Bukkit.getConsoleSender().sendMessage("");
					
				}
				
				if (args.length == 1) {
					Bukkit.getConsoleSender().sendMessage(Main.prefix + "Config 'config.yml' foi recarregada com sucesso !");
					Main.plugin.reloadConfig();
				}
				
			}
		}
		return false;
	}
}
