package golden.alf.patient_apointmets.services;

import golden.alf.patient_apointmets.model.Patient;
import golden.alf.patient_apointmets.repository.PatientRepository;
import golden.alf.patient_apointmets.utils.exeptions.PatientErrorException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;

    public Patient getPatient(Long id) {
        return patientRepository.findById(id).orElseThrow(() -> new PatientErrorException("пациент не найден"));
    }
}
