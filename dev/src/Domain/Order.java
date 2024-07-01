package Domain;

import DAL.ItemDTO;
import DAL.OrderDTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Order
{
    private static int next_id = 1;
    private int id;
    private ArrayList<Item> items;
    private Site source;
    private Site destination;
    private LocalDate date;
    private double orderWeight;


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

    public Order(OrderDTO orderDTO, ItemDTO itemDTO) {
        setId(orderDTO.id);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date_ = LocalDate.parse(orderDTO.date, formatter);
        setDate(date_);
        setOrderWeight(0.0);

        //setSource(orderDTO.source);
        //setDestination(orderDTO.destination);

        // Load items from the OrderDTO
        this.items = new ArrayList<>();
        List<Integer> itemIds = orderDTO.items.get(orderDTO.id);
        if (itemIds != null) {
            for (Integer itemId : itemIds) {
                Item item = new Item(itemDTO);
                this.items.add(item);
            }
        }
    }




    public void setId(int id) {this.id = id;}

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

    public boolean removeItem(Item item)
    {
        return items.remove(item);
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

    public List<Integer> getItemIdsForOrder(int orderId, ItemRepository itemRepository) {
        return itemRepository.getItemsByOrderId(orderId);
    }
}





