package golden.alf.patient_apointmets.dto;

import golden.alf.patient_apointmets.model.Doctor;
import golden.alf.patient_apointmets.model.Patient;
import jakarta.persistence.*;
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
    private Long id;
    private LocalDateTime startTime;
    private Long doctorId;
    private Long patientId;
}
