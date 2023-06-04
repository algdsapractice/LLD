package company.Atlassian;

import java.util.Date;

public class Customer {

    private   int counter=5;
    private Date registeredTime;

    public Date getRegisteredTime() {
        return registeredTime;
    }

    public void setRegisteredTime(Date registeredTime) {
        this.registeredTime = registeredTime;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public Customer(Date registeredTime) {
        this.registeredTime = registeredTime;
    }
}
