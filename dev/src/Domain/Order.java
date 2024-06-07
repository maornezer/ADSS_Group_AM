package Domain;

import java.time.LocalDate;
import java.util.ArrayList;

public class Order
{
    private static int next_id = 1;
    private int id;
    private ArrayList<Item> items;
    private Site source;
    private Site destination;
    private LocalDate date;
    private double orderWeight;

//
    public Order(LocalDate date, Site destination, Site source, ArrayList<Item> itemsList)
    {
        id = next_id++;
        setDate(date);
        setSource(source);
        setDestination(destination);
        setOrderWeight(0.0);
        items = new ArrayList<>();
        for (Item item : itemsList)
        {
            addItem(item.getId(), item.getName(), item.getAmount());
        }

    }

    //public void setId(int newid) {id = newid;}

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setDestination(Site destination) {
        this.destination = destination;
    }

    public void setSource(Site source) {
            this.source = source;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
    public void setOrderWeight(double orderWeight) {
        this.orderWeight = orderWeight;
    }
    public double getOrderWeight() {
        return orderWeight;
    }



    public int getId() {
        return id;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public Site getSource() {
        return source;
    }

    public Site getDestination() {
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

    public boolean removeItem(int id)
    {
        if(existItemID(id))
        {
            Item item = items.get(id);
            return items.remove(item);
        }
        return false;
    }
    public Item getItemByID(int itemID)
    {
        for (Item item :items)
        {
            if(item.getId() == itemID)
                return item;
        }
        return null;
    }
    public boolean changeAmount(int id, int amount)
    {
        if (existItemID(id))
        {
            Item item = items.get(id);
            if (item.getAmount() <= amount)
            {
                return removeItem(id);
            }
            return item.subAmount(amount);
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
//    public void toStringReport()
//    {
//        StringBuilder report = new StringBuilder();
//        report.append("Order ID: ").append(id).append("\n");
//        report.append("Destination: ").append(destination.getAddress()).append("\n");
//        report.append("Items:\n");
//        for (Item item : items) {
//            item.toString();
//            report.append(item.toString()).append("\n");
//        }
////        System.out.println("order");
////        System.out.println(report.toString());
//        report.toString();
//    }


}





