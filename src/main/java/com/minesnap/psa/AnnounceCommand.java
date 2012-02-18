package com.minesnap.psa;

import org.apache.commons.lang3.StringUtils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class AnnounceCommand implements CommandExecutor {
    private final PSAPlugin plugin;

    public AnnounceCommand(PSAPlugin plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0)
            return false;

        String message = PSAUtils.join(args, ' ');
        plugin.announce(message);

        return true;
    }
}
