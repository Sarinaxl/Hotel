import UI.LoginPage;
import UI.MainPage;
import UI.SignUpPage;
import UI.manager.ManagerWelcomePage;
import components.CustomeButton;
import database.Database;

public  class App {



    public static void main(String[] args) {
        MainPage mainPage = new MainPage(500, 500, "Main Page");

        ManagerWelcomePage managerWelcomePage = new ManagerWelcomePage(600,600,"Admin Panel");


        CustomeButton loginButton = new CustomeButton(
                "Login",
                25,
                25,
                75,
                45,
                e -> {
                    mainPage.closeFrame();
                    LoginPage loginPage = new LoginPage(500, 500, "Login");
                }
        );


        CustomeButton signUpButton = new CustomeButton(
                "Sign Up",
                150,
                25,
                120,
                45,
                e -> {
                    mainPage.closeFrame();
                    SignUpPage loginPage = new SignUpPage(500, 500, "SignUp");
                }
        );


        mainPage.addComponent(loginButton);
        mainPage.addComponent(signUpButton);
        mainPage.closeFrame();


    }
}
