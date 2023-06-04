package company.Atlassian;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

// Perform rate limiting logic for provided customer ID. Return true if the
// request is allowed, and false if it is not.
//boolean rateLimit(int customerId)
/// 120 mbs  5mbs/
//
//
public class RateLimiter {

    private Map<Integer, Customer> registeredAccounts = new HashMap<>();
    // Check if it is rate limited
    public Boolean rateLimit(Integer cutomerId){
        //result of rate limited Data
        boolean isRateLimited=true;
        Customer customer = new Customer(new Date());
        registeredAccounts.put(cutomerId,customer);
        // validate if the account exist if not initialise it
        if(registeredAccounts.containsKey(cutomerId)){
            Customer customer1= registeredAccounts.get(cutomerId);
            int counter=customer1.getCounter();
            // get the eligibility details
            boolean isEligible =getEligibility(customer1);
            isRateLimited=getLimit(counter,isEligible,customer1);
            resetTimeAndCounter(customer1, isEligible);
        }
        else{
            registeredAccounts.put(cutomerId,customer);

        }
        return isRateLimited;
    }
    // reset the counter
    private void resetTimeAndCounter(Customer customer1, boolean isEligible) {
        if(!isEligible){
            customer1.setRegisteredTime(new Date());
            customer1.setCounter(5);
        }
    }

    private boolean getLimit(int counter, boolean isEligible, Customer customer){
        boolean isEligibleResult= true;
        if(counter==0 ){
            return  isEligibleResult=false;
        }else if (counter!=0 && isEligible){

            customer.setCounter(--counter);
        }
        return isEligibleResult;
    }
    private boolean getEligibility(Customer customer1) {
        Date regDate = customer1.getRegisteredTime();
        long regSecs = regDate.getTime() / 1000L;
        Date currentdate = new Date();
        long currentSecs = currentdate.getTime() / 1000L;
        if(regSecs>currentSecs-60){
            return true;
        }
        else{
            return false;
        }
    }


}
