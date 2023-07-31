package food_app.api.controller;

import Food_app.api.controller.GlobalExceptionHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class GlobalExceptionHandlerTest {
    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;


    @Test
    public void testHandleExceptionWithExceptionShouldReturnErrorModelAndView() {
        Exception ex = new Exception("Test Exception");

        ModelAndView modelAndView = globalExceptionHandler.handleException(ex);

        assertEquals("error", modelAndView.getViewName());
        assertEquals("Test Exception", modelAndView.getModel().get("errorMessage"));
    }

    @Test
    public void testHandleExceptionWithNullMessageShouldReturnDefaultErrorMessage() {
        Exception ex = new Exception();

        ModelAndView modelAndView = globalExceptionHandler.handleException(ex);

        assertEquals("error", modelAndView.getViewName());
        assertEquals("Something went wrong, please try again", modelAndView.getModel().get("errorMessage"));
    }

    @Test
    public void testHandleExceptionWithCustomMessageShouldReturnCustomErrorMessage() {
        Exception ex = new Exception("Custom Error Message");

        ModelAndView modelAndView = globalExceptionHandler.handleException(ex);

        assertEquals("error", modelAndView.getViewName());
        assertEquals("Custom Error Message", modelAndView.getModel().get("errorMessage"));
    }
}
