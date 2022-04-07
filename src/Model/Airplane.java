package Model;

public class Airplane {
    private String name;
    private Integer capacity;
    private Integer numberOfFlights;
    private boolean haveFlight;

    public Airplane(String name, Integer capacity,Integer numberOfFlights, boolean haveFlight) {
        this.name = name;
        this.capacity = capacity;
        this.haveFlight = haveFlight;
        this.numberOfFlights = numberOfFlights;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getName() {
        return this.name;
    }

    public Integer getCapacity() {
        return this.capacity;
    }

    public void setNumberOfFlights(Integer numberOfFlights) {
        this.numberOfFlights = numberOfFlights;
    }

    public Integer getNumberOfFlights() {
        return numberOfFlights;
    }

    public void setHaveFlight(boolean haveFlight) {
        this.haveFlight = haveFlight;
    }

    public boolean hadFlight() {
        return haveFlight;
    }
}
