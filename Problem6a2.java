import java.util.Random

class Problem1Iterative{

        class Node{
                int key;
                Node left, right;

                public Node(int item){
                        key = item;
                        left = null;
                        right = null;
                }
        }

        Node newNode(int key){
                Node temp = new Node();

                temp.key = data;
                temp.left = null;
                temp.right = null;

                return temp;
        }
        
        Node root;
        int counter = 0;

        Problem2Iterative(){

                root = null;

        }

        Node insert(int key){
                root = insertN(root, key);
                return root;
        }

        Node insertN(Node root, int key){
                Node newNode = newNode(key);

                Node curr = root;
                Node next = null;

                while(curr != null){ //Loops through the tree until the spot is found for a new insert
                        next = curr;
                        if(key < curr.key){
                                counter++;
                                curr = curr.left;
                        }
                        else{
                                counter++;
                                curr = curr.right;
                        }
                }

                else if(key < curr.key){ //Checks if the left is open first
                        counter++;
                        next.left = newNode;
                }

                else{    //Else place it in the right
                        counter++;
                        next.right = newNode;
                }

                return next;
        }

        Node remove(int key){
                root = removeN(root, key);
                return root;
        }

        Node removeN(Node root, int key){
                Node newNode = newNode(key);

                Node curr = root;
                Node next = null;

                while(curr != null){
                        if(key > curr.key){
                                if(curr.right != null){
                                        counter++;
                                        next = curr;
                                        curr = curr.right;
                                }
                                else{
                                        counter++;
                                        break;
                                }
                        }
                        else if(key < curr.key){
                                if(curr.left != null){
                                        counter++;
                                        next = curr;
                                        curr = curr.left;
                                }
                                else{
                                        counter++;
                                        break;
                                }
                        }
                        else{ //If key == curr.key or if value is found
                                if(curr.left == null && curr.right == null){
                                        if(next == null){
                                                root = null;
                                        }
                                        else if(next.left.key == curr.key){
                                                next.left = null;
                                        }
                                        else{
                                                next.right = null;
                                        }
                                }
                                else if(curr.left == null){
                                        if(next == null){
                                                counter++;
                                                root = curr.right;
                                        }
                                        else if(next.left.key == curr.key){
                                                counter++;
                                                next.left = curr.right;
                                        }
                                        else{
                                                counter++;
                                                next.right = curr.right;
                                        }
                                }
                                else if(curr.right == null){
                                        if(next == null){
                                                counter++;
                                                root = curr.left;
                                        }
                                        else if(next.left.key == curr.key){
                                                counter++;
                                                next.left == curr.left;
                                        }
                                        else{
                                                counter++;
                                                next.right = curr.left;
                                        }
                                }
                                else{
                                        Node check = curr.right;
                                        while(true){
                                                if(check.left != null){
                                                        counter++;
                                                        check = curr.left;
                                                }
                                                else
                                                        break;
                                        }
                                        int successor = check.key;
                                        curr = successor;
                                }
                        }
                }
                return next;
        }

        Node findNext(int key){
                root = findNextSuc(root, key);
                return root;
        }

        Node findNextSuc(Node root, int key){
                if(root == null)
                        return null;
                if(root.right != null){
                        counter++;
                        Node temp = root.right;
                        while(temp.left != null){
                                counter++;
                                temp = temp.left;
                        }
                        return temp;
                }
                else{
                        Node successor = null;
                        Node curr = root;
                        while(curr != root){
                                if(root.key < curr.key){
                                        counter++;
                                        successor = curr;
                                        curr = curr.left;
                                }
                                else{
                                        counter++;
                                        curr = curr.right;
                                }
                        }
                }
        }

        Node findPrev(int key){
                root = findPrevSuc(root, key);
                return root;
        }

        Node findPrevSuc(Node root, int key){
                if(root = null)
                        return root;

                if(root.left != null){
                        counter++;
                        Node temp = root.left;
                        while(temp.right != null){
                                counter++;
                                temp = temp.right;
                        }
                        return temp;
                }
                else{
                        Node successor = null;
                        Node curr = root;
                        while(curr != root){
                                if(root.key < curr.key){
                                        counter++;
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
                Node curr = root;

                while(curr.left != null){
                        counter++;
                        curr = curr.left;
                }        

                return curr.key;
        }

        int findMax(Node root){
                Node curr = root;

                while(curr.right != null){
                        counter++;
                        curr = curr.right;
                }

                return curr.key;
        }
        
        void getRandomArray(int size){
                Random rand = new Random();
      
                int i;
      
                int[] arr =  new int[size];
      
                for(i = 0; i < size; i++){
                        arr[i] = rand.nextInt(100);
                }
                for(i = 0; i < size; i++){
                        insert(root, arr[i]);
                }
        }

        public static void main(String[] args){
                Problem2Iterative tree = new Problem2Iterative();
                
                getRandomArray(10000);

                tree.root = tree.remove(10);

                int max = tree.findMin(tree.root);
                int min = tree.findMax(tree.root);

                tree.root = findPrev(1);
                tree.root = findNext(1);
                System.out.println(counter);
        }

}
