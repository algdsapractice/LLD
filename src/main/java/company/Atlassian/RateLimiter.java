package company.Atlassian;

public class RateLimiter implements IRateLimiter{

    private UserBucket bucket;
    private Integer id;

    public RateLimiter(int id) {
        this.id=id;
        this.bucket = new UserBucket(id);
    }

    @Override
    public boolean rateLimiter(int userId) {
        return bucket.rateLimit(id);
    }
}
