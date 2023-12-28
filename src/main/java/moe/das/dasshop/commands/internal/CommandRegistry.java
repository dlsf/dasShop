package moe.das.dasshop.commands.internal;

import moe.das.dasshop.commands.internal.parser.CommandParser;
import moe.das.dasshop.commands.internal.parser.ParsedCommand;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CommandRegistry {
    private final Map<Command, Set<ParsedCommand>> registeredCommands = new HashMap<>();
    private final CommandParser commandParser = new CommandParser();

    public void register(@NotNull Command command) {
        var subCommands = command.getSubcommandMethods();
        if (subCommands.isEmpty()) {
            throw new IllegalStateException("A command has been registered, but there are no subcommands configured");
        }

        var parsedCommands = commandParser.parseSubCommands(subCommands);
        registeredCommands.put(command, parsedCommands);

        System.out.println("Successfully registered command " + command.getName());
    }

    public void unregister(@NotNull Command command) throws IllegalStateException {
        if (!registeredCommands.containsKey(command)) {
            throw new IllegalStateException();
        }

        this.registeredCommands.remove(command);
    }

    public void unregisterAll() {
        registeredCommands.keySet().forEach(this::unregister);
    }
}
