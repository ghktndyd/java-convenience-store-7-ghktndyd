package store.receipt;

import java.util.List;

public class Receipt {
    private final List<ReceiptItem> items;
    private final List<ReceiptItem> freeItems;
    private final int totalQuantity;
    private final int totalPrice;
    private final int promotionDiscount;
    private int membershipDiscountPrice = 0;
    private int finalPrice;

    public Receipt(List<ReceiptItem> items, List<ReceiptItem> freeItems, int promotionDiscount) {
        this.items = items;
        this.freeItems = freeItems;
        this.totalQuantity = calculateTotalQuantity();
        this.totalPrice = calculateTotalPrice();
        this.promotionDiscount = promotionDiscount;
        this.finalPrice = totalPrice - promotionDiscount;
    }

    public List<ReceiptItem> getItems() {
        return items;
    }

    public List<ReceiptItem> getFreeItems() {
        return freeItems;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public int getPromotionDiscount() {
        return promotionDiscount;
    }

    public int getMembershipDiscountPrice() {
        return membershipDiscountPrice;
    }

    public int getFinalPrice() {
        return finalPrice;
    }

    public void applyMembershipDiscount(boolean isMembership) {
        if (isMembership) {
            membershipDiscountPrice = (int) (finalPrice * 0.3);
            if (membershipDiscountPrice > 8000) {
                membershipDiscountPrice = 8000;
            }
            finalPrice -= membershipDiscountPrice;
        }
    }

    private int calculateTotalQuantity() {
        return items.stream()
                .mapToInt(ReceiptItem::getQuantity)
                .sum();
    }

    private int calculateTotalPrice() {
        return items.stream()
                .mapToInt(ReceiptItem::getTotalPrice)
                .sum();
    }
}
