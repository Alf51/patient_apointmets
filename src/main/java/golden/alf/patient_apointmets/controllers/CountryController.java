package golden.alf.patient_apointmets.controllers;


import golden.alf.patient_apointmets.model.Ticket;
import golden.alf.patient_apointmets.services.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.schedule.GetSchedule;
import ru.schedule.GetScheduleResponse;

import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Endpoint
@RequiredArgsConstructor
public class CountryController {
    private static final String NAMESPACE_URI = "http://schedule.ru/";
    private final TicketService ticketService;


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getSchedule")
    @ResponsePayload
    public GetScheduleResponse getSchedule(@RequestPayload GetSchedule request) {
        LocalDateTime startDate = convertXMLGregorianCalendartoLocalDateTime(request.getStartTime());

        int durationInMin = request.getDurationInMin();
        int numberOfTickets = request.getNumberOfTickets();
        Long doctorId = request.getDoctorId();
        List<Ticket> ticketList = ticketService.createTickets(startDate, durationInMin, numberOfTickets, doctorId);

        ticketList.forEach(System.out::println);

        GetScheduleResponse response = new GetScheduleResponse();
        response.setSchedule(true);
        return response;
    }

    private LocalDateTime convertXMLGregorianCalendartoLocalDateTime(XMLGregorianCalendar dateTime) {
        return dateTime.toGregorianCalendar()
                .toZonedDateTime()
                .withZoneSameInstant(ZoneId.systemDefault())
                .toLocalDateTime();
    }
}