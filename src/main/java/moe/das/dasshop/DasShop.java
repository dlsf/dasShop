package moe.das.dasshop;

import moe.das.dasshop.commands.TestCommand;
import moe.das.dasshop.commands.internal.CommandRegistry;
import org.bukkit.plugin.java.JavaPlugin;

public class DasShop extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("Here we go!");

        var registry = new CommandRegistry();
        var testCommand = new TestCommand();

        registry.register(testCommand);
    }
}
