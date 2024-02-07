package golden.alf.patient_apointmets.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto {
    @NotNull(message = "{ticket.id.not-null}")
    @Min(value = 0, message = "{id.positive-value}")
    private Long id;

    @NotNull(message = "{date.not-null}")
    private LocalDateTime startTime;

    private Long doctorId;
    private Long patientId;
}
