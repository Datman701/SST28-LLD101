/**
 * Result of a send operation.
 * Allows senders to report success/failure without throwing exceptions.
 */
class SendResult {
    public final boolean success;
    public final String errorMessage;

    private SendResult(boolean success, String errorMessage) {
        this.success = success;
        this.errorMessage = errorMessage;
    }

    public static SendResult success() {
        return new SendResult(true, null);
    }

    public static SendResult failure(String message) {
        return new SendResult(false, message);
    }
}

// Config class for senders
public class SenderConfig {
    public int maxLen = 160;
}
