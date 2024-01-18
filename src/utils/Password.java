package utils;

import interfaces.PasswordInterface;

public class Password {

    public static void checkPasswordStrong(String password, PasswordInterface passwordInterface) {

        String level1Regex = "^[a-zA-Z0-9]+$";
        String level2Regex = "^(?=.*[A-Z])[a-zA-Z0-9]+$";
        String level3Regex = "^(?=.*[A-Z])(?=.*\\d)[a-zA-Z0-9]+$";
        String level4Regex = "^(?=.*[A-Z])(?=.*[!@_\\-.])[a-zA-Z0-9]+$";
        String level5Regex = "^.{8,}$";
        int level = 0;
        if (password.matches(level1Regex)) {
            System.out.println("Level 1: Weak");
            level = 1;
        } else if (password.matches(level2Regex)) {
            System.out.println("Level 2: Moderate");
            level = 2;
        } else if (password.matches(level3Regex)) {
            System.out.println("Level 3: Strong");
            level = 3;
        } else if (password.matches(level4Regex)) {
            System.out.println("Level 4: Very Strong");
            level = 4;
        } else if (password.matches(level5Regex)) {
            System.out.println("Level 5: Extremely Strong");
            level = 5;
        } else {
            System.out.println("Invalid password");
            passwordInterface.onPasswordDenied("Invalid password.");
            return;
        }

        if (level < 3) {
            passwordInterface.onPasswordDenied("week Password not Allowed!  please Enter Strong password.");
            return;
        }

        passwordInterface.onPasswordGranted();

    }
}
