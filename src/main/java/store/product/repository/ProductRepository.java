package store.product.repository;

import java.util.HashMap;
import java.util.Map;
import store.product.domain.Product;

public class ProductRepository {

    private final Map<String, Product> products = new HashMap<>();

}
