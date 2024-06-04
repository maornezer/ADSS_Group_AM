package Presentation;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Scanner;

public class Menu {
    private Scanner scanner;
    private PresentationController controller;

    public Menu() {
        this.scanner = new Scanner(System.in);
        this.controller = new PresentationController();
    }

    public void printMenu() {

        System.out.println("Welcome to Super-Li Shipment module");
        System.out.println("Please enter your username:");
        String username = scanner.nextLine();
        System.out.println("Please enter your password:");
        String password = scanner.nextLine();
        if (username.compareTo("manager shipments") == 0 && password.compareTo("manager shipments") == 0) {
            managerMenu();
        }
        if (username.compareTo("branch manager") == 0 && password.compareTo("branch manager") == 0) {
            branchManagerMenu();
        }
        if (username.compareTo("admin") == 0 && password.compareTo("admin") == 0) {
            boolean flag = false;
            while (!flag) {
                System.out.println("Please choose data to manage:");
                System.out.println("1. Manage Shipments");
                System.out.println("2. Manage Branch order");
                System.out.println("3. Exit");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "1":
                        managerMenu();
                        flag = true;
                    case "2":
                        branchManagerMenu();
                        flag = true;
                    case "3":
                        printMenu();
                        flag = true;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }

    public void managerMenu() {

    }

    public void branchManagerMenu() {
        Dictionary<String, String> data = new Hashtable<String, String>();

        System.out.println("Hello Branch Manager!");
        System.out.println("Please choose what you would like to do:");
        System.out.println("1. Create Branch Order");
        System.out.println("2. Delete Branch Order");
        System.out.println("3. Edit Branch Order");
        System.out.println("4. Get Shipment Status");
        System.out.println("5. Return to the main menu");
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                createBranchOrder(data);
                break;

            case 2:
                deleteBranchOrder(data);
        }
    }
    public void deleteBranchOrder(Dictionary<String, String> data)
    {
        System.out.println("Please enter the order number:");
        String orderNumber = scanner.nextLine();

    }

    public void createBranchOrder(Dictionary<String, String> data)
    {
        System.out.println("Enter Branch Zone: ");
        this.scanner.skip("\\R?");
        String shippingZone = scanner.nextLine();
        data.put("shippingZone", shippingZone);

        System.out.println("Enter Shipping Date: ");
        int year = scanner.nextInt();
        int month = scanner.nextInt();
        int day = scanner.nextInt();
        //String ShippingDate = scanner.nextLine();
        data.put("year", Integer.toString(year));
        data.put("month", Integer.toString(month));
        data.put("day", Integer.toString(day));

        System.out.println("Enter Departure Time: ");
        int hour = scanner.nextInt();
        int minute = scanner.nextInt();
        //String DepartureTime = scanner.nextLine();
        data.put("hour", Integer.toString(hour));
        data.put("minute", Integer.toString(minute));

        this.controller.creatNewOrder(data);
    }


    public void addTruck(){
        Dictionary<String, String> data = new Hashtable<String, String>();

        System.out.println("Enter Truck ID: ");
        int id = scanner.nextInt();
        data.put("idT", Integer.toString(id));

        System.out.println("Enter initial weight: ");
        double initialWeight = scanner.nextDouble();
        data.put("initialWeight", Double.toString(initialWeight));

        System.out.println("Enter max weight: ");
        double maxWeight = scanner.nextDouble();
        data.put("maxWeight", Double.toString(maxWeight));

        System.out.println("Enter model: ");
        String modelName = scanner.next();
        data.put("model", modelName);

        this.controller.addTruck(data);
    }
}
