package View;

import Controller.CustomerMenuController;
import Model.Flight;
import enums.regexes;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class CustomerMenuView {
    private static CustomerMenuController controller = new CustomerMenuController();

    public CustomerMenuView(CustomerMenuController controller) {
        CustomerMenuView.controller = controller;
    }

    int integer;

    public String run(Scanner scanner, Matcher matcher, String CustomerName) {
        String input;
        while (true) {
            input = scanner.nextLine();

            if ((matcher = regexes.getCommand(input, regexes.PURCHASE_TICKET)) != null) {
                String temperaryInput = "";
                String origin = matcher.group("origin");
                String destination = matcher.group("destination");
                ArrayList<String> arrayList = null;
                if (controller.showFlightsFromOriginToDestination(controller.chooseOneDestinationFlights((origin = matcher.group("origin"))
                        , (destination = matcher.group("destination")))) != null) {
                    int i = 0;
                    arrayList = new ArrayList<>(controller.showFlightsFromOriginToDestination(controller.chooseOneDestinationFlights(origin, destination)));
                    while (i < arrayList.size()) {
                        System.out.println(arrayList.get(i));
                        i++;
                    }
                    while (true) {
                        temperaryInput = scanner.nextLine();
                        ArrayList<Flight> flights = new ArrayList<Flight>(controller.chooseOneDestinationFlights(origin, destination));
                        if (regexes.getCommand(temperaryInput, regexes.END) != null)
                            break;
                        else if (regexes.getCommand(temperaryInput, regexes.NUMBER) == null
                                && regexes.getCommand(temperaryInput, regexes.END) == null) {
                            System.out.println("invalid command!");
                        } else if ((matcher = regexes.getCommand(temperaryInput, regexes.NUMBER)) != null
                                && (Integer.parseInt(matcher.group("number")) > arrayList.size()
                                || Integer.parseInt(matcher.group("number")) <= 0)) {
                            System.out.println("invalid number");
                        } else if ((matcher = regexes.getCommand(temperaryInput, regexes.NUMBER)) != null
                                && controller.getCompony().findAirplaneByName(flights.get(Integer.parseInt(matcher.group("number")) - 1).getAirplaneName()).getCapacity() == 0) {
                            System.out.println("no empty seat");
                        } else if ((matcher = regexes.getCommand(temperaryInput, regexes.NUMBER)) != null
                                && (controller.userIsLogined().getUserMony() < controller.chooseOneDestinationFlights(origin, destination).get(Integer.parseInt(matcher.group("number")) - 1).getTicketPrice())) {
                            System.out.println("you don't have enough money");
                        } else {
                            System.out.println("purchase successful");
                            assert matcher != null;
                            controller.userPurchaseSuccessful(origin, destination, Integer.parseInt(matcher.group("number")));
                        }

                    }
                } else {
                    System.out.println("There is no direct flight from " + origin + " to " + destination);
                    if (controller.showTwoDestinationsFlights(controller.chooseFirstFlights(origin, destination)) != null) {
                        arrayList = new ArrayList<>(controller.showTwoDestinationsFlights(controller.chooseFirstFlights(origin, destination)));
                        int i = 0;
                        while (i < arrayList.size()) {
                            System.out.println(arrayList.get(i));
                            i++;
                        }
                        while (true) {
                            temperaryInput = scanner.nextLine();


                            if (regexes.getCommand(temperaryInput, regexes.END) != null)
                                break;
                            else if (regexes.getCommand(temperaryInput, regexes.NUMBER) == null
                                    && regexes.getCommand(temperaryInput, regexes.END) == null) {
                                System.out.println("invalid command!");
                                continue;
                            } else if ((matcher = regexes.getCommand(temperaryInput, regexes.NUMBER)) != null && (Integer.parseInt(matcher.group("number")) > arrayList.size()
                                    || Integer.parseInt(matcher.group("number")) <= 0)) {
                                System.out.println("invalid number");
                                continue;
                            }
                            Flight firstFlight =(Flight)(controller.chooseFirstFlights(origin, destination).keySet().toArray()[Integer.parseInt(matcher.group("number")) - 1]);
                            String nameOfFirstFlight = firstFlight.getAirplaneName();
                            String nameOfSecondFlight = controller.chooseFirstFlights(origin, destination).get(firstFlight).getAirplaneName();
                            int sum = firstFlight.getTicketPrice() +
                                    controller.chooseFirstFlights(origin, destination).get(firstFlight).getTicketPrice();
                            if ((matcher = regexes.getCommand(temperaryInput, regexes.NUMBER)) != null
                                    && (controller.getCompony().findAirplaneByName(nameOfFirstFlight).getCapacity() == 0
                                    || controller.getCompony().findAirplaneByName(nameOfSecondFlight).getCapacity() == 0)) {
                                System.out.println("no empty seat");
                            } else if ((matcher = regexes.getCommand(temperaryInput, regexes.NUMBER)) != null
                                    && controller.userIsLogined().getUserMony() < sum) {
                                System.out.println("you don't have enough money");
                            } else {
                                System.out.println("purchase successful");
                                controller.userPurchaseTwoDestination(origin, destination, Integer.parseInt(matcher.group("number")));
                            }

                        }
                    }
                }
            } else if ((matcher = regexes.getCommand(input, regexes.CHARGE_AMOUNT)) != null) {
                if ((integer = Integer.parseInt(matcher.group("number"))) <= 0) {
                    System.out.println("invalid amount");
                } else {
                    System.out.println("account charged successfully");
                    controller.chargeAccount(integer);
                }
            } else if (regexes.getCommand(input, regexes.CANCEL_TICKET) != null) {
                if (controller.userIsLogined().getPurchaseTickets() == null) {
                    System.out.println("you don't have any tickets");
                } else {
                    int i = 0;
                    while (controller.showTicketsForCanceling(i) != null) {
                        System.out.println(controller.showTicketsForCanceling(i));
                        i++;
                    }

                    String temperaryInput = "";
                    while (true) {
                        temperaryInput = scanner.nextLine();
                        if (regexes.getCommand(temperaryInput, regexes.NUMBER) == null
                                && regexes.getCommand(temperaryInput, regexes.END) == null) {
                            System.out.println("invalid command!");
                        } else if ((matcher = regexes.getCommand(temperaryInput, regexes.NUMBER)) != null) {
                            if (Integer.parseInt(matcher.group("number")) <= 0
                                    || Integer.parseInt(matcher.group("number")) > controller.userIsLogined().getPurchaseTickets().size()) {
                                System.out.println("invalid number");
                            } else {
                                System.out.println("cancel successful");
                                controller.cancelTicket(Integer.parseInt(matcher.group("number")));
                                break;
                            }
                        } else if (regexes.getCommand(temperaryInput, regexes.END) != null) {
                            break;
                        }
                    }
                }
            } else if (regexes.getCommand(input, regexes.SHOW_CAPITAL_FOR_USER) != null) {
                System.out.println(controller.userIsLogined().getUserMony());
            } else if (regexes.getCommand(input, regexes.BACK_TO_MENU) != null) {
                return "back " + controller.userIsLogined().getUsername();
            } else {
                System.out.println("invalid command!");
            }
        }
    }
}
