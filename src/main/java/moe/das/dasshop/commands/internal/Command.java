package moe.das.dasshop.commands.internal;

import org.bukkit.command.CommandSender;

public abstract class Command {
    @SubCommand(senderType = SenderType.ALL, synonyms = {"test"})
    public void basicTest(CommandSender commandSender, String text) {

    }
}
