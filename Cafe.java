/* This is a stub for the Cafe class */
public class Cafe extends Building {
    private int nCoffeeOunces; 
    private int nSugarPackets; 
    private int nCreams; 
    private int nCups; 

/**
 * Constructs a new Cafe object.
 *
 * @param name the name of the cafe
 * @param address the address of the cafe
 * @param nFloors the number of floors in the cafe
 * @param CoffeeOunce the initial amount of coffee in ounces
 * @param SugarPackets the initial number of sugar packets
 * @param Creams the initial number of creams
 * @param Cups the initial number of cups
 */
    public Cafe(String name, String address, int nFloors, boolean hasElevator,int CoffeeOunce,int SugarPackets,int Creams,int Cups) {
        super(name, address, nFloors);
        System.out.println("You have built a cafe: ☕");
        nCoffeeOunces = CoffeeOunce;
        nSugarPackets = SugarPackets;
        nCreams = Creams;
        nCups = Cups;

    }
    
/**
 * Sells a coffee and decreases inventory accordingly.
 *
 * @param size the size of the coffee in ounces
 * @param nSugarPackets the number of sugar packets
 * @param nCreams the number of creams
 */
    public void sellCoffee(int size, int nSugarPackets, int nCreams){
        
        if (this.nCoffeeOunces < size) {
            restock(size - this.nCoffeeOunces, 0, 0, 0);
        }
        if (this.nSugarPackets < nSugarPackets) {
            restock(0, nSugarPackets - this.nSugarPackets, 0, 0);
        }
        if (this.nCreams < nCreams) {
            restock(0, 0, nCreams - this.nCreams, 0);
        }
        if (this.nCups <= 0) {
            restock(0, 0, 0, 5);
        }
    
        if (this.nCoffeeOunces >= size && this.nSugarPackets >= nSugarPackets && 
            this.nCreams >= nCreams && this.nCups > 0) {
            this.nCoffeeOunces-=size;
            this.nSugarPackets -= nSugarPackets;
            this.nCreams -= nCreams;
            this.nCups -= 1;
            System.out.println("Sold coffee: " + size + "oz, " + nSugarPackets + " sugar packets, " + nCreams + " creams.");
        }  else {
            System.out.println("Unable to complete sale due to insufficient stock.");
        }
        
    }

/**
 * Restocks the cafe's inventory with the specified amounts of coffee, sugar packets, cream, and cups.
 * Only non-zero values are added to the inventory.
 *
 * @param nCoffeeOunces the amount of coffee (in ounces) to add to the inventory
 * @param nSugarPackets the number of sugar packets to add to the inventory
 * @param nCreams the number of cream units to add to the inventory
 * @param nCups the number of cups to add to the inventory
 */
    private void restock(int nCoffeeOunces, int nSugarPackets, int nCreams, int nCups){
        if (nCoffeeOunces > 0){
            System.out.println("restock "+nCoffeeOunces+ " ounces of coffee");
            this.nCoffeeOunces += nCoffeeOunces;
        } 
        if (nSugarPackets > 0){
            System.out.println("restock "+ nSugarPackets+" packs of suger");
            this.nSugarPackets += nSugarPackets;
        }
        if (nCreams > 0){
            System.out.println("restock "+ nCreams+ " creams");
            this.nCreams += nCreams;
        } 
        if (nCups > 0){
            System.out.println("restock "+nCups+" cups");
            this.nCups += nCups;
        } 

    }

/**
 * Displays the available options for interaction with the building.
 * If the building has an elevator, additional options are shown.
 */
    public void showOptions() {
       
         System.out.println("Available options at " + this.name + ":\n + sellCoffee(n,n,n) \n + enter() \n + exit() \n + goUp() \n + goDown()\n ");
        
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
        if (this.activeFloor == -1) {
        throw new RuntimeException("You are not inside this Cafe. Must call enter() before navigating between floors.");
        }
        if (floorNum < 1 || floorNum > this.nFloors) {
            throw new RuntimeException("Invalid floor number. Valid range for this Cafe is 1-" + this.nFloors +".");
        }
        System.out.println("You are now on floor #" + floorNum + " of " + this.name);
        this.activeFloor = floorNum;
    } 
/**
 * Allows a user to enter the building.
 * Throws an exception if already inside.
 * @return the current building instance.
 */
    public Building enter() {
        if (activeFloor != -1) {
            throw new RuntimeException("You are already inside this Cafe.");
        }
        return this; // Return a pointer to the current building
    }

/**
 * Allows a user to exit the building.
 * Throws an exception if not inside.
 * @return null to indicate the user is outside.
 */
    public Building exit() {
        if (this.activeFloor == -1) {
            throw new RuntimeException("You are not inside this Cafe. Must call enter() before exit().");
        }
        return null; // We're outside now, so the building is null
    }

    public static void main(String[] args) {
    Cafe CC=new Cafe(null, null, 1, false, 0, 0, 0, 0);
    CC.showOptions();

    } 
}
