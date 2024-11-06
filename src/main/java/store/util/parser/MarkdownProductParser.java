package store.util.parser;

import store.product.domain.Product;
import store.promotion.domain.Promotion;
import store.promotion.domain.Promotions;

public class MarkdownProductParser extends MarkdownParser<Product> {

    private static final String HEADER = "name,price,quantity,promotion";
    public static final String SEPARATOR = ",";

    private final Promotions promotions;

    public MarkdownProductParser(String filePath, Promotions promotions) {
        super(filePath, HEADER);
        this.promotions = promotions;
    }

    @Override
    protected Product parseLine(String line) {
        String[] columns = line.split(SEPARATOR);
        String name = columns[0];
        int price = validateNumber(columns[1]);
        int quantity = validateNumber(columns[2]);
        Promotion promotion = promotions.findByPromotionName(columns[3]);

        return new Product(name, price, quantity, promotion);
    }
}
