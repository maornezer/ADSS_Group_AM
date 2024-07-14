package Domain;

import DAL.OrderDTO;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Order
{
    private int id;
    private ArrayList<Item> items;
    private Branch source;
    private Branch destination;
    private int sourceID;
    private int destinationID;
    private LocalDate date;
    private double orderWeight;
    private int transportAssociation;


    public Order(int id, LocalDate date, int destID,Branch destination, int sourceID,Branch source, ArrayList<Item> itemsList)
    {
        setId(id);
        setDate(date);
        setSource(source);
        setDestination(destination);
        setOrderWeight(0.0);
        items = new ArrayList<>();
        for (Item item : itemsList)
        {
            addItem(item.getId(), item.getName(), item.getAmount());
        }
        transportAssociation = -1;
        setDestinationID(destID);
        setSourceID(sourceID);

    }

    public Order(OrderDTO orderDTO) {
        setId(orderDTO.id);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date_ = LocalDate.parse(orderDTO.date, formatter);
        setDate(date_);
        setOrderWeight(0.0);
        setSource(null);//set in operation controller
        setDestination(null);//set in operation controller
        setSourceID(orderDTO.sourceID);
        setDestinationID(orderDTO.destinationID);
        transportAssociation = orderDTO.transportId;
        this.items = new ArrayList<>();//set in operation controller
    }

    public void setId(int id) {this.id = id;}

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setDestination(Branch destination) {this.destination = destination;}

    public void setDestinationID(int id){this.destinationID = id;}

    public void setSourceID(int id){this.sourceID = id;}

    public boolean isHaveTransport() {return transportAssociation != -1;}

    public int setTransportAssociation(int transportID) {return this.transportAssociation = transportID;}

    public int getSourceID() {
        return sourceID;
    }

    public int getDestinationID() {
        return destinationID;
    }

    public void setSource(Branch source) {this.source = source;}

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void setOrderWeight(double orderWeight) {
        this.orderWeight = orderWeight;
    }


    public int getTransportAssociation() {return transportAssociation;}

    public int getId() {
        return id;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public Branch getSource() {
        return source;
    }

    public Branch getDestination() {
        return destination;
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean addItem(int id, String name, int amount)
    {
        if (amount < 0 && existItemID(id) )
        {
            return false;
        }
        Item item = new Item(id,name,amount);
        return items.add(item);
    }

    public boolean removeItem(Item item)
    {
        return items.remove(item);
    }

    public boolean changeAmount(int id, int amount)
    {
        Item item1 = null;
        if (existItemID(id))
        {
            for(Item item: items)
            {
                if (item.getId() == id)
                {
                    item1 = item;
                }
            }
            if(item1 != null)
            {

                if (item1.getAmount() <= amount)
                {
                    return removeItem(item1);
                }
                return item1.subAmount(amount);
            }
        }
        return false;
    }
    public boolean existItemID(int id)
    {
        for (Item item : items)
        {
            if(item.getId() == id)
            {
                return true;
            }
        }
        return false;
    }
    public String toStringReport()
    {
        StringBuilder report = new StringBuilder();
        report.append("Order ID: ").append(id).append("\n");
        report.append("Destination: ").append(destination.getAddress()).append("\n");
        report.append("Items:\n");
        for (Item item : items) {
            report.append(item.toString()).append("\n");
        }
        return report.toString();
    }

}





