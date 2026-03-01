public class PublicityLeadTool implements PublicityManager {
    @Override
    public void postAnnouncement(String message) {
        System.out.println("Announcement posted: \"" + message + "\"");
    }
}
