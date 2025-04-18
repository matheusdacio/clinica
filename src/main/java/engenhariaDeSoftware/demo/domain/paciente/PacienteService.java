package engenhariaDeSoftware.demo.domain.paciente;

import org.springframework.stereotype.Service;

@Service
public interface PacienteService {
    Paciente salvar(Paciente paciente);
}
