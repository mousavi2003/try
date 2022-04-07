package enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
//PASSWORD_IS_VALID ro ham mesl username sade ash kon
public enum regexes {
    REGISTER_USERNAME("^\\s*register\\s+(?<username>\\S+)\\s+(?<password>\\S+)\\s*$"),
    USERNAME("^(?<username>(?=.*[a-zA-Z])[a-zA-Z\\d_]+)$"),
    PASSWORD_IS_VALID("^\\s*register\\s+(?<username>\\S+)\\s+(?<password>[a-zA-Z0-9_]+)\\s*$"),
    PASSWORD("^\\s*register\\s+(?<username>\\S+)\\s+(?<password>(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[A-Za-z\\d_]{5,})\\s*$"),
    REGISTER_ADMIN("^\\s*register\\s+as\\s+admin\\s+(?<username>\\S+)\\s+(?<password>\\S+)\\s*$"),
    ADMIN_USERNAME("^(?<username>(?=.*[a-zA-Z])[a-zA-Z0-9_]+)$"),
    ADMIN_PASSWORD_IS_VALID("^\\s*register\\s+as\\s+admin\\s+(?<username>\\S+)\\s+(?<password>[a-zA-Z0-9_]+)\\s*$"),
    ADMIN_PASSWORD("^\\s*register\\s+as\\s+admin\\s+(?<username>\\S+)\\s+(?<password>(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[A-Za-z\\d_]{5,})\\s*$"),
    LOGIN("^\\s*login\\s+(?<username>\\S+)\\s+(?<password>\\S+)\\s*$"),
    CHANGE_PASSWORD("^\\s*change\\s+password\\s+(?<username>\\S+)\\s+(?<oldPassword>\\S+)\\s+(?<newPassword>\\S+)\\s*$"),
    NEW_PASSWORD_IS_VALID("^\\s*change\\s+password\\s+(?<username>\\S+)\\s+(?<oldPassword>\\S+)\\s+(?<newPassword>[a-zA-Z0-9_]+)\\s*$"),
    NEW_PASSWORD("^\\s*change\\s+password\\s+(?<username>\\S+)\\s+(?<oldPassword>\\S+)\\s+(?<newPassword>(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[A-Za-z\\d_]{5,})\\s*$"),
    REMOVE_ACCOUNT("^\\s*remove\\s+account\\s+(?<username>\\S+)\\s+(?<password>\\S+)\\s*$"),
    Exit("^\\s*Exit\\s*$"),


    ADMIN_USER("^\\s*admin\\s+menu\\s*$"),
    CUSTOMER_MENU("^\\s*customer\\s+menu\\s*$"),
    LOGOUT("^\\s*logout\\s*$"),

    ADD_AIRPLANE("^\\s*add\\s+airplane\\s+(?<name>\\S+)\\s+(?<capacity>\\S+)\\s*$"),
    ADD_FLIGHT("^\\s*add\\s+flight\\s+(?<origin>\\S+)\\s+(?<destination>\\S+)\\s+(?<date>\\d{4}-\\d{2}-\\d{2})\\s+(?<name>\\S+)\\s+(?<ticketPrice>-?\\d+)\\s*$"),
    SHOW_ALL_FLIGHTS("^\\s*show\\s+all\\s+flights\\s*$"),
    SHOW_AIRPLANES("^\\s*show\\s+airplanes\\s*$"),
    SHOW_FLIGHTS_ON_DATE("^\\s*show\\s+flights\\s+on\\s+(?<date>\\d{4}-\\d{2}-\\d{2})\\s*$"),
    SHOW_CAPITAL("^\\s*show\\s+capital\\s*$"),
    BACK_TO_MENU("^\\s*back\\s*$"),
    BACK_WITH_USERNAME("^back\\s+(?<username>\\S+)$"),

    PURCHASE_TICKET("^\\s*purchase\\s+ticket\\s+(?<origin>\\S+)\\s+(?<destination>\\S+)\\s*$"),
    END("^\\s*end\\s*$"),
    NUMBER("^\\s*(?<number>-?\\d+)\\s*$"),
    CHARGE_AMOUNT("^\\s*charge\\s+account\\s+(?<number>-?\\d+)\\s*$"),
    CANCEL_TICKET("^\\s*cancel\\s+ticket\\s*$"),
    SHOW_CAPITAL_FOR_USER("^\\s*show\\s+capital\\s*$");




    private String regex;
    regexes(String regex) {
        this.regex = regex;
    }
    public static Matcher getCommand (String input, regexes comand) {
        Matcher matcher = Pattern.compile(comand.regex).matcher(input);
        if (matcher.matches()) {
            return matcher;
        }
        return null;
    }

}
