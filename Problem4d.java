import java.util.Stack;
import java.util.*;

class avlTree{

        class Node{
                int key, size;
                Node left, right;
        };

        Node root;
        Node nextVal, prevVal;

        avlTree(){
                root = null;
        }

        int heightCheck(Node root){
                if(root == null)
                        return 0;

                return root.size;
        }

        int max(int a, int b){
                return (a > b) ? a : b;
        }

        Node rightRotate(Node root){
                Node curr = root.left;
                Node next = curr.right;

                curr.right = root;
                root.left = next;

                root.size = max(heightCheck(root.left), heightCheck(root.right)) + 1;
                curr.size = max(heightCheck(curr.left), heightCheck(curr.right)) + 1;
                return curr;
        }

        Node leftRotate(Node root){
                Node curr = root.right;
                Node next = curr.left;

                curr.left = root;
                root.right = next;

                root.size = max(heightCheck(root.left), heightCheck(root.right)) + 1;
                curr.size = max(heightCheck(curr.left), heightCheck(curr.right)) + 1;
                return curr;
        }

        int balanceCheck(Node root){
                if(root == null)
                        return 0;
                return heightCheck(root.left)-heightCheck(root.right);
        }

        Node newNode(int data){
                Node temp = new Node();

                temp.key = data;
                temp.left = null;
                temp.right = null;
                temp.size = 1;

                return temp;
        }

        Node insert(Node root, int key){

                Node newnode = newNode(key);
                Node curr = root;
                Node next = null;

                while(curr != null){
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
                else if(key < next.key){
                        next.left = newnode;
                }
                else{
                        next.right = newnode;
                }

                next.size = 1 + max(heightCheck(next.left), heightCheck(next.right));
                int balance = balanceCheck(next);

                if(balance > 1 && key < next.left.key){
                        return rightRotate(next);
                }
                if(balance < -1 && key > next.right.key){
                        return leftRotate(next);
                }
                if(balance > 1 && key > next.left.key){
                        next.left = leftRotate(next.left);
                        return rightRotate(next);
                }
                if(balance < -1 && key < next.right.key){
                        next.right = rightRotate(next.right);
                        return leftRotate(next);
                }

                return next;

        }

        Node minVal(Node root){
                Node curr = root;
                while(curr.left != null){
                        curr = curr.left;
                }

                return curr;
        }

        Node maxVal(Node root){
                Node curr = root;
                while(curr.right != null){
                        curr = curr.right;
                }

                return curr;
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
                                if(curr.left == null){
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

                next.size = 1 + max(heightCheck(next.left), heightCheck(next.right));

                int balance = balanceCheck(next);

                if(balance > 1 && balanceCheck(next.left) >= 0){
                        rightRotate(next);
                        return 1;
                }
                if(balance < -1 && balanceCheck(next.right) <= 0){
                        leftRotate(next);
                        return 1;
                }
                if(balance > 1 && balanceCheck(next.left) < 0){
                        next.left = leftRotate(next.left);
                        rightRotate(next);
                        return 1;
                }
                if(balance < -1 && balanceCheck(next.right) > 0){
                        next.right = rightRotate(next.right);
                        leftRotate(next);
                        return 1;
                }

                return 1;
        }

        void findNext(Node root, int key){
                Node curr = root;
                while(curr != null){
                        if(curr.key == key){
                                if(curr.right != null){
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

        void findPrev(Node root, int key){
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

        void preOrder(Node root){ //Avl needs PreOrder
                if(root == null){
                        return;
                }
                Stack<Node> stack = new Stack();
                stack.push(root);

                while(!stack.empty()){
                        Node curr = stack.pop();
                        System.out.print(curr.key + " ");

                        if(curr.right != null){
                                stack.push(curr.right);
                        }
                        if(curr.left != null){
                                stack.push(curr.left);
                        }
                }
        }

        public static void main(String[] args){
                avlTree tree = new avlTree();
                Node tempCheck;
                Node temp = null;

                temp = tree.insert(temp, 10);
                tree.insert(temp, 15);
                tree.insert(temp, 5);
                tree.insert(temp, 4);
                tree.insert(temp, 12);
                tree.preOrder(temp);
                System.out.println();
                tree.remove(temp, 12);

                tree.preOrder(temp);
                System.out.println();

                tree.findPrev(temp, 5);
                if(tree.prevVal != null)
                        System.out.println(tree.prevVal.key);
                else
                        System.out.println("0");
                tree.findNext(temp, 10);
                if(tree.nextVal != null)
                        System.out.println(tree.nextVal.key);
                else
                        System.out.println("0");

                tempCheck = tree.minVal(temp);
                System.out.println("Min: " + tempCheck.key);
                tempCheck = tree.maxVal(temp);
                System.out.println("Max: " + tempCheck.key);

        }

}
