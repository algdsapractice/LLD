package company.Atlassian;

import java.util.HashMap;
import java.util.Map;

public class UserBucket {


    Map<Integer,SlidingWindowRateLimiter> bucket;

    public UserBucket(int id) {
        this.bucket = new HashMap<>();
        bucket.put(id, new SlidingWindowRateLimiter(5,1));
    }


    public boolean rateLimit(int id) {

        return  bucket.get(id).isPersonAllowed();
    }
}
