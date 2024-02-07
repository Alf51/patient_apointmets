package golden.alf.patient_apointmets.services;

import golden.alf.patient_apointmets.model.Doctor;
import golden.alf.patient_apointmets.model.Patient;
import golden.alf.patient_apointmets.model.Ticket;
import golden.alf.patient_apointmets.repository.TicketRepository;
import golden.alf.patient_apointmets.utils.erorsHandler.ErrorHandler;
import golden.alf.patient_apointmets.utils.exeptions.TicketErrorException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final DoctorService doctorService;
    private final PatientService patientService;
    private final ErrorHandler errorHandler;

    @Transactional
    public Ticket bookTicket(Long patientId, Long ticketId) {
        Patient patient = patientService.getPatient(patientId);
        Ticket ticket = getTicket(ticketId);
        ticket.setPatient(patient);
        return ticket;
    }

    @Transactional(readOnly = true)
    public Ticket getTicket(Long ticketId) {
        return ticketRepository.findById(ticketId).orElseThrow(() -> new TicketErrorException(errorHandler
                .getErrorMessage("ticket.not-found")));
    }

    @Transactional
    public Ticket createTicket(LocalDateTime startTime, int durationInMinute, Long doctorId) {
        Doctor doctor = doctorService.getDoctor(doctorId);

        if (startTime.getHour() < 9) {
            startTime = startTime.withHour(9);
            startTime = startTime.withMinute(0);
        }

        Ticket ticket = new Ticket(startTime, doctor);
        doctor.addTicket(ticket);
        ticketRepository.save(ticket);
        return ticket;
    }

    @Transactional
    public List<Ticket> createTickets(LocalDateTime startTime, int durationInMinute, int numberOfTickets, Long doctorId) {
        List<Ticket> ticketList = new ArrayList<>(numberOfTickets);
        Doctor doctor = doctorService.getDoctor(doctorId);

        if (startTime.getHour() < 9) {
            startTime = startTime.withHour(9);
            startTime = startTime.withMinute(0);
        }

        for (int i = 1; i <= numberOfTickets; i++) {
            if (startTime.getHour() >= 18) {
                startTime = startTime.plusDays(1);
                startTime = startTime.withHour(9);
                startTime = startTime.withMinute(0);
            }
            Ticket ticket = new Ticket(startTime, doctor);
            startTime = startTime.plusMinutes(durationInMinute);
            ticketList.add(ticket);
        }
        doctor.addTicket(ticketList);

        return ticketRepository.saveAll(ticketList);
    }

    public List<Ticket> getAllPatientTicket(Long patientId) {
        Patient patient = patientService.getPatient(patientId);
        return patient.getTicketList();
    }

    public List<Ticket> getFreeDoctorTicketsOnDate(Long doctorId, LocalDate date) {
        Doctor doctor = doctorService.getDoctor(doctorId);
        return doctor.getTicketList().stream()
                .filter(ticket -> ticket.getPatient() == null)
                .filter(ticket -> ticket.getStartTime().toLocalDate().isEqual(date))
                .toList();
    }
}
