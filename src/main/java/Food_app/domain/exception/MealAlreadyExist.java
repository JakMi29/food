package Food_app.domain.exception;

public class MealAlreadyExist extends RuntimeException {

    public MealAlreadyExist(final String message) {
        super(message);
    }
}
