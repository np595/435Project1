class avlTreeRec{

        class Node{
                int key, size;
                Node left, right, parent;

                Node(int item){
                        key = item;
                        size = 1;
                }
        }

        Node root;
        int nextVal, prevVal;

        avlTreeRec(){
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

                root.size = max(heightCheck(root.left), heightCheck(root.right))+1;
                curr.size = max(heightCheck(curr.left), heightCheck(curr.right))+1;
                return curr;
        }

        Node leftRotate(Node root){
                Node curr = root.right;
                Node next = curr.left;

                curr.left = root;
                root.right = next;

                root.size = max(heightCheck(root.left), heightCheck(root.right))+1;
                curr.size = max(heightCheck(curr.left), heightCheck(curr.right))+1;
                return curr;
        }

        int getBalance(Node root){
                if(root == null)
                        return 0;
                return heightCheck(root.left) - heightCheck(root.right);
        }

        Node insert(Node root, int key){
                if(root == null)
                        return(new Node(key));

                if(key < root.key){
                        root.left = insert(root.left, key);
                }
                else if(key > root.key){
                        root.right = insert(root.right, key);
                }
                else //No duplicates
                        return root;

                root.size = max(heightCheck(root.left), heightCheck(root.right))+1;
                int balance = getBalance(root);

                if(balance > 1 && key < root.left.key){
                        return rightRotate(root);
                }
                if(balance < -1 && key > root.right.key){
                        return leftRotate(root);
                }
                if(balance > 1 && key > root.left.key){
                        root.left = leftRotate(root.left);
                        return rightRotate(root);
                }
                if(balance < -1 && key < root.right.key){
                        root.right = rightRotate(root.right);
                        return leftRotate(root);
                }
                return root;
        }

        Node minVal(Node root){
                Node curr = root;

                while(curr.left != null)
                        curr = curr.left;

                return curr;
        }

        Node maxVal(Node root){
                Node curr = root;

                while(curr.right != null)
                        curr = curr.right;

                return curr;
        }

        Node remove(Node root, int key){
                if(root == null)
                        return root;

                if(key < root.key){
                        root.left = remove(root.left, key);
                }

                else if(key > root.key){
                        root.right = remove(root.right, key);
                }

                else{
                        if((root.left == null) || (root.right == null)){
                                Node temp = null;
                                if(temp == root.left)
                                        temp = root.right;
                                else
                                        temp = root.left;

                                if(temp == null){
                                        temp = root;
                                        root = null;
                                }
                                else{
                                        root = temp;
                                }
                        }
                        else{
                                Node temp = minVal(root.right);
                                root.key = temp.key;
                                root.right = remove(root.right, temp.key);
                        }
                }

                if(root == null)
                        return root;

                root.size = max(heightCheck(root.left),heightCheck(root.right)) + 1;
                int balance = getBalance(root);

                if(balance > 1 && getBalance(root.left) >= 0){
                        return rightRotate(root);
                }
                if(balance > 1 && getBalance(root.left) < 0){
                        root.left = leftRotate(root.left);
                        return rightRotate(root);
                }
                if(balance < -1 && getBalance(root.right) <= 0){
                        return leftRotate(root);
                }
                if(balance < -1 && getBalance(root.right) > 0){
                        root.right = rightRotate(root.right);
                        return leftRotate(root);
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
                        else if(root.key < key){
                                prevVal = root.key;
                                findPrev(root.right, key);
                        }
                }
        }

        void preOrder(Node root){
                if(root != null){
                        System.out.print(root.key + " ");
                        preOrder(root.left);
                        preOrder(root.right);
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
