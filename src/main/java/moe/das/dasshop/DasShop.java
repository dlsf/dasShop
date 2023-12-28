package moe.das.dasshop;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;
import me.lucko.commodore.Commodore;
import me.lucko.commodore.CommodoreProvider;
import moe.das.dasshop.commands.TestCommand;
import moe.das.dasshop.commands.internal.CommandRegistry;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class DasShop extends JavaPlugin {
    private CommandRegistry registry;

    @Override
    public void onEnable() {
        getLogger().info("Here we go!");

        this.registry = new CommandRegistry();

        var testCommand = new TestCommand("test");
        this.registry.register(this, testCommand);

        if (CommodoreProvider.isSupported()) {
            getLogger().info("Enabling commodore support");
            Commodore commodore = CommodoreProvider.getCommodore(this);
            registerCompletions(commodore, getCommand("test"));
        }
    }

    private void registerCompletions(Commodore commodore, PluginCommand command) {
        getLogger().info("Registering completions");
        LiteralCommandNode<?> timeCommand = LiteralArgumentBuilder.literal("test")
                .then(LiteralArgumentBuilder.literal("set")
                        .then(RequiredArgumentBuilder.argument("time", IntegerArgumentType.integer())))
                .then(LiteralArgumentBuilder.literal("add")
                        .then(RequiredArgumentBuilder.argument("time", IntegerArgumentType.integer()))
                ).build();

        commodore.register(command, timeCommand);
    }

    @Override
    public void onDisable() {
        this.registry.unregisterAll();
    }
}
