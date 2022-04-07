package Model;

import java.util.ArrayList;

public class AirplaneCompany {
    private ArrayList<Airplane> airplanes = new ArrayList<Airplane>();
    private ArrayList<Flight> flights = new ArrayList<Flight>();
    private Integer money = 0;

    public ArrayList<Airplane> getAirplanes() {
        return airplanes;
    }

    public ArrayList<Flight> getFlights() {
        return flights;
    }

    public void setMoney(Integer mony) {
        this.money = mony;
    }

    public Integer getMoney() {
        return money;
    }

    public void addAirplanes(String name, Integer capacity) {
        this.airplanes.add(new Airplane(name, capacity,0 , false));
    }

    public void addFlight(String origin, String destination, String date, String airplaneName, Integer ticketPrice) {
        this.flights.add(new Flight(origin, destination, date, airplaneName, ticketPrice));
        findAirplaneByName(airplaneName).setNumberOfFlights(findAirplaneByName(airplaneName).getNumberOfFlights() + 1);
    }

    public Airplane findAirplaneByName(String name) {
        for (int i = 0; i < airplanes.size(); i++) {
            if (airplanes.get(i) != null
                    && airplanes.get(i).getName().equals(name)) {
                return airplanes.get(i);
            }
        }
        return null;
    }
}
