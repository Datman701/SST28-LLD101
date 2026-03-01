/**
 * Base contract for notification senders:
 * Preconditions:
 *   - n must not be null
 *   - No additional constraints on fields (subject, body, email, phone can be any values)
 * Postconditions:
 *   - Must return SendResult (never null)
 *   - Must not throw exceptions for format/validation issues
 *   - Must audit the attempt
 * Invariant:
 *   - All senders must handle any valid Notification without throwing
 *   - Senders may adapt content to channel constraints but must report outcome
 */
public abstract class NotificationSender {
    protected final AuditLog audit;

    protected NotificationSender(AuditLog audit) {
        this.audit = audit;
    }

    /**
     * Sends a notification via this channel.
     * @param n the notification to send (must not be null)
     * @return result indicating success or failure (never null)
     */
    public abstract SendResult send(Notification n);

    /**
     * Validates if this sender can handle the notification.
     * Default implementation accepts all notifications.
     * @param n the notification
     * @return true if can send, false otherwise
     */
    public boolean canSend(Notification n) {
        return n != null;
    }
}
