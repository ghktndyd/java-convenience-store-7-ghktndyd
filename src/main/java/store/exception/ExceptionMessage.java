package store.exception;

public enum ExceptionMessage {

    INVALID_FORMAT("올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요."),
    NONE_EXISTENT_PRODUCT("존재하지 않는 상품입니다. 다시 입력해 주세요."),
    EXCEED_PRODUCT_QUANTITY("재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요."),
    INVALID_INPUT("잘못된 입력입니다. 다시 입력해 주세요."),

    NONE_EXISTENT_FILE("파일을 읽을 수 없습니다. 파일을 확인해 주세요."),
    INVALID_HEADER("파일 헤더가 올바르지 않습니다. 헤더 형식을 확인해 주세요."),
    INVALID_NUMBER_FORMAT("숫자여야 하는 값입니다. 형식을 확인해 주세요."),
    INVALID_DATE_FORMAT("잘못된 날짜 형식입니다. 형식을 확인해 주세요."),
    EMPTY_FILE("파일이 비어있습니다. 파일을 확인해 주세요.");

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
