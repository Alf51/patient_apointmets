package golden.alf.patient_apointmets.controllers;

import golden.alf.patient_apointmets.dto.PatientTicketDto;
import golden.alf.patient_apointmets.dto.TicketDto;
import golden.alf.patient_apointmets.dto.TicketOnDate;
import golden.alf.patient_apointmets.model.Ticket;
import golden.alf.patient_apointmets.services.TicketService;
import golden.alf.patient_apointmets.utils.erorsHandler.ErrorHandler;
import golden.alf.patient_apointmets.utils.exeptions.TicketErrorException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;
    private final ModelMapper modelMapper;
    private final ErrorHandler errorHandler;

    @PostMapping("/book")
    public ResponseEntity<HttpStatus> bookTicket(@RequestBody @Valid PatientTicketDto patientTicket,
                                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new TicketErrorException(errorHandler.getErrorMessage(bindingResult));
        }
        ticketService.bookTicket(patientTicket.getPatientId(), patientTicket.getTicketId());
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{patientId}")
    public List<TicketDto> getAllPatientTicket(@PathVariable Long patientId) {
        List<Ticket> ticketList = ticketService.getAllPatientTicket(patientId);
        return convertTicketListToTicketDtoList(ticketList);
    }

    @GetMapping("/free/{doctorId}")
    public List<TicketDto> getFreeTicketsCurrentDoctor(@PathVariable Long doctorId, @RequestBody @Valid TicketOnDate ticket) {
        List<Ticket> ticketList = ticketService.getFreeDoctorTicketsOnDate(doctorId, ticket.getDate());
        return convertTicketListToTicketDtoList(ticketList);
    }

    private List<TicketDto> convertTicketListToTicketDtoList(List<Ticket> ticketList) {
        Type listType = new TypeToken<List<TicketDto>>() {
        }.getType();
        return modelMapper.map(ticketList, listType);
    }
}
