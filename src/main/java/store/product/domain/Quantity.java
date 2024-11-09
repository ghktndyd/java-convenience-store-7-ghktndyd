package store.product.domain;

import store.exception.CustomException;
import store.exception.ExceptionMessage;

public class Quantity {

    private final int value;

    public Quantity(int value) {
        validateQuantity(value);
        this.value = value;
    }

    private void validateQuantity(int value) {
        if (value < 0) {
            throw new CustomException(ExceptionMessage.INVALID_INPUT);
        }
    }

    public int getValue() {
        return value;
    }
}
