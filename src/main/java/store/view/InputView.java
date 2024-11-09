package store.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.List;
import store.exception.CustomException;
import store.exception.ExceptionMessage;
import store.order.OrderItem;
import store.order.OrderItems;

public class InputView {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String REQUEST_ORDERS_MESSAGE = NEW_LINE + "구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])";

    public OrderItems requestOrderItems() {
        System.out.println(REQUEST_ORDERS_MESSAGE);
        String input = Console.readLine();
        validateBlankInput(input);
        validateOrderItemsFormat(input);

        List<OrderItem> orderItems = parseOrderItems(input);
        return new OrderItems(orderItems);
    }

    private void validateBlankInput(String input) {
        if (input == null || input.isBlank()) {
            throw new CustomException(ExceptionMessage.INVALID_INPUT);
        }
    }

    private void validateOrderItemsFormat(String input) {
        if (!input.startsWith("[") || !input.endsWith("]")) {
            throw new CustomException(ExceptionMessage.INVALID_FORMAT);
        }

        String[] orders = splitToOrderItemsFormat(input);

        for (String order : orders) {
            validateOrderFormat(order);
        }
    }

    private String[] splitToOrderItemsFormat(String input) {
        String content = input.substring(1, input.length() - 1);
        return content.split("],\\[");
    }

    private void validateOrderFormat(String order) {
        String[] parts = order.split("-");
        validateOrderItemLength(parts);
        validateQuantity(parts[1]);
    }

    private void validateOrderItemLength(String[] parts) {
        if (parts.length != 2) {
            throw new CustomException(ExceptionMessage.INVALID_FORMAT);
        }
    }

    private int validateQuantity(String quantity) {
        try {
            return Integer.parseInt(quantity);
        } catch (NumberFormatException e) {
            throw new CustomException(ExceptionMessage.INVALID_FORMAT);
        }
    }

    private List<OrderItem> parseOrderItems(String input) {
        String[] orders = splitToOrderItemsFormat(input);
        return generateOrderItems(orders);
    }

    private List<OrderItem> generateOrderItems(String[] orders) {
        List<OrderItem> orderItems = new ArrayList<>();
        for (String order : orders) {
            String[] parts = order.split("-");
            String productName = parts[0];
            int quantity = validateQuantity(parts[1]);
            orderItems.add(new OrderItem(productName, quantity));
        }
        return orderItems;
    }
}
