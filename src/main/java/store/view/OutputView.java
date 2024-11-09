package store.view;

import java.util.List;
import store.product.domain.Product;
import store.product.repository.ProductRepository;
import store.receipt.Receipt;
import store.receipt.ReceiptItem;

public class OutputView {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String WELCOME_MESSAGE = "안녕하세요. W편의점입니다.";
    private static final String PRODUCTS_INFO_HEADER = "현재 보유하고 있는 상품입니다." + NEW_LINE;
    private static final String PRODUCT_INFO_FORMAT = "- %s %,d원 %s%s" + NEW_LINE;
    private static final String NO_STOCK = "재고 없음";
    public static final String EA = "개";

    public static void printErrorMessage(String message) {
        System.out.println(message);
    }

    public void printWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
    }

    public void printProducts(ProductRepository productRepository) {
        System.out.println(PRODUCTS_INFO_HEADER);
        List<Product> products = productRepository.findAll();
        products.forEach(this::printProductInfo);
    }

    public void printReceipt(Receipt receipt) {
        System.out.println("==============W 편의점================");
        System.out.println("상품명           수량          금액");
        List<ReceiptItem> receiptItems = receipt.getItems();
        for (ReceiptItem receiptItem : receiptItems) {
            System.out.printf("%-12s\t%-4d\t%,10d%n",
                    receiptItem.getProductName(),
                    receiptItem.getQuantity(),
                    receiptItem.getTotalPrice());
        }
        System.out.println("=============증    정===============");
        System.out.println("====================================");
        System.out.printf("%-10s %4s %10s%n", "총구매액", receipt.getTotalQuantity(),
                String.format("%,d", receipt.getTotalPrice()));
        System.out.printf("%-10s %4s %10s%n", "행사할인", "", String.format("-%,d", receipt.getTotalPrice()));
        System.out.printf("%-10s %4s %10s%n", "멤버십할인", "", String.format("-%,d", receipt.getTotalPrice()));
        System.out.printf("%-10s %4s %10s%n", "내실돈", "", String.format("%,d", receipt.getTotalPrice()));
    }

    private void printProductInfo(Product product) {
        System.out.printf(PRODUCT_INFO_FORMAT,
                product.getName(),
                product.getPrice(),
                getQuantityInfo(product),
                getPromotionInfo(product));
    }

    private String getQuantityInfo(Product product) {
        if (product.getQuantity() > 0) {
            return product.getQuantity() + EA;
        }
        return NO_STOCK;
    }

    private String getPromotionInfo(Product product) {
        if (product.getPromotion() != null) {
            return " " + product.getPromotionName();
        }
        return "";
    }
}
