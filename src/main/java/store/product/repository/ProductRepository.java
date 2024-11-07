package store.product.repository;

import java.util.ArrayList;
import java.util.List;
import store.product.domain.Product;

public class ProductRepository {

    private final List<Product> products;

    public ProductRepository(List<Product> products) {
        this.products = products;
    }

    public List<Product> findAll() {
        return new ArrayList<>(products);
    }
}
