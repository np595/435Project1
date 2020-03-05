import java.util.Stack;
import java.util.*;
import java.util.Random;

class avlTree{

        class Node{
                int key, size;
                Node left, right;
        };

        Node root;
        Node nextVal, prevVal;
        int counter = 0;

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

        @SuppressWarnings("unchecked")
        Node rightRotate(Node root){
                Node curr = root.left;
                Node next = curr.right;

                counter = counter + 1;
                curr.right = root;
                root.left = next;

                root.size = max(heightCheck(root.left), heightCheck(root.right)) + 1;
                curr.size = max(heightCheck(curr.left), heightCheck(curr.right)) + 1;
                return curr;
        }

        @SuppressWarnings("unchecked")
        Node leftRotate(Node root){
                Node curr = root.right;
                Node next = curr.left;

                counter = counter + 1;
                curr.left = root;
                root.right = next;

                root.size = max(heightCheck(root.left), heightCheck(root.right)) + 1;
                curr.size = max(heightCheck(curr.left), heightCheck(curr.right)) + 1;
                return curr;
        }

        @SuppressWarnings("unchecked")
        int balanceCheck(Node root){
                if(root == null)
                        return 0;
                counter = counter + 1;
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

        @SuppressWarnings("unchecked")
        Node insert(Node root, int key){

                Node newnode = newNode(key);
                Node curr = root;
                Node next = null;

                while(curr != null){
                        next = curr;
                        if(key < curr.key){
                                counter = counter + 1;
                                curr = curr.left;
                        }
                        else{
                                counter = counter + 1;
                                curr = curr.right;
                        }
                }

                if(next == null)
                        next = newnode;
                else if(key < next.key){
                        counter = counter + 1;
                        next.left = newnode;
                }
                else{
                        counter = counter + 1;
                        next.right = newnode;
                }

                counter = counter + 1;
                next.size = 1 + max(heightCheck(next.left), heightCheck(next.right));
                int balance = balanceCheck(next);

                if(balance > 1 && key < next.left.key){
                        counter = counter + 1;
                        return rightRotate(next);
                }
                if(balance < -1 && key > next.right.key){
                        counter = counter + 1;
                        return leftRotate(next);
                }
                if(balance > 1 && key > next.left.key){
                        counter = counter + 1;
                        next.left = leftRotate(next.left);
                        return rightRotate(next);
                }
                if(balance < -1 && key < next.right.key){
                        counter = counter + 1;
                        next.right = rightRotate(next.right);
                        return leftRotate(next);
                }

                return next;

        }

        @SuppressWarnings("unchecked")
        Node minVal(Node root){
                Node curr = root;
                while(curr.left != null){
                        counter = counter + 1;
                        curr = curr.left;
                }

                return curr;
        }

        @SuppressWarnings("unchecked")
        Node maxVal(Node root){
                Node curr = root;
                while(curr.right != null){
                        counter = counter + 1;
                        curr = curr.right;
                }

                return curr;
        }

        @SuppressWarnings("unchecked")
        int remove(Node root, int key){
                if(root == null)
                        return 0;

                Node parent = null;
                Node curr = root;
                Node next = null;

                while(true){
                        if(curr.key == key)
                                next = curr;
                        if(key < curr.key){
                                counter = counter + 1;
                                if(curr.left == null){
                                        break;
                                }
                                parent = curr;
                                curr = curr.left;
                        }
                        else{
                                counter = counter + 1;
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
                                        counter = counter + 1;
                                        parent.left = curr.right;
                                }
                                else{
                                        counter = counter + 1;
                                        parent.right = curr.left;
                                        curr = null;
                                }
                        }
                }

                counter = counter + 1;
                next.size = 1 + max(heightCheck(next.left), heightCheck(next.right));

                int balance = balanceCheck(next);

                if(balance > 1 && balanceCheck(next.left) >= 0){
                        counter = counter + 1;
                        rightRotate(next);
                        return 1;
                }
                if(balance < -1 && balanceCheck(next.right) <= 0){
                        counter = counter + 1;
                        leftRotate(next);
                        return 1;
                }
                if(balance > 1 && balanceCheck(next.left) < 0){
                        counter = counter + 1;
                        next.left = leftRotate(next.left);
                        rightRotate(next);
                        return 1;
                }
                if(balance < -1 && balanceCheck(next.right) > 0){
                        counter = counter + 1;
                        next.right = rightRotate(next.right);
                        leftRotate(next);
                        return 1;
                }

                return 1;
        }

        @SuppressWarnings("unchecked")
        void findNext(Node root, int key){
                Node curr = root;
                while(curr != null){
                        if(curr.key == key){
                                if(curr.right != null){
                                        counter = counter + 1;
                                        Node next = curr.right;
                                        while(next.left != null){
                                                counter = counter + 1;
                                                next = next.left;
                                        }
                                        nextVal = next;
                                        break;
                                }
                        }
                        else if(root.key > key){
                                counter = counter + 1;
                                nextVal = curr;
                                curr = curr.left;
                        }
                        else{
                                counter = counter + 1;
                                break;
                        }
                }
        }

        @SuppressWarnings("unchecked")
        void findPrev(Node root, int key){
                Node curr = root;
                while(curr != null){
                        if(curr.key == key){
                                if(curr.left != null){
                                        counter = counter + 1;
                                        Node next = curr.left;
                                        while(next != null){
                                                counter = counter + 1;
                                                next = next.right;
                                        }
                                        prevVal = next;
                                        break;
                                }
                        }
                        else if(curr.key < key){
                                counter = counter + 1;
                                prevVal = curr;
                                curr = curr.right;
                        }
                        else{
                                counter = counter + 1;
                                break;
                        }
                }
        }

        @SuppressWarnings("unchecked")
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
                                counter = counter + 1;
                                stack.push(curr.right);
                        }
                        if(curr.left != null){
                                counter = counter + 1;
                                stack.push(curr.left);
                        }
                }
        }

        @SuppressWarnings("unchecked")
        Node getRandomArray(int size){
                Random rand = new Random();
                Node temp = null;
                Node tempCheck;

                int i, j;
                int[] arr = new int[size];
                for(i = 0; i < size; i++){
                        arr[i] = rand.nextInt(100 + 1);
                }
                temp = insert(temp, arr[0]);
                for(j = 1; j < size; j++){
                        insert(temp, arr[j]);
                }
                preOrder(temp);
                System.out.println();

                int randomInt = rand.nextInt(size);
                remove(temp, arr[randomInt]);

                preOrder(temp);
                System.out.println();

                int findNextInt = rand.nextInt(size);
                int findPrevInt = rand.nextInt(size);

                findPrev(temp, arr[findNextInt]);
                if(prevVal != null)
                        System.out.println(prevVal.key);
                else
                        System.out.println("0");

                findNext(temp, arr[findPrevInt]);
                if(nextVal != null)
                        System.out.println(nextVal.key);
                else
                        System.out.println("0");

                tempCheck = minVal(temp);
                System.out.println("Min: " + tempCheck.key);
                tempCheck = maxVal(temp);
                System.out.println("Max: " + tempCheck.key);

                System.out.println("Transverse Count: " + counter);

                return temp;
        }

        public static void main(String[] args){
                avlTree tree = new avlTree();
                Node tempCheck;
                Node temp = null;

                int size = 10;

                temp = tree.getRandomArray(size);

        }

}
