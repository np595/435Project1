import java.util.Stack;
import java.util.*;

class Problem1Iterative{

        class Node{
                int key;
                Node left, right;
        };

        Node newNode(int key){
                Node temp = new Node();

                temp.key = data;
                temp.left = null;
                temp.right = null;

                return temp;
        }

        Node root;
        Node nextVal, prevVal;

        Problem2Iterative(){

                root = null;

        }

        Node insert(Node root, int key){
                Node newnode = newNode(key);

                Node curr = root;
                Node next = null;

                while(curr != null){ //Loops through the tree until the spot is found for a new insert
                        next = curr;
                        if(key < curr.key){
                                curr = curr.left;
                        }
                        else{
                                curr = curr.right;
                        }
                }
                if(next == null)
                        next = newnode;

                else if(key < next.key){ //Checks if the left is open first
                        next.left = newnode;
                }

                else{   //Else place it in the right
                        next.right = newnode;
                }

                return next;
        }

        int remove(Node root, int key){
                if(root == null)
                        return 0;

                Node parent = null;
                Node curr = root;
                Node next = null;

                while(true){
                        if(curr.key == key)
                                next = curr;
                        else if(key < curr.key){
                                if(curr.left == null{
                                        break;
                                }
                                parent = curr;
                                curr = curr.left;
                        }
                        else{
                                if(curr.right == null){
                                        break;
                                }
                                parent = curr;
                                curr = curr.right;
                        }
                }

                if(next == null){
                        return 0;
                }
                else{
                        if(parent == null){
                                curr = null;
                                root = null;
                        }
                        else{
                                next.key = curr.key;
                                if(parent.left == curr){
                                        parent.left = curr.right;
                                }
                                else{
                                        parent.right = curr.left;
                                        curr = null;
                                }
                        }
                }
                return 1;
        }

        Node findNext(Node root, int key){
                Node curr = root;
                while(curr != null){
                        if(curr.key == key){
                                (curr.right != null){
                                        Node next = curr.right;
                                        while(next.left != null){
                                                next = next.left;
                                        }
                                        nextVal = next;
                                        break;
                                }
                        }
                        else if(root.key > key){
                                nextVal = curr;
                                curr = curr.left;
                        }
                        else{
                                break;
                        }
                }
        }

        Node findPrev(Node root, int key){
                Node curr = root;
                while(curr != null){
                        if(curr.key == key){
                                if(curr.left != null){
                                        Node next = curr.left;
                                        while(next != null){
                                                next = next.right;
                                        }
                                        prevVal = next;
                                        break;
                                }
                        }
                        else if(curr.key < key){
                                prevVal = curr;
                                curr = curr.right;
                        }
                        else{
                                break;
                        }
                }
        }

        Node findMin(Node root){
                Node curr = root;

                while(curr.left != null)
                        curr = curr.left;

                return curr;
        }

        Node findMax(Node root){
                Node curr = root;

                while(curr.right != null)
                        curr = curr.right;

                return curr;
        }

        void inOrder(Node root){
                if(root == null)
                        return;

                Stack<Node> stack = new Stack<Node>();
                Node curr = root;

                while(curr != null || stack.size() > 0){
                        while(curr != null){
                                stack.push(curr);
                                curr = curr.left;
                        }
                        curr = stack.pop();

                        System.out.print(curr.data + " ");

                        curr = curr.right;
                }
        }

        public static void main(String[] args){
                Problem1Iterative tree = new Problem1Iterative();
                Node temp = null;
                Node tempCheck;

                temp = tree.insert(temp, 5);
                tree.insert(temp, 10);
                tree.insert(temp, 15);
                tree.insert(temp, 23);
                tree.insert(temp, 12);
                tree.insert(temp, 24);

                tree.inOrder(temp);
                System.out.println();

                tree.remove(temp, 10);

                tree.inOrder(temp);
                System.out.println();

                tempCheck = tree.findMin(root);
                System.out.println("Min: " + tempCheck.key);
                tempCheck = tree.findMax(root);
                System.out.println("Max: " + tempCheck.key);

                tree.findPrev(temp, 15);
                if(tree.prevVal != null)
                        System.out.println(tree.prevVal.key);
                else
                        System.out.println("0");

                tree.findNext(temp, 10);
                if(tree.nextVal != null)
                        System.out.println(tree.nextVal.key);
                else
                        System.out.println("0");
        }

}
