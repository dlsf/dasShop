package moe.das.dasshop.commands.internal.parser;

import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

public enum ParameterType {
    DOUBLE(double.class, float.class, Double.class, Float.class),
    INT(int.class, Integer.class),
    PLAYER(Player.class),
    STRING(String.class);

    private final Set<Class<?>> classes;

    ParameterType(Class<?>... classes) {
        this.classes = Set.of(classes);
    }

    public static Optional<ParameterType> resolveParameterType(Class<?> clazz) {
        return Arrays.stream(values())
                .filter(parameterType -> parameterType.classes.contains(clazz))
                .findAny();
    }
}
