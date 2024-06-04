package Domain;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;


public class TruckController
{
    private List <Truck> trucks;
    private static TruckController instance;

    public TruckController()
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

    public boolean addTruck(int idT,double initialWeight, double maxWeight, String model)
    {
        Truck t = new Truck( idT,  initialWeight,  maxWeight,  model);
        return addTruck(t);
    }

    public boolean addTruck(Dictionary<String, String> data)
    {
        int idT = Integer.parseInt(data.get("idT"));
        double initialWeight = Double.parseDouble(data.get("initialWeight"));
        double maxWeight = Double.parseDouble(data.get("maxWeight"));
        String model = data.get("model");
        Truck t = new Truck( idT,  initialWeight,  maxWeight,  model);
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


//    public Truck getTruckByLicenceType(TypeOfLicense type )
//    {
//        for (Truck truck : trucks)
//        {
//            if (truck.getTypeOfLicense().equals(type)) {
//                return truck;
//            }
//        }
//        return null;
//    }

    public Truck getTruckByID(int id)
    {
        for (Truck truck : trucks) {
            if (truck.getIdTruck() == id) {
                return truck;
            }
        }
        return null;
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

