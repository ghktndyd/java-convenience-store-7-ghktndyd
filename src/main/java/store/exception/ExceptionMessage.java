package store.exception;

public enum ExceptionMessage {

    INVALID_FORMAT("올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요."),
    NONE_EXISTENT_PRODUCT("존재하지 않는 상품입니다. 다시 입력해 주세요."),
    EXCEED_PRODUCT_QUANTITY("재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요."),
    INVALID_INPUT("잘못된 입력입니다. 다시 입력해 주세요."),

    NONE_EXISTENT_FILE("파일을 읽을 수 없습니다."),
    ;

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
