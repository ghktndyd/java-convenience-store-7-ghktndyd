package store.order;

import store.promotion.domain.Promotion;

public class OrderItem {

    private final String productName;
    private final int buyQuantity;
    private int freeQuantity = 0;
    private int promotionDiscount = 0;

    public OrderItem(String productName, int buyQuantity) {
        this.productName = productName;
        this.buyQuantity = buyQuantity;
    }

    public String getProductName() {
        return productName;
    }

    public int getBuyQuantity() {
        return buyQuantity;
    }

    public int getFreeQuantity() {
        return freeQuantity;
    }

    public int getTotalQuantity() {
        return buyQuantity + freeQuantity;
    }

    public int getPromotionDiscount() {
        return promotionDiscount;
    }

    public void applyPromotion(Promotion promotion, int applicableStock, int productPrice) {
        int promotionApplyCount = buyQuantity / promotion.getBuyQuantity();

        freeQuantity = promotionApplyCount * promotion.getFreeQuantity();

        int totalQuantity = buyQuantity + freeQuantity;

        if (totalQuantity > applicableStock) {
            freeQuantity = applicableStock - buyQuantity;
            if (freeQuantity < 0) {
                freeQuantity = 0;
            }
        }

        promotionDiscount = freeQuantity * productPrice;
    }
}
