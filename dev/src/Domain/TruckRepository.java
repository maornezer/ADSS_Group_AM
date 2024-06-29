package Domain;


import DAL.TruckDAO;
import DAL.TruckDTO;
import java.util.ArrayList;
import java.util.List;

public class TruckRepository
{
    private ArrayList<Truck> trucks;
    private TruckDAO truckDAO;

    public TruckRepository()
    {
        trucks = new ArrayList<>();
        truckDAO = new TruckDAO();
    }

    public boolean insert(Truck truck) {
        truckDAO.insert(truck);
        trucks.add(truck);
        return true;
    }
    public boolean remove(int id) {
        for (Truck truck : trucks) {
            if (truck.getIdTruck() == id) {
                trucks.remove(truck);
            }
        }
        truckDAO.remove(id);
        return true;
    }
    public boolean search(int id) {
        for (Truck truck : trucks){
            if (truck.getIdTruck() == id){
                return true;
            }
        }
        TruckDTO temp = (TruckDTO) truckDAO.get(id);
        return temp != null;
    }


    public Truck get(int id) {
        for (Truck truck : trucks) {
            if (truck.getIdTruck() == id) {
                return truck;
            }
        }
        Truck truck = new Truck((TruckDTO) truckDAO.get(id));
        trucks.add(truck);
        return truck;
    }

    public int countRecords() {return truckDAO.countRecords();}




    public List<Truck> getTrucks() {return trucks;}//for tostring


}
