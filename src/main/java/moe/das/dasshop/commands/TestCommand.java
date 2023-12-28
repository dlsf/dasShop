package moe.das.dasshop.commands;

import moe.das.dasshop.commands.internal.Command;
import moe.das.dasshop.commands.internal.SenderType;
import moe.das.dasshop.commands.internal.SubCommand;
import org.bukkit.entity.Player;

public class TestCommand extends Command {
    public TestCommand(String name) {
        super(name);
    }

    @SubCommand(senderType = SenderType.PLAYER_ONLY)
    public void test(Player commandSender, String input, int t1, float f1) {

    }
}
