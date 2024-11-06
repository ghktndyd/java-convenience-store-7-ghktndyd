package store.util.parser;

import java.time.LocalDate;
import store.config.FilePath;
import store.promotion.domain.Promotion;

public class MarkdownPromotionParser extends MarkdownParser<Promotion> {

    private static final String HEADER = "name,buy,get,start_date,end_date";
    private static final String SEPARATOR = ",";

    public MarkdownPromotionParser(FilePath filePath) {
        super(filePath.getFilePath(), HEADER);
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
}
