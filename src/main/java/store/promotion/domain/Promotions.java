package store.promotion.domain;

import java.util.List;
import store.exception.CustomException;
import store.exception.ExceptionMessage;

public class Promotions {

    private final List<Promotion> promotions;

    public Promotions(List<Promotion> promotions) {
        this.promotions = promotions;
    }

    public Promotion findByPromotionName(String promotionName) {
        if ("null".equals(promotionName)) {
            return null;
        }

        return promotions.stream()
                .filter(promotion -> promotion.getName().equals(promotionName))
                .findFirst()
                .orElseThrow(() -> new CustomException(ExceptionMessage.INVALID_INPUT));
    }
}
