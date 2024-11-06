package store.config;

public enum FilePath {

    PROMOTIONS_FILE("src/main/resources/promotions.md"),
    PRODUCTS_FILE("src/main/resources/products.md");

    private final String filePath;

    FilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }
}
