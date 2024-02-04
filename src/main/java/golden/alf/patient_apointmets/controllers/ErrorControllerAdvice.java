package golden.alf.patient_apointmets.controllers;

import golden.alf.patient_apointmets.utils.exeptions.DoctorErrorException;
import golden.alf.patient_apointmets.utils.exeptions.PatientErrorException;
import golden.alf.patient_apointmets.utils.exeptions.TicketErrorException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
@RequiredArgsConstructor
public class ErrorControllerAdvice {
    private static final Logger logger = LoggerFactory.getLogger(ErrorControllerAdvice.class);

    @ExceptionHandler({DoctorErrorException.class,
            PatientErrorException.class,
            TicketErrorException.class})
    public void handleException(Exception e) {
        logger.error(e.getMessage());
    }
}
