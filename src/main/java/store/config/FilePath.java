package store.config;

public enum FilePath {

    PROMOTIONS_FILE("src/test/resources/promotions.md"),
    ;

    private final String filePath;

    FilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }
}
