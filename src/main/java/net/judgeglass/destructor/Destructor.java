package net.judgeglass.destructor;

import org.bukkit.plugin.java.JavaPlugin;

public final class Destructor extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("LOADING DESTRUCTOR PLUGIN");

    }

    @Override
    public void onDisable() {
        getLogger().info("CLOSING DESTRUCTOR PLUGIN. Good Bye!");
    }
}
