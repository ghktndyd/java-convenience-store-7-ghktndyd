package store.receipt;

import java.util.List;

public class Receipt {
    private final List<ReceiptItem> items;
    private final int totalQuantity;
    private final int totalPrice;

    public Receipt(List<ReceiptItem> items) {
        this.items = items;
        this.totalQuantity = calculateTotalQuantity();
        this.totalPrice = calculateTotalPrice();
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

    public List<ReceiptItem> getItems() {
        return items;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }
}
