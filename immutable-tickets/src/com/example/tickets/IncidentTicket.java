package com.example.tickets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Immutable incident ticket with Builder pattern.
 * - All fields are private and final
 * - No setters (immutable after creation)
 * - Defensive copying for collections (tags)
 * - All validation centralized in Builder.build()
 * - Use toBuilder() to create modified copies
 */
public final class IncidentTicket {

    // All fields are private and final for immutability
    private final String id;
    private final String reporterEmail;
    private final String title;
    private final String description;
    private final String priority;
    private final List<String> tags;
    private final String assigneeEmail;
    private final boolean customerVisible;
    private final Integer slaMinutes;
    private final String source;

    // Private constructor - only Builder can create instances
    private IncidentTicket(Builder builder) {
        this.id = builder.id;
        this.reporterEmail = builder.reporterEmail;
        this.title = builder.title;
        this.description = builder.description;
        this.priority = builder.priority;
        // Defensive copy: create unmodifiable list from builder's tags
        this.tags = builder.tags != null ?
            Collections.unmodifiableList(new ArrayList<>(builder.tags)) :
            Collections.emptyList();
        this.assigneeEmail = builder.assigneeEmail;
        this.customerVisible = builder.customerVisible;
        this.slaMinutes = builder.slaMinutes;
        this.source = builder.source;
    }

    // Getters only (no setters)
    public String getId() { return id; }
    public String getReporterEmail() { return reporterEmail; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getPriority() { return priority; }
    // Returns unmodifiable list - safe from external mutation
    public List<String> getTags() { return tags; }
    public String getAssigneeEmail() { return assigneeEmail; }
    public boolean isCustomerVisible() { return customerVisible; }
    public Integer getSlaMinutes() { return slaMinutes; }
    public String getSource() { return source; }

    /**
     * Create a new Builder to modify this ticket (creates a new instance).
     * This is the proper way to "update" an immutable object.
     */
    public Builder toBuilder() {
        return new Builder()
            .id(this.id)
            .reporterEmail(this.reporterEmail)
            .title(this.title)
            .description(this.description)
            .priority(this.priority)
            .tags(new ArrayList<>(this.tags)) // Make a mutable copy for builder
            .assigneeEmail(this.assigneeEmail)
            .customerVisible(this.customerVisible)
            .slaMinutes(this.slaMinutes)
            .source(this.source);
    }

    @Override
    public String toString() {
        return "IncidentTicket{" +
                "id='" + id + '\'' +
                ", reporterEmail='" + reporterEmail + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", priority='" + priority + '\'' +
                ", tags=" + tags +
                ", assigneeEmail='" + assigneeEmail + '\'' +
                ", customerVisible=" + customerVisible +
                ", slaMinutes=" + slaMinutes +
                ", source='" + source + '\'' +
                '}';
    }

    /**
     * Fluent Builder for IncidentTicket.
     * Required fields: id, reporterEmail, title
     * Optional fields: all others
     * All validation is centralized in build()
     */
    public static class Builder {
        // Required fields
        private String id;
        private String reporterEmail;
        private String title;

        // Optional fields with defaults
        private String description;
        private String priority;
        private List<String> tags = new ArrayList<>();
        private String assigneeEmail;
        private boolean customerVisible = false;
        private Integer slaMinutes;
        private String source;

        public Builder() {}

        // Fluent setters for required fields
        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder reporterEmail(String reporterEmail) {
            this.reporterEmail = reporterEmail;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        // Fluent setters for optional fields
        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder priority(String priority) {
            this.priority = priority;
            return this;
        }

        public Builder tags(List<String> tags) {
            this.tags = tags != null ? tags : new ArrayList<>();
            return this;
        }

        public Builder addTag(String tag) {
            if (tag != null) {
                this.tags.add(tag);
            }
            return this;
        }

        public Builder assigneeEmail(String assigneeEmail) {
            this.assigneeEmail = assigneeEmail;
            return this;
        }

        public Builder customerVisible(boolean customerVisible) {
            this.customerVisible = customerVisible;
            return this;
        }

        public Builder slaMinutes(Integer slaMinutes) {
            this.slaMinutes = slaMinutes;
            return this;
        }

        public Builder source(String source) {
            this.source = source;
            return this;
        }

        /**
         * Build and validate the IncidentTicket.
         * All validation is centralized here.
         */
        public IncidentTicket build() {
            // Validate required fields
            Validation.requireTicketId(id);
            Validation.requireEmail(reporterEmail, "reporterEmail");
            Validation.requireNonBlank(title, "title");
            Validation.requireMaxLen(title, 80, "title");

            // Validate optional fields if present
            if (assigneeEmail != null && !assigneeEmail.trim().isEmpty()) {
                Validation.requireEmail(assigneeEmail, "assigneeEmail");
            }

            if (priority != null) {
                Validation.requireOneOf(priority, "priority", "LOW", "MEDIUM", "HIGH", "CRITICAL");
            }

            Validation.requireRange(slaMinutes, 5, 7200, "slaMinutes");

            return new IncidentTicket(this);
        }
    }
}
