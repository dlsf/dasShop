package moe.das.dasshop.commands.internal;

import moe.das.dasshop.commands.internal.parser.CommandParser;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class CommandRegistry {
    private final Set<ParsedCommand> registeredCommands = new HashSet<>();
    private final CommandParser commandParser = new CommandParser();
    private final CommandExecutor commandExecutor = new CommandExecutor(this);

    public void register(@NotNull JavaPlugin plugin, @NotNull Command command) {
        var subCommands = command.getSubcommandMethods();
        if (subCommands.isEmpty()) {
            throw new IllegalStateException("A command has been registered, but there are no subcommands configured");
        }

        var parsedCommands = commandParser.parseSubCommands(subCommands);
        registeredCommands.add(new ParsedCommand(command, parsedCommands));

        plugin.getCommand(command.getName()).setExecutor(commandExecutor);
        System.out.println("Successfully registered command " + command.getName());
    }

    public void unregister(@NotNull ParsedCommand command) throws IllegalStateException {
        this.registeredCommands.remove(command);
    }

    public void unregisterAll() {
        registeredCommands.forEach(this::unregister);
    }

    @NotNull
    public Optional<ParsedCommand> getCommandByName(String name) {
        return registeredCommands.stream()
                .filter(parsedCommand -> parsedCommand.command().getName().equalsIgnoreCase(name))
                .findAny();
    }
}
