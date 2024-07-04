package DAL;

public class OrderDTO {
    public int id;
    public String date;
    public String source;
    public String destination;
    public int transportId;
    public int sourceID;
    public int destinationID;

    public OrderDTO(int id, String date, String source, String destination, int sourceID, int destinationID, int transportId) {
        this.id = id;
        this.date = date;
        this.source = source;
        this.destination = destination;
        this.sourceID = sourceID;
        this.destinationID = destinationID;
        this.transportId = transportId;
    }

}
