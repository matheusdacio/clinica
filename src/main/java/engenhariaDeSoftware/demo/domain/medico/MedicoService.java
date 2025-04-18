package engenhariaDeSoftware.demo.domain.medico;

import engenhariaDeSoftware.demo.domain.paciente.Paciente;
import org.springframework.stereotype.Service;

@Service
public interface MedicoService {
    Paciente salvar(Paciente paciente);
}
