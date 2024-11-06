package store.util.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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

    public List<T> parse(BufferedReader reader) {
        List<T> items = new ArrayList<>();
        try {
            parseFile(reader, items);
        } catch (IOException e) {
            throw new CustomException(ExceptionMessage.NONE_EXISTENT_FILE);
        }
        return items;
    }

    private void parseFile(BufferedReader reader, List<T> items) throws IOException {
        String headerLine = reader.readLine();

        validateEmptyFile(headerLine);
        validateHeader(headerLine);

        parseLines(reader, items);
    }

    private void validateHeader(String header) {
        if (!fileHeader.equals(header)) {
            throw new CustomException(ExceptionMessage.INVALID_HEADER);
        }
    }

    private void validateEmptyFile(String headerLine) {
        if (headerLine == null || headerLine.isBlank()) {
            throw new CustomException(ExceptionMessage.EMPTY_FILE);
        }
    }

    private void parseLines(BufferedReader reader, List<T> items) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            items.add(parseLine(line));
        }
    }

    protected abstract T parseLine(String line);

    int validateNumber(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new CustomException(ExceptionMessage.INVALID_NUMBER_FORMAT);
        }
    }

    LocalDate validateDate(String rawDate) {
        try {
            return LocalDate.parse(rawDate);
        } catch (DateTimeParseException e) {
            throw new CustomException(ExceptionMessage.INVALID_DATE_FORMAT);
        }
    }
}
