package moe.das.dasshop.commands.internal;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class CommandExecutor implements org.bukkit.command.CommandExecutor {
    private final CommandRegistry commandRegistry;

    public CommandExecutor(CommandRegistry commandRegistry) {
        this.commandRegistry = commandRegistry;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        // TODO: implement
        return false;
    }
}
