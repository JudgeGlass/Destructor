package net.judgeglass.destructor;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Destructor extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("LOADING DESTRUCTOR PLUGIN");

        this.getCommand("destructor").setExecutor(new CommandListener());
    }

    @Override
    public void onDisable() {
        getLogger().info("CLOSING DESTRUCTOR PLUGIN. Good Bye!");
    }
}
