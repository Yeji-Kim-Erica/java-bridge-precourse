package bridge.model;

import bridge.error.ErrorMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BridgeGameTest {
    private final List<String> defaultBridge = List.of("U","U","U","D","D","U","D","U");
    private BridgeGame defaultBridgeGame;

    @BeforeEach
    void setUp() {
        defaultBridgeGame = new BridgeGame(defaultBridge);
    }

    @Nested
    class SuccessTest {
        @ParameterizedTest
        @CsvSource(value = {"U,O", "D,X"})
        void 이동가능한_칸으로_전진(String movingBlock, String result) {
            // when
            defaultBridgeGame.move(movingBlock);

            // then
            assertThat(defaultBridgeGame.getGameResult()).containsExactly(result);
        }

        @Test
        void 다리_끝까지_건너면_게임_종료() {
            // when
            for (String block : defaultBridge) {
                defaultBridgeGame.move(block);
            }

            // then
            assertThat(defaultBridgeGame.isOver()).isTrue();
        }

        @Test
        void 다리_건너기_실패시_게임_종료() {
            // given
            List<String> movingBlocks = List.of("U","D");

            // when
            for (String block : movingBlocks) {
                defaultBridgeGame.move(block);
            }

            // then
            assertThat(defaultBridgeGame.isOver()).isTrue();
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
