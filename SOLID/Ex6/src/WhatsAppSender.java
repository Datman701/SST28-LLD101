public class WhatsAppSender extends NotificationSender {
    public WhatsAppSender(AuditLog audit) { super(audit); }

    @Override
    public SendResult send(Notification n) {
        // Honor base contract: don't throw, return failure result instead
        if (n.phone == null || !n.phone.startsWith("+")) {
            // Return failure instead of throwing
            return SendResult.failure("phone must start with + and country code");
        }
        System.out.println("WA -> to=" + n.phone + " body=" + n.body);
        audit.add("wa sent");
        return SendResult.success();
    }

    @Override
    public boolean canSend(Notification n) {
        return super.canSend(n) &&
               n.phone != null &&
               n.phone.startsWith("+");
    }
}
