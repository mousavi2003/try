package Controller;

import Model.Flight;
import java.util.ArrayList;
import java.util.HashMap;

public class CustomerMenuController extends AdminMenuController {


    public ArrayList<String> showFlightsFromOriginToDestination(ArrayList<Flight> flights) {

        int counter = 0;
        ArrayList<String> listOfFlights = new ArrayList<>();
        if (flights != null) {
            for (int i = 0; i < flights.size(); i++) {
                counter++;
                listOfFlights.add(counter + "- " +
                        flights.get(i).getDate() +
                        " " + flights.get(i).getAirplaneName() +
                        " " + flights.get(i).getTicketPrice());
            }
            if (listOfFlights.size() != 0) return listOfFlights;
        }
        return null;
    }

    public ArrayList<Flight> chooseOneDestinationFlights(String origin, String destination) {
        sortFlights();
        ArrayList<Flight> flights = new ArrayList<>();
        if (getCompony().getFlights() != null) {
            for (int i = 0; i < getCompony().getFlights().size(); i++) {
                if (getCompony().getFlights().get(i).getOrigin().equals(origin)
                        && getCompony().getFlights().get(i).getDestination().equals(destination)) {

                    flights.add(getCompony().getFlights().get(i));
                }
            }
            if (flights.size() != 0) return flights;
        }
        return null;

    }

    public HashMap<Flight, Flight> chooseFirstFlights(String origin, String destination) {
        sortFlights();

        HashMap<Flight, Flight> flights = new HashMap<>();
        if (getCompony().getFlights() != null) {
            for (int i = 0; i < getCompony().getFlights().size(); i++) {
                for (int j = i + 1; j < getCompony().getFlights().size(); j++) {
                    if (getCompony().getFlights().get(i).getOrigin().equals(origin)
                            && getCompony().getFlights().get(i).getDestination().equals(getCompony().getFlights().get(j).getOrigin())
                            && getCompony().getFlights().get(j).getDestination().equals(destination)) {
                        if (sortDates(getCompony().getFlights().get(i).getDate(), getCompony().getFlights().get(j).getDate()) < 0) {
                            flights.put(getCompony().getFlights().get(i), getCompony().getFlights().get(j));
                        }

                    }
                }
            }
            if (flights.size() != 0) return flights;
        }

        return null;
    }

    public ArrayList<String> showTwoDestinationsFlights(HashMap<Flight ,Flight> flights) {
        int counter = 0;
        ArrayList<String> listOfFlights = new ArrayList<>();
        if (flights != null ) {
            for (Flight first : flights.keySet()) {
                counter++;
                listOfFlights.add(counter + "- " + first.getOrigin() +
                        "->" + first.getDestination() +
                        " " + first.getDate() +
                        " " + first.getTicketPrice() +
                        " | " + flights.get(first).getOrigin() +
                        "->" + flights.get(first).getDestination() +
                        " " + flights.get(first).getDate() +
                        " " + flights.get(first).getTicketPrice());
            }
            if (listOfFlights.size() != 0) return listOfFlights;
        }
        return null;
    }

    public void userPurchaseSuccessful(String origin, String destination, int number) {
        Flight flight = chooseOneDestinationFlights(origin, destination).get(number - 1);
        String name = chooseOneDestinationFlights(origin, destination).get(number - 1).getAirplaneName();
        userIsLogined().setUserMony(userIsLogined().getUserMony() - chooseOneDestinationFlights(origin, destination).get(number - 1).getTicketPrice());
        getCompony().findAirplaneByName(name).setCapacity(getCompony().findAirplaneByName(name).getCapacity() - 1);
        getCompony().setMoney(getCompony().getMoney() + chooseOneDestinationFlights(origin, destination).get(number - 1).getTicketPrice());
        userIsLogined().setPurchaseTickets(flight);
    }

    public void userPurchaseTwoDestination(String origin, String destination, int number) {
        Flight firstFlight = (Flight) chooseFirstFlights(origin, destination).keySet().toArray()[0];
        Flight secondFlight = chooseFirstFlights(origin, destination).get(firstFlight);
        String nameOfFirstFlight = firstFlight.getAirplaneName();
        String nameOfSecondFlight = secondFlight.getAirplaneName();
        int sum = firstFlight.getTicketPrice() +
               secondFlight.getTicketPrice();
        userIsLogined().setUserMony(userIsLogined().getUserMony() - sum);
        getCompony().findAirplaneByName(nameOfFirstFlight).setCapacity(getCompony().findAirplaneByName(nameOfFirstFlight).getCapacity() - 1);
        getCompony().findAirplaneByName(nameOfSecondFlight).setCapacity(getCompony().findAirplaneByName(nameOfSecondFlight).getCapacity() - 1);
        getCompony().setMoney(getCompony().getMoney() + sum);

        userIsLogined().setPurchaseTickets(firstFlight);
        userIsLogined().setPurchaseTickets(secondFlight);
    }

    public void chargeAccount(int amount) {
        userIsLogined().setUserMony(userIsLogined().getUserMony() + amount);
    }

    public String showTicketsForCanceling(int i) {

        while (i < userIsLogined().getPurchaseTickets().size()) {
            if(userIsLogined().getPurchaseTickets() != null) {
                Flight flight = userIsLogined().getPurchaseTickets().get(i);
                return  i+1 + "- " + flight.getOrigin() + "->" +flight.getDestination() +
                        " " + flight.getDate() + " " + flight.getTicketPrice();
            }
        }
        return null;
    }

    public void cancelTicket(int number) {
        int amount = (int) (0.2 * userIsLogined().getPurchaseTickets().get(number - 1).getTicketPrice());
        int ticketPrice = userIsLogined().getPurchaseTickets().get(number - 1).getTicketPrice();
        getCompony().findAirplaneByName(userIsLogined().getPurchaseTickets().get(number -1).getAirplaneName()).setCapacity(getCompony().findAirplaneByName(userIsLogined().getPurchaseTickets().get(number -1).getAirplaneName()).getCapacity() + 1);
        userIsLogined().getPurchaseTickets().remove(number - 1);
        userIsLogined().setUserMony(userIsLogined().getUserMony() + ticketPrice - amount);
        getCompony().setMoney(getCompony().getMoney() - ticketPrice + amount);
        int i;

    }
}

