package company.netcraker;


import java.util.Stack;

//
public class Solution2 {

    public static Stack<Integer> s1 = new Stack<Integer>();
    public static Stack<Integer> s2 = new Stack<Integer>();

    public static void enqueue(Integer data) {
        s1.push(data);
    }

    public static Integer deQueue() {

        exchange(s1,s2);
        int data = s2.pop();
        if(s2.isEmpty()) {
            exchange(s2,s1);
        }
        return data;
    }

    private static void exchange(Stack<Integer> stack1, Stack<Integer> stack2 ) {
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
    }

    public static void main(String[] args) {

        Solution2.enqueue(1);
        Solution2.enqueue(2);
        Solution2.enqueue(3);
        Solution2.enqueue(4);

        Solution2.enqueue(5);

        System.out.println(Solution2.deQueue());
        System.out.println(Solution2.deQueue());
        System.out.println(Solution2.deQueue());


    }
}
