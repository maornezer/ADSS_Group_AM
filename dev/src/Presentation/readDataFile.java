package Presentation;

import Domain.Order;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class readDataFile
{
    private PresentationController prController;

    public readDataFile(PresentationController prController )
    {
        this.prController = prController;
    }

    public void loadData()
    {
        String csvFilePath = "/data.csv";
        Dictionary<String,String> data1 = new Hashtable<String, String>();
        Dictionary<Integer, ArrayList<String>> data2  = new Hashtable<Integer, ArrayList<String>>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(Main.class.getResourceAsStream(csvFilePath)))) {
            String line;
            String currentSection = "";
            int lineNumber = 0;
            int itemNum = 1;
            // Read each line from the CSV file
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty())
                    continue; // Skip empty lines

                if (line.equalsIgnoreCase("driver,,,") ||
                        line.equalsIgnoreCase("site,,,") ||
                        line.equalsIgnoreCase("truck,,,") ||
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

                        Dictionary<String,String> d = addDriverDict(driverName,driverLicence,driverID);
                        prController.addDriver(d);
                        break;
                    case "site":
                        if (parts.length < 4) break; // Ensure correct number of fields
                        String siteName = parts[0];
                        String siteZone = parts[1];
                        String siteAddress = parts[2];
                        String sitePhoneNumber = parts[3];
                        Dictionary<String,String> s = addSiteDict(siteAddress,siteZone,siteName,sitePhoneNumber);
                        prController.addSite(s,"csv");
                        break;
                    case "truck":
                        if (parts.length < 4) break; // Ensure correct number of fields
                        String truckInitialWeight = parts[0];
                        String truckMaxWeight = parts[1];
                        String truckModel = parts[2];
                        String truckID = parts[3];
                        Dictionary<String,String> t = addTruckDict(truckID,truckInitialWeight,truckMaxWeight,truckModel);
                        prController.addTruck(t);
                        break;

                    case "order":
                        if (parts.length < 6) break; // Ensure correct number of fields
                        String orderDate = parts[0];
                        String orderSource = parts[1];
                        String orderDestination = parts[2];
                        String [] datePart =orderDate.split("/");
                        String year = datePart[2];
                        String month = datePart [1];
                        String day = datePart [0];
                        data1.put("year", year);
                        data1.put("month", month);
                        data1.put("day", day);
                        data1.put("destination",orderDestination);
                        data1.put("source",orderSource);
                        String itemName = parts[3];
                        String itemID = parts[4];
                        String itemAmount = parts[5];
                        data2.put(itemNum, addItemDict(itemID,itemName,itemAmount));
                        // data1 = addOrderDict(orderDate,orderSource,orderDestination) ;
                        prController.creatNewOrder(data1,data2);
                        break;


                }
                lineNumber++;
            }
              prController.printAllOrders();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    private static void printList(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
    public Dictionary<String, String> addDriverDict(String driverName, String driverLicence, String driverID)
    {
        Dictionary<String,String> driver = new Hashtable<String, String>();
        driver.put("name", driverName);
        driver.put("id", driverID);
        driver.put("typeOfLicense",driverLicence);
        return driver;
    }
    public Dictionary<String, String> addTruckDict(String idT, String initialWeight, String maxWeight, String model)
    {
        Dictionary<String,String> truck = new Hashtable<String, String>();
        truck.put("idT", idT);
        truck.put("initialWeight", initialWeight);
        truck.put("maxWeight",maxWeight);
        truck.put("model",model);

        return truck;
    }
    public Dictionary<String, String> addSiteDict(String address, String zone, String contactName,String phoneNumber)
    {
        Dictionary<String,String> site = new Hashtable<String, String>();
        site.put("address", address);
        site.put("zone", zone);
        site.put("contactName",contactName);
        site.put("phoneNumber",phoneNumber);
        return site;
    }
//    public Dictionary<String, String> addOrderDict(String date,String destination,String source )
//    {
//        Dictionary<String,String> data1 = new Hashtable<String, String>();
//        String [] datePart =date.split("/");
//        String year = datePart[2];
//        String month = datePart [1];
//        String day = datePart [0];
//        data1.put("year", year);
//        data1.put("month", month);
//        data1.put("day", day);
//        data1.put("destination",destination);
//        data1.put("source",source);
//        return data1;
//    }

    public ArrayList<String> addItemDict(String id, String name, String amount)
    {
        ArrayList<String> itemi = new ArrayList<>();

        itemi.add(id);
        itemi.add(name);
        itemi.add(amount);



        return itemi;
    }


}

/////////////////////////////
/////////////////////////////
/////////  2  ///////////////
/////////////////////////////
/////////////////////////////
//package Presentation;
//
//import Domain.Order;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.Dictionary;
//import java.util.Hashtable;
//
//public class readDataFile {
//    private PresentationController prController;
//
//    public readDataFile() {
//        prController = new PresentationController();
//    }
//
//    public void loadData() {
//        String csvFilePath = "/data.csv";
//
//        try (BufferedReader br = new BufferedReader(new InputStreamReader(Main.class.getResourceAsStream(csvFilePath)))) {
//            String line;
//            String currentSection = "";
//            int itemNum = 1;
//            Dictionary<String, String> data1 = null; // Holds order details
//            Dictionary<Integer, ArrayList<String>> data2 = new Hashtable<>(); // Holds item details for the current order
//
//            // Read each line from the CSV file
//            while ((line = br.readLine()) != null) {
//                line = line.trim();
//                if (line.isEmpty())
//                    continue; // Skip empty lines
//
//                // Adjust section detection to match the CSV file format
//                if (line.startsWith("driver,,,,,,,,,,") ||
//                        line.startsWith("site,,,,,,,,,,") ||
//                        line.startsWith("truck,,,,,,,,,,") ||
//                        line.startsWith("item,,,,,,,,,,") ||
//                        line.startsWith("order,,,,,,,,,,")) {
//                    currentSection = line.split(",")[0].toLowerCase(); // set current section based on the line
//                    continue;
//                }
//
//                // Split the line by comma (assuming CSV format)
//                String[] parts = line.split(",");
//
//                switch (currentSection) {
//                    case "driver":
//                        if (parts.length < 3) break; // Ensure correct number of fields
//                        String driverName = parts[0];
//                        String driverLicence = parts[1];
//                        String driverID = parts[2];
//
//                        Dictionary<String, String> d = addDriverDict(driverName, driverLicence, driverID);
//                        prController.addDriver(d);
//                        break;
//                    case "site":
//                        if (parts.length < 4) break; // Ensure correct number of fields
//                        String siteName = parts[0];
//                        String siteZone = parts[1];
//                        String siteAddress = parts[2];
//                        String sitePhoneNumber = parts[3];
//                        Dictionary<String, String> s = addSiteDict(siteAddress, siteZone, siteName, sitePhoneNumber);
//                        prController.addSite(s);
//                        break;
//                    case "truck":
//                        if (parts.length < 4) break; // Ensure correct number of fields
//                        String truckInitialWeight = parts[0];
//                        String truckMaxWeight = parts[1];
//                        String truckModel = parts[2];
//                        String truckID = parts[3];
//                        Dictionary<String, String> t = addTruckDict(truckID, truckInitialWeight, truckMaxWeight, truckModel);
//                        prController.addTruck(t);
//                        break;
//                    case "order":
//                        if (parts.length < 3) break; // Ensure correct number of fields
//                        String orderDate = parts[0];
//                        String orderSource = parts[1];
//                        String orderDestination = parts[2];
//
//                        Order order = prController.createNewOrder();
//                        int orderID = order.getId();
//                        data1 = addOrderDict(orderID, orderDate, orderSource, orderDestination);
//                        break;
//                    case "item":
//                        if (parts.length < 3) break; // Ensure correct number of fields
//                        String itemName = parts[0];
//                        String itemID = parts[1];
//                        String itemAmount = parts[2];
//
//                        if (data1 == null) {
//                            System.out.println("No order found for item: " + itemName);
//                            break;
//                        }
//
//                        ArrayList<String> itemi = new ArrayList<>();
//                        itemi.add(itemID);
//                        itemi.add(itemName);
//                        itemi.add(itemAmount);
//
//                        data2.put(itemNum++, itemi);
//
//                        prController.creatNewOrder(data1, data2);
//
//                        break;
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public Dictionary<String, String> addDriverDict(String driverName, String driverLicence, String driverID) {
//        Dictionary<String, String> driver = new Hashtable<>();
//        driver.put("name", driverName);
//        driver.put("id", driverID);
//        driver.put("typeOfLicense", driverLicence);
//        return driver;
//    }
//
//    public Dictionary<String, String> addTruckDict(String idT, String initialWeight, String maxWeight, String model) {
//        Dictionary<String, String> truck = new Hashtable<>();
//        truck.put("idT", idT);
//        truck.put("initialWeight", initialWeight);
//        truck.put("maxWeight", maxWeight);
//        truck.put("model", model);
//
//        return truck;
//    }
//
//    public Dictionary<String, String> addSiteDict(String address, String zone, String contactName, String phoneNumber) {
//        Dictionary<String, String> site = new Hashtable<>();
//        site.put("address", address);
//        site.put("zone", zone);
//        site.put("contactName", contactName);
//        site.put("phoneNumber", phoneNumber);
//        return site;
//    }
//
//    public Dictionary<String, String> addOrderDict(int orderID, String date, String destination, String source) {
//        Dictionary<String, String> data1 = new Hashtable<>();
//
//        String[] datePart = date.split("/");
//        String year = datePart[2];
//        String month = datePart[1];
//        String day = datePart[0];
//        data1.put("orderID", Integer.toString(orderID));
//        data1.put("year", year);
//        data1.put("month", month);
//        data1.put("day", day);
//        data1.put("destination", destination);
//        data1.put("source", source);
//        return data1;
//    }
//}
///////////////////////////////
///////////////////////////////
///////////  3  ///////////////
///////////////////////////////
///////////////////////////////
//package Presentation;
//
//import Domain.Order;
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.Dictionary;
//import java.util.Hashtable;
//
//public class readDataFile
//{
//    private PresentationController prController;
//
//    public readDataFile()
//    {
//        prController = new PresentationController();
//    }
//
//    public void loadData()
//    {
//        String csvFilePath = "/data.text";
//        BufferedReader reader = new BufferedReader(new FileReader(csvFilePath));
//        String line;
//        String currentSection = "";
//        int itemNum = 1;
//        Dictionary<String, String> data1 = null; // Holds order details
//        Dictionary<Integer, ArrayList<String>> data2 = new Hashtable<>(); // Holds item details for the current order
//        while ((line = reader.readLine()) != null)
//        {
//            line = line.trim();
//            if (line.isEmpty())
//                continue; // Skip empty lines
//
//            switch (line.toLowerCase())
//            {
//                case "driver":
//                case "site":
//                case "truck":
//                case "order":
//                case "item":
//                    section = line.toLowerCase();
//                    continue;
//            }
//            String[] parts = line.split(",");
//
//            switch (currentSection)
//            {
//                case "driver":
//                    if (parts.length < 3) break; // Ensure correct number of fields
//                    String driverName = parts[0];
//                    String driverLicence = parts[1];
//                    String driverID = parts[2];
//
//                    Dictionary<String, String> d = addDriverDict(driverName, driverLicence, driverID);
//                    prController.addDriver(d);
//                    break;
//                case "site":
//                    if (parts.length < 4) break; // Ensure correct number of fields
//                    String siteName = parts[0];
//                    String siteZone = parts[1];
//                    String siteAddress = parts[2];
//                    String sitePhoneNumber = parts[3];
//                    Dictionary<String, String> s = addSiteDict(siteAddress, siteZone, siteName, sitePhoneNumber);
//                    prController.addSite(s);
//                    break;
//                case "truck":
//                    if (parts.length < 4) break; // Ensure correct number of fields
//                    String truckInitialWeight = parts[0];
//                    String truckMaxWeight = parts[1];
//                    String truckModel = parts[2];
//                    String truckID = parts[3];
//                    Dictionary<String, String> t = addTruckDict(truckID, truckInitialWeight, truckMaxWeight, truckModel);
//                    prController.addTruck(t);
//                    break;
//                case "order":
//                    if (parts.length < 3) break; // Ensure correct number of fields
//                    String orderDate = parts[0];
//                    String orderSource = parts[1];
//                    String orderDestination = parts[2];
//
//                    Order order = prController.createNewOrder();
//                    int orderID = order.getId();
//                    data1 = addOrderDict(orderID, orderDate, orderSource, orderDestination);
//                    break;
//                case "item":
//                    if (parts.length < 3) break; // Ensure correct number of fields
//                    String itemName = parts[0];
//                    String itemID = parts[1];
//                    String itemAmount = parts[2];
//
//                    if (data1 == null)
//                    {
//                        System.out.println("No order found for item: " + itemName);
//                        break;
//                    }
//                    ArrayList<String> itemi = new ArrayList<>();
//                    itemi.add(itemID);
//                    itemi.add(itemName);
//                    itemi.add(itemAmount);
//
//                    data2.put(itemNum++, itemi);
//                    prController.creatNewOrder(data1, data2);
//                    break;
//            }
//        }
//        reader.close();
//
//    }
//
//    public Dictionary<String, String> addDriverDict(String driverName, String driverLicence, String driverID) {
//        Dictionary<String, String> driver = new Hashtable<>();
//        driver.put("name", driverName);
//        driver.put("id", driverID);
//        driver.put("typeOfLicense", driverLicence);
//        return driver;
//    }
//
//    public Dictionary<String, String> addTruckDict(String idT, String initialWeight, String maxWeight, String model) {
//        Dictionary<String, String> truck = new Hashtable<>();
//        truck.put("idT", idT);
//        truck.put("initialWeight", initialWeight);
//        truck.put("maxWeight", maxWeight);
//        truck.put("model", model);
//
//        return truck;
//    }
//
//    public Dictionary<String, String> addSiteDict(String address, String zone, String contactName, String phoneNumber) {
//        Dictionary<String, String> site = new Hashtable<>();
//        site.put("address", address);
//        site.put("zone", zone);
//        site.put("contactName", contactName);
//        site.put("phoneNumber", phoneNumber);
//        return site;
//    }
//
//    public Dictionary<String, String> addOrderDict(int orderID, String date, String destination, String source) {
//        Dictionary<String, String> data1 = new Hashtable<>();
//
//        String[] datePart = date.split("/");
//        String year = datePart[2];
//        String month = datePart[1];
//        String day = datePart[0];
//        data1.put("orderID", Integer.toString(orderID));
//        data1.put("year", year);
//        data1.put("month", month);
//        data1.put("day", day);
//        data1.put("destination", destination);
//        data1.put("source", source);
//        return data1;
//    }
//}
///////////////////////////////
///////////////////////////////
///////////  4  ///////////////
///////////////////////////////
///////////////////////////////
//package Presentation;
//
//import Domain.Order;
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Dictionary;
//import java.util.Hashtable;
//
//public class readDataFile {
//    private PresentationController prController;
//
//    public readDataFile() {
//        prController = new PresentationController();
//    }
//
//    public void loadData() {
//        String csvFilePath = "C:\\Users\\USER\\Desktop\\Ben Gurion\\second year\\semester D\\analysis and planning\\PROJECT\\dev\\src\\data.txt";
//        BufferedReader reader = null;
//        try {
//            reader = new BufferedReader(new FileReader(csvFilePath));
//            String line;
//            String currentSection = "";
//            int itemNum = 1;
//            Dictionary<String, String> data1 = null; // Holds order details
//            Dictionary<Integer, ArrayList<String>> data2 = new Hashtable<>(); // Holds item details for the current order
//
//            while ((line = reader.readLine()) != null) {
//                line = line.trim();
//                if (line.isEmpty()) {
//                    continue; // Skip empty lines
//                }
//
//                switch (line.toLowerCase()) {
//                    case "driver":
//                    case "site":
//                    case "truck":
//                    case "order":
//                    case "item":
//                        currentSection = line.toLowerCase();
//                        continue;
//                }
//
//                String[] parts = line.split(",");
//
//                switch (currentSection) {
//                    case "driver":
//                        if (parts.length < 3) break; // Ensure correct number of fields
//                        String driverName = parts[0].trim();
//                        String driverLicence = parts[1].trim();
//                        String driverID = parts[2].trim();
//                        Dictionary<String, String> d = addDriverDict(driverName, driverLicence, driverID);
//                        prController.addDriver(d);
//                        break;
//                    case "site":
//                        if (parts.length < 4) break; // Ensure correct number of fields
//                        String siteName = parts[0].trim();
//                        String siteZone = parts[1].trim();
//                        String siteAddress = parts[2].trim();
//                        String sitePhoneNumber = parts[3].trim();
//                        Dictionary<String, String> s = addSiteDict(siteAddress, siteZone, siteName, sitePhoneNumber);
//                        prController.addSite(s);
//                        break;
//                    case "truck":
//                        if (parts.length < 4) break; // Ensure correct number of fields
//                        String truckInitialWeight = parts[0].trim();
//                        String truckMaxWeight = parts[1].trim();
//                        String truckModel = parts[2].trim();
//                        String truckID = parts[3].trim();
//                        Dictionary<String, String> t = addTruckDict(truckID, truckInitialWeight, truckMaxWeight, truckModel);
//                        prController.addTruck(t);
//                        break;
//                    case "order":
//                        if (parts.length < 3) break; // Ensure correct number of fields
//                        String orderDate = parts[0].trim();
//                        String orderSource = parts[1].trim();
//                        String orderDestination = parts[2].trim();
//
//                        Order order = prController.createNewOrder();
//                        int orderID = order.getId();
//                        data1 = addOrderDict(orderID, orderDate, orderSource, orderDestination);
//                        break;
//                    case "item":
//                        if (parts.length < 3) break; // Ensure correct number of fields
//                        String itemName = parts[0].trim();
//                        String itemID = parts[1].trim();
//                        String itemAmount = parts[2].trim();
//
//                        if (data1 == null) {
//                            System.out.println("No order found for item: " + itemName);
//                            break;
//                        }
//                        ArrayList<String> itemi = new ArrayList<>();
//                        itemi.add(itemID);
//                        itemi.add(itemName);
//                        itemi.add(itemAmount);
//
//                        data2.put(itemNum++, itemi);
//                        prController.creatNewOrder(data1,data2);
//                        break;
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (reader != null) {
//                try {
//                    reader.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    public Dictionary<String, String> addDriverDict(String driverName, String driverLicence, String driverID) {
//        Dictionary<String, String> driver = new Hashtable<>();
//        driver.put("name", driverName);
//        driver.put("id", driverID);
//        driver.put("typeOfLicense", driverLicence);
//        return driver;
//    }
//
//    public Dictionary<String, String> addTruckDict(String idT, String initialWeight, String maxWeight, String model) {
//        Dictionary<String, String> truck = new Hashtable<>();
//        truck.put("idT", idT);
//        truck.put("initialWeight", initialWeight);
//        truck.put("maxWeight", maxWeight);
//        truck.put("model", model);
//
//        return truck;
//    }
//
//    public Dictionary<String, String> addSiteDict(String address, String zone, String contactName, String phoneNumber) {
//        Dictionary<String, String> site = new Hashtable<>();
//        site.put("address", address);
//        site.put("zone", zone);
//        site.put("contactName", contactName);
//        site.put("phoneNumber", phoneNumber);
//        return site;
//    }
//
//    public Dictionary<String, String> addOrderDict(int orderID, String date, String destination, String source) {
//        Dictionary<String, String> data1 = new Hashtable<>();
//        String[] datePart = date.split("/");
//        String year = datePart[2].trim();
//        String month = datePart[1].trim();
//        String day = datePart[0].trim();
//        data1.put("orderID", Integer.toString(orderID));
//        data1.put("year", year);
//        data1.put("month", month);
//        data1.put("day", day);
//        data1.put("destination", destination);
//        data1.put("source", source);
//        return data1;
//    }
//}
