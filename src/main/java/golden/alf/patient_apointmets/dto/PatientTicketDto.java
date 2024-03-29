package golden.alf.patient_apointmets.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientTicketDto {
    @NotNull(message = "{patient.id.not-null}")
    Long patientId;

    @NotNull(message = "{ticket.id.not-null}")
    Long ticketId;
}
