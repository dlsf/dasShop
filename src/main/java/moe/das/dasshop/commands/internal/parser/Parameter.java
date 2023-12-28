package moe.das.dasshop.commands.internal.parser;

public abstract class Parameter {
    private final String parameterName;

    protected Parameter(String parameterName) {
        this.parameterName = parameterName;
    }
}
