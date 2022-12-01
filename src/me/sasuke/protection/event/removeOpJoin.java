package me.sasuke.protection.event;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.sasuke.protection.Main;
import me.sasuke.protection.ProtectionAPI;

public class removeOpJoin implements Listener {

	@EventHandler
	public void remove(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		
			Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.plugin, new Runnable() {
				
				@Override
				public void run() {
					
					if (ProtectionAPI.getBooleanConfig("AntiOP.Ativar") == true) {
						
						if (!p.isOnline()) {
							Bukkit.getScheduler().cancelAllTasks();	
						}
						
						if (ProtectionAPI.getBooleanConfig("AntiOP.removerOp") == true) {
							if (p.isOp()) {
								if (p.isOnline()) {
									p.setOp(false);
									if (ProtectionAPI.getBooleanConfig("AntiOP.PunirJogador") == true) {
										Bukkit.dispatchCommand(Bukkit.getConsoleSender(), ProtectionAPI.getStringConfig("AntiOP.PunirComando").replace("{player}", p.getName()));
									}
								}
							}
						}else {
							if (ProtectionAPI.getBooleanConfig("AntiOP.PunirJogador") == true) {
								if (p.isOp()) {
									Bukkit.dispatchCommand(Bukkit.getConsoleSender(), ProtectionAPI.getStringConfig("AntiOP.PunirComando").replace("{player}", p.getName()));
								}
							}
						}
						
					}
				}
			}, 0, 20*1);
	}
}
