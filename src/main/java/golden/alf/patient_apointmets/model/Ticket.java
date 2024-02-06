package golden.alf.patient_apointmets.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
@Getter
@Setter
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;

    public Ticket(LocalDateTime startTime, Doctor doctor) {
        this.startTime = startTime;
        this.doctor = doctor;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", startTime=" + startTime +
                '}';
    }
}
