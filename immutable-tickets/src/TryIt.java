import com.example.tickets.IncidentTicket;
import com.example.tickets.TicketService;

import java.util.List;

/**
 * Demo showing the power of immutability.
 *
 * After refactor:
 * - No setters available (won't compile if we try to mutate)
 * - External attempts to modify tags have no effect
 * - Service "updates" return NEW ticket instances
 */
public class TryIt {

    public static void main(String[] args) {
        TicketService service = new TicketService();

        // Create initial ticket using Builder (via service)
        IncidentTicket t = service.createTicket("TCK-1001", "reporter@example.com", "Payment failing on checkout");
        System.out.println("Created: " + t);

        // Service methods now return NEW tickets instead of mutating
        IncidentTicket t2 = service.assign(t, "agent@example.com");
        IncidentTicket t3 = service.escalateToCritical(t2);

        System.out.println("\nOriginal ticket (unchanged): " + t);
        System.out.println("After assignment (new instance): " + t2);
        System.out.println("After escalation (new instance): " + t3);

        // Try to "hack" the tags - this is now safe!
        List<String> tags = t3.getTags();
        try {
            tags.add("HACKED_FROM_OUTSIDE");
            System.out.println("\n❌ This line should not print - tags should be unmodifiable!");
        } catch (UnsupportedOperationException e) {
            System.out.println("\n✓ Tags are properly protected! Cannot modify: " + e.getMessage());
        }
        System.out.println("Ticket after attempted external mutation: " + t3);

        // Demonstrate Builder pattern directly
        System.out.println("\n--- Builder Pattern Demo ---");
        IncidentTicket customTicket = new IncidentTicket.Builder()
                .id("TCK-2000")
                .reporterEmail("user@company.com")
                .title("Custom ticket with all options")
                .description("This shows all builder options")
                .priority("HIGH")
                .addTag("CUSTOM")
                .addTag("DEMO")
                .assigneeEmail("support@company.com")
                .customerVisible(true)
                .slaMinutes(120)
                .source("WEBHOOK")
                .build();

        System.out.println("Custom ticket: " + customTicket);

        // Demonstrate toBuilder() for creating modified copies
        System.out.println("\n--- Using toBuilder() ---");
        IncidentTicket modifiedTicket = customTicket.toBuilder()
                .priority("CRITICAL")
                .addTag("URGENT")
                .build();

        System.out.println("Original: " + customTicket);
        System.out.println("Modified copy: " + modifiedTicket);

        // Demonstrate validation
        System.out.println("\n--- Validation Demo ---");
        try {
            IncidentTicket invalid = new IncidentTicket.Builder()
                    .id("TCK-3000")
                    .reporterEmail("not-an-email")  // Invalid email
                    .title("Test")
                    .build();
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Validation caught invalid email: " + e.getMessage());
        }

        try {
            IncidentTicket invalid = new IncidentTicket.Builder()
                    .id("INVALID ID WITH SPACES")  // Invalid ID format
                    .reporterEmail("user@company.com")
                    .title("Test")
                    .build();
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Validation caught invalid ID: " + e.getMessage());
        }

        System.out.println("\n✓ All immutability guarantees are working correctly!");
    }
}
