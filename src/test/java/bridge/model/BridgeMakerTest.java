package bridge.model;

import bridge.error.ErrorMessage;
import bridge.util.BridgeRandomNumberGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BridgeMakerTest {
    private BridgeMaker defaultBridgeMaker = new BridgeMaker(new BridgeRandomNumberGenerator());

    @Nested
    class SuccessTest {
        @Test
        void 올바른_다리_길이로_다리생성() {
            // given
            int size = 5;

            // when & then
            assertThat(defaultBridgeMaker.makeBridge(size)).isNotNull();
        }
    }

    @Nested
    class ExceptionTest {
        @ParameterizedTest
        @ValueSource(ints = {0, 1, 21})
        void 범위를_벗어나는_다리_길이로_다리생성시_오류발생(int size) {
            // when & then
            assertThatThrownBy(() -> defaultBridgeMaker.makeBridge(size))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ErrorMessage.BRIDGE_SIZE_OUT_OF_RANGE.getMessage());
        }
    }
}
