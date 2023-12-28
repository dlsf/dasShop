package moe.das.dasshop.commands.internal.parser;

import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class ParsedCommandBuilder {
    private Class<CommandSender> sender;
    private final List<Parameter> parameters;

    public ParsedCommandBuilder() {
        this.parameters = new ArrayList<>();
    }

    public void withSender(Class<CommandSender> sender) {
        this.sender = sender;
    }

    public void withParameter(Parameter parameter) {
        this.parameters.add(parameter);
    }

    public ParsedCommand build() {
        if (sender == null) {
            throw new IllegalStateException("Command parameters haven't been configured correctly");
        }

        return new ParsedCommand(sender, parameters);
    }
}
