package golden.alf.patient_apointmets.services;

import golden.alf.patient_apointmets.model.Doctor;
import golden.alf.patient_apointmets.repository.DoctorRepository;
import golden.alf.patient_apointmets.utils.erorsHandler.ErrorHandler;
import golden.alf.patient_apointmets.utils.exeptions.DoctorErrorException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final ErrorHandler errorHandler;

    public Doctor getDoctor(Long id) {
        return doctorRepository.findById(id).orElseThrow(() -> new DoctorErrorException(errorHandler
                .getErrorMessage("doctor.not-found")));
    }
}
