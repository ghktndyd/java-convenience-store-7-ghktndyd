package store;

import java.util.List;
import store.config.FilePath;
import store.product.domain.Product;
import store.product.repository.ProductRepository;
import store.promotion.domain.Promotion;
import store.promotion.domain.Promotions;
import store.util.parser.MarkdownProductParser;
import store.util.parser.MarkdownPromotionParser;
import store.view.InputView;
import store.view.OutputView;

public class ConvenienceStore {

    private final InputView inputView;
    private final OutputView outputView;
    private Promotions promotions;
    private ProductRepository productRepository;

    public ConvenienceStore(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void initialize() {
        this.promotions = new Promotions(readPromotions());
        this.productRepository = new ProductRepository(readProducts());
    }

    public void run() {
        outputView.printWelcomeMessage();
        outputView.printProducts(productRepository);

        inputView.requestOrders();
    }

    private List<Promotion> readPromotions() {
        MarkdownPromotionParser markdownPromotionParser = new MarkdownPromotionParser(FilePath.PROMOTIONS_FILE);
        return markdownPromotionParser.parse();
    }

    private List<Product> readProducts() {
        MarkdownProductParser markdownProductParser = new MarkdownProductParser(FilePath.PRODUCTS_FILE, promotions);
        return markdownProductParser.parse();
    }
}
