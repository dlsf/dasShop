package moe.das.dasshop.commands.internal;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class Command {
    private final String name;

    protected Command(String name) {
        this.name = name;
    }

    public final String getName() {
        return name;
    }

    public final Set<Method> getSubcommandMethods() {
        return Arrays.stream(getClass().getMethods())
                .filter(method -> method.isAnnotationPresent(SubCommand.class))
                .collect(Collectors.toSet());
    }
}
