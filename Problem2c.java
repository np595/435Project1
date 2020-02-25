class Problem2c{

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
  
    Node sort(int arr[], int min, int max) { 

        if (min > max) { //If min is greated than max, then return null since it will not sort into a correct BST
            return null; 
        } 
      
        int mid = (start + end) / 2; 
        Node node = new Node(arr[mid]); 

        node.left = sortedArrayToBST(arr, start, mid - 1); 

        node.right = sortedArrayToBST(arr, mid + 1, end); 
          
        return node; 
    }

}
