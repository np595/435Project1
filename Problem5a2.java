import java.util.Random;

class Problem1Recursive {

        class Node {
                int key;
                Node left, right;

                public Node(int item) {
                        key = item;
                        left = null;
                        right = null;
                }
        }

        Node root;
        int prevVal, nextVal;

        Problem1Recursive(){

                root = null;

        }

        Node insert(Node root, int key){

                if(root == null){ //Check if tree is empty and return the number as a new node
                        root = new Node(key);
                        return root;
                }

                if (key < root.key)
                        root.left = insert(root.left, key);
                else if (key > root.key)
                        root.right = insert(root.right, key);

                return root;
        }

        Node remove(Node root, int key){
                if(root == null)
                        return root;

                if(key < root.key)
                        root.left = remove(root.left, key);
                else if(key > root.key)
                        root.right = remove(root.right, key);
                else{
                        if(root.left == null)
                                return root.right;
                        else if(root.right == null)
                                return root.left;

                        root = findMin(root.right);

                        root.right = remove(root.right, root.key);
                }

                return root;
        }

        void findNext(Node root, int key){

                if(root != null){
                        if(root.key == key){
                                if(root.right != null){
                                        Node next = root.right;
                                        while(next.left != null){
                                                next = next.left;
                                        }
                                        nextVal = next.key;
                                }
                        }
                        else if(root.key > key){
                                nextVal = root.key;
                                findNext(root.left, key);
                        }
                }
        }

        void findPrev(Node root, int key){

                if(root != null){
                        if(root.key == key){
                                if(root.left != null){
                                        Node next = root.left;
                                        while(next.right != null){
                                                next = next.right;
                                        }
                                        prevVal = next.key;
                                }
                        }
                }
                else if(root.key < key){
                        prevVal = root.key;
                        findPrev(root.right, key);
                }

        }

        Node findMin(Node root){

                Node curr = root;

                while(curr.left != null){

                        curr = curr.left;

                }

                return curr;

        }

        Node findMax(Node root){

                Node curr = root;

                while(curr.right != null){
                        curr = curr.right;
                }
                return curr;

        }

        void inorder(Node root){
                if(root != null){
                        inorder(root.left);
                        System.out.print(root.key + " ");
                        inorder(root.right);
                }
        }

        Node getRandomArray(Node root, int size){
                Random rand = new Random();
                int i;
                int[] arr = new int[size];
                for(i = 0; i < size; i++){
                        arr[i] = rand.nextInt(100 + 1);
                }
                for(i = 0; i < size; i++){
                        root = insert(root, arr[i]);
                }
                inorder(root);
                System.out.println();
                
                int randomInt = rand.nextInt(size);
                root = remove(root, arr[randomInt]);
                inorder(root);
                System.out.println();

                int findNextInt = rand.nextInt(size);
                int findPrevInt = rand.nextInt(size);

                findNext(root, arr[findNextInt]);
                System.out.println("Next val from " + arr[findNextInt] + " is " + nextVal);

                findPrev(root, arr[findPrevInt]);
                System.out.println("Prev val from " + arr[findPrevInt] + " is " + prevVal);

                return root;
        }

        public static void main(String[] args){
                Problem1Recursive tree = new Problem1Recursive();

                int size = 10000;

                Node valCheck;

                tree.root = tree.getRandomArray(tree.root, size);

                valCheck = tree.findMin(tree.root);
                System.out.println("Min: " + valCheck.key);
                valCheck = tree.findMax(tree.root);
                System.out.println("Max: " + valCheck.key);

        }


}
