package Domain;

import java.util.ArrayList;
import java.util.List;


public class TruckController
{
    private List <Truck> trucks;
    private static TruckController instance;

    private TruckController()
    {
        trucks = new ArrayList<>();
        instance=this;
    }
    public static TruckController getInstance()
    {
        if(instance == null)
            instance = new TruckController();
        return instance;
    }

    public boolean addTruck(int idTruck,double initialWeight, double maxWeight, String model)
    {
        Truck t = new Truck( idTruck,  initialWeight,  maxWeight,  model);
        return addTruck(t);
    }
    public boolean addTruck(Truck t)
    {
        if (isTruckExists(t))
        {
            return false;
        }
        return trucks.add(t);
    }

    public boolean isTruckExists(Truck truck)
    {
        return trucks.contains(truck);
    }

    public boolean removeTruckByIDTruck(int id)
    {
        for (int i = 0; i<trucks.size(); i++)
        {
            if(trucks.get(i).getIdTruck() == id)
            {
                trucks.remove(i);
                return true;
            }
        }
        return false;//throw new IllegalArgumentException("Truck with this id " + id + " not found");
    }
    public void editTruckModelByIDTruck(int id, String model)
    {
        getTruckByID(id).setTruckModel(model);
        //return false;//throw new IllegalArgumentException("Truck with this id " + id + " not found");
    }

    public void editTruckMaxWeightByIDTruck(int id, double w)
    {
        getTruckByID(id).setMaxWeight(w);
        //return false;//throw new IllegalArgumentException("Truck with this id " + id + " not found");
    }

    public void editTruckInitialWeightByIDTruck(int id, double w)
    {
        getTruckByID(id).setInitialWeight(w);
        //return false;//throw new IllegalArgumentException("Truck with this id " + id + " not found");
    }
    public void editTruckIDtByIDTruck(int oldID, int newID)
    {
        getTruckByID(oldID).setIdTruck(newID);
        //return false;//throw new IllegalArgumentException("Truck with this id " + id + " not found");
    }

    public Truck getTruckByLicenceType(TypeOfLicense type )
    {
        for (Truck truck : trucks)
        {
            if (truck.getTypeOfLicense().equals(type)) {
                return truck;
            }
        }
        return null;//throw new IllegalArgumentException("Truck with license type " + type + " not found");
    }

    public Truck getTruckByID(int id )
    {
        for (Truck truck : trucks) {
            if (truck.getIdTruck() == id) {
                return truck;
            }
        }
        return null;//throw new IllegalArgumentException("Truck with this id " + id + " not found");
    }
    private List<Truck> getAllTrucksByLicence(TypeOfLicense licenceType)
    {
        List<Truck> trucksByLicence = new ArrayList<>();
        for (Truck truck : trucks) {
            if (truck.getTypeOfLicense().equals(licenceType)) {
                trucksByLicence.add(truck);
            }
        }
        return trucksByLicence;
    }


    public int getAmountOfTrucks(){return trucks.size();}

    public String toString()
    {
        StringBuilder allTrucksInfo = new StringBuilder();
        for (Truck truck : trucks) {
            allTrucksInfo.append(truck.toString()).append("\n");
        }
        return allTrucksInfo.toString();
    }
    public String printTruckByID(int id) {
        Truck truck = getTruckByID(id);
        if (truck != null)
        {
            return truck.toString();
        }

        return "Truck with ID " + id + " not found.";
    }

}

