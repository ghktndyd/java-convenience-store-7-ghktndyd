package store.view;

public class InputView {

    private static final String REQUEST_ORDERS_MESSAGE = "구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])";

    public void requestOrders() {
        System.out.println(REQUEST_ORDERS_MESSAGE);
    }
}
