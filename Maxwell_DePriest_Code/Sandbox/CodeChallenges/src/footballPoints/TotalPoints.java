package footballPoints;


public class TotalPoints {
	
	public TotalPoints() {
		
	}
	  
    public static int points(String[] games) {
      int xScore = 0;
      
      for ( String game : games) {
        String ar[] = game.split(":");
        
        if (Integer.parseInt(ar[0]) > Integer.parseInt(ar[1])) {
          xScore += 3;
        }
        else if (Integer.parseInt(ar[0]) == Integer.parseInt(ar[1])) {
          xScore += 1;
        }
        else {
          continue;
        }
      }
      
      
      return xScore; 
    }
    
}
