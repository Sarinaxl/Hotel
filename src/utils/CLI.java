import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class CLI {


    String error = "";


    public static Map<String, String> readAllUsers() {
        Map<String, String> userMap = new LinkedHashMap<>();

        File file = new File("user_data.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader("user_data.txt"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                String email = parts[0];
                String hashedPassword = parts[1];

                userMap.put(email, hashedPassword);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return userMap;
    }

    public boolean signUp(String email, String password) {
        if (!EmailValidator.isValid(email)) {
            error = "Invalid email format. Please try again.";
            System.out.println("Invalid email format. Please try again.");
            return false;
        }

        if (isUserExist(email)) {
            error = " ********* There is User Already registered with same email.";
            System.out.println(" ********* There is User Already registered with same email.");
            return false;

        }

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
            return false;

        }

        if (level < 3) {
            error = "week Password not Allowed!  please Enter Strong password." ;
            System.out.println("week Password not Allowed!  please Enter Strong password.");
            return false;

        }


        String hashedPassword = new PasswordHasher().getSHA(password);

        try (FileWriter writer = new FileWriter("user_data.txt", true)) {
            writer.write(email + ":" + hashedPassword + "\n");
            System.out.println("Registration successful!");
            return  true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  false;
    }


    public boolean login(String email, String password) {
        if (!EmailValidator.isValid(email)) {
            error = "Invalid email format. Please try again.";
            System.out.println("Invalid email format. Please try again.");
            return  false ;
        }

        if (!isUserExist(email)) {
            error = "Email or password is invalid";
            System.out.println(" Email or password is invalid !");
            return  false ;
        }
        if (validateUser(email, password)) {
            System.out.println(" Welcome Back !");
            return  true;
        } else {
            error = "********* User Not Founded!";
            System.out.println("********* User Not Founded!");
            return  false ;
        }



    }

    public Boolean isUserExist(String email) {
        return readAllUsers().containsKey(email);
    }


    public Boolean validateUser(String email, String password) {
        PasswordHasher passwordHasher = new PasswordHasher();
        String hashedPassword = passwordHasher.getSHA(password);
        if (isUserExist(email)) {
            String savedPassword = readAllUsers().get(email);
            return savedPassword.equals(hashedPassword);
        }
        return false;
    }


}
