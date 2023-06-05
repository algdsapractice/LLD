package company.Atlassian;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SlidingWindowRateLimiterTest {

    private RateLimiter rateLimiter;

    @BeforeEach
    public void setup() {
        rateLimiter = new RateLimiter(123);
    }

    @Test
    public void testRateLimiter_withValidUserId_shouldReturnTrue() {
        int userId = 456;
        assertTrue(rateLimiter.rateLimiter(userId));
    }

    @Test
    public void testRateLimiter_withInvalidUserId_shouldReturnFalse() {
        int userId = 789;
        assertTrue(rateLimiter.rateLimiter(userId));
        assertTrue(rateLimiter.rateLimiter(userId));
        assertTrue(rateLimiter.rateLimiter(userId));
        assertTrue(rateLimiter.rateLimiter(userId));
        assertTrue(rateLimiter.rateLimiter(userId));
        assertFalse(rateLimiter.rateLimiter(userId));
    }
}