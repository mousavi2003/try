package View;

import Controller.CustomerMenuController;
import Controller.MainMenuController;
import Controller.RegisterMenuController;
import enums.regexes;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MainMenuView {

    //MainMenuController controller = new MainMenuController();
    private static CustomerMenuController controller = new CustomerMenuController();

    public MainMenuView(CustomerMenuController controller) {

        MainMenuView.controller = controller;
    }

    public String run(Scanner scanner, String person, Matcher matcher) {
        String input;
        while (true) {
            input = scanner.nextLine();
            if (controller.getPeople().findUserByUsername(person) == null) {
                if (regexes.getCommand(input, regexes.ADMIN_USER) != null) {
                    return input;
                } else if (regexes.getCommand(input, regexes.LOGOUT) != null) {
                    System.out.println("logout successful");
                    return "logout";
                } else if (regexes.getCommand(input, regexes.CUSTOMER_MENU) != null) {
                    System.out.println("you don't have access to this menu");
                } else {
                    System.out.println("invalid command!");
                }
            } else if (controller.getPeople().findUserByUsername(person) != null) {
                if (regexes.getCommand(input, regexes.CUSTOMER_MENU) != null) {
                    return input;
                } else if (regexes.getCommand(input, regexes.LOGOUT) != null) {
                    System.out.println("logout successful");
                    controller.getPeople().findUserByUsername(person).setLogedIN(false);
                    return "logout";
                } else if (regexes.getCommand(input, regexes.ADMIN_USER) != null) {
                    System.out.println("you don't have access to this menu");
                } else {
                    System.out.println("invalid command!");
                }
            }

        }
    }
}
