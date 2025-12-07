package xs.badyanov.onec_logerr.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class AppServiceException extends RuntimeException {

    public AppServiceException() {
        super();
    }

    public AppServiceException(String message) {
        super(message);
    }

    public AppServiceException(String message, Throwable cause) {
        super(message, cause);
    }

}
