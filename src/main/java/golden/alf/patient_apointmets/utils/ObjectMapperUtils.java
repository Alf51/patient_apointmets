package golden.alf.patient_apointmets.utils;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ObjectMapperUtils {

    @Bean
    public ModelMapper modelMapper () {
        return new ModelMapper();
    }
}
