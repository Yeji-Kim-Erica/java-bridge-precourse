package bridge.model;

import bridge.error.ErrorMessage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {
    private static final String UPPER_BLOCK = "U";
    private static final String LOWER_BLOCK = "D";

    private final List<String> bridge;
    private List<String> gameResult;

    public BridgeGame(List<String> bridge) {
        this.bridge = bridge;
        this.gameResult = new ArrayList<>();
    }

    public List<String> getGameResult() {
        return Collections.unmodifiableList(gameResult);
    }

    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void move(String movingBlock) {
        validateMovingBlock(movingBlock);
        int index = gameResult.size();
        boolean isPassable = bridge.get(index).equals(movingBlock);
        if (isPassable) {
            gameResult.add("O");
        }
        if (!isPassable) {
            gameResult.add("X");
        }
    }

    public boolean isOver() {
        return hasPassedBridge() || hasFailed();
    }

    public boolean hasPassedBridge() {
        return gameResult.size() == bridge.size();
    }

    public boolean hasFailed() {
        return gameResult.get(gameResult.size() - 1).equals("X");
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void retry() {
    }

    private void validateMovingBlock(String movingBlock) {
        if (movingBlock.equals(UPPER_BLOCK) || movingBlock.equals(LOWER_BLOCK)) {
            return;
        }
        throw new IllegalArgumentException(ErrorMessage.INVALID_MOVING_BLOCK.getMessage());
    }
}