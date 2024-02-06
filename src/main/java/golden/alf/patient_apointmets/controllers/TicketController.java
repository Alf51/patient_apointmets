package golden.alf.patient_apointmets.controllers;

import golden.alf.patient_apointmets.dto.PatientTicketDto;
import golden.alf.patient_apointmets.services.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;

    @PostMapping("/book")
    public ResponseEntity<HttpStatus> bookTicket(@RequestBody PatientTicketDto patientTicket) {
        ticketService.bookTicket(patientTicket.getPatientId(), patientTicket.getTicketId());
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
