package store.util.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import store.exception.CustomException;
import store.exception.ExceptionMessage;
import store.promotion.domain.Promotion;

public class MarkdownPromotionParser extends MarkdownParser<Promotion> {

    private static final String HEADER = "name,buy,get,start_date,end_date";
    private static final String SEPARATOR = ",";

    public MarkdownPromotionParser(String filePath) {
        super(filePath, HEADER);
    }

    @Override
    protected Promotion parseLine(String line) {
        String[] columns = line.split(SEPARATOR);
        String name = columns[0];
        int buyQuantity = validateNumber(columns[1]);
        int freeQuantity = validateNumber(columns[2]);
        LocalDate startDate = validateDate(columns[3]);
        LocalDate endDate = validateDate(columns[4]);

        return new Promotion(name, buyQuantity, freeQuantity, startDate, endDate);
    }

    private int validateNumber(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new CustomException(ExceptionMessage.INVALID_NUMBER_FORMAT);
        }
    }

    private LocalDate validateDate(String rawDate) {
        try {
            return LocalDate.parse(rawDate);
        } catch (DateTimeParseException e) {
            throw new CustomException(ExceptionMessage.INVALID_DATE_FORMAT);
        }
    }
}
