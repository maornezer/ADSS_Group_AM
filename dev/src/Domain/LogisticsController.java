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

    //***************************************** TRUCK **************************************************************//


    public boolean addTruck(Dictionary<String, String> data) {
        int idT = Integer.parseInt(data.get("idT"));
        if (searchTruck(idT)) {
            return false;
        }
        double initialWeight = Double.parseDouble(data.get("initialWeight"));
        double maxWeight = Double.parseDouble(data.get("maxWeight"));
        String model = data.get("model");
        Truck t = new Truck(idT,initialWeight,maxWeight,model);
        return truckRepo.insert(t);
    }
    public boolean remove(Dictionary<String, String> data)
    {
        int idT = Integer.parseInt(data.get("id"));
        return remove(idT);
    }
    public boolean remove(int id) {
        if (!searchTruck(id))
            return false;
        return truckRepo.remove(id);
    }

    public boolean searchTruck(int idTruck)
    {
        return truckRepo.search(idTruck);
    }

    public Truck getTruck(Dictionary<String, String> data)
    {
        int idT = Integer.parseInt(data.get("id"));
        return getTruck(idT);
    }
    public Truck getTruck(int idTruck) {
        if (!searchTruck(idTruck))
            return null;
        return truckRepo.get(idTruck);}

    public int getSizeTrucks() {return truckRepo.countRecords();}

    //***************************************** DRIVER **************************************************************//

    public boolean addDriver(Dictionary<String, String> data) {
        int id = Integer.parseInt(data.get("id"));
        if (searchDriver(id)) {
            return false;
        }
        String name = data.get("name");
        String typeOfLicense = data.get("typeOfLicense");
        Driver d = new Driver(name, id, typeOfLicense);
        return driverRepo.insert(d);
    }

    public boolean removeDriver(Dictionary<String, String> data)
    {
        int id = Integer.parseInt(data.get("id"));
        return removeDriver(id);
    }
    public boolean removeDriver(int id) {
        if (!searchDriver(id))
            return false;
        return driverRepo.remove(id);}

    public boolean searchDriver(int id)
    {
        return driverRepo.search(id);
    }

    public Driver getDriver(Dictionary<String, String> data)
    {
        int id = Integer.parseInt(data.get("id"));
        return getDriver(id);
    }
        public Driver getDriver(int id) {
        if (!driverRepo.search(id))
            return null;
        return driverRepo.get(id);
    }
    public int getSizeDrivers() {
        return driverRepo.countRecords();
    }


    public boolean checkMatchLicense(String idT, String idD) {
        Driver d = getDriver(Integer.parseInt(idD));
        Truck t = getTruck(Integer.parseInt(idT));
        if (d.getTypeOfLicense().compareTo(t.getTypeOfLicense()) == 0)
        {
            return true;
        }
        return false;
    }
}
