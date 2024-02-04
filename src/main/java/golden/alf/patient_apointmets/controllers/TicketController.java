package golden.alf.patient_apointmets.controllers;

import golden.alf.patient_apointmets.model.Patient;
import golden.alf.patient_apointmets.model.Ticket;
import golden.alf.patient_apointmets.services.PatientService;
import golden.alf.patient_apointmets.services.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/rest")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;
    private final PatientService patientService;

    @PostMapping
    public List<Ticket> createSchedule(Long doctorId, LocalDateTime startTime, LocalDateTime fishTime, int receptionTimeMinutes) {
        return ticketService.createSchedule(doctorId, startTime, fishTime, receptionTimeMinutes);
    }

    @PostMapping("/take")
    public Ticket takeTicket(Long ticketId, Long patientId) {
        Ticket ticket = ticketService.getTicket(ticketId);
        Patient patient = patientService.getPatient(patientId);
        ticket.setPatient(patient);
        return ticket;
    }
}
