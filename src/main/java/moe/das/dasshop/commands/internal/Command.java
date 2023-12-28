package moe.das.dasshop.commands.internal;

import org.bukkit.command.CommandSender;

public abstract class Command {
    private final String name;

    protected Command(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
