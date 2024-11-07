/* This is a stub for the Library class */

import java.util.Hashtable;

public class Library extends Building {
    private Hashtable<String, Boolean> collection;
    private static boolean hasElevator=true;
/**
 * Constructs a new Library object.
 *
 * @param name the name of the library
 * @param address the address of the library
 * @param nFloors the number of floors in the library
 * @param hasElevator whether the library has an elevator
 */
    public Library(String name, String address, int nFloors) {
      super(name, address, nFloors);
      collection=new Hashtable<>();
      System.out.println("You have built a library: 📖");
    }

/**
 * Adds a book title to the library's collection.
 *
 * @param title the title of the book to add
 */
    public void addTitle(String title){
      collection.put(title,true);
    }

/**
 * Removes a book title from the library's collection.
 *
 * @param title the title of the book to remove
 * @return the removed title if it existed, or null otherwise
 */
    public String removeTitle(String title){
      if(collection.containsKey(title)){
        collection.remove(title);
        return title;
      }
      else{
        return null;
      }
    }

/**
 * Checks out a book, setting its availability to false.
 *
 * @param title the title of the book to check out
*/
    public void checkOut(String title){
      if(collection.containsKey(title)){
        collection.replace(title, false);
      }
    }

/**
 * Returns a book, setting its availability to true.
 *
 * @param title the title of the book to return
*/
    public void returnBook(String title){
      if(collection.containsKey(title)){
      collection.replace(title, true);
      }
    }

/**
 * Checks if the library contains a specific title.
 *
 * @param title the title to check
 * @return true if the title is in the collection, false otherwise
 */
    public boolean containsTitle(String title){
      if(collection.containsKey(title)){
        return true;
      }
      else{
        return false;
      }
    }

/**
 * Checks if a book is available.
 *
 * @param title the title to check
 * @return true if the book is available, false otherwise
 */
    public boolean isAvailable(String title){
      return collection.get(title);
    }

/*
 * Prints the library's collection and each book's availability.
 */
    public void printCollection(){
      System.out.println("Library Collection:");
      for (String title : collection.keySet()) {
          String status = collection.get(title) ? "Available" : "Checked out";
          System.out.println("- " + title + " (" + status + ")");
      }
    }

/**
 * Displays the available options for interacting with the library.
 * Options vary depending on whether the library has an elevator.
 */
    public void showOptions() {
      if(hasElevator){
      System.out.println("Available options at " + this.name + ":\n + addTitle(title) \n + removeTitle(title) \n + checkOut(title) \n + returnBook(title) \n + containsTitle(title) \n + isAvailable(title) \n + printCollection() \n + enter() \n + exit() \n + goUp() \n + goDown()\n + goToFloor(n)");
      }
      else{
        System.out.println("Available options at " + this.name + ":\n + addTitle(title) \n + removeTitle(title) \n + checkOut(title) \n + returnBook(title) \n + containsTitle(title) \n + isAvailable(title) \n + printCollection() \n + enter() \n + exit() \n + goUp() \n + goDown()\n ");
      }
    }

/**
 * Moves the user to the specified floor in the library.
 * 
 * @param floorNum The target floor number.
 * @throws RuntimeException if the library does not have an elevator.
 * @throws RuntimeException if the user is not inside the library.
 * @throws RuntimeException if the floor number is out of range.
 */
    public void goToFloor(int floorNum) {
      if(!hasElevator){
        throw new RuntimeException("Cannot go to that floor because this Library does not have an elevator.");
      }
      if (this.activeFloor == -1) {
          throw new RuntimeException("You are not inside this Library. Must call enter() before navigating between floors.");
      }
      if (floorNum < 1 || floorNum > this.nFloors) {
          throw new RuntimeException("Invalid floor number. Valid range for this Library is 1-" + this.nFloors +".");
      }
      System.out.println("You are now on floor #" + floorNum + " of " + this.name);
      this.activeFloor = floorNum;
    }

/**
 * Allows the user to enter the library.
 * 
 * @return the current library instance.
 * @throws RuntimeException if the user is already inside.
 */
    public Building enter(int section) {
      if (activeFloor != -1) {
          throw new RuntimeException("You are already inside this Library.");
      }
      this.activeFloor=1;
      System.out.println("you're now in section "+ section + " of the library, enjoy!");
      return this; 
    }

/**
 * Allows the user to exit the library.
 * 
 * @return null to indicate the user is outside.
 * @throws RuntimeException if the user is not inside the library.
 */
    public Building exit(int section) {
      if (this.activeFloor == -1) {
          throw new RuntimeException("You are not inside this Library. Must call enter() before exit().");
      }
      if (this.activeFloor > 1) {
        throw new RuntimeException("You have fallen out a window from floor #" +this.activeFloor + "!");
      }
      System.out.println("you're now left section "+ section + " of the library, bye!");
      this.activeFloor=-1;
      return null; 
    }
    public static void main(String[] args) {
      
    }

  
  }