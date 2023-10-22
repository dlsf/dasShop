package moe.das.dasshop.commands.internal.parser;

import org.bukkit.command.CommandSender;

import java.util.List;

public class ParsedCommand {
    private final Class<CommandSender> sender;
    private final List<Parameter> parameters;

    ParsedCommand(Class<CommandSender> sender, List<Parameter> parameters) {
        this.sender = sender;
        this.parameters = parameters;
    }
}
