package moe.das.dasshop.commands.internal;

import moe.das.dasshop.commands.internal.parser.ParameterFactory;
import moe.das.dasshop.commands.internal.parser.ParsedCommand;
import moe.das.dasshop.commands.internal.parser.ParsedCommandBuilder;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class CommandRegistry {
    private final Map<Command, Set<ParsedCommand>> registeredCommands;

    public CommandRegistry() {
        this.registeredCommands = new HashMap<>();
    }

    public void unregister(@NotNull Command command) throws IllegalStateException {
        if (!registeredCommands.containsKey(command)) {
            throw new IllegalStateException();
        }

        this.registeredCommands.remove(command);
    }

    public void register(@NotNull Command command) {
        var subCommands = getSubcommandMethods(command);
        if (subCommands.isEmpty()) {
            throw new IllegalArgumentException("A command has been registered, but there is no subcommand configured");
        }

        var parsedCommands = subCommands.stream()
                .map(this::parseSubCommand)
                .collect(Collectors.toSet());

        registeredCommands.put(command, parsedCommands);

        // TODO: Remove
        System.out.println("Successfully registered command!");
    }

    private Set<Method> getSubcommandMethods(Command command) {
        return Arrays.stream(command.getClass().getMethods())
                .filter(method -> method.isAnnotationPresent(SubCommand.class))
                .collect(Collectors.toSet());
    }

    private ParsedCommand parseSubCommand(Method subCommand) {
        var annotation = subCommand.getAnnotation(SubCommand.class);
        var parameters = subCommand.getParameters();
        var firstParameter = parameters[0];
        if (firstParameter.getType() != annotation.senderType().getWrappingClass()) {
            throw new IllegalStateException("Incorrectly configured subcommand " + subCommand.getName());
        }

        var parsedCommandBuilder = ParsedCommandBuilder.create();
        parsedCommandBuilder.withSender((Class<CommandSender>) firstParameter.getType());

        for (int i = 1; (parameters.length > 1) && (i < parameters.length); i++) {
            var parsableParameter = ParameterFactory.fromMethodParameter(parameters[i]);
            parsedCommandBuilder.withParameter(parsableParameter);
            System.out.println(i);
        }

        return parsedCommandBuilder.build();
    }
}
