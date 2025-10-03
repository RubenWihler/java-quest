package JavaQuest.Exceptions;

public class AlredyInitializedException extends RuntimeException {
    public AlredyInitializedException() {
        super("Trying init an Object that is alredy initialized !");
    }
}
