package store.util.parser;

import java.util.List;

public interface Parser<T> {

    List<T> parse();
}
