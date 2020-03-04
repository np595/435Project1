import java.util.Random;

class avlTree{

        class Node{
                int key, size;
                Node left, right, parent;

                public Node(int item){
                        key = item;
                        left = null;
                        right = null;
                        parent = null;
                        size = 1;
                }
        }

        Node root;

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
                Node temp = new Node(data);

                temp.key = data;
                temp.left = null;
                temp.right = null;
                temp.parent = null;
                temp.size = 1;

                return temp;
        }

        Node insert(Node root, int key){
                Node newNode = newNode(key);

                Node curr = root;
                Node next = null;

                while(curr != null){
                        next = curr;
                        if(key < curr.key)
                                curr = curr.left;
                        else
                                curr = curr.right;
                }
                if(next == null)
                        next = newNode;
                else if(key < next.key)
                        next.left = newNode;
                else
                        next.right = newNode;

                //System.out.println(root.key + " " + curr.key);

                next.size = 1 + max(heightCheck(next.left), heightCheck(next.right));
                int balance = balanceCheck(next);
                //System.out.println(root.key + " " + next.key);
                if((next.left == null) && (next.right == null))
                        return next;

                if(balance > 1 && key < next.left.key){
                        return rightRotate(next);
                }
                else if(balance < -1 && key > next.right.key){
                        return leftRotate(next);
                }
                else if(balance > 1 && key > next.left.key){
                        next.left = leftRotate(next.left);
                        return rightRotate(next);
                }
                else if(balance < -1 && key < next.right.key){
                        next.right = rightRotate(next.right);
                        return  leftRotate(next);
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
                while(curr.left != null){
                        curr = curr.left;
                }

                return curr;
        }

        Node remove(Node root, int key){

                Node curr = root;
                Node next = null;
                //System.out.println(curr.key);

                while(curr != null){
                        next = curr;
                        if(key < curr.key){
                                curr = curr.left;
                        }
                        else if(key > curr.key){
                                curr = curr.right;
                        }
                        else{
                                if(curr.left == null)
                                        return curr.right;
                                else if(curr.right == null)
                                        return curr.left;

                                curr = minVal(curr.right);

                                curr = curr.right;
                        }
                }

                next.size = max(heightCheck(next.left), heightCheck(next.right)) + 1;
                int balance = balanceCheck(next);

                if(balance > 1 && balanceCheck(next.left) >= 0)
                        return rightRotate(next);
                if(balance < -1 && balanceCheck(next.right) <= 0)
                        return leftRotate(next);
                if(balance > 1 && balanceCheck(next.left) < 0){
                        next.left = leftRotate(next.left);
                        return rightRotate(next);
                }
                if(balance < -1 && balanceCheck(next.right) > 0){
                        next.right = rightRotate(next.right);
                        return leftRotate(next);
                }

                return 1;

        }

        Node findNext(Node root, Node temp){
                if(temp.right != null){
                        return minVal(temp.right);
                }
                Node par = temp.parent;
                while(par != null && temp == par.right){
                        temp = par;
                        par = par.parent;
                }
                return par;
        }

        Node findPrev(Node root, Node temp){
                if(temp.left != null){
                        return minVal(temp.left);
                }
                Node par = temp.parent;
                while(par != null && temp == par.left){
                        temp = par;
                        par = par.parent;
                }
                return par;
        }

        void preOrder(Node root){

        }
        
        void randomInsert(int size){
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
                avlTree tree = new avlTree();
                Node tempCheck;
                Node temp = null;
                
                randomInsert(10);

                temp = tree.root.left;
                tempCheck = tree.minVal(tree.root);
                System.out.println(tempCheck.key);

        }

}
