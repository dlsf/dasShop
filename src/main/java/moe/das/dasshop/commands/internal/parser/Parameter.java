package moe.das.dasshop.commands.internal.parser;

public abstract class Parameter {
    private final String parameterName;

    protected Parameter(String parameterName) {
        this.parameterName = parameterName;
    }

    public static Parameter forMethodParameter(java.lang.reflect.Parameter methodParameter) {
        var parameterType = ParameterType.resolveParameterType(methodParameter.getType()).orElseThrow();

        return ParameterFactory.fromType(parameterType, methodParameter.getName());
    }
}
