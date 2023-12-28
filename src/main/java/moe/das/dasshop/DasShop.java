package moe.das.dasshop;

import moe.das.dasshop.commands.TestCommand;
import moe.das.dasshop.commands.internal.CommandRegistry;
import org.bukkit.plugin.java.JavaPlugin;

public class DasShop extends JavaPlugin {
    private CommandRegistry registry;

    @Override
    public void onEnable() {
        getLogger().info("Here we go!");

        this.registry = new CommandRegistry();

        var testCommand = new TestCommand("test");
        this.registry.register(testCommand);
    }

    @Override
    public void onDisable() {
        this.registry.unregisterAll();
    }
}
