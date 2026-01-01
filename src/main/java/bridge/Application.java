package bridge;

import bridge.controller.BridgeController;
import bridge.model.BridgeMaker;
import bridge.util.BridgeNumberGenerator;
import bridge.util.BridgeRandomNumberGenerator;
import bridge.view.InputView;
import bridge.view.OutputView;

/**
 * 프로그램 진입점을 담당하는 클래스
 */
public class Application {
    public static void main(String[] args) {
        BridgeController controller = appConfig();
        controller.run();
    }

    private static BridgeController appConfig() {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        BridgeNumberGenerator bridgeNumberGenerator = new BridgeRandomNumberGenerator();
        BridgeMaker bridgeMaker = new BridgeMaker(bridgeNumberGenerator);

        return new BridgeController(inputView, outputView, bridgeMaker);
    }
}
