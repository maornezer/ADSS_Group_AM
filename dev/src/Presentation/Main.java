package Presentation;


import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;


public class Main
{
    //configuration
    private static void loadData(String csvFilePath, Map<String, String> sites, Map<String, String> drivers, Map<String, String> trucks, Map<String, String> orders, Map<String, String> items)
    {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath)))
        {
            String line;
            int lineNumber = 0;
            // Read each line from the CSV file
            while ((line = br.readLine()) != null)
            {
                if (lineNumber == 0)
                {
                    // Skip the header line
                    lineNumber++;
                    continue;
                }
                // Split the line by comma (assuming CSV format)
                String[] parts = line.split(",");
                // Assuming the CSV has the following columns:
                // Site, Driver, Truck, Order, Item
                String site = parts[0];
                String driver = parts[1];
                String truck = parts[2];
                String order = parts[3];
                String item = parts[4];
                // Process the data into the appropriate maps
                sites.put("Site" + lineNumber, site);
                drivers.put("Driver" + lineNumber, driver);
                trucks.put("Truck" + lineNumber, truck);
                orders.put("Order" + lineNumber, order);
                items.put("Item" + lineNumber, item);
                lineNumber++;
            }
            // Print the maps to verify the data
            System.out.println("Sites: " + sites);
            System.out.println("Drivers: " + drivers);
            System.out.println("Trucks: " + trucks);
            System.out.println("Orders: " + orders);
            System.out.println("Items: " + items);

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static void printData(Map<String, String> sites, Map<String, String> drivers, Map<String, String> trucks, Map<String, String> orders, Map<String, String> items)
    {
        System.out.println("Sites:");
        printMap(sites);
        System.out.println("\nDrivers:");
        printMap(drivers);
        System.out.println("\nTrucks:");
        printMap(trucks);
        System.out.println("\nOrders:");
        printMap(orders);
        System.out.println("\nItems:");
        printMap(items);
    }

    private static void printMap(Map<String, String> map)
    {
        for (Map.Entry<String, String> entry : map.entrySet())
        {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

//    public void printMenu()
//    {
//        Menu menu = new Menu();
//    }

    public static void main(String[] args) {
        String csvFilePath = "C:\\Users\\USER\\Desktop\\Ben Gurion\\second year\\semester D\\analysis and planning\\PROJECT\\dev\\src\\data.csv";
        Map<String, String> sites = new HashMap<>();
        Map<String, String> drivers = new HashMap<>();
        Map<String, String> trucks = new HashMap<>();
        Map<String, String> orders = new HashMap<>();
        Map<String, String> items = new HashMap<>();

        loadData(csvFilePath, sites, drivers, trucks, orders, items);
        printData(sites, drivers, trucks, orders, items);

//        Main main = new Main();
//        main.printMenu();
    }































//    private Scanner scanner;
//    private TruckCLI truckCLI;
//    private SiteCLI siteCLII;

    //private ShippingUI shippingUI;


//    public Main()
//    {
//        //configution
//
//        this.scanner = new Scanner(System.in);
//        this.truckCLI = new TruckCLI();
//        this.siteCLII = new SiteCLI();
//        //this.shippingUI = new ShippingUI();
//
//    }
//    public void run() {
//        System.out.println("Welcome to Super-Li Shipping control module!");
//        while (true) {
//            boolean exit = mainMenu();
//            if (exit) {
//                break;
//            }
//        }
//    }
//
//    private boolean mainMenu() {
//        System.out.println("Please choose data to manage:");
//        System.out.println("\t1. Manage Trucks");
//        System.out.println("\t2. Manage Shipments");
//        System.out.println("\t3. Manage Sites");
//        System.out.println("\tX. Exit");
//
//        String choice = scanner.nextLine();
//        switch (choice) {
//            case "1":
//                truckCLI.run();
//                return false;
//            case "2":
//                //shippingUI.run();
//                return false;
//            case "3":
//                //siteCLII.run();
//                return false;
//            case "X":
//                return true;
//            default:
//                System.out.println("Invalid choice. Please try again.");
//                return false;
//        }
//    }


}
