package Domain;


import DAL.TruckDAO;
import DAL.TruckDTO;

import java.util.ArrayList;
import java.util.List;

public class TruckRepository
{
    //private List<Truck> trucks;
    private TruckDAO truckDAO;

    public TruckRepository()
    {
        //trucks =  new ArrayList<>();
        truckDAO = new TruckDAO();
//        this.insert();
    }

    public void insert()
    {
        truckDAO.insert(new TruckDTO(200,200,"sss",200));
    }


}
