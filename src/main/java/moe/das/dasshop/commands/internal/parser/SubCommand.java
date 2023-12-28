package moe.das.dasshop.commands.internal.parser;

import org.bukkit.command.CommandSender;

import java.util.List;

public record SubCommand(Class<CommandSender> sender, List<Parameter> parameters) {
}
