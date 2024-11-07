package store.product.repository;

import java.util.HashMap;
import java.util.Map;
import store.product.domain.Product;

public class ProductRepository {

    private final Map<String, Product> products = new HashMap<>();

    public void addProduct(Product product) {
        products.put(product.getName(), product);
    }

    public Product findByName(String name) {
        return products.get(name);
    }
}
