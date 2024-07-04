package Domain;


import DAL.TruckDAO;
import DAL.TruckDTO;
import java.util.ArrayList;
import java.util.List;

public class TruckRepository implements IRepository
{
    private ArrayList<Truck> trucks;
    private TruckDAO truckDAO;

    public TruckRepository()
    {
        trucks = new ArrayList<>();
        truckDAO = new TruckDAO();
    }

    public boolean insert(Object o) {
        Truck truck = (Truck)o;
        TruckDTO truckDTO = new TruckDTO(truck.getInitialWeight(),truck.getMaxWeight(),truck.getTruckModel(),truck.getIdTruck());
        truckDAO.insert(truckDTO);
        trucks.add(truck);
        return true;
    }
    public boolean remove(int id) {
        for (Truck truck : trucks) {
            if (truck.getIdTruck() == id) {
                trucks.remove(truck);
                break;
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
