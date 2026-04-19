package project.kompass.illagerVZombies;

import org.bukkit.plugin.java.JavaPlugin;


public final class IllagerVZombies extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new MobAIListener(), this);

        getLogger().info("Raiders vs Giants AI has been enabled!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
