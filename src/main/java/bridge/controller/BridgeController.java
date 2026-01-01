package bridge.controller;

import bridge.model.BridgeGame;
import bridge.model.BridgeMaker;
import bridge.view.InputView;
import bridge.view.OutputView;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 프로그램의 전체 흐름 조율을 담당하는 클래스
 */
public class BridgeController {
    private final InputView inputView;
    private final OutputView outputView;
    private final BridgeMaker bridgeMaker;

    public BridgeController(InputView inputView, OutputView outputView, BridgeMaker bridgeMaker) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.bridgeMaker = bridgeMaker;
    }

    public void run() {
        outputView.printStart();

        BridgeGame bridgeGame = createGame();

        do {
            outputView.printMovingBlockPrompt();
            retry(this::proceedGame, bridgeGame);
        } while (!bridgeGame.isOver());
    }

    private BridgeGame createGame() {
        outputView.printBridgeSizePrompt();
        List<String> bridge = retry(this::createBridge);
        return new BridgeGame(bridge);
    }

    private <T> T retry(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e);
            }
        }
    }

    private List<String> createBridge() {
        int bridgeSize = inputView.readBridgeSize();
        return bridgeMaker.makeBridge(bridgeSize);
    }

    private void retry(Consumer<BridgeGame> consumer, BridgeGame bridgeGame) {
        while (true) {
            try {
                consumer.accept(bridgeGame);
                return;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e);
            }
        }
    }

    private void proceedGame(BridgeGame bridgeGame) {
        String movingBlock = inputView.readMoving();
        bridgeGame.move(movingBlock);
    }
}
