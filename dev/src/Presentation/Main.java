package Presentation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static void loadData(String csvFilePath, List<String> sites, List<String> drivers, List<String> trucks, List<String> orders, List<String> items) {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            String currentSection = "";
            int lineNumber = 0;

            // Read each line from the CSV file
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty())
                    continue; // Skip empty lines

                if (line.equalsIgnoreCase("driver,,,") ||
                        line.equalsIgnoreCase("site,,,") ||
                        line.equalsIgnoreCase("truck,,,") ||
                        line.equalsIgnoreCase("item,,,") ||
                        line.equalsIgnoreCase("order,,,")) {
                    currentSection = line.split(",")[0].toLowerCase(); // set current section based on the line
                    lineNumber = 0;
                    continue;
                }

                // Split the line by comma (assuming CSV format)
                String[] parts = line.split(",");

                switch (currentSection) {
                    case "driver":
                        if (parts.length < 3) break; // Ensure correct number of fields
                        String driverName = parts[0];
                        String driverLicence = parts[1];
                        String driverID = parts[2];
                        drivers.add("Name: " + driverName + ", Licence: " + driverLicence + ", ID: " + driverID);
                        break;
                    case "site":
                        if (parts.length < 4) break; // Ensure correct number of fields
                        String siteName = parts[0];
                        String siteZone = parts[1];
                        String siteAddress = parts[2];
                        String sitePhoneNumber = parts[3];
                        sites.add("Name: " + siteName + ", Zone: " + siteZone + ", Address: " + siteAddress + ", Phone Number: " + sitePhoneNumber);
                        break;
                    case "truck":
                        if (parts.length < 4) break; // Ensure correct number of fields
                        String truckInitialWeight = parts[0];
                        String truckMaxWeight = parts[1];
                        String truckModel = parts[2];
                        String truckID = parts[3];
                        trucks.add("Initial Weight: " + truckInitialWeight + ", Max Weight: " + truckMaxWeight + ", Model: " + truckModel + ", ID: " + truckID);
                        break;
                    case "item":
                        if (parts.length < 3) break; // Ensure correct number of fields
                        String itemName = parts[0];
                        String itemID = parts[1];
                        String itemAmount = parts[2];
                        items.add("Name: " + itemName + ", ID: " + itemID + ", Amount: " + itemAmount);
                        break;
                    case "order":
                        if (parts.length < 4) break; // Ensure correct number of fields
                        String orderDate = parts[0];
                        String orderTime = parts[1];
                        String orderSource = parts[2];
                        String orderDestination = parts[3];
                        orders.add("Date: " + orderDate + ", Time: " + orderTime + ", Source: " + orderSource + ", Destination: " + orderDestination);
                        break;
                }
                lineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void printData(List<String> sites, List<String> drivers, List<String> trucks, List<String> orders, List<String> items) {
        System.out.println("Sites:");
        printList(sites);
        System.out.println("\nDrivers:");
        printList(drivers);
        System.out.println("\nTrucks:");
        printList(trucks);
        System.out.println("\nOrders:");
        printList(orders);
        System.out.println("\nItems:");
        printList(items);
    }

    private static void printList(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
    public void printMenu()
    {
        Menu menu = new Menu();
        menu.printMenu();
    }

    public static void main(String[] args) {
        String csvFilePath = "C:\\Users\\USER\\Desktop\\Ben Gurion\\second year\\semester D\\analysis and planning\\PROJECT\\dev\\src\\data.csv";

        List<String> sites = new ArrayList<>();
        List<String> drivers = new ArrayList<>();
        List<String> trucks = new ArrayList<>();
        List<String> orders = new ArrayList<>();
        List<String> items = new ArrayList<>();

        loadData(csvFilePath, sites, drivers, trucks, orders, items);
        printData(sites, drivers, trucks, orders, items);

        Main main = new Main();
        main.printMenu();
    }
}
