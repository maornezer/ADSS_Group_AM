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

    public readDataFile()
    {
        prController = new PresentationController();
    }

    public void loadData()
    {
        String csvFilePath = "/data.csv";
        Dictionary<String,String> data1 = new Hashtable<String, String>();

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
                        prController.addSite(s);
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
                        if (parts.length < 3) break; // Ensure correct number of fields
                        String orderDate = parts[0];
                        String orderSource = parts[1];
                        String orderDestination = parts[2];

                        Order order = prController.createNewOrder();
                        int orderID = order.getId();
                        data1 = addOrderDict(orderID,orderDate,orderSource,orderDestination) ;
                        break;
                    case "item":
                        if (parts.length < 3) break; // Ensure correct number of fields
                        String itemName = parts[0];
                        String itemID = parts[1];
                        String itemAmount = parts[2];

                        Dictionary<Integer, ArrayList<String>> data2 = addItemDict(itemNum,itemID,itemName,itemAmount) ;

                        prController.creatNewOrder(data1,data2);

                        break;

                }
                lineNumber++;
            }
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
    public Dictionary<String, String> addOrderDict(int orderID,String date,String destination,String source )
    {
        Dictionary<String,String> data1 = new Hashtable<String, String>();

        String [] datePart =date.split("/");
        String year = datePart[2];
        String month = datePart [1];
        String day = datePart [0];
        data1.put("orderID",Integer.toString(orderID));
        data1.put("year", year);
        data1.put("month", month);
        data1.put("day", day);
        data1.put("destination",destination);
        data1.put("source",source);
        return data1;
    }

    public Dictionary<Integer, ArrayList<String>> addItemDict(int itemNum,String id, String name, String amount)
    {
        Dictionary<Integer, ArrayList<String>> data2 = new Hashtable<Integer, ArrayList<String>>();
        ArrayList<String> itemi = new ArrayList<>();

        itemi.add(id);
        itemi.add(name);
        itemi.add(amount);

        data2.put(itemNum,itemi);

        return data2;
    }


}
