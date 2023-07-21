package Food_app.domain.exception;

public class UserAlreadyExist extends RuntimeException {

    public UserAlreadyExist(final String message) {
        super(message);
    }
}
