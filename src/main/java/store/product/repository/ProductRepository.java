package store.product.repository;

import java.util.ArrayList;
import java.util.List;
import store.exception.CustomException;
import store.exception.ExceptionMessage;
import store.order.OrderItem;
import store.order.OrderItems;
import store.product.domain.Product;

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

    public void processOrder(OrderItems orderItems) {
        for (OrderItem orderItem : orderItems.getOrderItems()) {
            String productName = orderItem.getProductName();
            Product product = findByProductName(productName);
            product.deductQuantity(orderItem.getQuantity());
        }
    }
}
