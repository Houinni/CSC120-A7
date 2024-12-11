/* This is a stub for the House class */

import java.util.ArrayList;

public class House extends Building {

  private ArrayList<String> residents;
  private boolean hasDiningRoom;
  private boolean hasElevator;

/**
 * Constructs a new House object.
 *
 * @param name the name of the house
 * @param address the address of the house
 * @param nFloors the number of floors in the house
 * @param DiningRoom indicates if the house has a dining room
 * @param hasElevator indicate if the house has an elevator
 */
  public House(String name, String address, int nFloors, boolean hasElevator, boolean DiningRoom) {
    super(name, address, nFloors);
    this.residents=new ArrayList<>();
    this.hasDiningRoom=DiningRoom;
    this.hasElevator=hasElevator;
    System.out.println("You have built a house: 🏠");
  }

/**
 * Checks if the house has a dining room.
 *
 * @return true if the house has a dining room, false otherwise
 */
  public boolean hasDiningRoom(){
    return hasDiningRoom;
  }

/**
 * Returns the number of residents currently in the house.
 *
 * @return the number of residents
 */
  public int nResidents(){
    return residents.size();
  }

/**
 * Adds a resident to the house.
 *
 * @param name the name of the resident moving in
 */
  public void moveIn(String name){
    if(!residents.contains(name)){
      residents.add(name);
      System.out.println(name+" just moved in!");
    }else{
     System.out.println(name+ " already lives here");
    }
  }

/**
 * Removes a resident from the house.
 *
 * @param name the name of the resident moving out
 * @return the name of the person who moved out, or an error message if they weren't a resident
 */
  public String moveOut(String name){
    if(isResident(name)){
        residents.remove(name);
        System.out.println(name+" just moved out!");
        return name;
    }else{
      System.out.println(name+" doesn't lives here!");
      return "doesn't lives here";
    }
  }

/**
 * Checks if a given person is a resident of the house.
 *
 * @param person the name of the person to check
 * @return true if the person is a resident, false otherwise
 */
  public boolean isResident(String person){
    return residents.contains(person);
  }

/**
 * Displays the available options for interacting with the building.
 * Options vary depending on whether the building has an elevator.
 */
  public void showOptions() {
    if(hasElevator){
      System.out.println("Available options at " + this.name + ":\n + hasDiningRoom() \n + nResidents() \n + moveIn(name) \n + moveOut(name) \n + isResident(name) \n + enter() \n + exit() \n + goUp() \n + goDown()\n + goToFloor(n)");
    }
    else{
      System.out.println("Available options at " + this.name + ":\n + hasDiningRoom() \n + nResidents() \n + moveIn(name) \n + moveOut(name) \n + isResident(name) \n + enter() \n + exit() \n + goUp() \n + goDown()\n ");
    }
  }

/**
 * Moves the user to the specified floor.
 * 
 * @param floorNum The target floor number.
 * @throws RuntimeException if the building does not have an elevator.
 * @throws RuntimeException if the user is not inside the building.
 * @throws RuntimeException if the floor number is invalid.
 */
  public void goToFloor(int floorNum) {
    if(!hasElevator &&(this.activeFloor-floorNum>1 || floorNum-this.activeFloor>1 )){
        throw new RuntimeException("Cannot go to that floor because this House does not have an elevator.");
    }
    if (this.activeFloor == -1) {
        throw new RuntimeException("You are not inside this House. Must call enter() before navigating between floors.");
    }
    if (floorNum < 1 || floorNum > this.nFloors) {
        throw new RuntimeException("Invalid floor number. Valid range for this House is 1-" + this.nFloors +".");
    }
    
    System.out.println("You are now on floor #" + floorNum + " of " + this.name);
    this.activeFloor = floorNum;
  } 

/**
 * Allows the user to enter the building.
 * @param roomNumber the room number that the user enters
 * @return the current building instance.
 * @throws RuntimeException if the user is already inside.
 */
  public Building enter(int roomNumber) {
    if (activeFloor != -1) {
        throw new RuntimeException("You are already inside this House.");
    }
    this.activeFloor = 1;
    System.out.println("You've entered room # " + roomNumber + " of this House" );
    return this; 
  }

/**
 * Allows the user to exit the building.
 * @param roomNumber the room number that the user exit from
 * @return null to indicate the user is outside.
 * @throws RuntimeException if the user is not inside the building.
 */
  public Building exit(int roomNumber) {
    if (this.activeFloor == -1) {
        throw new RuntimeException("You are not inside this House. Must call enter() before exit().");
    }
    if (this.activeFloor > 1) {
      throw new RuntimeException("You have fallen out a window from floor #" +this.activeFloor + "!");
    }
    this.activeFloor = -1;
    System.out.println("You've left room # " + roomNumber + " of this House" );
    return null; 
  }
  public static void main(String[] args) {
    // Create a House without an elevator
    House houseWithoutElevator = new House("No Elevator House", "123 Main St", 5, false, true);

    // Enter the house
    houseWithoutElevator.enter(101);

    // Try to go to floor 3 from floor 1
    try {
        houseWithoutElevator.goToFloor(3);
    } catch (RuntimeException e) {
        System.out.println(e.getMessage());
    }

    // Move to floor 2 and then attempt floor 4
    try {
        houseWithoutElevator.goToFloor(2); // Should succeed
        houseWithoutElevator.goToFloor(4); // Should throw an exception
    } catch (RuntimeException e) {
        System.out.println(e.getMessage());
    }

    // Exit the house
    houseWithoutElevator.exit(101);
}

  /*public static void main(String[] args) {
    House Morris=new House("morris", "Northampton", 3, false,false);
    System.out.println(Morris.hasDiningRoom());
    Morris.moveIn("Kira");
    Morris.moveOut("Kira");
    Morris.moveOut("Kira");
    Morris.moveIn("Kira");
    Morris.moveOut("Kira");
    Morris.enter(100);
    Morris.goUp();
    Morris.goUp();
    Morris.goDown();
    Morris.goDown();
    Morris.exit(100);
    System.out.println(Morris.isResident("Kira"));
    System.out.println("-----------------------");
    Morris.showOptions();
  }*/

}