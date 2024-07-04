package Presentation;

import Domain.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Menu {
    private Scanner scanner;
    private PresentationController controller;

    public void printMenu()
    {
        scanner = new Scanner(System.in);
        controller = new PresentationController();
        System.out.println("Welcome to Super-Li Shipment module :)");
        while (true) {
            System.out.println("Please enter your username [for logout please enter exit]");
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
        scanner = new Scanner(System.in);
        controller = new PresentationController();
        System.out.println("Hello Manager!");
        while (true)
        {
            System.out.println("Please choose what you would like to do:");
            System.out.println("1. Add / Delete / Get");
            System.out.println("2. Create Transport");
            System.out.println("3. Update transport");
            System.out.println("4. Starting transport");
            System.out.println("5. Log out");
            System.out.println("6. Exit");
            scanner.skip("\\R?");
            String choice = scanner.nextLine();
            switch (choice)
            {
                case "1":
                    editDatabase();
                    break;
                case "2":
                    createNewTransport();
                    break;
                case "3":
                    editTransport();
                    break;
                case "4":
                    deliveryStartUpdate();
                    break;
                case "5":
                    System.out.println("Logging out. Returning to the main menu...");
                    printMenu();
                    break;
                case "6":
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
        System.out.println("Enter source site id:");
        scanner.skip("\\R?");
        String sourceID = scanner.nextLine();
        boolean checkId = controller.searchSite(sourceID);
        if (!checkId) {
            System.out.println("Site id is not registered in the system, order canceled");
            return;
        }
        System.out.println("Enter destination site id:");
        scanner.skip("\\R?");
        String destID = scanner.nextLine();
        boolean checkDestID = controller.searchSite(destID);
        if (!checkDestID) {
            System.out.println("Site id is not registered in the system, order canceled");
            return;
        }
        boolean machZone = controller.validMatchZone(sourceID, destID);
        if (!machZone)
        {
            System.out.println("Source site and destination site are not in the same zone, order canceled");
            return;
        }
        data1.put("source", sourceID);
        data1.put("destination", destID);
        Dictionary<Integer, ArrayList<String>> data2 = addItems();
        int ans = controller.creatNewOrder(data1, data2);
        System.out.println("Your order id is: " + ans );
        managerMenu();
    }

    public Dictionary<Integer, ArrayList<String>> addItems(){
        Dictionary<Integer, ArrayList<String>> data2 = new Hashtable< Integer,ArrayList<String>>();

        System.out.println("Enter the items of the order ");
        int i = 1;
        boolean choice = true;
        while (choice)
        {
            ArrayList<String> itemi = new ArrayList<>();
            System.out.println("Item number: "+ i);
            System.out.println("Enter item ID");
            scanner.skip("\\R?");
            String itemID = scanner.nextLine();
            boolean searchItem = controller.searchItem(Integer.parseInt(itemID));
            if (searchItem)
            {
                System.out.println("Id item is already exit, please try again");
                addItems();
            }
            itemi.add(itemID);
            System.out.println("Enter item name");
            scanner.skip("\\R?");
            String itemName = scanner.nextLine();
            itemi.add(itemName);
            System.out.println("Enter item amount");
            scanner.skip("\\R?");
            String amount = scanner.nextLine();
            itemi.add(amount);
            System.out.println("Would you like to add more items to the order? [Y/N]");
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
        return data2;
    }

    public void  createNewTransport()
    {
//        int conditionTrucks = controller.getSizeOfListTrucks();
//        int conditionDrivers = controller.getSizeOfListDrivers();
        Dictionary<String, String> data = new Hashtable<String, String>();
        System.out.println("Please enter id order that you would like to add to transport:");
        scanner.skip("\\R?");
        String idOrder = scanner.nextLine();
        boolean checkOrder = controller.checkOrder(idOrder);
        if (!checkOrder) {
            System.out.println("Order "+ idOrder +" was not exist, please try again");
            return;
        }
        boolean associatedTransport = controller.associatedTransport(idOrder);
        if(associatedTransport)
        {
            System.out.println("This order is already associated to different transport");
            return;
        }
//        System.out.println("Please choose truck ID from truck list: ");
//        printAllTrucks();
        System.out.println("Enter Truck ID: ");
        scanner.skip("\\R?");
        String idT = scanner.nextLine();
        boolean existTruck = controller.searchTruck(Integer.parseInt(idT));
        if (!existTruck)
        {
            System.out.println("Truck "+ idT +" was not exist");
            return;
        }
        boolean freeTruck = controller.freeTruck(Integer.parseInt(idT), Integer.parseInt(idOrder));
        while(!freeTruck)
        {
            System.out.println("Truck "+ idT +" was not free in this order date");
            System.out.println("Enter Truck ID again: ");
            scanner.skip("\\R?");
            idT = scanner.nextLine();
            existTruck = controller.searchTruck(Integer.parseInt(idT));
            if (!existTruck)
            {
                System.out.println("Truck "+ idT +" was not exist");
                return;
            }
            freeTruck = controller.freeTruck(Integer.parseInt(idT), Integer.parseInt(idOrder));
        }

        String licenseType = getTypeOfLicense(Integer.parseInt(idT));
        System.out.println("Enter Driver ID: ");
        scanner.skip("\\R?");
        String idD = scanner.nextLine();
        boolean driverExist = controller.searchDriver(Integer.parseInt(idD));

        while(!driverExist) {
            System.out.println("Driver " + idD + " was not exist");
            System.out.println("Enter Driver ID again: ");
            scanner.skip("\\R?");
            idD = scanner.nextLine();
            driverExist = controller.searchDriver(Integer.parseInt(idD));
        }

        boolean machLicense = controller.checkMatchLicense(idT, idD);
        if (!machLicense)
        {
            System.out.println("Driver "+ idD +" was not have mach license to Truck");
            return;
        }
        boolean freeDriver = controller.freeDriver(Integer.parseInt(idD), Integer.parseInt(idOrder));
        if (!freeDriver)
        {
            System.out.println("Driver "+ idD +" was not free in this order date");
            return;
        }
        data.put("idO", idOrder);
        data.put("idT", idT);
        data.put("idD", idD);
        int ans = controller.addTransport(data);
        System.out.println("The transport has been successfully added to the system");
        System.out.println("Your transport number is: " + ans);
    }


    public void addOrderTonewTransport(int transportID)
    {
        Dictionary<String, String> data = new Hashtable<String, String>();
        System.out.println("Please enter id order you would like to add to transport: "+ transportID);
        scanner.skip("\\R?");
        String idOrder = scanner.nextLine();
        boolean checkOrder = controller.checkOrder(idOrder);
        if (!checkOrder) {
            System.out.println("Order "+ idOrder +" was not exist, please try again");
            return;
        }
        boolean associatedTransport = controller.associatedTransport(idOrder);
        if(associatedTransport)
        {
            System.out.println("This order is already associated to different transport");
            return;
        }
        data.put("transportID",Integer.toString(transportID));
        data.put("orderID", idOrder);
        boolean b = controller.addOrderToTransport(data);
        if (!b)
        {
            System.out.println("The Order with number id " + idOrder + " was not added to transport "+ transportID);
        }
        else
        {
            System.out.println("The Order with number id " + idOrder + " was added successfully to transport "+ transportID);
        }
    }
    //for over weight:
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

    public void editTransport()
    {
        System.out.println("Enter ID of the Transport you want to edit: ");
        scanner.skip("\\R?");
        String id = scanner.nextLine();
        boolean existTransport = controller.isTransportExist(Integer.parseInt(id));
        if (!existTransport)
        {
            System.out.println("Transport "+ id +" was not exist");
            editTransport();
        }
        System.out.println("Choose what change you would like to do in transport " + id + ": ");
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
                managerMenu();
                break;
        }
    }

    public void changeTruck(int id)
    {
        Dictionary<String, String> data = new Hashtable<String, String>();
        int idTransport = id;
        data.put("idTransport", Integer.toString(idTransport));
        System.out.println("Enter new Truck ID: ");
        scanner.skip("\\R?");
        String idTruck = scanner.nextLine();
        boolean truckExist = controller.searchTruck(Integer.parseInt(idTruck));
        if(!truckExist)
        {
            System.out.println("There is no truck with id " + id + " in the system");
            changeTruck(id);
        }
        data.put("idTruck", idTruck);
        boolean b = controller.changeTruck(data);
        if(!b)
        {
            System.out.println("Truck in transport "+ idTransport + " was not change");
            return;
        }
        else
            System.out.println("Truck in transport "+ idTransport + " was change successfully");
    }

    public void changeDriver(int id)
    {
        Dictionary<String, String> data = new Hashtable<String, String>();
        int idTransport = id;
        data.put("idTransport", Integer.toString(idTransport));
        System.out.println("Enter new Driver ID: ");
        scanner.skip("\\R?");
        String idDriver = scanner.nextLine();
        boolean existDriver = controller.searchDriver(Integer.parseInt(idDriver));
        while (!existDriver)
        {
            System.out.println("There is no driver with id " + id + " in the system");
            changeDriver(id);
        }

        data.put("idDriver",idDriver);
        boolean b = controller.changeDriver(data);
        if(!b)
        {
            System.out.println("Driver in transport "+ idTransport + " was not change");
            return;
        }
        else
            System.out.println("Driver in transport "+ idTransport + " was change successfully");
    }

    public void editDatabase()
    {
        System.out.println("Enter what do you want to edit:");
        System.out.println("1. Site");
        System.out.println("2. Truck");
        System.out.println("3. Driver");
        System.out.println("4. Order");
        System.out.println("5. Return back");

        scanner.skip("\\R?");
        String choice = scanner.nextLine();
        switch (choice)
        {
            case "1":
                siteMenu();
                break;
            case "2":
                truckMenu();
                break;
            case "3":
                driverMenu();
                break;
            case "4":
                orderMenu();
                break;
            case "5":
                managerMenu();
                break;
            default:
                System.out.println("There is no such option of choice, please choose 1 or 2 or 3 or 4 for return to back");
                editDatabase();
        }
    }
    public void siteMenu()
    {
        System.out.println("Enter what do you want to do:");
        System.out.println("1. Add new Site");
        System.out.println("2. Remove site");
        System.out.println("3. Get Site information");
        System.out.println("4. Return back");
        scanner.skip("\\R?");
        String choice = scanner.nextLine();
        switch (choice)
        {
            case "1":
                addSite();
                break;
            case "2":
                removeSite();
                break;
            case "3":
                getSite();
                break;
            case "4":
                managerMenu();
                break;
            default:
                System.out.println("There is no such option of choice please try again");
                editDatabase();
        }
    }


    public void addSite()
    {
        Dictionary<String, String> data = new Hashtable<String, String>();

        System.out.println("Enter Site id: ");
        scanner.skip("\\R?");
        String id = scanner.nextLine();
        data.put("id", id);

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

        boolean b = controller.addSite(data,"menu");
        if (!b) {
            System.out.println("Adding the site failed");

        }
        else
            System.out.println("Adding the site complete");
    }
    public void removeSite()
    {
        Dictionary<String, String> data = new Hashtable<String, String>();
        System.out.println("Please enter site id you want to remove: ");
        scanner.skip("\\R?");
        String id = scanner.nextLine();
        data.put("id", id);
        boolean b = controller.removeSite(data);
        if (!b) {
            System.out.println("Removing the site failed");

        }
        else
            System.out.println("Removing the site complete");
    }

    public void getSite()
    {
        Dictionary<String, String> data = new Hashtable<String, String>();
        System.out.println("Please enter site id you want to get his information: ");
        scanner.skip("\\R?");
        String id = scanner.nextLine();
        data.put("id", id);
        Site s = controller.getSite(data);
        if (s == null) {
            System.out.println("There is no site with id " + id + " in the system");
        }
        else
            System.out.println("Address: " + s.getAddress() + ", Contact Name: " + s.getContactName() + ", Phone number: " + s.getPhoneNumber() + " Zone: " + s.getSiteZone());
    }
    public void truckMenu()
    {
        System.out.println("Enter what do you want to do:");
        System.out.println("1. Add new truck");
        System.out.println("2. Remove truck");
        System.out.println("3. Get truck information");
        System.out.println("4. Return back");
        scanner.skip("\\R?");
        String choice = scanner.nextLine();
        switch (choice)
        {
            case "1":
                addTruck();
                break;
            case "2":
                removeTruck();
                break;
            case "3":
                getTruck();
                break;
            case "4":
                managerMenu();
                break;
            default:
                System.out.println("There is no such option of choice please try again");
                editDatabase();
        }
    }
    public void addTruck()
    {
        Dictionary<String, String> data = new Hashtable<String, String>();

        System.out.println("Enter Truck ID: ");
        scanner.skip("\\R?");
        String id = scanner.nextLine();
        data.put("idT", id);

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

        boolean b = controller.addTruck(data);
        if (!b) {
            System.out.println("Adding the truck failed");

        }
        else
            System.out.println("Adding the truck complete");

    }
    public void removeTruck()
    {
        Dictionary<String, String> data = new Hashtable<String, String>();
        System.out.println("Please enter truck id you want to remove: ");
        scanner.skip("\\R?");
        String idT = scanner.nextLine();
        data.put("id", idT);
        boolean b = controller.removeTruck(data);
        if (!b)
            System.out.println("Removing the truck failed");
        else
            System.out.println("Removing the truck complete");
    }

    public void getTruck()
    {
        Dictionary<String, String> data = new Hashtable<String, String>();
        System.out.println("Please enter truck id you want to get his information: ");
        scanner.skip("\\R?");
        String id = scanner.nextLine();
        data.put("id", id);
        Truck t = controller.getTruck(data);
        if (t == null) {
            System.out.println("There is no truck with id " + id + " in the system");
        }
        else
            System.out.println("Truck ID: " + id + ", Model: " + t.getTruckModel() + ", Initial Weight: " + t.getInitialWeight() + ", Max Weight: " + t.getMaxWeight() + ", License required: " + t.getTypeOfLicense());
    }
    public void driverMenu()
    {
        System.out.println("Enter what do you want to do:");
        System.out.println("1. Add new driver");
        System.out.println("2. Remove driver");
        System.out.println("3. Get driver information");
        System.out.println("4. Return back");
        scanner.skip("\\R?");
        String choice = scanner.nextLine();
        switch (choice)
        {
            case "1":
                addDriver();
                break;
            case "2":
                removeDriver();
                break;
            case "3":
                getDriver();
                break;
            case "4":
                managerMenu();
                break;
            default:
                System.out.println("There is no such option of choice please try again");
                editDatabase();
        }
    }

    public void addDriver()
    {
        Dictionary<String, String> data = new Hashtable<String, String>();
        System.out.println("Enter Driver Name: ");
        scanner.skip("\\R?");
        String name = scanner.nextLine();
        data.put("name", name);

        System.out.println("Enter Driver ID: ");
        scanner.skip("\\R?");
        String id = scanner.nextLine();
        data.put("id",id);

        System.out.println("Enter Driver Type of License: [B, C, D]");
        scanner.skip("\\R?");
        String typeOfLicense = scanner.nextLine();
        while (typeOfLicense.compareTo("B")!=0 && typeOfLicense.compareTo("C")!=0 && typeOfLicense.compareTo("D")!=0)
        {
            System.out.println("Please choose valid option: [B, C, D]");
            scanner.skip("\\R?");
            typeOfLicense = scanner.nextLine();
        }
        data.put("typeOfLicense", typeOfLicense);

        boolean b = controller.addDriver(data);
        if (!b)
            System.out.println("Adding the driver failed");
        else
            System.out.println("Adding the driver complete");
    }
    public void removeDriver()
    {
        Dictionary<String, String> data = new Hashtable<String, String>();
        System.out.println("Please enter driver id you want to remove: ");
        scanner.skip("\\R?");
        String idD = scanner.nextLine();
        data.put("id", idD);
        boolean b = controller.removeDriver(data);
        if (!b)
            System.out.println("Removing the driver failed");
        else
            System.out.println("Removing the driver complete");
    }
    public void getDriver()
    {
        Dictionary<String, String> data = new Hashtable<String, String>();
        System.out.println("Please enter driver id you want to get his information: ");
        scanner.skip("\\R?");
        String id = scanner.nextLine();
        data.put("id", id);
        Driver d = controller.getDriver(data);
        if (d == null) {
            System.out.println("There is no driver with id " + id + " in the system");
        }
        else
            System.out.println("Name: "+ d.getName() + ", ID: " + id +", License type: " + d.getTypeOfLicense());
    }
    public void orderMenu()
    {
        System.out.println("Enter what do you want to do:");
        System.out.println("1. Add new order");
        System.out.println("2. Remove order");
        System.out.println("3. Get order information");
        System.out.println("4. Return back");
        scanner.skip("\\R?");
        String choice = scanner.nextLine();
        switch (choice)
        {
            case "1":
                createOrder();
                break;
            case "2":
                removeOrder();
                break;
            case "3":
                getOrder();
                break;
            case "4":
                managerMenu();
                break;
            default:
                System.out.println("There is no such option of choice please try again");
                editDatabase();
        }
    }
    public void removeOrder()
    {
        Dictionary<String, String> data = new Hashtable<String, String>();
        System.out.println("Please enter oder id you want to remove: ");
        scanner.skip("\\R?");
        String idO = scanner.nextLine();
        data.put("id", idO);
        boolean b = controller.removeOrder(data);
        if (!b)
            System.out.println("Removing the order failed");
        else
            System.out.println("Removing the order complete");
    }
    public void getOrder()
    {
        System.out.println("Please enter order id you want to get her information: ");
        scanner.skip("\\R?");
        String id = scanner.nextLine();
        Order o = controller.getOrder(id);
        if (o == null) {
            System.out.println("There is no order with id " + id + " in the system");
        }
        else
        {
            System.out.println("Order ID: "+ id);
            System.out.println("Zone: "+ o.getDestination().getSiteZone());
            System.out.println("Source: "+ o.getSource().getAddress());
            System.out.println("Destination: "+ o.getDestination().getAddress());
            System.out.println("Deliver in: "+ o.getDate().toString());
            System.out.println(("Items:"));
            for (Item item : o.getItems()) {
                System.out.println("Item ID: " + item.getId() + ", Name: " + item.getName() + ", Amount: " + item.getAmount());
            }
            if (o.getTransportAssociation() != -1 )
            {
                System.out.println("Transport Association: "+ o.getTransportAssociation());
            }
            else
                System.out.println("The order is not have Transport Association");
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
        boolean check = controller.isTransportExist(Integer.parseInt(transportID));
        if (!check)
        {
            System.out.println("Transport with ID "+transportID+" does not exist in the system, please enter it again");
            System.out.println("Please choose if you want try again or return back");
            System.out.println("1. Try again");
            System.out.println("2. Return back");
            scanner.skip("\\R?");
            String choose = scanner.nextLine();
            if (choose.compareTo("2") == 0)
            {
                managerMenu();
            }
            else {
                deliveryStartUpdate();
            }
        }
        ArrayList<Order> orderArrayList = controller.getAllOrdersByTransport(Integer.parseInt(transportID));
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
    }
    public boolean Orderweightupdate(int transportID, int orderID)
    {
        Dictionary<String, String> data = new Hashtable<String, String>();
        System.out.println("Please enter the order "+ orderID +" weight: ");
        scanner.skip("\\R?");
        String weight = scanner.nextLine();
        data.put("transportID", Integer.toString(transportID));
        data.put("orderID", Integer.toString(orderID));
        data.put("weight", weight);
        return controller.loadOrderToTruck(data);
    }
    public void managerSulotion(int transportID, int orderID)
    {
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
            ArrayList<Order> orderArrayList = controller.getAllOrdersByTransport(transportID);
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
        scanner.skip("\\R?");
        String itemID = scanner.nextLine();
        System.out.println("What is the amount you would like to reduce from the item ");
        scanner.skip("\\R?");
        String amount = scanner.nextLine();
        data.put("itemID", itemID);
        data.put("amount", amount);
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
            ArrayList<Order> orderArrayList = controller.getAllOrdersByTransport(transportID);
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
            ArrayList<Order> orderArrayList = controller.getAllOrdersByTransport(transportID);
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
