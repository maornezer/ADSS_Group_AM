package Presentation;

import Domain.Item;
import Domain.Site;

import java.time.LocalTime;
import java.util.ArrayList;
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
        if (username.compareTo("driver") == 0 && password.compareTo("driver") == 0) {
            DriverMenu();
        }
//        if (username.compareTo("admin") == 0 && password.compareTo("admin") == 0) {
//            boolean flag = false;
//            while (!flag) {
//                System.out.println("Please choose data to manage:");
//                System.out.println("1. Manage Shipments");
//                System.out.println("2. Manage Branch order");
//                System.out.println("3. Exit");
//                String choice = scanner.nextLine();
//                switch (choice) {
//                    case "1":
//                        managerMenu();
//                        flag = true;
//                    case "2":
//                        branchManagerMenu();
//                        flag = true;
//                    case "3":
//                        printMenu();
//                        flag = true;
//                    default:
//                        System.out.println("Invalid choice. Please try again.");
//                }
//            }
//        }
    }

    public void managerMenu()
    {
        Dictionary<String, String> data = new Hashtable<String, String>();

        System.out.println("Hello Transport Manager!");
        System.out.println("Please choose what you would like to do:");
        System.out.println("1. Create Branch Order");
        System.out.println("2. Create new Transport");
        System.out.println("3. Edit Branch Order or Transport");
        System.out.println("4. Edit the database");
        System.out.println("5. View all orders or all shipments");
        System.out.println("6. Delivery start update");
        System.out.println("7. Return to the main menu");

        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                createBranchOrder(data);
                break;

            case "2":
                //createNewTransport(data);
                break;
            case "3":
                //editOrderOrTransport(data);
                break;
            case  "4":
                //editDatabase(data);
                break;
            case "5":
                //seeAllOrdersOrAllTransports(data);
                break;
            case "6":
                //deliveryStartUpdate(data);
                break;
            case "7":
                printMenu();
                break;
        }
    }

    public void DriverMenu() {
        Dictionary<String, String> data = new Hashtable<String, String>();


        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                createBranchOrder(data);
                break;

            case "2":
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
        System.out.println("Enter Departure Time: ");
        int hour = scanner.nextInt();
        int minute = scanner.nextInt();
        //String time = scanner.nextLine();
        data.put("hour", Integer.toString(hour));
        data.put("minute", Integer.toString(minute));

        System.out.println("Enter Shipping Date: ");
        int year = scanner.nextInt();
        int month = scanner.nextInt();
        int day = scanner.nextInt();
        //String date = scanner.nextLine();
        data.put("year", Integer.toString(year));
        data.put("month", Integer.toString(month));
        data.put("day", Integer.toString(day));

        System.out.println("Enter the Address of unloading destination of the transport: ");
        this.scanner.skip("\\R?");
        String destination = scanner.nextLine();
        data.put("destination", destination);

        System.out.println("Enter the Address of pickup destination: ");
        this.scanner.skip("\\R?");
        String source = scanner.nextLine();
        data.put("source", source);
        Dictionary<String,Dictionary<String,String>> dataItems = new Hashtable<String,Dictionary<String,String>>();
        boolean flag = true;
        int i = 0;
        while (flag)
        {
            System.out.println("Enter 1 if you want to add Item to this Order: ");
            String choice = scanner.nextLine();
            switch (choice)
            {
                case "1":
                    dataItems.put("item_" + i, createListOfItems(i));
                    i++;
                    break;
                default:
                    flag = false;
                    break;
            }
        }

        //list of items

        this.controller.creatNewOrder(data, dataItems);
    }
    public Dictionary<String,String> createListOfItems(int i)
    {
        Dictionary<String,String> dataItems = new Hashtable<String, String>();

        System.out.println("Enter Item number" + i + " ID: ");
        int id = scanner.nextInt();
        dataItems.put("id" + i, Integer.toString(id));

        System.out.println("Enter Item number" + i + " Name: ");
        this.scanner.skip("\\R?");
        String name = scanner.nextLine();
        dataItems.put("name" + i, name);

        System.out.println("Enter Item number" + i + " Amount: ");
        int amount = scanner.nextInt();
        dataItems.put("amount" + i, Integer.toString(amount));

        return dataItems;

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
