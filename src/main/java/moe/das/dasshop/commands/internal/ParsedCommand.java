package moe.das.dasshop.commands.internal;

import moe.das.dasshop.commands.internal.parser.SubCommand;

import java.util.Set;

public record ParsedCommand(Command command, Set<SubCommand> subCommands) {
}
