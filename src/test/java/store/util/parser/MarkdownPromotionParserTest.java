package store.util.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.config.FilePath;
import store.exception.CustomException;
import store.exception.ExceptionMessage;
import store.promotion.domain.Promotion;

import java.io.BufferedReader;
import java.io.StringReader;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MarkdownPromotionParserTest {

    private MarkdownPromotionParser parser;

    @BeforeEach
    void setUp() {
        parser = new MarkdownPromotionParser(FilePath.PROMOTIONS_FILE);
    }

    @DisplayName("모든 값이 정상이라면 promotions.md 파일이 정상적으로 파싱된다.")
    @Test
    void parseValidPromotion() {
        String validFileContents = """
                name,buy,get,start_date,end_date
                탄산2+1,2,1,2024-01-01,2024-12-31
                """;

        List<Promotion> promotions = parser.parse(new BufferedReader(new StringReader(validFileContents)));

        assertThat(promotions).hasSize(1);
        assertThat(promotions.getFirst()).isEqualTo(
                new Promotion("탄산2+1", 2, 1, LocalDate.of(2024, 1, 1), LocalDate.of(2024, 12, 31))
        );
    }

    @DisplayName("헤더 형식이 잘못 되었다면 예외가 발생한다.")
    @Test
    void parseInvalidHeader() {
        String invalidHeader = """
                test,buy,get,start_date,end_date
                탄산2+1,2,1,2024-01-01,2024-12-31
                """;

        assertThatThrownBy(() -> parser.parse(new BufferedReader(new StringReader(invalidHeader))))
                .isInstanceOf(CustomException.class)
                .hasMessageContaining(ExceptionMessage.INVALID_HEADER.getMessage());
    }

    @DisplayName("수량 형식이 잘못 되었다면 예외가 발생한다.")
    @Test
    void parseInvalidQuantity() {
        String invalidQuantity = """
                name,buy,get,start_date,end_date
                탄산2+1,test,1,2024-01-01,2024-12-31
                """;

        assertThatThrownBy(() -> parser.parse(new BufferedReader(new StringReader(invalidQuantity))))
                .isInstanceOf(CustomException.class)
                .hasMessageContaining(ExceptionMessage.INVALID_NUMBER_FORMAT.getMessage());
    }

    @DisplayName("날짜 형식이 잘못 되었다면 예외 발생한다.")
    @Test
    void parseInvalidDate() {
        String invalidDate = """
                name,buy,get,start_date,end_date
                탄산2+1,2,1,test,2024-12-31
                """;

        assertThatThrownBy(() -> parser.parse(new BufferedReader(new StringReader(invalidDate))))
                .isInstanceOf(CustomException.class)
                .hasMessageContaining(ExceptionMessage.INVALID_DATE_FORMAT.getMessage());
    }

    @DisplayName("파일이 비어 있다면 예외가 발생한다.")
    @Test
    void parseEmptyFile() {
        String emptyContent = "";

        assertThatThrownBy(() -> parser.parse(new BufferedReader(new StringReader(emptyContent))))
                .isInstanceOf(CustomException.class)
                .hasMessageContaining(ExceptionMessage.EMPTY_FILE.getMessage());
    }
}