package Model;

public class Flight {
    private String origin;
    private String destination;
    private String date;
    private String airplaneName;
    private Integer ticketPrice;

    public Flight(String origin, String destination, String date, String airplaneName, Integer ticketPrice) {
        this.origin = origin;
        this.destination = destination;
        this.date = date;
        this.airplaneName = airplaneName;
        this.ticketPrice = ticketPrice;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getOrigin() {
        return this.origin;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDestination() {
        return this.destination;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return this.date;
    }

    public void setAirplaneName(String airplaneName) {
        this.airplaneName = airplaneName;
    }

    public String getAirplaneName() {
        return this.airplaneName;
    }

    public void setTicketPrice(Integer ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public Integer getTicketPrice() {
        return this.ticketPrice;
    }
}
