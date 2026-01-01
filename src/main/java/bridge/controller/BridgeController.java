package bridge.controller;

import bridge.view.InputView;
import bridge.view.OutputView;

/**
 * 프로그램의 전체 흐름 조율을 담당하는 클래스
 */
public class BridgeController {
    private final InputView inputView;
    private final OutputView outputView;

    public BridgeController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printStart();
    }
}
