package store.promotion.domain;

import java.util.List;

public class Promotions {

    private final List<Promotion> promotions;

    public Promotions(List<Promotion> promotions) {
        this.promotions = promotions;
    }

    public Promotion findByPromotionName(String column) {
        return null;
    }
}
