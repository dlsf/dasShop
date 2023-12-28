package moe.das.dasshop.commands.internal.parser;

import org.bukkit.command.CommandSender;

import java.lang.reflect.Method;
import java.util.Set;
import java.util.stream.Collectors;

public class CommandParser {
    public Set<SubCommand> parseSubCommands(Set<Method> subCommandMethods) {
        return subCommandMethods.stream()
                .map(this::parseSubCommand)
                .collect(Collectors.toSet());
    }
    
    private SubCommand parseSubCommand(Method subCommand) {
        var annotation = subCommand.getAnnotation(moe.das.dasshop.commands.internal.SubCommand.class);
        var methodParameters = subCommand.getParameters();

        var firstParameter = methodParameters[0];
        if (firstParameter.getType() != annotation.senderType().getWrappingClass()) {
            throw new IllegalStateException("Incorrectly configured subcommand " + subCommand.getName());
        }

        var parsedCommandBuilder = new ParsedCommandBuilder();
        // We can perform the unchecked cast here since we already checked the first parameter's type above
        parsedCommandBuilder.withSender((Class<CommandSender>) firstParameter.getType());

        for (int i = 1; (methodParameters.length > 1) && (i < methodParameters.length); i++) {
            var parameter = ParameterFactory.fromMethodParameter(methodParameters[i]);
            parsedCommandBuilder.withParameter(parameter);
        }

        return parsedCommandBuilder.build();
    }
}
