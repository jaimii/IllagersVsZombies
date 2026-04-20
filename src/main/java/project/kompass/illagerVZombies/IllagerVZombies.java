package project.kompass.illagerVZombies;

import org.bukkit.plugin.java.JavaPlugin;


public final class IllagerVZombies extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new MobAIListener(), this);

        getLogger().info("Illager, Piglin and Zombie Behaviours are now modified!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
