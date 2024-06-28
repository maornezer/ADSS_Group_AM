package DAL;

public class OrderDTO {
    public int id;
    public String date;
    public String source;
    public String destination;

    public OrderDTO(int id, String date, String source, String destination) {
        this.id = id;
        this.date = date;
        this.source = source;
        this.destination = destination;
    }
}
