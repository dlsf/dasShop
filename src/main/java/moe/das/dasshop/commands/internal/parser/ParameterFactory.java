package moe.das.dasshop.commands.internal.parser;

import moe.das.dasshop.commands.internal.parser.types.DoubleParameter;
import moe.das.dasshop.commands.internal.parser.types.IntParameter;
import moe.das.dasshop.commands.internal.parser.types.PlayerParameter;
import moe.das.dasshop.commands.internal.parser.types.StringParameter;
import org.jetbrains.annotations.NotNull;

public class ParameterFactory {
    @NotNull
    public static Parameter fromMethodParameter(@NotNull java.lang.reflect.Parameter methodParameter) {
        var parameterType = ParameterType.resolveParameterType(methodParameter.getType()).orElseThrow();

        return ParameterFactory.fromType(parameterType, methodParameter.getName());
    }

    @NotNull
    public static Parameter fromType(@NotNull ParameterType type, @NotNull String parameterName) {
        return switch (type) {
            case DOUBLE -> new DoubleParameter(parameterName);
            case INT -> new IntParameter(parameterName);
            case PLAYER -> new PlayerParameter(parameterName);
            case STRING -> new StringParameter(parameterName);
        };
    }
}
