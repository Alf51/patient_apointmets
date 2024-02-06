package golden.alf.patient_apointmets.services;


import golden.alf.patient_apointmets.model.Doctor;
import golden.alf.patient_apointmets.model.Ticket;
import golden.alf.patient_apointmets.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final DoctorService doctorService;


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

}
