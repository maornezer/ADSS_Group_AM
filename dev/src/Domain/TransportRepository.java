package Domain;

import DAL.TransportDAO;
import DAL.TransportDTO;
import com.sun.net.httpserver.Filter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class TransportRepository implements IRepository
{
    private ArrayList<Transport> transports;
    protected Dictionary<LocalDate, List<String[]>> transportDetails;
    public TransportDAO transportDAO;


    public TransportRepository()
    {
        transportDetails = new Hashtable<>();
        transports = new ArrayList<>();
        transportDAO = new TransportDAO();
    }

    public boolean insert(Object o)
    {
        Transport transport = (Transport)o;
        TransportDTO transportDTO = new TransportDTO(transport.getId(), transport.getTruck().getIdTruck(),transport.getDriver().getId());
        transports.add(transport);
        transportDAO.insert(transportDTO);
        return true;
    }

    public boolean remove(int id) {
        for (Transport t : transports) {
            if (t.getId() == id) {
                transports.remove(t);
                break;
            }
        }
        transportDAO.remove(id);
        return true;
    }

    public boolean search(int id) {
        for (Transport t : transports) {
            if (t.getId() == id){
                return true;
            }
        }
        TransportDTO temp = (TransportDTO) transportDAO.get(id);
        return temp != null;
    }


    public Transport get(int id){
        for (Transport transport : transports){
            if (transport.getId() == id){
                return transport;
            }
        }
        return null;
    }

    public TransportDTO helpGetFunc(int id) {return (TransportDTO) transportDAO.get(id);}


    public ArrayList<Transport> getTransports() {return transports;}


    public List<Integer> getTransportIdsByTruck(int idTruck){return transportDAO.getTransportIdsByTruck(idTruck);}

    public List<Integer> getTransportIdsByDriver(int idDriver){return transportDAO.getTransportIdsByDriver(idDriver);}

    public int getMaxId() {return transportDAO.getMaxTransportId();}

    public void updateTruck(int idTruck, int idTransport) {transportDAO.updateTruck(idTruck, idTransport);}

    public void updateDriver(int newDriverID, int transportID) {transportDAO.updateDriver(newDriverID,transportID);}

    public void updateComplete(int transportID) {transportDAO.updateComplete(transportID);}

    public boolean getStatus(int idT) {return transportDAO.getStatus(idT);}

    public String getTransportDetails(LocalDate date) {
        List<String[]> transportDetails = transportDAO.getTransportDetails(date);
        StringBuilder detailsStringBuilder = new StringBuilder();

        for (String[] detail : transportDetails) {
            for (String field : detail) {
                detailsStringBuilder.append(field).append(" ");
            }
            detailsStringBuilder.append("\n");
        }

        return detailsStringBuilder.toString();
    }

    //for noa
    public Dictionary<LocalDate, List<String[]>> getTransportDetails()
    {
        Dictionary<LocalDate, List<String[]>> res = new Hashtable<>();
        for(int i = 0; i < 7; i++)
        {
            List<String[]> transports = transportDetails.get(Chain.getNextWeekDates()[i]);
            if (transports == null)
            {
                transports = transportDAO.getTransportDetails(Chain.getNextWeekDates()[i]);
                if (transports != null)
                {
                    transportDetails.put(Chain.getNextWeekDates()[i], transports);
                }
            }
            res.put(Chain.getNextWeekDates()[i],transports);
        }
        return res;
    }
}
