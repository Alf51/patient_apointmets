package golden.alf.patient_apointmets.services;

import golden.alf.patient_apointmets.model.Patient;
import golden.alf.patient_apointmets.repository.PatientRepository;
import golden.alf.patient_apointmets.utils.erorsHandler.ErrorHandler;
import golden.alf.patient_apointmets.utils.exeptions.PatientErrorException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;
    private final ErrorHandler errorHandler;

    public Patient getPatient(Long id) {
        return patientRepository.findById(id).orElseThrow(() -> new PatientErrorException(errorHandler
                .getErrorMessage("patient.not-found")));
    }
}
