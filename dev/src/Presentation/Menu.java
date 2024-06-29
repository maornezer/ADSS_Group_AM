package Presentation;

import Domain.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Scanner;

public class Menu {
    private Scanner scanner;
    private PresentationController controller;
    boolean readFile = false;

    public void startMenu()
    {
        scanner = new Scanner(System.in);
        controller = new PresentationController();
        int choice = -1;
        while (choice != 1 || choice != 2)
        {
            System.out.println("Please choose:");
            System.out.println("1. Initialize system with information");
            System.out.println("2. Initialize empty system");
            scanner.skip("\\R?");
            choice = scanner.nextInt();
            if (choice == 1) {
                readDataFile data = new readDataFile(controller);
                data.loadData();
                readFile = true;
                System.out.println("Sites, trucks, orders and drivers loaded successfully to the system");
                printMenu();
                return;
            }
            else if (choice == 2)
            {
                printMenu();
                return;
            }
            else
            {
                System.out.println("Please choose option 1 or 2 only");
            }
        }


    }

    public void printMenu()
    {
        System.out.println("Welcome to Super-Li Shipment module\n");
        System.out.println("Please login to the system");

        //..boolean exit = false;
        while (true)
        {
            System.out.println("Please enter your username:");
            System.out.println("If you want to logout please enter exit");
            scanner.skip("\\R?");
            String username =  scanner.nextLine();
            if(username.compareTo("exit") == 0){
                System.out.println("Exiting the program. Goodbye!");
                scanner.close();
                System.exit(0);

            }
            System.out.println("Please enter your password:");
            scanner.skip("\\R?");
            String password =  scanner.nextLine();

            if (username.compareTo("manager") == 0 && password.compareTo("1234") == 0)
            {
                managerMenu();

                return;
            }
            if (username.compareTo("driver") == 0 && password.compareTo("1111") == 0)
            {
                DriverMenu();
                return;
            }
            System.out.println("Username or password incorrect. Please enter again");
        }

    }

    public void managerMenu()
    {
        //Dictionary<String, String> data = new Hashtable<String, String>();
        System.out.println("Hello Manager!");
        //boolean exitMenu = true;
        while (true)
        {
            System.out.println("Please choose what you would like to do:");
            System.out.println("1. Create Order");
            System.out.println("2. Create Transport");
            System.out.println("3. Edit existing order or existing transport");
            System.out.println("4. Add driver / truck / site");
            System.out.println("5. View all orders or all shipments");
            System.out.println("6. Starting transport");
            System.out.println("7. Load the system with information");
            System.out.println("8. Log out");
            System.out.println("9. Exit");

            scanner.skip("\\R?");
            String choice = scanner.nextLine();
            switch (choice)
            {
                case "1":
                    createOrder();
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
                    deliveryStartUpdate();
                    break;
                case "7":
                    if (!readFile)
                    {
                        readDataFile data1 = new readDataFile(controller);
                        data1.loadData();
                        System.out.println("Sites, trucks, orders and drivers loaded successfully to the system");
                        readFile = true;
                    }
                    else {
                        System.out.println("The data is already exists in the system");

                    }
                    break;
                case "8":
                    System.out.println("Logging out. Returning to the main menu...");
                    printMenu();
                    break;
                case "9":
                    System.out.println("Exiting the program. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("There is no such option of choice, please choose valid number\n");
                    break;
            }
        }


    }

    public void DriverMenu()
    {
        Dictionary<String, String> data = new Hashtable<String, String>();
        System.out.println("Hello Driver!");
        while (true)
        {
            System.out.println("Please choose what you would like to do:");
            System.out.println("1. See all my transports");
            System.out.println("2. Producing a item report");
            System.out.println("3. Producing a transport report");
            System.out.println("4. Log out");
            System.out.println("5. Exit");

            scanner.skip("\\R?");
            String choice = scanner.nextLine();
            switch (choice)
            {
                case "1":
                    getAllDeliveries();
                    break;
                case "2":
                    getItemsReport();
                    break;
                case "3":
                    getTransportReport();
                    break;
                case "4":
                    System.out.println("Logging out. Returning to the main menu...");
                    printMenu();
                    break;
                case "5":
                    System.out.println("Exiting the program. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("There is no such option of choice, please choose valid number\n");
                    //DriverMenu();
                    break;
            }
         }
    }

public void createOrder()
{
    Dictionary<Integer, ArrayList<String>> data2 = new Hashtable< Integer,ArrayList<String>>();
    Dictionary<String, String> data1= new Hashtable<String, String>();


    System.out.println("Enter Shipping Date [yyyy/mm/dd]: ");
    scanner.skip("\\R?");
    String date = scanner.nextLine();
    LocalDate check = validateAndParseDate(date);
    if (check == null) {
        System.out.println("Invalid date format. Please enter the date in the format [yyyy/mm/dd]");
        createOrder();
    }


    String[] dateParts = date.split("/");
    if (dateParts.length == 3)
    {
        String year = dateParts[0];
        String month = dateParts[1];
        String day = dateParts[2];
        data1.put("year", year);
        data1.put("month", month);
        data1.put("day", day);
    }




    String source = checkAddressSource();
    String destination = checkAddressDestination();
    boolean b = controller.validMatchZone(source,destination );
    while (!b)
    {
        System.out.println("Source zone and destination zone are not the same");
        source = checkAddressSource();
        destination = checkAddressDestination();
        b = controller.validMatchZone(source,destination );
    }

    data1.put("source", source);
    data1.put("destination", destination);

    int i = 1;
    System.out.println("Enter the items of the order ");
    boolean choice = true;
    while (choice)
    {
        ArrayList<String> itemi = new ArrayList<>();
        System.out.println("Item number: "+i);
        System.out.println("Enter item ID");
        int itemID = scanner.nextInt();
        itemi.add(Integer.toString(itemID));
        System.out.println("Enter item name");
        scanner.skip("\\R?");
        String itemName = scanner.nextLine();
        itemi.add(itemName);
        System.out.println("Enter item amount");
        int amount = scanner.nextInt();
        itemi.add(Integer.toString(amount));
        System.out.println("Are there more items? [Y/N]");
        scanner.skip("\\R?");
        String choose = scanner.nextLine();
        data2.put(i,itemi);

        if (choose.compareTo("Y")==0)
        {
            i++;
        }
        else if (choose.compareTo("N")==0)
        {
            choice = false;
        }
        else
            System.out.println("There is no such option of choice, please choose valid number\n");
    }
    int ans = controller.creatNewOrder(data1, data2);
    if(ans == -2)
    {
        addressSolution(2) ;
    }
    if(ans == -1)
    {
        addressSolution(1);
    }
    System.out.println("Your order id is: " + ans );

}

    public String checkAddressSource() {

        while (true) {
            System.out.println("Enter the source address (Enter 0 to see all the addresses in the system)");
            scanner.skip("\\R?");
            String source = scanner.nextLine();
            boolean condition = controller.listSizeIsEmpty();
            if (source.compareTo("0") == 0) {
                if (condition) {
                    System.out.println("There are no registered sites in the system");
                    continue;
                }
                controller.printAllAddress();
                continue;
            }
            if (controller.checkAddress(source)) {
                return source;
            } else {
                addressSolution(1);
            }
        }
    }

    public String checkAddressDestination() {
        while (true) {
            System.out.println("Enter the destination address");
            scanner.skip("\\R?");
            String destination = scanner.nextLine();
            if (controller.checkAddress(destination)) {
                return destination;
            } else {
                addressSolution(2);
            }
        }
    }
    public void addressSolution(int x) {
        while (true) {
            System.out.println("Please choose what you would like to do:");
            System.out.println("1. Enter this address into the system ");
            System.out.println("2. Enter address again ");
            scanner.skip("\\R?");
            int s = scanner.nextInt();
            scanner.nextLine(); // Clear the newline
            if (s == 1) {
                addSite();
                return; // Exit the function after adding the site
            } else if (s == 2) {
                return; // Exit the function to re-enter the address
            } else {
                System.out.println("There is no such option of choice, please choose a valid number\n");
            }
        }
    }


    public void  createNewTransport()
    {
        int conditionTrucks = controller.getSizeOfListTrucks();
        int conditionDrivers = controller.getSizeOfListDrivers();
        Dictionary<String, String> data = new Hashtable<String, String>();
        if (conditionTrucks == 0)
        {
            System.out.println("You do not have trucks in the system");
            //managerMenu();
            return;
        }
        if (conditionDrivers == 0)
        {
            System.out.println("You do not have drivers in the system");
            //managerMenu();
            return;
        }
        System.out.println("Please choose truck ID from truck list: ");
        printAllTrucks();
        System.out.println("Enter Truck ID: ");
        int idT = scanner.nextInt();
        boolean existTruck = controller.existTruck(idT);
        while (!existTruck)
        {
            System.out.println("Please choose truck ID from truck list");
            System.out.println("Enter Truck ID: ");
            idT = scanner.nextInt();
            existTruck = controller.existTruck(idT);
        }
        String licenseType = getTypeOfLicense(idT);

        data.put("idT", Integer.toString(idT));
        if (!controller.checkIfDriverExistsByLicence(licenseType))
        {
            System.out.println("You do not have drivers with license type: " + licenseType+" in the system");
            return;
        }

        System.out.println("Please choose driver ID from driver list with type license " + licenseType + ": ");
        printallDriversByLicense(licenseType);
        System.out.println("Enter Driver ID: ");
        scanner.skip("\\R?");
        String idD = scanner.nextLine();
        boolean driverExist = controller.isIdDriverExists(idD);
        while (!driverExist)
        {
            System.out.println("Please choose driver ID from driver list ");
            System.out.println("Enter Driver ID: ");
            scanner.skip("\\R?");
            idD = scanner.nextLine();
            driverExist = controller.isIdDriverExists(idD);
        }

        data.put("idD", idD);
        int ans = controller.addTransport(data);

        if (ans == -1)
        {
            System.out.println("The Truck id is not registered in the system");
            System.out.println("Failed to create the shipment");
            createNewTransport();
        }
        if (ans == -2)
        {
            System.out.println("The Driver id is not registered in the system");
            System.out.println("Failed to create the transport");
            createNewTransport();

        }
        else
        {
            System.out.println("The transport has been successfully added to the system");
            System.out.println("Your transport number is: " + ans);
            System.out.println("Enter if you want to add a order number to this transport [Y/N]");
            scanner.skip("\\R?");
            String s = scanner.nextLine();
            if (s.compareTo("Y") == 0)
            {
                addOrderTonewTransport(ans);
            }
            managerMenu();
        }

    }
    public void addOrderTonewTransport(int transportID) {
//        System.out.println("Please enter the ID number of the transport you received: ");
//        int transID = scanner.nextInt();
//        boolean tranIDExist = controller.isTransportExist(transID);
//        if (!tranIDExist)
//        {
//            System.out.println("There is no such a transport ID in the system");
//            JEKDFHRaddOrderTonewTransport();
//        }

        int conditionOrders = controller.getSizeOfListOrders();
        System.out.println("Please choose:");
        //System.out.println("1. Creat new order");
        System.out.println("1. Add an existing order");
        System.out.println("2. Exit");
        scanner.skip("\\R?");
        String choose = scanner.nextLine();

        switch (choose) {
//            case "1":
//                createOrder();
//                anotherOrder(transportID);
//                break;
            case "1":
                if (conditionOrders == 0)
                {
                    System.out.println("There is no orders system");
                    break;
                }
                addOrderToTransport(transportID);
                anotherOrder(transportID);
                break;
            case "2":
                break;
            default:
                System.out.println("There is no such option of choice, please choose valid number\n");
        }
    }

    public void editOrderOrTransport()
    {
        int conditionOrders = controller.getSizeOfListOrders();
        int conditionTransports = controller.getSizeOfListTransports();
        System.out.println("Enter what do you like to edit: ");
        System.out.println("1. Edit the address of the order");
        System.out.println("2. Edit Transport");
        System.out.println("3. Return to Menu");
        scanner.skip("\\R?");
        String choice = scanner.nextLine();
        switch (choice)
        {
            case "1":
                if (conditionOrders == 0)
                {
                    System.out.println("You do not have orders in the system");
                }
                else
                {
                    editOrder();
                }
                break;
            case "2":
                if (conditionTransports == 0)
                {
                    System.out.println("You do not have transports in the system");
                }
                else
                {
                    editTransport();
                }
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

        System.out.println("Choose if you want to change the Address of destination order: " + idOrder + ": [Y/N]");
        scanner.skip("\\R?");
        String ans = scanner.nextLine();
        if (ans.compareTo("Y") == 0)
        {
            changeDestination(idOrder);

        }
        else if (ans.compareTo("N") == 0)
        {
            System.out.println("You Choose that you dont want to change anything in order: " + idOrder );
            editOrderOrTransport();
        }
        else
        {
            System.out.println("You must choose Y/N please try again" );

        }

        ///complete
    }
    public void changeDestination(int orderID)
    {
        Dictionary<String, String> data = new Hashtable<String, String>();
        int id = orderID;
        data.put("id", Integer.toString(id));
        System.out.println("Enter Site Address of new destination: ");
        scanner.skip("\\R?");
        String destination = scanner.nextLine();
        data.put("destination", destination);
        boolean b = controller.changeDestination(data);
        if (!b)
        {
            System.out.println("Change destination failed! The zone is different from the original destination zone");
        }
        else
        {
            System.out.println("Change destination successfully!");
        }
    }
    public void editTransport() {
        System.out.println("Choose transport that you want to edit: ");
        controller.printAllTransports();
        System.out.println("Enter ID of the Transport you want to change: ");
        scanner.skip("\\R?");
        String id = scanner.nextLine();
        boolean existTransport = controller.isTransportExist(id);
        if (!existTransport)
        {
            System.out.println("Enter valid ID from the list");
            editTransport();
        }
        System.out.println("Choose what change you would like to make in the shipment with an ID number " + id + ": ");
        System.out.println("1. Change Truck");
        System.out.println("2. Change Driver");
        System.out.println("3. Add order to transport");
        System.out.println("4. Return back");
        scanner.skip("\\R?");
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                changeTruck(Integer.parseInt(id));

                break;
            case "2":
                changeDriver(Integer.parseInt(id));
                break;
            case "3":
                addOrderTonewTransport(Integer.parseInt(id));
                break;
            case "4":
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
        //String licenseTruck = getTypeOfLicense();
        System.out.println("Enter new Truck ID: ");
        int idTruck = scanner.nextInt();
        boolean truckExist = controller.existTruck(idTruck);
        while (!truckExist)
        {
            System.out.println("Please enter truck ID that exist in the system");
            changeTruck(id);
        }
        String licenseTruckNew = getTypeOfLicense(idTruck);
        data.put("idTruck", Integer.toString(idTruck));
        controller.changeTruck(data);
    }

    public void changeDriver(int id)
    {
        Dictionary<String, String> data = new Hashtable<String, String>();
        int idTransport = id;
        data.put("idTransport", Integer.toString(idTransport));

        System.out.println("Enter new Driver ID: ");
        scanner.skip("\\R?");
        String idDriver = scanner.nextLine();
        boolean existDriver = controller.isIdDriverExists(idDriver);
        while (!existDriver)
        {
            System.out.println("The id of driver that not exist");
            changeDriver(id);
        }
        data.put("idDriver",idDriver);

        controller.changeDriver(data);

    }
    public void editDatabase()
    {
        System.out.println("Enter what do you want to add to your system: ");
        System.out.println("1. add new Site");
        System.out.println("2. add new Truck");
        System.out.println("3. add new Driver");
        System.out.println("4. Return back");

        scanner.skip("\\R?");
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
            case "4":
                managerMenu();
                break;
            default:
                System.out.println("There is no such option of choice, please choose 1 or 2 or 3 or 4 for return to back");
                editDatabase();
        }
    }


    public void addSite()
    {
        Dictionary<String, String> data = new Hashtable<String, String>();

        System.out.println("Enter Site Address: ");
        scanner.skip("\\R?");
        String address = scanner.nextLine();
        data.put("address", address);


        System.out.println("Choose Site Zone: [North, South, Center]");
        scanner.skip("\\R?");
        String zone = scanner.nextLine();

        while (zone.compareTo("North") !=0 && zone.compareTo("South")!=0 && zone.compareTo("Center")!=0)
        {
            System.out.println("There is no such option of choice");
            System.out.println("Choose Site Zone: [North, South, Center]");
            scanner.skip("\\R?");
            zone = scanner.nextLine();
        }

        data.put("zone", zone);

        System.out.println("Enter Contact Name of Site: ");
        scanner.skip("\\R?");
        String contactName = scanner.nextLine();
        data.put("contactName", contactName);

        System.out.println("Enter Contact Phone Number of Site: ");
        scanner.skip("\\R?");
        String phoneNumber = scanner.nextLine();
        data.put("phoneNumber", phoneNumber);

        controller.addSite(data,"menu");
        System.out.println("The site has been registered in the system");
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
        scanner.skip("\\R?");
        String modelName = scanner.nextLine();
        data.put("model", modelName);

        controller.addTruck(data);
    }
        public void addDriver()
    {
        Dictionary<String, String> data = new Hashtable<String, String>();
        System.out.println("Enter Driver Name: ");
        scanner.skip("\\R?");
        String name = scanner.nextLine();
        data.put("name", name);

        System.out.println("Enter Driver ID: ");
        int id = scanner.nextInt();
        data.put("id", Integer.toString(id));

        System.out.println("Enter Driver Type of License: [B, C, D]");
        scanner.skip("\\R?");
        String typeOfLicense = scanner.nextLine();
        data.put("typeOfLicense", typeOfLicense);

        controller.addDriver(data);
    }
    public void seeAllOrdersOrAllTransports()
    {
        System.out.println("What would you like to watch? ");
        System.out.println("1. Orders");
        System.out.println("2. Transports");
        System.out.println("3. Return back");
        scanner.skip("\\R?");
        String choice = scanner.nextLine();
        switch (choice)
        {
            case "1":
                printAllOrders();
                break;
            case "2":
                printAllTransports();
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
        scanner.skip("\\R?");
        String choice = scanner.nextLine();
        switch (choice)
        {
            case "1":
                System.out.println("Pleas enter Transport ID: ");
                int id = scanner.nextInt();
                data.put("id", Integer.toString(id));
                controller.printAllOrdersByTransport(data);
                break;
            case "2":
                controller.printAllOrders();
                 break;
            case "3":
                managerMenu();
                break;
        }
    }
    public void anotherOrder(int transportID)
    {
        System.out.println("Do you want to add order to this transport? [Y/N] ");
        scanner.skip("\\R?");
        String choice = scanner.nextLine();
        if (choice.compareTo("Y") == 0)
        {
            addOrderTonewTransport(transportID);
        }
        else
            managerMenu();

    }
    public void getAllDeliveries()
    {
        System.out.println("Please enter your ID:");
        scanner.skip("\\R?");
        String idDriver = scanner.nextLine();
        controller.seeAllTransportByDriver(idDriver);

    }

    public void getItemsReport()
    {
        System.out.println("Please enter order ID:");
        scanner.skip("\\R?");
        String idOrder = scanner.nextLine();
        controller.getItemInOrder(idOrder);
    }
    public void getTransportReport()
    {
        System.out.println("Please enter transport ID:");
        scanner.skip("\\R?");
        String transportId = scanner.nextLine();
        controller.getTransportReport(transportId);
    }
    public void printAllTransports()
    {
        Dictionary<String, String> data = new Hashtable<String, String>();
        System.out.println("1. all transports in system");
        System.out.println("2. transport by ID");
        System.out.println("3. Return to Menu");
        scanner.skip("\\R?");
        String choice = scanner.nextLine();
        switch (choice)
        {
            case "1":
                controller.printAllTransports();
                break;
            case "2":
                getTransportReport();
                break;
            case "3":
                managerMenu();
                break;
        }
    }

    public void addOrderToTransport(int transportID)
    {
        System.out.println("Please enter order ID would you like to add to transport "+ transportID + ": ");
        scanner.skip("\\R?");
        String orderID = scanner.nextLine();
        boolean orderExist = controller.orderExist(orderID , Integer.toString(transportID));
        if (orderExist)
        {
            System.out.println("The order with ID "+orderID+ " already exit in this transport");
            return;

        }
        Dictionary<String, String> data = new Hashtable<String, String>();
        data.put("transportID",Integer.toString(transportID));
        data.put("orderID", orderID);
        boolean b = controller.addOrderToTransport(data);
        if (!b)
        {
            System.out.println("The Order with number id " + orderID + " was not added to transport "+ transportID);
        }
        else
        {
            System.out.println("The Order with number id " + orderID + " was added successfully to transport "+ transportID);
        }

    }

    public void printAllTrucks()
    {
        controller.printAllTrucks();
    }

    public String getTypeOfLicense(int idT)
    {
        return controller.getTypeOfLicense(idT);
    }
    public void printallDriversByLicense(String licenseType)
    {
        controller.printallDriversByLicense( licenseType);
    }
    public void deliveryStartUpdate()
    {
        System.out.println("Please enter the transport id you want to send ");
        scanner.skip("\\R?");
        String transportID = scanner.nextLine();
        boolean check = controller.isTransportExist(transportID);
        if (!check)
        {
            System.out.println("Transport with ID "+transportID+" does not exist in the system, please enter it again");
            System.out.println("Please choose if you want try again or return back");
            System.out.println("1. Try again");
            System.out.println("2. Return back");
            int choose = scanner.nextInt();
            if (choose == 2)
            {
                managerMenu();
            }
            else {
                deliveryStartUpdate();
            }
        }
        ArrayList<Order> orderArrayList = controller.getAllOrdersByTransport(transportID);
        boolean b ;
        if(!orderArrayList.isEmpty())
        {
            for (Order order: orderArrayList)
            {
                if (order == null)
                {
                    break;
                }
                b = Orderweightupdate(Integer.parseInt(transportID),order.getId());
                if (!b)
                {
                    System.out.println("Unsuccessful loading! The weight of the truck is greater than its maximum weight");
                    System.out.println("Hi manager, please select a solution for shipment "+ transportID +" containing order " + order.getId());
                    managerSulotion(Integer.parseInt(transportID), order.getId());
                }
                else
                {
                    System.out.println("Adding order number " + order.getId() + " to the truck has been successfully completed");
                }

            }
        }

        System.out.println("Weighing was done successfully! The truck can leave");

//        System.out.println("Please enter the order ID for which you would like to update the weight");
//        int orderID = scanner.nextInt();
//        boolean b = Orderweightupdate(transportID,orderID);
//        if (!b)
//        {
//            System.out.println("Hi manager, please select a solution for shipment "+ transportID +" containing order " + orderID);
//            managerSulotion(transportID, orderID);
//        }


    }
    public boolean Orderweightupdate(int transportID, int orderID)
    {
        Dictionary<String, String> data = new Hashtable<String, String>();
//        System.out.println("Please enter the order ID for which you would like to update the weight");
//        int orderID = scanner.nextInt();
        System.out.println("Please enter the order "+ orderID +" weight: ");
        int weight = scanner.nextInt();
        data.put("transportID", Integer.toString(transportID));
        data.put("orderID", Integer.toString(orderID));
        data.put("weight", Integer.toString(weight));
        return controller.loadOrderToTruck(data);
    }
    public void managerSulotion(int transportID, int orderID)
    {
        //System.out.println("Hi manager, please select a solution for shipment "+ transportID +" containing order " + orderID);
        System.out.println("1. Change Truck");
        System.out.println("2. Unloading Item");
        System.out.println("3. Change destination");
        scanner.skip("\\R?");
        String choice = scanner.nextLine();
        switch (choice)
        {
            case "1":
                changeTruckSol(transportID, orderID);
                break;
            case "2":
                UnloadingItemSol(transportID, orderID);
                break;
            case "3":
                changeDestinationSol(transportID,orderID);
                break;
            default:
                System.out.println("You must choose a solution to the weight problem ");
                managerSulotion(transportID, orderID);
                break;
        }
//        public boolean treatmentWeightProblemChangeDestination(int orderID,String address,int transportID)

    }



    public void changeTruckSol(int transportID , int orderID)
    {
        Dictionary<String, String> data = new Hashtable<String, String>();
        System.out.println("Please enter new truck ID: ");
        scanner.skip("\\R?");
        String truckId = scanner.nextLine();
        data.put("transportID", Integer.toString(transportID));
        data.put("truckId", truckId);

        boolean sol1 = controller.treatmentWeightProblemChangeTruck(data);
        if (!sol1)
        {
            System.out.println("Changing the truck failed Please choose again a solution for the weight problem");
            managerSulotion(transportID, orderID);
        }
        else
        {
            System.out.println("The truck was successfully replaced");
            System.out.println("Weigh the truck again");
            ArrayList<Order> orderArrayList = controller.getAllOrdersByTransport(Integer.toString(transportID));
            for (Order order: orderArrayList)
            {
                Orderweightupdate(transportID, order.getId());
            }
        }
    }

    public void UnloadingItemSol(int transportID , int orderID)
    {
        Dictionary<String, String> data = new Hashtable<String, String>();
        data.put("transportID", Integer.toString(transportID));
        data.put("orderID", Integer.toString(orderID));
        controller.printOrder(data);
        System.out.println("Please enter the ID of the item you want to change the quantity of ");
        int itemID = scanner.nextInt();
        System.out.println("What is the amount you would like to reduce from the item ");
        int amount = scanner.nextInt();
        data.put("itemID", Integer.toString(itemID));
        data.put("amount", Integer.toString(amount));
        boolean sol2 = controller.UnloadingItems(data);
        if (!sol2)
        {
            System.out.println("Changing the quantity of item "+ itemID + " failed Please choose again a solution for the weight problem");
            managerSulotion(transportID, orderID);
        }
        else
        {
            System.out.println("The quantity of item "+ itemID + " was successfully replaced");
            System.out.println("Weigh the truck again");
            ArrayList<Order> orderArrayList = controller.getAllOrdersByTransport(Integer.toString(transportID));
            for (Order order: orderArrayList)
            {
                Orderweightupdate(transportID, orderID);
            }

        }
    }
    private void changeDestinationSol(int transportID, int orderID)
    {
        Dictionary<String, String> data = new Hashtable<String, String>();
        System.out.println("Please enter new destination address: ");
        scanner.skip("\\R?");
        String address = scanner.nextLine();
        data.put("orderID", Integer.toString(orderID));
        data.put("transportID", Integer.toString(transportID));
        data.put("address", address);
        boolean sol3 = controller.treatmentWeightProblemChangeDestination(data);
        if (!sol3)
        {
            System.out.println("Changing the destination failed Please choose again a solution for the weight problem");
            managerSulotion(transportID, orderID);
        }
        else
        {
            System.out.println("The destination was successfully replaced");
            System.out.println("Weigh the truck again");
            ArrayList<Order> orderArrayList = controller.getAllOrdersByTransport(Integer.toString(transportID));
            for (Order order: orderArrayList)
            {
                Orderweightupdate(transportID, orderID);
            }
        }
    }
    public static LocalDate validateAndParseDate(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        try {
            LocalDate date = LocalDate.parse(dateStr, formatter);
            return date;
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}
