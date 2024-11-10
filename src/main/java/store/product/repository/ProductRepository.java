package store.product.repository;

import java.util.ArrayList;
import java.util.List;
import store.exception.CustomException;
import store.exception.ExceptionMessage;
import store.order.OrderItem;
import store.order.OrderItems;
import store.product.domain.Product;
import store.receipt.Receipt;
import store.receipt.ReceiptItem;

public class ProductRepository {

    private final List<Product> products;

    public ProductRepository(List<Product> products) {
        this.products = products;
    }

    public List<Product> findAll() {
        return new ArrayList<>(products);
    }

    public Product findByProductName(String name) {
        return products.stream()
                .filter(product -> product.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new CustomException(ExceptionMessage.NONE_EXISTENT_PRODUCT));
    }

    public Receipt processOrder(OrderItems orderItems) {
        List<ReceiptItem> receiptItems = new ArrayList<>();
        List<ReceiptItem> freeItems = new ArrayList<>();
        int totalPromotionDiscount = 0;

        for (OrderItem orderItem : orderItems.getOrderItems()) {
            Product product = findByProductName(orderItem.getProductName());
            int applicableStock = product.getQuantity();
            int productPrice = product.getPrice();

            if (product.hasPromotions()) {
                orderItem.applyPromotion(product.getPromotion(), applicableStock, productPrice);

                int requiredTotalQuantity = orderItem.getTotalQuantity();

                product.deductQuantity(requiredTotalQuantity);
                receiptItems.add(new ReceiptItem(product.getName(), orderItem.getTotalQuantity(), productPrice));
                if (orderItem.getFreeQuantity() > 0) {
                    freeItems.add(new ReceiptItem(product.getName(), orderItem.getFreeQuantity(), 0));
                }

                totalPromotionDiscount += orderItem.getPromotionDiscount();
            } else {
                product.deductQuantity(orderItem.getBuyQuantity());
                receiptItems.add(new ReceiptItem(product.getName(), orderItem.getBuyQuantity(), productPrice));
            }
        }

        return new Receipt(receiptItems, freeItems, totalPromotionDiscount);
    }
}
