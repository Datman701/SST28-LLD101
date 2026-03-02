package com.example.tickets;

import java.util.ArrayList;
import java.util.List;

/**
 * Service layer that creates tickets.
 *
 * Refactored to work with immutable IncidentTicket:
 * - Uses Builder pattern for ticket creation
 * - No validation here (centralized in Builder.build())
 * - "Updates" return NEW ticket instances (immutability)
 */
public class TicketService {

    /**
     * Create a new ticket with default settings.
     * All validation is handled by Builder.build().
     */
    public IncidentTicket createTicket(String id, String reporterEmail, String title) {
        return new IncidentTicket.Builder()
                .id(id)
                .reporterEmail(reporterEmail)
                .title(title)
                .priority("MEDIUM")
                .source("CLI")
                .customerVisible(false)
                .addTag("NEW")
                .build(); // Validation happens here
    }

    /**
     * "Escalate" ticket by creating a new instance with updated priority and tag.
     * Returns a NEW ticket (does not mutate the original).
     */
    public IncidentTicket escalateToCritical(IncidentTicket t) {
        List<String> updatedTags = new ArrayList<>(t.getTags());
        updatedTags.add("ESCALATED");

        return t.toBuilder()
                .priority("CRITICAL")
                .tags(updatedTags)
                .build();
    }

    /**
     * "Assign" ticket by creating a new instance with assignee.
     * Returns a NEW ticket (does not mutate the original).
     */
    public IncidentTicket assign(IncidentTicket t, String assigneeEmail) {
        return t.toBuilder()
                .assigneeEmail(assigneeEmail)
                .build(); // Validation happens here
    }
}
