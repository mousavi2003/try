package View;

import Controller.CustomerMenuController;
import Controller.RegisterMenuController;
import Model.Admin;
import enums.regexes;

import java.util.Scanner;
import java.util.regex.Matcher;

public class RegisterMenuView {
    private static CustomerMenuController menuController = new CustomerMenuController();

    public RegisterMenuView(CustomerMenuController menuController) {
        RegisterMenuView.menuController = menuController;
    }


    public String run(Scanner scanner, Matcher matcher) {
        String input;
        while (true) {
            input = scanner.nextLine();
            if ((matcher = regexes.getCommand(input, regexes.REGISTER_USERNAME)) != null) {
                System.out.println(menuController.registerUser(input, matcher));
            } else if ((matcher = regexes.getCommand(input, regexes.REGISTER_ADMIN)) != null) {
                System.out.println(menuController.registerAsAdmin(input, matcher));
            } else if ((matcher = regexes.getCommand(input, regexes.LOGIN)) != null) {
                System.out.println(menuController.login(matcher));
                if (menuController.login(matcher).equals("login successful"))
                    return input;
            } else if ((matcher = regexes.getCommand(input, regexes.CHANGE_PASSWORD)) != null) {
                System.out.println(menuController.chngePassword(input, matcher));
            } else if ((matcher = regexes.getCommand(input, regexes.REMOVE_ACCOUNT)) != null) {
                System.out.println(menuController.removeAccount(input, matcher));
            } else if (regexes.getCommand(input, regexes.Exit) != null) {
                return input;
            } else {
                System.out.println("invalid command!");
            }
        }
    }


}
