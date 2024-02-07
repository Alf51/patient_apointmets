package golden.alf.patient_apointmets.controllers;

import golden.alf.patient_apointmets.utils.erorsHandler.ErrorHandler;
import golden.alf.patient_apointmets.utils.erorsHandler.ErrorResponse;
import golden.alf.patient_apointmets.utils.exeptions.DoctorErrorException;
import golden.alf.patient_apointmets.utils.exeptions.PatientErrorException;
import golden.alf.patient_apointmets.utils.exeptions.TicketErrorException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
@RequiredArgsConstructor
public class ErrorControllerAdvice {
    private final ErrorHandler errorHandler;
    private static final Logger logger = LoggerFactory.getLogger(ErrorControllerAdvice.class);

    @ExceptionHandler({DoctorErrorException.class,
            PatientErrorException.class,
            TicketErrorException.class})
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidExceptionException(MethodArgumentNotValidException e) {
        String errorMessage = errorHandler.getErrorMessage(e.getBindingResult());
        ErrorResponse errorResponse = new ErrorResponse(errorMessage, System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleServerException(Exception e) {
        logger.error(e.getMessage());
        String errorMessage = errorHandler.getErrorMessage("server.error");
        ErrorResponse errorResponse = new ErrorResponse(errorMessage, System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
