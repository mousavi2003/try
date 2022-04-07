package View;

import Controller.CustomerMenuController;
import enums.regexes;

import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class AdminMenuView {
    private static CustomerMenuController controller = new CustomerMenuController();

    public AdminMenuView(CustomerMenuController controller) {
        AdminMenuView.controller = controller;
    }

    public String run(Scanner scanner, Matcher matcher, String adminName) {
        String input;
        while (true) {
            input = scanner.nextLine();
            String name;
            int integer;
            if ((matcher = regexes.getCommand(input, regexes.ADD_AIRPLANE)) != null) {
                if (controller.getCompony().findAirplaneByName((name = matcher.group("name"))) != null) {
                    System.out.println("an airplane exists with this name");
                } else if ((integer = Integer.parseInt(matcher.group("capacity"))) < 10) {
                    System.out.println("invalid capacity");
                } else {
                    System.out.println("plane created successfully");
                    controller.getCompony().addAirplanes(name, integer);
                }
            } else if ((matcher = regexes.getCommand(input, regexes.ADD_FLIGHT)) != null) {
                if (controller.getCompony().findAirplaneByName((name = matcher.group("name"))) == null) {
                    System.out.println("no airplane exists with this name");
                } else if ((integer = Integer.parseInt(matcher.group("ticketPrice"))) <= 0) {
                    System.out.println("invalid ticket price");
                } else if (controller.checkHadFlight(matcher.group("date"), matcher.group("name"))) {
                    System.out.println("This aircraft already has a flight on this date");
                } else {
                    System.out.println("flight created successfully");
                    controller.getCompony().addFlight(matcher.group("origin"),
                            matcher.group("destination"),
                            matcher.group("date"),
                            name,
                            integer);
                    controller.getCompony().findAirplaneByName(name).setHaveFlight(true);
                }
            } else if (regexes.getCommand(input, regexes.SHOW_ALL_FLIGHTS) != null) {
                controller.sortFlights();
                if (controller.getCompony().getFlights() == null || controller.getCompony().getFlights().size() == 0)
                    System.out.println("nothing");
                else {
                    ArrayList<String> arrayList = controller.showAllFlights();
                    for (String such : arrayList) {
                        System.out.println(such);
                    }
                }
            } else if ((matcher = regexes.getCommand(input, regexes.SHOW_FLIGHTS_ON_DATE)) != null) {
                controller.sortFlights();
                ArrayList<String> list = controller.showFlightsOnDate(matcher.group("date"));
                if (list == null || list.size() == 0) System.out.println("nothing");
                else {
                    for (String each : list) {
                        System.out.println(each);
                    }
                }

            } else if (regexes.getCommand(input, regexes.SHOW_AIRPLANES) != null) {
                controller.sortAirplanes();
                ArrayList<String> list = controller.showAllAirplanes();
                if (list == null || list.size() == 0) System.out.println("nothing");
                else {
                    for (String temp : list) {
                        System.out.println(temp);
                    }
                }
            } else if (regexes.getCommand(input, regexes.SHOW_CAPITAL) != null) {
                System.out.println(controller.getCompony().getMoney());
            } else if (regexes.getCommand(input, regexes.BACK_TO_MENU) != null) {
                input = "back " + controller.getPeople().getAdmin().getUsername();

                return input;
            } else {
                System.out.println("invalid command!");
            }

        }
    }
}
