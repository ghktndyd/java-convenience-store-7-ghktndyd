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

        addReceiptItems(orderItems, receiptItems);

        return new Receipt(receiptItems);
    }

    private void addReceiptItems(OrderItems orderItems, List<ReceiptItem> receiptItems) {
        for (OrderItem orderItem : orderItems.getOrderItems()) {
            String productName = orderItem.getProductName();
            int orderQuantity = orderItem.getQuantity();

            Product product = findByProductName(productName);
            product.deductQuantity(orderQuantity);

            receiptItems.add(new ReceiptItem(productName, orderQuantity, product.getPrice()));
        }
    }
}
