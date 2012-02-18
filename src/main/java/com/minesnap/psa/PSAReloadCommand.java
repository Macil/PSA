package com.minesnap.psa;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class PSAReloadCommand implements CommandExecutor {
    private final PSAPlugin plugin;

    public PSAReloadCommand(PSAPlugin plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        plugin.reload();
        sender.sendMessage("PSA Config reloaded.");
        return true;
    }
}
