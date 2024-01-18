package interfaces;

public interface InsertCallbacks {
    void onSuccess(int affectedRows);
    void onError(Exception e );
}
