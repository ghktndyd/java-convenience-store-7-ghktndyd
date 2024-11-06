package store;

import store.view.InputView;
import store.view.OutputView;

public class ConvenienceStore {

    private final InputView inputView;
    private final OutputView outputView;

    public ConvenienceStore(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
    }
}
