package golden.alf.patient_apointmets.services;

import golden.alf.patient_apointmets.model.Doctor;
import golden.alf.patient_apointmets.model.Ticket;
import golden.alf.patient_apointmets.repository.TicketRepository;
import golden.alf.patient_apointmets.utils.exeptions.TicketErrorException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final  DoctorService doctorService;

    //TODO временная заглушка, реализовать согласно ТЗ
    @Transactional
    public List<Ticket> createSchedule(Long doctorId, LocalDateTime startTime, LocalDateTime finishTime, int receptionTimeMinutes) {
        Doctor doctor = doctorService.getDoctor(1L);
        LocalDateTime mockTime = LocalDateTime.of(2024, Month.FEBRUARY, 5, 9, 0);
        List<Ticket> ticketList = new ArrayList<>();
        ticketList.add(new Ticket(mockTime, doctor));
        ticketList.add(new Ticket(mockTime.plusHours(1), doctor));
        ticketList.add(new Ticket(mockTime.plusHours(1), doctor));

        return ticketRepository.saveAll(ticketList);
    }

    @Transactional(readOnly = true)
    public Ticket getTicket(Long id) {
        return ticketRepository.findById(id).orElseThrow(() -> new TicketErrorException("Талон не найден"));
    }
}
