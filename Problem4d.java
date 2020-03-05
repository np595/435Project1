import java.util.Stack;

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
        };

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

        Node newNode(int data, Node root){
                Node temp = new Node(data);

                temp.key = data;
                temp.left = null;
                temp.right = null;
                temp.parent = root;
                temp.size = 1;

                return temp;
        }

        Node insert(Node root, int key){
                Node prev = null;

                if(root == null){
                        return newNode(key, prev);
                }

                Node curr = root;
                Node next = null;

                while(curr != null){
                        next = curr;
                        if(key < curr.key){//Moves down left
                                prev = curr;
                                curr = curr.left;
                        }
                        else if(key > curr.key){//Moves down right
                                prev = curr;
                                curr = curr.right;
                        }
                }
                if(key < next.key){
                        next.left = newNode(key, prev);
                        System.out.println(next.key + " " + next.left.key);
                }
                else if(key > next.key){
                        next.right = newNode(key, prev);
                        System.out.println(next.key + " " + next.right.key);
                }

                if(next.right != null && next.left != null){
                        System.out.println(next.key + " " + next.right.key + " " + next.left.key);
                }

                System.out.println();
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
                        return leftRotate(next);
                }

                while(next.parent != null){//Moves back up the tree
                        next = next.parent;
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

                return next;

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
                if(root == null)
                        return;

                Stack<Node> nodeStack = new Stack<Node>();
                nodeStack.push(root);

                while(nodeStack.empty() == false){
                        Node node = nodeStack.peek();
                        System.out.print(node.key + " ");
                        nodeStack.pop();

                        if(node.right != null){
                                nodeStack.push(node.right);
                        }
                        else if(node.left != null){
                                nodeStack.push(node.left);
                        }
                }
        }

        public static void main(String[] args){
                avlTree tree = new avlTree();
                Node tempCheck;
                Node temp = null;

                tree.root = tree.insert(tree.root, 10);
                //System.out.println(tree.root.key);
                tree.root = tree.insert(tree.root, 15);
                //System.out.println(tree.root.key + " " + tree.root.right.key);
                tree.root = tree.insert(tree.root, 5);
                //System.out.println(tree.root.key + " " + tree.root.right.key + " " + tree.root.left.key);
                tree.root = tree.insert(tree.root, 4);
                //System.out.println(tree.root.key + " " + tree.root.left.key);
                tree.root = tree.insert(tree.root, 12);
                //System.out.println(tree.root.key + " " + tree.root.right.key + " " + tree.root.left.key);
                tree.preOrder(tree.root);
                //System.out.println();
                tree.root = tree.remove(tree.root, 5);

                tree.preOrder(tree.root);
                //System.out.println();
                temp = tree.root.left;
                tempCheck = tree.minVal(tree.root);
                System.out.println(tempCheck.key);
                tempCheck = tree.maxVal(tree.root);
                System.out.println(tempCheck.key);

        }

}
