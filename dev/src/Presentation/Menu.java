package Presentation;

import Domain.Driver;
import Domain.Item;
import Domain.Site;
import Domain.Truck;

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
//                this.scanner.skip("\\R?");
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
        this.scanner.skip("\\R?");
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                createBranchOrder(data);
                break;

            case "2":
                createNewTransport();
                break;
            case "3":
                editOrderOrTransport();
                break;
            case  "4":
                editDatabase();
                break;
            case "5":
                seeAllOrdersOrAllTransports();
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

        this.scanner.skip("\\R?");
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                //getAllDeliveries();
                break;
            case "2":
               // getItemsReport();
                break;
            case "3":
                // getTransportReport();
                break;
            case "4":
                printMenu();
                break;
        }
    }
//    public void deleteBranchOrder(Dictionary<String, String> data)
//    {
//        System.out.println("Please enter the order number:");
//        String orderNumber = scanner.nextLine();
//
//    }

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

        //list of items:
        Dictionary<String,Dictionary<String,String>> dataItems = new Hashtable<String,Dictionary<String,String>>();
        boolean flag = true;
        int i = 0;
        while (flag)
        {
            System.out.println("Enter 1 if you want to add Item to this Order: ");
            String choice = scanner.nextLine();
            if (choice.equals("1")) {
                dataItems.put("item_" + i, createListOfItems(i));
                i++;
            }
            else
            {
                flag = false;
            }
        }


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
    public void  createNewTransport()
    {
        Dictionary<String, String> data = new Hashtable<String, String>();
        System.out.println("Enter Truck ID: ");
        int idT = scanner.nextInt();
        data.put("idT", Integer.toString(idT));

        System.out.println("Enter Driver ID: ");
        int idD = scanner.nextInt();
        data.put("idD", Integer.toString(idD));

        this.controller.addTransport(data);
    }

    public void editOrderOrTransport()
    {
        System.out.println("Enter what do you like to edit: ");
        System.out.println("1. Edit Order");
        System.out.println("2. Edit Transport");
        System.out.println("3. Return to Menu");
        this.scanner.skip("\\R?");
        String choice = scanner.nextLine();
        switch (choice)
        {
            case "1":
                editOrder();
                break;
            case "2":
                editTransport();
                break;
            case "3":
                managerMenu();
                break;
            default:
                System.out.println("There is no such option of choice, please choose 1 or 2 or 3 for return to back");
                editOrderOrTransport();
                break;

        }
    }
    public void editOrder()
    {

        System.out.println("Enter ID of the Order you want to change: ");
        int idOrder = scanner.nextInt();
        System.out.println("Choose if you want to change the Address of destination order: " + idOrder + ": [Yes or No]");
        this.scanner.skip("\\R?");
        String ans = scanner.nextLine();
        if (ans.compareTo("Yes") == 0)
        {
            changeDestination(idOrder);
        }
        else {
            System.out.println("You Choose that you dont want to change anything in order: " + idOrder );
            editOrderOrTransport();
        }

        ///complete
    }
    public void changeDestination(int orderID)
    {
        Dictionary<String, String> data = new Hashtable<String, String>();
        int id = orderID;
        data.put("id", Integer.toString(id));
        System.out.println("Enter Site Address of new destination: ");
        this.scanner.skip("\\R?");
        String destination = scanner.nextLine();
        data.put("destination", destination);
        this.controller.changeDestination(data);
    }
    public void editTransport() {
        System.out.println("Enter ID of the Transport you want to change: ");
        int id = scanner.nextInt();
        System.out.println("Choose what change you would like to make in the shipment with an ID number : " + id + ": ");
        System.out.println("1. Change Truck");
        System.out.println("2. Change Driver");
        System.out.println("3. Return back");
        this.scanner.skip("\\R?");
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                changeTruck(id);
                break;
            case "2":
                changeDriver(id);
                break;
            case "3":
                editOrderOrTransport();
                break;


            ///complete
        }
    }
    public void changeTruck(int id)
    {
        Dictionary<String, String> data = new Hashtable<String, String>();
        int idTransport = id;
        data.put("idTransport", Integer.toString(idTransport));

        System.out.println("Enter new Truck ID: ");
        int idTruck = scanner.nextInt();
        data.put("idTruck", Integer.toString(idTruck));

        this.controller.changeTruck(data);
    }

    public void changeDriver(int id)
    {
        Dictionary<String, String> data = new Hashtable<String, String>();
        int idTransport = id;
        data.put("idTransport", Integer.toString(idTransport));

        System.out.println("Enter new Driver ID: ");
        int idDriver = scanner.nextInt();
        data.put("idDriver", Integer.toString(idDriver));

        this.controller.changeDriver(data);

    }
    public void editDatabase()
    {
        System.out.println("Enter what do you want to add to your DataBase: ");
        System.out.println("1. add new Site");
        System.out.println("2. add new Truck");
        System.out.println("3. add new Driver");
        this.scanner.skip("\\R?");
        String choice = scanner.nextLine();
        switch (choice)
        {
            case "1":
                addSite();
                break;
            case "2":
                addTruck();
                break;
            case "3":
                addDriver();
                break;

        }
    }


    public void addSite()
    {
        Dictionary<String, String> data = new Hashtable<String, String>();

        System.out.println("Enter Site Address: ");
        this.scanner.skip("\\R?");
        String address = scanner.nextLine();
        data.put("address", address);


        System.out.println("Choose Site Zone: [North, South, Center]");
        this.scanner.skip("\\R?");
        String zone = scanner.nextLine();
        data.put("zone", zone);

        System.out.println("Enter Contact Name of Site: ");
        this.scanner.skip("\\R?");
        String contactName = scanner.nextLine();
        data.put("contactName", contactName);

        System.out.println("Enter Contact Phone Number of Site: ");
        this.scanner.skip("\\R?");
        String phoneNumber = scanner.nextLine();
        data.put("phoneNumber", phoneNumber);

        this.controller.addSite(data);

    }
    public void addTruck()
    {
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
        this.scanner.skip("\\R?");
        String modelName = scanner.nextLine();
        data.put("model", modelName);

        this.controller.addTruck(data);
    }
        public void addDriver()
    {
        Dictionary<String, String> data = new Hashtable<String, String>();
        System.out.println("Enter Driver Neme: ");
        this.scanner.skip("\\R?");
        String name = scanner.nextLine();
        data.put("name", name);

        System.out.println("Enter Driver ID: ");
        int id = scanner.nextInt();
        data.put("id", Integer.toString(id));

        System.out.println("Enter Driver Type of License: [B, C, D]");
        this.scanner.skip("\\R?");
        String typeOfLicense = scanner.nextLine();
        data.put("typeOfLicense", typeOfLicense);

        this.controller.addDriver(data);
    }
    public void seeAllOrdersOrAllTransports()
    {
        System.out.println("What would you like to watch? ");
        System.out.println("1. Orders");
        System.out.println("2. Transports");
        System.out.println("3. Return back");
        this.scanner.skip("\\R?");
        String choice = scanner.nextLine();
        switch (choice)
        {
            case "1":
                printAllOrders();
                break;
            case "2":
                //printAllTransports();
                break;
            case "3":
                managerMenu();
                break;
            default:
                System.out.println("There is no such option of choice, please choose 1 or 2 or 3 for return to back");
                seeAllOrdersOrAllTransports();
                break;
        }
    }

    public void printAllOrders()
    {
        Dictionary<String, String> data = new Hashtable<String, String>();
        System.out.println("1. all orders by transport");
        System.out.println("2. all orders in system");
        System.out.println("3. Return to Menu");
        this.scanner.skip("\\R?");
        String choice = scanner.nextLine();
        switch (choice)
        {
            case "1":
                System.out.println("Pleas enter Transport ID: ");
                int id = scanner.nextInt();
                data.put("id", Integer.toString(id));
                this.controller.printAllOrdersByTransport(data);
                break;
            case "2":
                this.controller.printAllOrders();
                break;
            case "3":
                managerMenu();
                break;
        }
    }


}
