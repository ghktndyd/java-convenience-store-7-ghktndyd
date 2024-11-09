package store.receipt;

public class ReceiptItem {
    private final String productName;
    private final int quantity;
    private final int unitPrice;

    public ReceiptItem(String productName, int quantity, int unitPrice) {
        this.productName = productName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getTotalPrice() {
        return quantity * unitPrice;
    }
}
