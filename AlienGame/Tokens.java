
/**
 * This class represents the aliens on the map. It contains their health and their coordinates.
 *
 * @author Jan Schneider 4919081 Group 8b
 */

 public class Tokens {
     private int health;
     private int xCoordinate;
     private int yCoordinate;

     public int gethealth() {
         return health;
     }
     public int getxCoordinate() {
         return xCoordinate;
     }
     public int getyCoordinate() {
         return yCoordinate;
     }
     public void sethealth(int health) {
         this.health = health;
     }
     public void setxCoordinate(int xCoordinate) {
         this.xCoordinate = xCoordinate;
     }
     public void setyCoordinate(int yCoordinate) {
         this.yCoordinate = yCoordinate;
     }
 }
