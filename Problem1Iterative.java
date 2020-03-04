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
                        if(key < curr.key)
                                curr = curr.left;
                        else
                                curr = curr.right;
                }

                else if(key < curr.key) //Checks if the left is open first
                        next.left = newNode;

                else    //Else place it in the right
                        next.right = newNode;

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
                                        next = curr;
                                        curr = curr.right;
                                }
                                else
                                        break;
                        }
                        else if(key < curr.key){
                                if(curr.left != null){
                                        next = curr;
                                        curr = curr.left;
                                }
                                else
                                        break;
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
                                                root = curr.right;
                                        }
                                        else if(next.left.key == curr.key){
                                                next.left = curr.right;
                                        }
                                        else{
                                                next.right = curr.right;
                                        }
                                }
                                else if(curr.right == null){
                                        if(next == null){
                                                root = curr.left;
                                        }
                                        else if(next.left.key == curr.key){
                                                next.left == curr.left;
                                        }
                                        else{
                                                next.right = curr.left;
                                        }
                                }
                                else{
                                        Node check = curr.right;
                                        while(true){
                                                if(check.left != null){
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
                Node curr = root;

                while(curr.left != null)
                        curr = curr.left;

                return curr.key;
        }

        int findMax(Node root){
                Node curr = root;

                while(curr.right != null)
                        curr = curr.right;

                return curr.key;
        }

        public static void main(String[] args){
                Problem2Iterative tree = new Problem2Iterative();
                
                tree.root = tree.insert(5);
                tree.root = tree.insert(10);
                tree.root = tree.insert(15);
                tree.root = tree.insert(23);
                tree.root = tree.insert(12);
                tree.root = tree.insert(24);

                tree.root = tree.remove(10);

                int max = tree.findMin(tree.root);
                int min = tree.findMax(tree.root);

                tree.root = findPrev(1);
                tree.root = findNext(1);
        }

}
