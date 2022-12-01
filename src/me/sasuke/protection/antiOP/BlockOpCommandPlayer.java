package me.sasuke.protection.antiOP;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import me.sasuke.protection.Main;
import me.sasuke.protection.ProtectionAPI;

public class BlockOpCommandPlayer implements Listener {

	@EventHandler
	public void BlockCommand(PlayerCommandPreprocessEvent e) {
		
		Player p = e.getPlayer();
		
		if (ProtectionAPI.getBooleanConfig("AntiOP.Ativar") ==  true) {
			if (ProtectionAPI.getBooleanConfig("AntiOP.desativarComando") == true) {
				if (e.getMessage().contains("/op")) {
					e.setCancelled(true);
					
					p.sendMessage(ProtectionAPI.getStringConfig("AntiOP.Mensagem"));
					
					if (ProtectionAPI.getBooleanConfig("AntiOP.Warn") == true) {
						Bukkit.getConsoleSender().sendMessage("");
						Bukkit.getConsoleSender().sendMessage(Main.prefix + "O Jogador: §f" + p.getName() + " §eTentou usar o Comando /op ou /deop");
						Bukkit.getConsoleSender().sendMessage("");
					}
					return;
				}
				
				if (e.getMessage().contains("/deop")) {
					e.setCancelled(true);
					
					p.sendMessage(ProtectionAPI.getStringConfig("AntiOP.Mensagem"));
					
					if (ProtectionAPI.getBooleanConfig("AntiOP.Warn") == true) {
						Bukkit.getConsoleSender().sendMessage("");
						Bukkit.getConsoleSender().sendMessage(Main.prefix + "O Jogador: §f" + p.getName() + " §eTentou usar o Comando /deop");
						Bukkit.getConsoleSender().sendMessage("");
					}
					return;
				}
			}
		}
	}
}
