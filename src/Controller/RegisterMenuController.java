package Controller;

import Model.People;
import Model.User;
import enums.regexes;

import java.util.regex.Matcher;

public class RegisterMenuController {
    private People people = new People();

    public People getPeople() {
        return this.people;
    }

    public void addUser(String username, String password) {
        getPeople().addUser(username, password);
    }

    public String registerUser(String input, Matcher matcher) {
        String username = matcher.group("username");
        if (regexes.getCommand(matcher.group("username"), regexes.USERNAME) == null) {
            return "username format is invalid";
        } else if (getPeople().findUserByUsername(username) != null
                || (getPeople().getAdmin() != null
                && getPeople().getAdmin().getUsername().equals(username))) {
            return "a user exists with this username";
        } else if (regexes.getCommand(input, regexes.PASSWORD_IS_VALID) == null) {
            return "password format is invalid";
        } else if (regexes.getCommand(input, regexes.PASSWORD) == null) {
            return "password is weak";
        } else {
            addUser(username, matcher.group("password"));
            return "register successful";
        }
    }

    public String registerAsAdmin(String input, Matcher matcher) {
        String username = matcher.group("username");
        if (getPeople().getAdmin() != null
                && getPeople().getAdmin().getUsername() != null) {     //here
            return "admin user already created";
        } else if (regexes.getCommand(username, regexes.ADMIN_USERNAME) == null) {
            return "username format is invalid";
        } else if (getPeople().findUserByUsername(username) != null) {
            return "a user exists with this username";
        } else if ((regexes.getCommand(input, regexes.ADMIN_PASSWORD_IS_VALID)) == null) {
            return "password format is invalid";
        } else if ((regexes.getCommand(input, regexes.ADMIN_PASSWORD)) == null) {
            return "password is weak";
        } else {
            getPeople().setAdmin(username, matcher.group("password"), true);
            return "admin user created successfully";
        }
    }

    public String login(Matcher matcher) {
        String username = matcher.group("username");
        if ((getPeople().getAdmin() == null || (getPeople().findUserByUsername(username) == null && (getPeople().getAdmin() != null
                && !getPeople().getAdmin().getUsername().equals(username))))
                && getPeople().findUserByUsername(username) == null) {
            return "no user exists with this username";
        } else if (getPeople().findUserByUsername(username) != null
                && !getPeople().findUserByUsername(username).getPassword().equals(matcher.group("password"))) {
            return "incorrect password";
        } else if (getPeople().getAdmin() != null
                && !getPeople().getAdmin().getPassword().equals(matcher.group("password"))) {
            return "incorrect password";
        } else if (getPeople().findUserByUsername(username) != null
                && getPeople().findUserByUsername(username).getPassword().equals(matcher.group("password"))) {
            getPeople().findUserByUsername(username).setLogedIN(true);
            return "login successful";
        } else if (getPeople().getAdmin() != null
                && getPeople().getAdmin().getPassword().equals(matcher.group("password"))) {
            return "login successful";
        }
        return null;
    }

    public String chngePassword(String input, Matcher matcher) {
        String username = matcher.group("username");
        if ((getPeople().getAdmin() == null || (getPeople().getAdmin() != null
                && !getPeople().getAdmin().getUsername().equals(username)))
                && getPeople().findUserByUsername(username) == null) {
            return "no user exists with this username";
        } else if (getPeople().findUserByUsername(username) != null
                && !getPeople().findUserByUsername(username).getPassword().equals(matcher.group("oldPassword"))) {
            return "incorrect password";
        } else if (getPeople().getAdmin() != null
                && !getPeople().getAdmin().getPassword().equals(matcher.group("oldPassword"))) {
            return "incorrect password";
        }
        if (regexes.getCommand(input, regexes.NEW_PASSWORD_IS_VALID) == null) {
            return "new password format is invalid";
        } else if (regexes.getCommand(input, regexes.NEW_PASSWORD) == null) {
            return "new password is weak";
        } else {
            matcher = regexes.getCommand(input, regexes.NEW_PASSWORD);
            assert matcher != null;
            String newPassword = matcher.group("newPassword");
            if (getPeople().getAdmin() != null
                    && getPeople().getAdmin().getUsername().equals(username)) {
                getPeople().getAdmin().setPassword(newPassword);
            } else {
                if (getPeople().findUserByUsername(username) != null)
                    getPeople().findUserByUsername(username).setPassword(newPassword);
            }
            return "password changed successfully";

        }
    }

    public String removeAccount(String input, Matcher matcher) {
        if (getPeople().findUserByUsername(matcher.group("username")) == null
                && (getPeople().getAdmin() == null
                || (getPeople().getAdmin() != null
                && !getPeople().getAdmin().getUsername().equals(matcher.group("username")))))
            return "no user exists with this username";
        if (getPeople().findUserByUsername(matcher.group("username")) != null && !getPeople().findUserByUsername(matcher.group("username")).getPassword().equals(matcher.group("password"))) {
            return "incorrect password";
        } else if (getPeople().getAdmin() != null
                && getPeople().getAdmin().getUsername().equals(matcher.group("username"))
                && !getPeople().getAdmin().getPassword().equals(matcher.group("password"))) {
            return "incorrect password";
        } else {
            if (getPeople().findUserByUsername(matcher.group("username")) != null) {
                getPeople().removeUserAccount(matcher.group("username"));
                return "account removed successfully";
            }
            getPeople().removeAdmin();
            return "account removed successfully";
        }

    }

    public User userIsLogined() {
        for (int i = 0; i < getPeople().getAllUsers().size(); i++) {
            if (getPeople().getAllUsers().get(i).isLogedIn()) {
                return getPeople().getAllUsers().get(i);
            }
        }
        return null;
    }


}
