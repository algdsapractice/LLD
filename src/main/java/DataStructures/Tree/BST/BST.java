package DataStructures.Tree.BST;

import DataStructures.Tree.BinaryTree.BinaryTree;

public class BST {

    public static class Node{
         Integer data;
         Node right;
         Node left;
        public Node(Integer data) {
            this.data = data;
            this.left = null;
            this.right = null;

        }

        public Node(Integer data ,Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }


    public static int maxValue(Node node){

        if(node==null){
            return Integer.MIN_VALUE;
        }
        int currentNodeValue = node.data;
        int leftMax= maxValue(node.left);
        int rightMax=maxValue(node.right);
        int max = Math.max(leftMax,rightMax);
        return Math.max(currentNodeValue,max);
    }

    public static  void display(Node node){

        if(null==node){
            return;
        }
        String str = "";
        if(null!=node.left){
            str  = str + node.left.data + " ";
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
        display(node.left);
        // right node in recursion
        display(node.right);

    }

    public static void main(String[] args) {

        Integer[] arr = {15,25,35,50,65,75,90};
        int low = 0;
        int high = arr.length;

        Node root = buildATree(arr,low,high);
        display(root);

    }

    public static Node buildATree(Integer[] arr , int low , int high) {
        // low + high /2
        //15,25,35,50,65,75,90
        if(low>=high){
            return null;
        }
        int mid = (low+high)/2;
        Integer data = arr[mid];

        Node leftNode = buildATree(arr, low, mid-1);
        Node rightNode= buildATree(arr, mid+1, high);

        Node root = new Node(data,leftNode,rightNode);

        return root;

    }


}
