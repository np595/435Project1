import java.util.Random; 

class Problem3a{

    public static void main(String[] args){
    
        Random rand = new Random();
      
        int i;
        int size = rand.nextInt(20); //Makes a random size
      
        int[] arr =  new int[size];
      
        for(i = 0; i < size; i++){
        
            arr[i] = rand.nextInt(100);
        
        }
    
    }

}
