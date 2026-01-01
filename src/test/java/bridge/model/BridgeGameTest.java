package bridge.model;

import bridge.error.ErrorMessage;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BridgeGameTest {
    private final List<String> defaultBridge = List.of("U","U","U","D","D","U","D","U");
    private final BridgeGame defaultBridgeGame = new BridgeGame(defaultBridge);

    @Nested
    class SuccessTest {
        @ParameterizedTest
        @ValueSource(strings = {"U","D"})
        void 이동가능한_칸으로_전진(String movingBlock) {
            // when
            defaultBridgeGame.move(movingBlock);

            // then
        }
    }

    @Nested
    class ExceptionTest {
        @ParameterizedTest
        @ValueSource(strings = {"S","1"})
        void 범위를_벗어나는_다리_길이로_다리생성시_오류발생(String movingBlock) {
            // when & then
            assertThatThrownBy(() -> defaultBridgeGame.move(movingBlock))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ErrorMessage.INVALID_MOVING_BLOCK.getMessage());
        }
    }
}
