public class SmsSender extends NotificationSender {
    public SmsSender(AuditLog audit) { super(audit); }

    @Override
    public SendResult send(Notification n) {
        // Honor base contract: handle any notification without throwing
        // SMS channel doesn't support subject, only body
        System.out.println("SMS -> to=" + n.phone + " body=" + n.body);
        audit.add("sms sent");
        return SendResult.success();
    }
}
