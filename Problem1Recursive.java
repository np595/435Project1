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
        
        Node root = null;
        
    }
    
    void insert(int key){
    
        root = insertN(root, key);
    
    }
    
    Node insertN(Node root, int key){
    
        if(root == null){ //Check if tree is empty and return the number as a new node
            
            root = new Node(key);
            return root;
        
        }
        
        //Else continue until left or right is found null
        if (key < root.key) 
            root.left = insertN(root.left, key); 
        else if (key > root.key) 
            root.right = insertN(root.right, key);
        
        return root;
    
    }
    
    void remove(int key){
    
        root = removeN(root, key);
    
    }
    
    Node removeN(Node root, int key){
    
        if(root == null)
            return root;
        
        if(key < root.key)
            root.left = removeN(root.left, key);
        else if(key > root.key)
            root.right = removeN(root.right, key);
        
        else{ //if root.key = key then that's the one we want to delete
        
            if (root.left == null) //Check for any leaves or children to then move up once deleted
                return root.right; 
            else if (root.right == null) 
                return root.left;
            
            root.right = removeN(root.right, root.key); 
        
        }
                
        return root;
    
    }
    
    Node findNext(Node root){ //Proceeds down the tree for the next highest value
    
        
    
    }
    
    Node findPrev(Node root){ //Proceeds up the tree to the limit of the root
    
        
    
    }
    
    int findMin(Node root) { 
        Node current = root; 
        
        while (current.left != null) { //Recursively look through the data until the absolute left three has been found which holds the min
            
            current = current.left; 
            
        } 
        return current.key; 
    } 

    int findMax(Node root){
    
        Node current = root;
        
        while(current.right != null){ //Continues until the absolute right has been found for the max value
        
            current = current.right;
            
        }
        
        return current.key;
        
    }
    
    public static void main(String[] args){
    
        
    
    }
    
}
