package store.promotion.domain;

import java.time.LocalDate;
import java.util.Objects;

public class Promotion {

    private final String name;
    private final int buyQuantity;
    private final int freeQuantity;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public Promotion(String name, int buyQuantity, int freeQuantity, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.buyQuantity = buyQuantity;
        this.freeQuantity = freeQuantity;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Promotion promotion = (Promotion) o;
        return buyQuantity == promotion.buyQuantity && freeQuantity == promotion.freeQuantity && Objects.equals(
                name, promotion.name) && Objects.equals(startDate, promotion.startDate)
                && Objects.equals(endDate, promotion.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, buyQuantity, freeQuantity, startDate, endDate);
    }
}
