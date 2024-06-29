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

    public boolean addTruck(Truck truck) {
        if (isTruckExists(truck.getIdTruck()))
        {
            return false;
        }
        truckDAO.insert(truck);
        trucks.add(truck);
        return true;
    }

    public boolean isTruckExists(int id) {
        for (Truck truck : trucks){
            if (truck.getIdTruck() == id){
                return true;
            }
        }
        TruckDTO temp = (TruckDTO) truckDAO.get(id);
        return temp != null;
    }


    public Truck getTruckByID(int id) {
        if (!isTruckExists(id))
            return null;
        for (Truck truck : trucks) {
            if (truck.getIdTruck() == id) {
                return truck;
            }
        }
        Truck truck = new Truck((TruckDTO) truckDAO.get(id));
        trucks.add(truck);
        return truck;
    }

    public boolean remove(int id) {
        if (!isTruckExists(id))
            return false;
        for (Truck truck : trucks) {
            if (truck.getIdTruck() == id) {
                trucks.remove(truck);
            }
        }
        trucks.remove(id);
        return true;
    }



    public List<Truck> getTrucks() {return trucks;}
    public int getSizeTrucks() {return truckDAO.countRecords();}


}
