package com.minesnap.psa;

import java.io.File;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class PSAPlugin extends JavaPlugin {
    private Random random = new Random();

    private Map<String, Message> messages;
    private RandomCollection<Message> randomMessages;

    // Note that both of these values here are in game tics. In the
    // config file, they're in minutes.
    private int time;
    private int variance;

    private static final int TICS_PER_SECOND = 20;
    private static final int TICS_PER_MINUTE = TICS_PER_SECOND*60;

    @Override
    public void onEnable() {
        getCommand("announce").setExecutor(new AnnounceCommand(this));
        getCommand("psanow").setExecutor(new PSANowCommand(this));
        getCommand("psatest").setExecutor(new PSATestCommand(this));
        getCommand("psareload").setExecutor(new PSAReloadCommand(this));

        init();
    }

    private void unload() {
        getServer().getScheduler().cancelTasks(this);
    }

    private void init() {
        messages = new HashMap<String, Message>();
        randomMessages = new RandomCollection<Message>(random);

        // Place the default config file if one doesn't exist.
        if(!(new File(getDataFolder(), "config.yml")).exists()) {
            saveDefaultConfig();
        }

        time = getConfig().getInt("time", 12) * TICS_PER_MINUTE;
        variance = getConfig().getInt("variance", 3) * TICS_PER_MINUTE;

        ConfigurationSection messagesSection = getConfig().getConfigurationSection("messages");
        for(String name : messagesSection.getKeys(false)) {
            String content = messagesSection.getString(name+".message");
            int chance = messagesSection.getInt(name+".chance", 100);
            Message message = new Message(name, content, chance);

            messages.put(name, message);
            randomMessages.add(chance, message);
        }

        getLogger().info("Loaded " + messages.size() + " messages.");

        queueMessage();
    }

    private void queueMessage() {
        final Message next = randomMessages.next();
        if(next == null) {
            getLogger().warning("Could not find next message!");
            return;
        }

        final int delay = time-variance + random.nextInt(2*variance+1);

        getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            public void run() {
                if(getServer().getOnlinePlayers().length > 0) {
                    announce(next);
                }
                queueMessage();
            }
        }, delay);
    }

    public void reload() {
        unload();
        reloadConfig();
        init();
    }

    public Message getMessage(String name) {
        return messages.get(name);
    }

    public void announce(Message message) {
        announce(message.getContent());
    }

    public void announce(CommandSender receiver, Message message) {
        announce(receiver, message.getContent());
    }

    public void announce(String message) {
        for(String line : message.split("\n")) {
            getServer().broadcastMessage(ChatColor.DARK_AQUA + line);
        }
    }

    public void announce(CommandSender receiver, String message) {
        for(String line : message.split("\n")) {
            receiver.sendMessage(ChatColor.DARK_AQUA + line);
        }
    }
}
