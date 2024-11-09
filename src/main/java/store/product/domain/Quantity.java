package store.product.domain;

import java.util.Objects;
import store.exception.CustomException;
import store.exception.ExceptionMessage;

public class Quantity {

    private int value;

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

    public void subtract(int quantity) {
        if (quantity > value) {
            throw new CustomException(ExceptionMessage.EXCEED_PRODUCT_QUANTITY);
        }

        value -= quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Quantity quantity = (Quantity) o;
        return value == quantity.value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
