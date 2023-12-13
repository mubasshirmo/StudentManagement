package StudentManagment.StudentManagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionalHandler {

    @ExceptionHandler(StudentException.class)
    public ResponseEntity<MyErrorDetails> handleStudentException(StudentException se, WebRequest req) {
        return handleException(se, req, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AddressException.class)
    public ResponseEntity<MyErrorDetails> handleAddressException(AddressException ae, WebRequest req) {
        return handleException(ae, req, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CourseException.class)
    public ResponseEntity<MyErrorDetails> handleCourseException(CourseException ce, WebRequest req) {
        return handleException(ce, req, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MyErrorDetails> handleGenericException(Exception e, WebRequest req) {
        return handleException(e, req, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<MyErrorDetails> handleException(Exception e, WebRequest req, HttpStatus status) {
        MyErrorDetails err = new MyErrorDetails();
        err.setDetails(req.getDescription(false));
        err.setHttpStatus(status);
        err.setTimeStamp(LocalDateTime.now());
        err.setMessage(e.getMessage());

        // Log the exception
        // logger.error("Exception occurred: {}", e.getMessage(), e);

        return new ResponseEntity<>(err, status);
    }
}
