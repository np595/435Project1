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

        Problem1Recursive(){

                root = null;

        }

        Node insert(int key){

                root = insertN(root, key);
                return root;

        }

        Node insertN(Node root, int key){

                if(root == null){ //Check if tree is empty and return the number as a new node
                        root = new Node(key);
                        return root;
                }

                if (key < root.key)
                        root.left = insertN(root.left, key);
                else if (key > root.key)
                        root.right = insertN(root.right, key);

                return root;
        }

        Node remove(int key){
                root = removeN(root, key);
                return root;
        }

        Node removeN(Node root, int key){
                if(root == null)
                        return null;

                if(key < root.key)
                        root.left = removeN(root.left, key);
                else if(key > root.key)
                        root.right = removeN(root.right, key);

                else{
                        if(root.left == null)
                                return root.right;
                        else if(root.right == null)
                                return root.left;

                        root.right = removeN(root.right, root.key);
                }

                return root;
        }

        Node findNext(int key){

                root = findNextSuc(root, key);
                return root;

        }

        Node findNextSuc(Node root, int key){

                if(root == null)
                        return null;

                if(root.right != null){
                        Node temp = root.right;
                        while(temp.left != null)
                                temp = temp.left;
                        return temp;
                }
                else{
                        Node successor = null;
                        Node curr = root;
                        while(curr != root){
                                if(root.key < curr.key){
                                        successor = curr;
                                        curr = curr.left;
                                }
                                else
                                        curr = curr.right;
                        }
                        return successor;
                }

        }

        Node findPrev(int key){

                root = findPrevSuc(root, key);
                return root;

        }

        Node findPrevSuc(Node root, int key){

                if(root == null)
                        return root;

                if(root.left != null){
                        Node temp = root.left;
                        while(temp.right != null)
                                temp = temp.right;
                        return temp;
                }
                else{
                        Node successor = null;
                        Node curr = root;
                        while(curr != root){
                                if(root.key < curr.key){
                                        successor = curr;
                                        curr = curr.right;
                                }
                                else
                                        curr = curr.left;
                        }
                        return successor;
                }

        }

        int findMin(Node root){

                Node current = root;

                while(current.left != null){

                        current = current.left;

                }

                return current.key;

        }

        int findMax(Node root){

                Node current = root;

                while(current.right != null){
                        current = current.right;
                }
                return current.key;

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
                preOrder(root);
                System.out.println();
                int randomInt = rand.nextInt(size);
                root = remove(root, arr[randomInt]);
                preOrder(root);
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
                avlTreeRec tree = new avlTreeRec();

                int size = 10000;

                Node valCheck;

                tree.root = tree.getRandomArray(tree.root, size);

                valCheck = tree.minVal(tree.root);
                System.out.println("Min: " + valCheck.key);
                valCheck = tree.maxVal(tree.root);
                System.out.println("Max: " + valCheck.key);

        }

}
