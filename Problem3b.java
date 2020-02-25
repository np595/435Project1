import java.util.Random; 

//Sorts program array from size to zero
class Problem3b{

    
    
    public static void main(String[] args){
    
        Random rand = new Random();
      
        int i, j;
        int size = rand.nextInt(20); //Makes a random size
      
        int[] arr =  new int[size];
        int[] newArr = new int[size];
      
        for(i = 0; i < size; i++){
        
            arr[i] = rand.nextInt(100);
        
        }
        
        for(i = size - 1, j = 0; i > 0; i--, j++){
        
            newArr[j] = arr[i];
        
        }
        
        for(i = 0; i < size; i++){
        
            System.out.println(newArr[i]);
        
        }
    
    }

}
