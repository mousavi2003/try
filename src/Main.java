import Controller.AdminMenuController;
import Controller.CustomerMenuController;
import Controller.MainMenuController;
import Controller.RegisterMenuController;
import Model.People;
import View.AdminMenuView;
import View.CustomerMenuView;
import View.MainMenuView;
import View.RegisterMenuView;
import enums.regexes;

import java.util.Scanner;
import java.util.regex.Matcher;

public class Main {

    public static void main(String[] args) {
        CustomerMenuController customerMenuController = new CustomerMenuController();
        CustomerMenuView customerMenuView = new CustomerMenuView(customerMenuController);
        RegisterMenuController registerMenuController = new RegisterMenuController();
        RegisterMenuView registerMenuView = new RegisterMenuView(customerMenuController);
        MainMenuController mainMenuController = new MainMenuController();
        MainMenuView mainMenuView = new MainMenuView(customerMenuController);
        AdminMenuController adminMenuController = new AdminMenuController();
        AdminMenuView adminMenuView = new AdminMenuView(customerMenuController);

        Scanner scanner = new Scanner(System.in);

        String input;
        input = "";
        int counter = 0;
        String name = "";
        while (true) {
            Matcher matcher = null;
            if (counter == 0)
                input = registerMenuView.run(scanner, matcher);
            if ((matcher = regexes.getCommand(input, regexes.LOGIN)) != null)
                input = mainMenuView.run(scanner, (name = matcher.group("username")), matcher);
            else if (input.equals("logout")) {
                input = registerMenuView.run(scanner, matcher);
            }
            else if (regexes.getCommand(input, regexes.ADMIN_USER) != null) {
                input = adminMenuView.run(scanner, matcher, name);
            } else if (regexes.getCommand(input, regexes.CUSTOMER_MENU) != null) {
                input = customerMenuView.run(scanner, matcher, name);
            } else if ((matcher = regexes.getCommand(input, regexes.BACK_WITH_USERNAME)) != null)
                input = mainMenuView.run(scanner, matcher.group("username"), matcher);
            else if (input.equals("Exit")) {
                break;
            }
            counter++;
        }


    }
}
