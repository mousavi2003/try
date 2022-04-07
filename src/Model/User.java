package Model;

import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private boolean logedIn;
    private Integer UserMony;
    private ArrayList<Flight> purchaseTickets = new ArrayList<>();
    private ArrayList<Flight> firstFlights = new ArrayList<>();
    private ArrayList<Flight> secondFlights = new ArrayList<>();

    public User(String username, String password, Integer UserMony, boolean logedIn) {
        this.username = username;
        this.password = password;
        this.logedIn = false;
        this.UserMony = UserMony;
        this.logedIn = logedIn;
    }

    public void setLogedIN(boolean logedIn) {
        this.logedIn = logedIn;
    }

    public boolean isLogedIn() {
        return this.logedIn;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setUserMony(Integer UserMony) {
        this.UserMony = UserMony;
    }

    public Integer getUserMony() {
        return this.UserMony;
    }

    public void setPurchaseTickets(Flight flight) {
        this.purchaseTickets.add(flight);
    }

    public ArrayList<Flight> getPurchaseTickets() {
        return purchaseTickets;
    }

    public void setFirstFlights(Flight flights) {
        this.firstFlights.add(flights);
    }

    public void setSecondFlights(Flight flight) {
        this.secondFlights.add(flight);
    }

    public ArrayList<Flight> getFirstFlights() {
        return firstFlights;
    }

    public ArrayList<Flight> getSecondFlights() {
        return secondFlights;
    }
}

