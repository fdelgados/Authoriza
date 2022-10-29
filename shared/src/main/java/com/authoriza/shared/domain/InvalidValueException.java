package com.authoriza.shared.domain;

public class InvalidValueException extends DomainError {
    public InvalidValueException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }

    public static InvalidValueException fromValue(Object value, Object obj) {
        var message = String.format("Invalid value \"%s\" for value object <%s>",
                value, obj.getClass().getSimpleName());

        return new InvalidValueException("invalid_value", message);
    }
}
