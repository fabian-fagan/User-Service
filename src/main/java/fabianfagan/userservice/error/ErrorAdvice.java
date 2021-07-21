package fabianfagan.userservice.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * Controller advice for rendering a generic unexpected server error (500).
 * @author Fabian Fagan
 */
@ControllerAdvice
public class ErrorAdvice {

  @ResponseBody
  @ExceptionHandler(Error.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) //500 (unexpected)
  String errorHandler(Error er) {
    return er.getMessage();
  }
}
