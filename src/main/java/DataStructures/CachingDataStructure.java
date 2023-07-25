package DataStructures;
import java.util.Optional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;

interface TimeProvider {
    long getMillis();
}

public class CachingDataStructure {
    private int maxSize;
    private TimeProvider timeProvider;
    private Map<String, CacheEntry> cacheMap;
    private PriorityQueue<CacheEntry> cacheQueue;

    public CachingDataStructure(TimeProvider timeProvider, int maxSize) {
        this.timeProvider = timeProvider;
        this.maxSize = maxSize;
        this.cacheMap = new HashMap<>();
        this.cacheQueue = new PriorityQueue<>();
    }

    public void put(String key, String value, long timeToLeaveInMilliseconds) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Key and value cannot be null");
        }

        if (timeToLeaveInMilliseconds <= 0) {
            throw new IllegalArgumentException("Time-to-leave cannot be negative");
        }
        long currentTime = timeProvider.getMillis();
        long expirationTime = currentTime + timeToLeaveInMilliseconds;

        if (cacheMap.containsKey(key)) {
            CacheEntry existingEntry = cacheMap.get(key);
            cacheQueue.remove(existingEntry);
        } else if (cacheMap.size() == maxSize) {
            CacheEntry leastRecentEntry = cacheQueue.poll();
            cacheMap.remove(leastRecentEntry.getKey());
        }

        CacheEntry newEntry = new CacheEntry(key, value, expirationTime);
        cacheMap.put(key, newEntry);
        cacheQueue.offer(newEntry);
    }

    public Optional<String> get(String key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }

        evictExpiredEntries();

        CacheEntry entry = cacheMap.get(key);
        if (entry != null) {
            return Optional.ofNullable(entry.getValue());
        }
        return Optional.empty();
    }

    public int size() {
        evictExpiredEntries();
        return cacheMap.size();
    }

    private void evictExpiredEntries() {
        long currentTime = timeProvider.getMillis();
        while (!cacheQueue.isEmpty()) {
            CacheEntry entry = cacheQueue.peek();
            if (entry.getExpirationTime() <= currentTime) {
                cacheQueue.poll();
                cacheMap.remove(entry.getKey());
            } else {
                break;
            }
        }
    }

    private class CacheEntry implements Comparable<CacheEntry> {
        private String key;
        private String value;
        private long expirationTime;

        CacheEntry(String key, String value, long expirationTime) {
            this.key = key;
            this.value = value;
            this.expirationTime = expirationTime;
        }

        String getKey() {
            return key;
        }

        String getValue() {
            return value;
        }

        long getExpirationTime() {
            return expirationTime;
        }

        @Override
        public int compareTo(CacheEntry other) {
            return Long.compare(this.expirationTime, other.expirationTime);
        }
    }
}

