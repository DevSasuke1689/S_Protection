package me.sasuke.protection;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.sasuke.protection.antiOP.BlockOPCommandConsole;
import me.sasuke.protection.antiOP.BlockOpCommandPlayer;
import me.sasuke.protection.commands.sProtection;
import me.sasuke.protection.event.removeOpJoin;

public class Main extends JavaPlugin {

	public static String prefix = "§6[S_Protection] §e";
	
	public static PluginManager pm = Bukkit.getPluginManager();
	public static Main plugin;
	
	@Override
	public void onEnable() {
		plugin = this;
		
		Bukkit.getConsoleSender().sendMessage("");
		Bukkit.getConsoleSender().sendMessage(prefix + "Plugin esta Sendo Iniciado !!");
		Bukkit.getConsoleSender().sendMessage("");
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
			
			@Override
			public void run() {
				checkConfig();
			}
		}, 20*1);
		
		for (Player all : Bukkit.getOnlinePlayers()) {
			all.setOp(false);
			Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.plugin, new Runnable() {
				
				@Override
				public void run() {
					
					if (ProtectionAPI.getBooleanConfig("AntiOP.Ativar") == true) {
						
						if (!all.isOnline()) {
							Bukkit.getScheduler().cancelAllTasks();	
						}
						
						if (ProtectionAPI.getBooleanConfig("AntiOP.removerOp") == true) {
							if (all.isOp()) {
								if (all.isOnline()) {
									all.setOp(false);
									if (ProtectionAPI.getBooleanConfig("AntiOP.PunirJogador") == true) {
										Bukkit.dispatchCommand(Bukkit.getConsoleSender(), ProtectionAPI.getStringConfig("AntiOP.PunirComando").replace("{player}", all.getName()));
									}
								}
							}
						}else {
							if (ProtectionAPI.getBooleanConfig("AntiOP.PunirJogador") == true) {
								if (all.isOp()) {
									Bukkit.dispatchCommand(Bukkit.getConsoleSender(), ProtectionAPI.getStringConfig("AntiOP.PunirComando").replace("{player}", all.getName()));
								}
							}
						}
						
					}
				}
			}, 0, 20*1);
		}
		
		commands();
		Events();
		
	}
	
	@Override
	public void onDisable() {
		plugin = null;
		
		
	}
	
	public void commands() {
		getCommand("sprotection").setExecutor(new sProtection());
	}
	
	public void Events() {
		pm.registerEvents(new BlockOPCommandConsole(), this);
		pm.registerEvents(new BlockOpCommandPlayer(), this);
		pm.registerEvents(new removeOpJoin(), this);
	}
	
	public void checkConfig() {
		saveDefaultConfig();
	
		if (getConfig().getBoolean("AntiOP.Ativar") == true) {
			Bukkit.getConsoleSender().sendMessage("");
			Bukkit.getConsoleSender().sendMessage(prefix + "AntiOP Esta ativado/Carregando metodos !");
			Bukkit.getConsoleSender().sendMessage("");
			if (getConfig().getBoolean("AntiOP.removerOp") == true) {
				
				Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
					
					@Override
					public void run() {
						Bukkit.getConsoleSender().sendMessage(prefix + "Remover Op do Jogador foi ativado com sucesso !");
						
					}
				}, 20*1);
				
			}else {
				
				Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
					
					@Override
					public void run() {
						Bukkit.getConsoleSender().sendMessage(prefix + "Remover Op do Jogador nao esta ativado !");
					}
				}, 20*1);
				
			}
			
			if (getConfig().getBoolean("AntiOP.desativarComando") == true) {
				
				Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
					
					@Override
					public void run() {
						Bukkit.getConsoleSender().sendMessage(prefix + "Desativar o comando Op foi ativado com sucesso !");
					}
				}, 20*2);
			}else {
				
				Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
					
					@Override
					public void run() {
						Bukkit.getConsoleSender().sendMessage(prefix + "Desativar o comando Op nao esta ativado !");
					}
				}, 20*2);
			}
			
		}else {
			Bukkit.getConsoleSender().sendMessage("");
			Bukkit.getConsoleSender().sendMessage(prefix + "AntiOP Esta Desativado Tome cuidado!!");
			Bukkit.getConsoleSender().sendMessage("");			
		}
		
	}
	
}
