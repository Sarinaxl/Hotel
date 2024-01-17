import UI.LoginPage;
import UI.MainPage;
import components.CustomeButton;

public class App {

    public static void main(String[] args) {
        MainPage mainPage = new MainPage(500, 500, "Main Page");
        CustomeButton button = new CustomeButton(
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


        mainPage.addComponent(button);
        mainPage.showFrame();


    }
}
