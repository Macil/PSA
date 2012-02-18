package com.minesnap.psa;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class PSATestCommand implements CommandExecutor {
    private final PSAPlugin plugin;

    public PSATestCommand(PSAPlugin plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length != 1)
            return false;

        Message message = plugin.getMessage(args[0]);
        if(message == null) {
            sender.sendMessage("Error: Could not find a stored message by that name.");
        } else {
            plugin.announce(sender, message);
        }

        return true;
    }
}
