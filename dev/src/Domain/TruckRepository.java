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
//        this.insert();
    }
    public boolean isTruckExists(int id) {
        for (Truck truck : trucks)
        {
            if (truck.getIdTruck() == id)
            {
                return true;
            }
        }
        return false;
    }
    public boolean isTruckExists(Truck truck) {
        return trucks.contains(truck);
    }

    public boolean addTruck(Truck t) {
        if (isTruckExists(t))
        {
            return false;
        }
        return trucks.add(t);
    }
    public Truck getTruckByID(int id) {
        for (Truck truck : trucks) {
            if (truck.getIdTruck() == id) {
                return truck;
            }
        }
        return null;
    }
    public List<Truck> getTrucks() {return trucks;}

    public int getAmountOfTrucks(){return trucks.size();}

    public void insert()
    {
        truckDAO.insert(new TruckDTO(200,200,"sss",200));
    }


    public int getSizeTrucks() {
        return trucks.size();
    }
}
