package store;

import java.util.List;
import java.util.function.Supplier;
import store.config.FilePath;
import store.order.OrderItems;
import store.product.domain.Product;
import store.product.repository.ProductRepository;
import store.promotion.domain.Promotion;
import store.promotion.domain.Promotions;
import store.receipt.Receipt;
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
        do {
            printProducts();
            OrderItems orderItems = getOrderItems();

            Receipt receipt = getReceipt(orderItems);

            boolean isMembership = inputView.askMembershipDiscountApply();

            receipt.applyMembershipDiscount(isMembership);

            outputView.printReceipt(receipt);

        } while (inputView.askAnotherPurchase());
    }

    private void printProducts() {
        outputView.printWelcomeMessage();
        outputView.printProducts(productRepository);
    }

    private OrderItems getOrderItems() {
        return retry(() -> {
            OrderItems orderItems = inputView.requestOrderItems();
            return orderItems;
        });
    }

    private Receipt getReceipt(OrderItems orderItems) {
        return retry(() -> {
            return productRepository.processOrder(orderItems);
        });
    }

    private List<Promotion> readPromotions() {
        MarkdownPromotionParser markdownPromotionParser = new MarkdownPromotionParser(FilePath.PROMOTIONS_FILE);
        return markdownPromotionParser.parse();
    }

    private List<Product> readProducts() {
        MarkdownProductParser markdownProductParser = new MarkdownProductParser(FilePath.PRODUCTS_FILE, promotions);
        return markdownProductParser.parse();
    }

    private <T> T retry(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }
}
