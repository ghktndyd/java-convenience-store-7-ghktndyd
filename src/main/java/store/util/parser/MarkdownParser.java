package store.util.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import store.exception.CustomException;
import store.exception.ExceptionMessage;

public abstract class MarkdownParser<T> implements Parser<T> {

    private final String filePath;
    private final String fileHeader;

    public MarkdownParser(String filePath, String fileHeader) {
        this.filePath = filePath;
        this.fileHeader = fileHeader;
    }

    @Override
    public List<T> parse() {
        List<T> items = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            parseFile(reader, items);
        } catch (IOException e) {
            throw new CustomException(ExceptionMessage.NONE_EXISTENT_FILE);
        }

        return items;
    }

    private void parseFile(BufferedReader reader, List<T> items) throws IOException {
        parseLines(reader, items);
    }

    private void parseLines(BufferedReader reader, List<T> items) throws IOException {
        while (reader.readLine() != null) {
            items.add(parseLine(reader.readLine()));
        }
    }

    protected abstract T parseLine(String line);
}
