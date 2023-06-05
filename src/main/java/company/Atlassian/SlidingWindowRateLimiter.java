package company.Atlassian;

import java.util.LinkedList;
import java.util.Queue;


public class SlidingWindowRateLimiter {

    private Queue<Long> slidingWindow;

    private int noOfRequestCapacity;
    private int threshold;

    public SlidingWindowRateLimiter(int noOfRequestCapacity, int threshold) {
        this.slidingWindow = new LinkedList<>();
        this.noOfRequestCapacity = noOfRequestCapacity;
        this.threshold = threshold;
    }



   public boolean isPersonAllowed()
   {

       long currentTime= System.currentTimeMillis();
       checkAndModifyWindow(currentTime);
       if(slidingWindow.size()<noOfRequestCapacity){
           slidingWindow.offer(currentTime);
           return true;
       }
       return false;

   }

    private void checkAndModifyWindow(long currentTime) {
        if(slidingWindow.isEmpty()) return;
       long delta = (currentTime-slidingWindow.peek())/1000;
       while( !slidingWindow.isEmpty() && delta>threshold ){
           slidingWindow.poll();
           delta =(currentTime-slidingWindow.peek())/1000;
       }
    }


}
