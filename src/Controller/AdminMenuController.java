package Controller;

import Model.Admin;
import Model.Airplane;
import Model.AirplaneCompany;
import Model.Flight;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class AdminMenuController extends MainMenuController {

    private AirplaneCompany company = new AirplaneCompany();

    public AirplaneCompany getCompony() {
        return this.company;
    }

    public ArrayList<String> showAllFlights() {
        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < company.getFlights().size(); i++) {
            list.add(i + 1 + "- " + company.getFlights().get(i).getOrigin() + "->" +
                    company.getFlights().get(i).getDestination() +
                    " " + company.getFlights().get(i).getDate() + " " +
                    company.getFlights().get(i).getAirplaneName() + " " +
                    company.getFlights().get(i).getTicketPrice());
        }
        return list;
    }

    public ArrayList<String> showFlightsOnDate(String date) {
        ArrayList<String> list = new ArrayList<>();
        int counter = 0;
        for (int j = 0; j < company.getFlights().size(); j++) {
            if (company.getFlights().get(j).getDate().equals(date)) {
                counter++;
                list.add(counter + "- " + company.getFlights().get(j).getOrigin() + "->" +
                        company.getFlights().get(j).getDestination() +
                        " " + company.getFlights().get(j).getDate() + " " +
                        company.getFlights().get(j).getAirplaneName() + " " +
                        company.getFlights().get(j).getTicketPrice());
            }
        }

        return list;
    }

    public ArrayList<String> showAllAirplanes() {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < company.getAirplanes().size(); i++) {
            arrayList.add(i + 1 + "- " + company.getAirplanes().get(i).getName() + " : " + company.getAirplanes().get(i).getNumberOfFlights());
        }
        return arrayList;
    }

    public void sortFlights() {

        company.getFlights().sort(new Comparator<Flight>() {
            @Override
            public int compare(Flight o1, Flight o2) {
                if (sortDates(o1.getDate(), o2.getDate()) == 0
                        && o1.getTicketPrice().compareTo(o2.getTicketPrice()) == 0)
                    return o1.getAirplaneName().compareTo(o2.getAirplaneName());
                if (sortDates(o1.getDate(), o2.getDate()) == 0)
                    return o1.getTicketPrice().compareTo(o2.getTicketPrice());

                return sortDates(o1.getDate(), o2.getDate());
            }
        });
    }

    public void sortAirplanes() {
        company.getAirplanes().sort(new Comparator<Airplane>() {
            @Override
            public int compare(Airplane o1, Airplane o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
    }

    public int sortDates(String firstDate, String secondDate) {
        Integer dayOfFirstDate = firstDate.charAt(8) * 10 + firstDate.charAt(9);
        Integer monthOfFirstDate = firstDate.charAt(5) * 10 + firstDate.charAt(6);
        Integer yearOfFirstdate = firstDate.charAt(0) * 1000 + firstDate.charAt(1) * 100 + firstDate.charAt(2) * 10 + firstDate.charAt(3);

        Integer dayOfSecondDate = secondDate.charAt(8) * 10 + secondDate.charAt(9);
        Integer monthOfSecondDate = secondDate.charAt(5) * 10 + secondDate.charAt(6);
        Integer yearOfSeconddate = secondDate.charAt(0) * 1000 + secondDate.charAt(1) * 100 + secondDate.charAt(2) * 10 + secondDate.charAt(3);

        if (yearOfFirstdate > yearOfSeconddate) return 1;
        else if (yearOfFirstdate.equals(yearOfSeconddate)) {
            if (monthOfFirstDate > monthOfSecondDate) return 1;
            else if (monthOfFirstDate.equals(monthOfSecondDate)) {
                if (dayOfFirstDate > dayOfSecondDate) return 1;
                else if (dayOfFirstDate.equals(dayOfSecondDate)) return 0;
                else return -1;
            } else return -1;
        } else return -1;

    }

    public boolean checkHadFlight(String date, String name) {
        if (getCompony().getFlights() != null) {
            for (int i = 0; i < getCompony().getFlights().size(); i++) {
                if (getCompony().getFlights().get(i).getDate().equals(date)
                        && getCompony().getFlights().get(i).getAirplaneName().equals(name))
                    return true;
            }
        }
        return false;

    }

}
