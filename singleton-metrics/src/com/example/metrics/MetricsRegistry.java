package com.example.metrics;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Thread-safe Singleton for global metrics registry using enum pattern.
 *
 * Enum singleton provides:
 * - Thread-safe initialization (JVM guarantee)
 * - Reflection-proof (JVM prevents enum reflection attacks)
 * - Serialization-proof (JVM preserves singleton on deserialization)
 * - Simple and concise implementation
 * - Joshua Bloch's recommended approach (Effective Java)
 */
public enum MetricsRegistry {
    INSTANCE;

    private final Map<String, Long> counters = new HashMap<>();

    public synchronized void setCount(String key, long value) {
        counters.put(key, value);
    }

    public synchronized void increment(String key) {
        counters.put(key, getCount(key) + 1);
    }

    public synchronized long getCount(String key) {
        return counters.getOrDefault(key, 0L);
    }

    public synchronized Map<String, Long> getAll() {
        return Collections.unmodifiableMap(new HashMap<>(counters));
    }
}
