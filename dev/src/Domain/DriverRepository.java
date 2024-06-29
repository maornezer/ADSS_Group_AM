package Domain;
import DAL.DriverDAO;
import DAL.DriverDTO;
import java.util.ArrayList;
import java.util.List;

public class DriverRepository {
    private ArrayList<Driver> drivers;
    private DriverDAO driverDAO;


    public DriverRepository()
    {
        drivers = new ArrayList<>();
        driverDAO = new DriverDAO();
    }

    public boolean insert(Driver d) {
        driverDAO.insert(d);
        drivers.add(d);
        return true;
    }

    public boolean remove(int id) {
        for (Driver driver : drivers) {
            if (driver.getId() == id) {
                drivers.remove(driver);
            }
        }
        drivers.remove(id);
        return true;
    }
    public boolean search (int id) {
        for (Driver driver: drivers){
            if (driver.getId() == id){
                return true;
            }
        }
        DriverDTO temp = (DriverDTO) driverDAO.get(id);
        return temp != null;
    }

    public Driver get(int id) {
        for (Driver driver : drivers) {
            if (driver.getId() == id) {
                return driver;
            }
        }
        Driver driver = new Driver((DriverDTO) driverDAO.get(id));
        drivers.add(driver);
        return driver;
    }

    public int countRecords() {return driverDAO.countRecords();}




    public boolean checkIfDriverExistsByLicence(String type)
    {
        if (drivers != null){
            for (Driver d : drivers){
                if (d.getTypeOfLicense().compareTo(type)==0){
                    return true;
                }
            }
        }
        return driverDAO.checkIfDriverExistsByLicence(type);
    }


    public List<Driver> getDrivers() {
        return drivers;
    }

}
