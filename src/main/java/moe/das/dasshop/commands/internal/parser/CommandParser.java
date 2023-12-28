package moe.das.dasshop.commands.internal.parser;

import moe.das.dasshop.commands.internal.SubCommand;
import org.bukkit.command.CommandSender;

import java.lang.reflect.Method;
import java.util.Set;
import java.util.stream.Collectors;

public class CommandParser {
    public Set<ParsedCommand> parseSubCommands(Set<Method> subCommandMethods) {
        return subCommandMethods.stream()
                .map(this::parseSubCommand)
                .collect(Collectors.toSet());
    }
    
    private ParsedCommand parseSubCommand(Method subCommand) {
        var annotation = subCommand.getAnnotation(SubCommand.class);
        var methodParameters = subCommand.getParameters();
        var firstParameter = methodParameters[0];
        if (firstParameter.getType() != annotation.senderType().getWrappingClass()) {
            throw new IllegalStateException("Incorrectly configured subcommand " + subCommand.getName());
        }

        var parsedCommandBuilder = new ParsedCommandBuilder();
        parsedCommandBuilder.withSender((Class<CommandSender>) firstParameter.getType());

        for (int i = 1; (methodParameters.length > 1) && (i < methodParameters.length); i++) {
            var parameter = ParameterFactory.fromMethodParameter(methodParameters[i]);
            parsedCommandBuilder.withParameter(parameter);
        }

        return parsedCommandBuilder.build();
    }
}
