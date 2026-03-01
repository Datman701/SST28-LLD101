public class EmailSender extends NotificationSender {
    public EmailSender(AuditLog audit) { super(audit); }

    @Override
    public SendResult send(Notification n) {
        // Honor base contract: handle any notification without throwing
        // Keep current behavior: truncate long messages for compatibility
        String body = n.body;
        if (body.length() > 40) {
            body = body.substring(0, 40);
        }
        System.out.println("EMAIL -> to=" + n.email + " subject=" + n.subject + " body=" + body);
        audit.add("email sent");
        return SendResult.success();
    }
}
