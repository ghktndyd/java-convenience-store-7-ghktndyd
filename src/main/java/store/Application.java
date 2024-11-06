package store;

import store.view.InputView;
import store.view.OutputView;

public class Application {
    public static void main(String[] args) {
        ConvenienceStore convenienceStore = new ConvenienceStore(new InputView(), new OutputView());
        convenienceStore.run();
    }
}
