package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {


    public static boolean isValid(String email) {
        String emailRegex = "^[a-zA-Z0-9]+(\\.[a-zA-Z0-9]+)*@[a-zA-Z0-9]+(\\.[a-zA-Z]{2,}){1,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
