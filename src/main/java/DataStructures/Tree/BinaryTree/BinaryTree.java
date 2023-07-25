package DataStructures.Tree.BinaryTree;

import java.util.Stack;

public class BinaryTree {
    public static class Node{
        Integer data;
        Node right;
        Node Left;
        public Node(Integer data) {
            this.data = data;
            this.right = null;
            Left = null;
        }
    }

   public static class Pair{
        Node node;
        int state;

       public Pair(Node node, int state) {
           this.node = node;
           this.state = state;
       }
   }

    public static  void display(Node node){

        if(null==node){
            return;
        }
        String str = "";
        if(null!=node.Left){
            str  = str + node.Left.data + " ";
        } else{
            str  = str + ".";
        }
        str  = str + "<-" + node.data + " -> ";

        if(null!= node.right){
            str  = str + node.right.data + " ";
        }
        else{
            str  = str + ".";
        }
        System.out.println(str);
        // left node in recursion
        display(node.Left);
        // right node in recursion
        display(node.right);

    }

    public static void main(String[] args) {
        Integer[] arr = {50,25,15, null , null , 35, 30 , null, null,null,75,60,null,70,null,null,90,null,null};
        Stack<Pair> stack = new Stack<>();
        Node root = new Node(arr[0]);
        Pair pair = new Pair(root,1);
        // 50 -1
        stack.push(pair);
        int index=0;
        while (!stack.isEmpty()){
            Pair topPair = stack.peek();
            if(topPair.state==1){
                index++;
                if(arr[index]!=null)
                {
                    Node leftNode= new Node(arr[index]);
                    topPair.node.Left=leftNode;
                    Pair pair1 = new Pair(leftNode,1);
                    stack.push(pair1);
                }
                else
                {
                    topPair.node.Left=null;
                }
            topPair.state++;
            } else if (topPair.state==2) {
                index++;
                if(arr[index]!=null)
                {
                    Node leftNode= new Node(arr[index]);
                    topPair.node.right=leftNode;
                    Pair pair1 = new Pair(leftNode,1);
                    stack.push(pair1);
                }
                else
                {
                    topPair.node.right=null;
                }

                topPair.state++;
            }else{
                stack.pop();
            }

        }
        display(root);


    }
}
