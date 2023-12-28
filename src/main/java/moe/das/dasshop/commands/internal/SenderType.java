package moe.das.dasshop.commands.internal;

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public enum SenderType {
    ALL(CommandSender.class),
    PLAYER_ONLY(Player.class),
    CONSOLE_ONLY(ConsoleCommandSender.class);

    private final Class<? extends CommandSender> wrappingClass;

    SenderType(Class<? extends CommandSender> clazz) {
        this.wrappingClass = clazz;
    }

    public Class<? extends CommandSender> getWrappingClass() {
        return wrappingClass;
    }
}
