class Problem2c{

    
  
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
