package bridge.error;

/**
 * 오류 메시지를 정의한 enum 클래스
 */
public enum ErrorMessage {
    BRIDGE_SIZE_NULL_OR_BLANK("다리의 길이는 공백이거나 비어 있을 수 없습니다."),
    BRIDGE_SIZE_NOT_NUMERIC("다리의 길이는 숫자여야 합니다."),
    BRIDGE_SIZE_OUT_OF_RANGE("다리 길이는 3부터 20 사이의 숫자여야 합니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
