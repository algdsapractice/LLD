package company.testKr;

/*
You are provided with a list of transactions representing consumers' purchases.
Each transaction can be either approved or rejected based on total sum of approved purchases the consumer made so far.
Specifically the transaction is rejected if total sum of purchases would increase above a certain number - creditLimit.
Otherwise the transaction is approved.

Your task is to process the list of transactions and return list of the rejected ones.

Input
You should write a function called findRejectedTransactions, which takes as input a list
of transactions to process and a creditLimit integer.

List<String> findRejectedTransactions(List<String> transactions, int creditLimit);
Transactions are provided in a CSV format. An example transaction may look this:

"john,doe,john@doe.com,30,TR000"
Each transaction contains consumer details as well as an amount and an id:

first name
last name
email
amount
transaction id
You should expect all the transactions being valid and containing all of the information listed above.

Additionally there is a creditLimit >= 0 defined that is equal to all the consumers.

Output
The function should return a list of Strings listing the transactions that supposed to be rejected
if the transactions were to be processed in a sequential manner following the initial order of elements.

A consumer is uniquely identified by all three of first name, last name and email,
i.e. two persons with equal names but different emails have separate credit line.

Examples
findRejectedTransactions([], 0) returns empty list []
findrejectedtransactions(["john,doe,john@doe.com,200,TR0001"], 200) returns empty list [] - the only John Doe's transaction is approved
findrejectedtransactions(["jane,doe,jane@doe.com,201,TR0001"], 200) returns [TR0001] - the only Jane Doe's transaction is rejected since its amount 201 is greater than creditLimit = 200
findrejectedtransactions(["jane,doe,jane@doe.com,199,TR0001", "jane,doe,jane@doe.com,2,TR0002"], 200) returns [TR0002] as approving the second transaction would make the credit limit exceeded

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TransactionsSampleTest {

    @Test
    public void shouldReturnEmptyListIfThereIsNoTransactions() {
        assertThat(Transactions.findRejectedTransactions(new ArrayList<>(), 0).size(), is(0));
    }

    @Test
    public void shouldReturnEmptyListIfThereIsATransactionWithinCreditLimit() {
        List<String> transactions = arrays.aslist("john,doe,john@doe.com,200,TR0001");

        List<String> rejectedTransactions = Transactions.findRejectedTransactions(transactions, 200);

        assertThat(rejectedTransactions.size(), is(0));
    }

    @Test
    public void shouldReturnTransationThatIsOverCreditLimit() {
        List<String> transactions = arrays.aslist("john,doe,john@doe.com,201,TR0001");

        List<String> rejectedTransactions = Transactions.findRejectedTransactions(transactions, 200);

        assertThat(rejectedTransactions, is(Arrays.asList("TR0001")));
    }

}
 */

import java.util.*;

public class Transactions {


    public static List<String> findRejectedTransactions(List<String> transactions, int creditLimit) {

        Map<String, Integer> map = new HashMap<>();
        List<String> rejected = new ArrayList<String>();

        if(transactions.size()<=0){

            new IllegalArgumentException("Invalid Input");
        }

        for (String transaction : transactions)
        {
            String[] data= transaction.split(",");
            String name = data[0]+data[1]+data[2];
            int amount = Integer.parseInt(data[3]);
            map.put(name, map.getOrDefault(name, 0)+amount);
            int tamount = map.get(name);

            if(tamount>creditLimit){
                 rejected.add(data[4]);
             }
        }

        return rejected;
    }

    public static void main(String[] args) {
        List<String> transactions = Arrays.asList("john,doe,john@doe.com,201,TR0001");

        List<String> rejectedTransactions = Transactions.findRejectedTransactions(transactions, 200);

        rejectedTransactions.forEach(System.out::println);


        List<String> transactions2 = Arrays.asList("john,doe,john@doe.com,200,TR0001");

        List<String> rejectedTransactions2 = Transactions.findRejectedTransactions(transactions2, 200);
        rejectedTransactions2.forEach(System.out::println);

        List<String> rejectedTransactions3 =  Transactions.findRejectedTransactions(new ArrayList<>(), 0);
        rejectedTransactions3.forEach(System.out::println);

    }
}