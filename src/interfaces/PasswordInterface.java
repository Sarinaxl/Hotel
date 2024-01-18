package interfaces;

public interface PasswordInterface {
    void onPasswordGranted();

    void onPasswordDenied(String error);
}
