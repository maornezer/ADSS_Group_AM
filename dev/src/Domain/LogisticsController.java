package Domain;
import java.util.Dictionary;


public class LogisticsController {
    private TruckRepository truckRepo;
    private DriverRepository driverRepo;

    public LogisticsController()
    {
        truckRepo = new TruckRepository();
        driverRepo = new DriverRepository();
    }

    public TruckRepository getTruckRepo() {
        return truckRepo;
    }

    public DriverRepository getDriverRepo() {
        return driverRepo;
    }

    //**********************TRUCK:**********************//
    public boolean addTruck(int idT,double initialWeight, double maxWeight, String model) {
        Truck t = new Truck( idT,  initialWeight,  maxWeight,  model);
        return truckRepo.addTruck(t);
    }
    public int getSizeTrucks() {
        return truckRepo.getSizeTrucks();
    }

    public boolean addTruck(Dictionary<String, String> data) {
        int idT = Integer.parseInt(data.get("idT"));
        double initialWeight = Double.parseDouble(data.get("initialWeight"));
        double maxWeight = Double.parseDouble(data.get("maxWeight"));
        String model = data.get("model");
        return addTruck(idT,initialWeight,maxWeight,model);
    }
    public boolean isTruckExists(int idTruck)
    {
        return truckRepo.isTruckExists(idTruck);
    }

    public Truck getTruckByID(int idTruck) {
        return truckRepo.getTruckByID(idTruck);
    }

    //**********************DRIVER:**********************//

    public boolean addDriver(String name, int id, String typeOfLicense) {
        Driver d = new Driver(name, id, typeOfLicense);
        return driverRepo.addDriver(d);
    }

    public boolean addDriver(Dictionary<String, String> data) {
        String name = data.get("name");
        int id = Integer.parseInt(data.get("id"));
        String typeOfLicense = data.get("typeOfLicense");
        return addDriver(name,id,typeOfLicense);
    }
    public boolean isDriverExists(String type)
    {
        return driverRepo.isDriverExists(type);
    }

    public boolean isIdDriverExists(String id)
    {
        int idDriver = Integer.parseInt(id);
        return isTruckExists(idDriver);
    }

    public Driver getDriverByID(int idDriver) {
        return getDriverByID(idDriver);
    }



    public int getSizeDrivers() {
        return driverRepo.getSizeDrivers();
    }


///truck list:
        public String toStringTrucks() {
        StringBuilder allTrucksInfo = new StringBuilder();
        for (Truck truck : truckRepo.getTrucks()) {
            allTrucksInfo.append(truck.toString()).append("\n");
        }
        return allTrucksInfo.toString();
    }
    public String printTruckByID(int id) {
        Truck truck = getTruckByID(id);
        if (truck != null) {
            return truck.toString();
        }

        return "Truck with ID " + id + " not found.";
    }
//    public Truck getTruckByID(int id) {
//        for (Truck truck : truckRepo.getTrucks()) {
//            if (truck.getIdTruck() == id) {
//                return truck;
//            }
//        }
//        return null;
//    }
//    public int getAmountOfTrucks(){return trucks.size();}

//    public List<Truck> getTrucks() {return trucks;}

/////////////////////////////////////////////////////////////

///driver list:
public String toStringDrivers() {
    StringBuilder sb = new StringBuilder();
    for (Driver driver : driverRepo.getDrivers()) {
        sb.append(driver.toString()).append("\n");
    }
    return sb.toString();
}
    public String printDriversByLicenseType(String licenseType) {
        StringBuilder sb = new StringBuilder();
        for (Driver driver : driverRepo.getDrivers()) {
            if (driver.getTypeOfLicense().equalsIgnoreCase(licenseType)) {
                sb.append(driver.toString()).append("\n");
            }
        }
        if (sb.length() == 0)
        {
            System.out.println("There are no drivers with a license type in the system " + licenseType);
        }
        return sb.toString();
    }
}
