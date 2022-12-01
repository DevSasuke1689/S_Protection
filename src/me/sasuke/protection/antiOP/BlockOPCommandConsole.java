package me.sasuke.protection.antiOP;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerCommandEvent;

import me.sasuke.protection.ProtectionAPI;

public class BlockOPCommandConsole implements Listener {

	@EventHandler
	public void Block(ServerCommandEvent e) {
		
		if (e.getSender() == Bukkit.getConsoleSender()) {
			if (ProtectionAPI.getBooleanConfig("AntiOP.Ativar") == true) {
				if (ProtectionAPI.getBooleanConfig("AntiOP.desativarComando") == true) {
					if (e.getCommand().contains("op")) {
						e.setCommand("");
						Bukkit.getConsoleSender().sendMessage(ProtectionAPI.getStringConfig("AntiOP.Mensagem"));
					}
				}
			}
		}
	}
}
