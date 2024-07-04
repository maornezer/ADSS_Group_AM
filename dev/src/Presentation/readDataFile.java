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

                String[] parts = line.split(",");

                switch (currentSection) {
                    case "driver":
                        if (parts.length < 3) break;
                        String driverName = parts[0];
                        String driverLicence = parts[1];
                        String driverID = parts[2];

                        Dictionary<String,String> d = addDriverDict(driverName,driverLicence,driverID);
                        prController.addDriver(d);
                        break;
                    case "site":
                        if (parts.length < 4) break;
                        String siteName = parts[0];
                        String siteZone = parts[1];
                        String siteAddress = parts[2];
                        String sitePhoneNumber = parts[3];
                        Dictionary<String,String> s = addSiteDict(siteAddress,siteZone,siteName,sitePhoneNumber);
                        //prController.addSite(s,"csv");
                        break;
                    case "truck":
                        if (parts.length < 4) break;
                        String truckInitialWeight = parts[0];
                        String truckMaxWeight = parts[1];
                        String truckModel = parts[2];
                        String truckID = parts[3];
                        Dictionary<String,String> t = addTruckDict(truckID,truckInitialWeight,truckMaxWeight,truckModel);
                        prController.addTruck(t);
                        break;

                    case "order":
                        if (parts.length < 6) break;
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
    public ArrayList<String> addItemDict(String id, String name, String amount)
    {
        ArrayList<String> itemi = new ArrayList<>();
        itemi.add(id);
        itemi.add(name);
        itemi.add(amount);
        return itemi;
    }

}
////////////////////////
//noa
//public static void readConfigurtionFile(String filename) {
//    String fileName1;
//    try {
//
//        if (filename == null) {
//
//            String currentDirectory = System.getProperty("user.dir");
//            File currentDirFile = new File(currentDirectory);
//            String parentDirectory = currentDirFile.getParent();
//            fileName1 = Paths.get(parentDirectory, "dev", "configoration.txt").toString();
//        }
//        else {
//            fileName1 = filename;
//        }