import java.util.ArrayList;
import java.util.Scanner;

public class CampusMap {

    ArrayList<Building> buildings;

    /* Default constructor, initializes empty ArrayList */
    public CampusMap() {
        buildings = new ArrayList<Building>();
    }

    /**
     * Adds a Building to the map
     * @param b the Building to add
     */
    public void addBuilding(Building b) {
        System.out.println("Adding building...");
        buildings.add(b);
        System.out.println("-->Successfully added " + b.getName() + " to the map.");
    }

    /**
     * Removes a Building from the map
     * @param b the Building to remove
     * @return the removed Building
     */
    public Building removeBuilding(Building b) {
        System.out.println("Removing building...");
        buildings.remove(b);
        System.out.println("-->Successfully removed " + b.getName() + " to the map.");
        return b;
    }

    public String toString() {
        String mapString = "DIRECTORY of BUILDINGS";

        for (int i = 0; i < this.buildings.size(); i ++) {
            mapString += "\n  " + (i+1) + ". "+ this.buildings.get(i).getName() + " (" + this.buildings.get(i).getAddress() + ")";
        }
        return mapString;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CampusMap myMap = new CampusMap();
        myMap.addBuilding(new Building("Ford Hall", "100 Green Street Northampton, MA 01063", 4));
        myMap.addBuilding(new Building("Bass Hall", "4 Tyler Court Northampton, MA 01063", 4));
        myMap.addBuilding(new Cafe("Campus Cafe", "Northampton", 2, 20, 30, 40, 50));
        myMap.addBuilding(new Cafe("Neilson Cafe", "Northampton", 1, 50, 40, 30, 20));
        myMap.addBuilding(new House("Morris", "Green Street", 4, false, false));
        myMap.addBuilding(new House("Lawrence", "Green Street", 4, false, false));
        myMap.addBuilding(new House("Tyler", "Green Street", 4, false, true));
        myMap.addBuilding(new Library("Neilson", "Northampton", 4));
        myMap.addBuilding(new Library("Alumnea Gym", "Northampton", 3));
        myMap.addBuilding(new Building("Anisworth ", "100 Green Street Northampton, MA 01063", 4));
        System.out.println("\nWelcome to the Campus Map! Where would you like to go?");
        System.out.println(myMap);
        System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
        myMap.exploreBuilding(choice, scanner);
          
    }

    public void exploreBuilding(int choice, Scanner scanner) {
        if (choice < 1 || choice > buildings.size()) {
            System.out.println("Invalid choice. Please try again.");
            return;
        }

        Building building = buildings.get(choice - 1);
        System.out.println("\nExploring " + building.getName() + "...");
        
        if (building instanceof Library) {
            interactWithLibrary((Library) building, scanner);
        } else {
            System.out.println("This is a standard building with no special interactions.");
        }
    }

    private void interactWithLibrary(Library library, Scanner scanner) {
        while (true) {
            System.out.println("\nYou're at " + library.getName());
            library.showOptions();
    
            System.out.print("Choose an action or type 'back' to return to the campus map: ");
            String action = scanner.nextLine().trim();
    
            if (action.equalsIgnoreCase("back")) break;
    
            switch (action) {
                case "enter":
                    library.enter();
                    break;
                case "exit":
                    library.exit();
                    break;
                case "addTitle":
                    System.out.print("Enter the book title to add: ");
                    library.addTitle(scanner.nextLine());
                    break;
                case "removeTitle":
                    System.out.print("Enter the book title to remove: ");
                    System.out.println(library.removeTitle(scanner.nextLine()) + " has been removed.");
                    break;
                case "checkOut":
                    System.out.print("Enter the book title to check out: ");
                    library.checkOut(scanner.nextLine());
                    break;
                case "returnBook":
                    System.out.print("Enter the book title to return: ");
                    library.returnBook(scanner.nextLine());
                    break;
                case "containsTitle":
                    System.out.print("Enter the book title to check: ");
                    System.out.println(library.containsTitle(scanner.nextLine()) ? "Book is in the collection." : "Book is not in the collection.");
                    break;
                case "isAvailable":
                    System.out.print("Enter the book title to check availability: ");
                    System.out.println(library.isAvailable(scanner.nextLine()) ? "Book is available." : "Book is checked out.");
                    break;
                case "printCollection":
                    library.printCollection();
                    break;
                case "goUp":
                    library.goUp();
                    break;
                case "goDown":
                    library.goDown();
                    break;
                case "goToFloor":
                    System.out.print("Enter floor number: ");
                    library.goToFloor(scanner.nextInt());
                    scanner.nextLine(); // Consume newline
                    break;
                default:
                    System.out.println("Invalid action. Please try again.");
            }
        }
    }
}
