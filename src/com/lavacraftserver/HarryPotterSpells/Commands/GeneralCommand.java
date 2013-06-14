package com.lavacraftserver.HarryPotterSpells.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.lavacraftserver.HarryPotterSpells.HPS;

@HCommand(name="harrypotterspells", description="cmdGenDescription", aliases="hps", usage="<command> [config (reload|edit)]")
public class GeneralCommand implements HCommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0)
            return false;
        
        if(args[0].equalsIgnoreCase("config")) { // Configuration editing/reloading
            if(args.length == 1)
                return false;
            else if(args[1].equalsIgnoreCase("reload")) {
                HPS.Plugin.reloadConfig();
                HPS.PM.dependantMessagingTell(sender, HPS.Localisation.getTranslation("cmdGenConfigReloaded"));
            } else if(args[1].equalsIgnoreCase("edit")) {
                if(args.length != 4)
                    HPS.PM.dependantMessagingTell(sender, ChatColor.RED + HPS.Localisation.getTranslation("cmdUsage", (sender instanceof Player ? "/" : "") + label, " config edit <key> <new value>"));
                else {
                    HPS.Plugin.getConfig().set(args[2], args[3]);
                    HPS.Plugin.saveConfig();
                    HPS.PM.dependantMessagingTell(sender, HPS.Localisation.getTranslation("cmdGenConfigUpdated"));
                }
            }
            
            return true;
        }
        
        return false;
    }
    
}
