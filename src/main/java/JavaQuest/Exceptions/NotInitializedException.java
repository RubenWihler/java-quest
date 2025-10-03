package JavaQuest.Exceptions;

public class NotInitializedException extends RuntimeException {
    public NotInitializedException() {
        super("Trying to access Non-Initialized Object !");
    }
}
