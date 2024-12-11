/* This is a stub for the Cafe class */
public class Cafe extends Building {
    private int nCoffeeOunces;
    private int nSugarPackets;
    private int nCreams;
    private int nCups;
    private static boolean hasElevator = false;

    /**
     * Constructs a new Cafe object.
     *
     * @param name         the name of the cafe
     * @param address      the address of the cafe
     * @param nFloors      the number of floors in the cafe
     * @param CoffeeOunce  the initial amount of coffee in ounces
     * @param SugarPackets the initial number of sugar packets
     * @param Creams       the initial number of creams
     * @param Cups         the initial number of cups
     */
    public Cafe(String name, String address, int nFloors, int coffeeOunce, int sugarPackets, int creams, int cups) {
        super(name, address, nFloors);
        System.out.println("You have built a cafe: ☕");
        nCoffeeOunces = coffeeOunce;
        nSugarPackets = sugarPackets;
        nCreams = creams;
        nCups = cups;
    }

    /**
     * Sells a coffee and decreases inventory accordingly.
     *
     * @param size          the size of the coffee in ounces
     * @param nSugarPackets the number of sugar packets
     * @param nCreams       the number of creams
     */
    public void sellCoffee(int size, int nSugarPackets, int nCreams) {

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
            this.nCoffeeOunces -= size;
            this.nSugarPackets -= nSugarPackets;
            this.nCreams -= nCreams;
            this.nCups -= 1;
            System.out.println(
                    "Sold coffee: " + size + "oz, " + nSugarPackets + " sugar packets, " + nCreams + " creams.");
        } else {
            System.out.println("Unable to complete sale due to insufficient stock.");
        }

    }

    /**
     * Restocks the cafe's inventory with the specified amounts of coffee, sugar
     * packets, cream, and cups.
     * Only non-zero values are added to the inventory.
     *
     * @param nCoffeeOunces the amount of coffee (in ounces) to add to the inventory
     * @param nSugarPackets the number of sugar packets to add to the inventory
     * @param nCreams       the number of cream units to add to the inventory
     * @param nCups         the number of cups to add to the inventory
     */
    private void restock(int nCoffeeOunces, int nSugarPackets, int nCreams, int nCups) {
        if (nCoffeeOunces > 0) {
            System.out.println("restock " + nCoffeeOunces + " ounces of coffee");
            this.nCoffeeOunces += nCoffeeOunces;
        }
        if (nSugarPackets > 0) {
            System.out.println("restock " + nSugarPackets + " packs of suger");
            this.nSugarPackets += nSugarPackets;
        }
        if (nCreams > 0) {
            System.out.println("restock " + nCreams + " creams");
            this.nCreams += nCreams;
        }
        if (nCups > 0) {
            System.out.println("restock " + nCups + " cups");
            this.nCups += nCups;
        }

    }

    /**
     * Displays the available options for interaction with the building.
     * If the building has an elevator, additional options are shown.
     */
    public void showOptions() {

        System.out.println("Available options at " + this.name
                + ":\n + sellCoffee(n,n,n) \n + enter() \n + exit() \n + goUp() \n + goDown()\n ");

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
        if (!hasElevator) {
            throw new RuntimeException("Cannot go to that floor because this Library does not have an elevator.");
        }
        if (this.activeFloor == -1) {
            throw new RuntimeException(
                    "You are not inside this Library. Must call enter() before navigating between floors.");
        }
        if (floorNum < 1 || floorNum > this.nFloors) {
            throw new RuntimeException("Invalid floor number. Valid range for this Library is 1-" + this.nFloors + ".");
        }
        System.out.println("You are now on floor #" + floorNum + " of " + this.name);
        this.activeFloor = floorNum;
    }

    /**
     * Allows a user to enter the building.
     * Throws an exception if already inside.
     * 
     * @param tableNumber
     * @return the current building instance.
     */
    public Building enter(int tableNumber) {
        if (activeFloor != -1) {
            throw new RuntimeException("You are already inside this Cafe.");
        }
        this.activeFloor = 1;
        System.out.println("Take a sit in table # " + tableNumber + " your coffee will be ready within a blink!");
        return this; // Return a pointer to the current building
    }

    /**
     * Allows a user to exit the building.
     * Throws an exception if not inside.
     * 
     * @param tableNumber
     * @return null to indicate the user is outside.
     */
    public Building exit(int tableNumber) {
        if (this.activeFloor == -1) {
            throw new RuntimeException("You are not inside this Cafe. Must call enter() before exit().");
        }
        if (this.activeFloor > 1) {
            throw new RuntimeException("You have fallen out a window from floor #" + this.activeFloor + "!");
        }
        this.activeFloor = -1;
        System.out.println("table # " + tableNumber + " would miss u! promise me to come back?");
        return null; // We're outside now, so the building is null
    }

    public static void main(String[] args) {
        Cafe CC = new Cafe(null, null, 1, 0, 0, 0, 0);
        CC.showOptions();
        CC.enter(2);
        CC.goUp();

        CC.exit(2);

    }
}
