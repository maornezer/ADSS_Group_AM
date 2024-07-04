package Domain;

import Data.DB;

import java.time.LocalDate;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class TransportationRepository {

    protected Dictionary<LocalDate, List<String[]>> TransportationRepo;

    public TransportationRepository(){
        TransportationRepo = new Hashtable<>();
    }

    public Dictionary<LocalDate, List<String[]>> getTransports(){
        Dictionary<LocalDate, List<String[]>> res = new Hashtable<>();
        for(int i = 0; i<7; i++){
            List<String[]> transports = TransportationRepo.get(Chain.getNextWeekDates()[i]);
            if(transports == null){
                transports = DB.getTransportationDAO().read(Chain.getNextWeekDates()[i]);
                if(transports != null) {
                    TransportationRepo.put(Chain.getNextWeekDates()[0], transports);
                }
            }
            res.put(Chain.getNextWeekDates()[i], transports);
        }
        return res;
    }
}
