package company.Atlassian;

public interface IRateLimiter {

    boolean rateLimiter(int userId);
}
