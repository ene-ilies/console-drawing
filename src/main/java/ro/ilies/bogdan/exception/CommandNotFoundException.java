package ro.ilies.bogdan.exception;

public class CommandNotFoundException extends ValidationException {

    public CommandNotFoundException(String message) {
        super(message);
    }
}
