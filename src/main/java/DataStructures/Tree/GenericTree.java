package DataStructures.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
public class GenericTree {
    public static class Node{
        int data;
        List<Node> children ;
        Node(int data){
            this.data=data;
            children = new ArrayList<>();
        }

    }

    public static void display(Node node){
        String str = node.data + "->";
        List<Node> childrens  = node.children;
        for(Node children:childrens){
           str = str + children.data +",";
        }
        str = str+".";
        System.out.println(str);

        for(Node child : childrens){
            display(child);
        }
    }

    public static void main(String[] args) {
        Node root=null;

        int[] arr = {10,20,-1,30,50,-1,60,-1,-1,40,-1,-1};

        Stack<Node> stack = new Stack<>();

        for(int i =0 ; i< arr.length;i++){
            int num = arr[i];

            if(arr[i]==-1){
                stack.pop();
            }
            else
            {
                //10
                Node newNode = new Node(num);

                if(stack.isEmpty()){
                    root=newNode;
                }
                else
                {
                    Node topNodeInStack=stack.peek();
                    topNodeInStack.children.add(newNode);
                }

                stack.push(newNode);

            }

        }

        display(root);


    }

}
