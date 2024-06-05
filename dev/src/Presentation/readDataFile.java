package Presentation;

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

    public readDataFile()
    {
        prController = new PresentationController();
    }

    public void loadData()
    {
        String csvFilePath = "/data.csv";
        Dictionary<String, Dictionary<String, String>> items = new Hashtable<String, Dictionary<String, String>>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(Main.class.getResourceAsStream(csvFilePath)))) {
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

                        Dictionary<String,String> d = addDriverDict(driverName,driverID, driverLicence);
                        prController.addDriver(d);
                        break;
                    case "site":
                        if (parts.length < 4) break; // Ensure correct number of fields
                        String siteName = parts[0];
                        String siteZone = parts[1];
                        String siteAddress = parts[2];
                        String sitePhoneNumber = parts[3];
                        Dictionary<String,String> s = addSiteDict(siteAddress,siteZone,siteName,sitePhoneNumber);
                        prController.addSite(s);
                        break;
                    case "truck":
                        if (parts.length < 4) break; // Ensure correct number of fields
                        String truckInitialWeight = parts[0];
                        String truckMaxWeight = parts[1];
                        String truckModel = parts[2];
                        String truckID = parts[3];
                        Dictionary<String,String> t =addTruckDict(truckID,truckInitialWeight,truckMaxWeight,truckModel) ;
                        prController.addTruck(t);
                        break;
                    case "item":
                        if (parts.length < 3) break; // Ensure correct number of fields
                        String itemName = parts[0];
                        String itemID = parts[1];
                        String itemAmount = parts[2];
                        Dictionary<String,String> item =addItemDict(itemID,itemName,itemAmount) ;
                        String lineNumberStr = String.valueOf(lineNumber);
                        items.put(lineNumberStr,item);
                        break;
                    case "order":
                        if (parts.length < 4) break; // Ensure correct number of fields
                        String orderDate = parts[0];
                        String orderTime = parts[1];
                        String orderSource = parts[2];
                        String orderDestination = parts[3];
                        Dictionary<String,String> o = addOrderDict(orderDate,orderTime,orderSource,orderDestination) ;
                        prController.creatNewOrder(o,items);
                        break;
                }
                lineNumber++;
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

//        System.out.println("Sites:");
//        printList(sites);
//        System.out.println("\nDrivers:");
//        printList(drivers);
//        System.out.println("\nTrucks:");
//        printList(trucks);
//        System.out.println("\nOrders:");
//        printList(orders);
//        System.out.println("\nItems:");
//        printList(items);
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
    public Dictionary<String, String> addOrderDict(String date, String time,String destination,String source )
    {
        String [] datePart =date.split("/");
        String year = datePart[2];
        String month = datePart [1];
        String day = datePart [0];
        String [] timePart = time.split(":");
        String hour = timePart[0];
        String minute = timePart [1];

        Dictionary<String,String> order = new Hashtable<String, String>();
        order.put("hour", hour);
        order.put("minute", minute);
        order.put("year", year);
        order.put("month", month);
        order.put("day", day);
        order.put("destination",destination);
        order.put("source",source);

        return order;
    }
    public Dictionary<String, String> addItemDict(String id, String name, String amount)
    {
        Dictionary<String,String> item = new Hashtable<String, String>();
        item.put("id", id);
        item.put("name", name);
        item.put("amount",amount);
        return item;
    }


}
