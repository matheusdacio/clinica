package engenhariaDeSoftware.demo.domain.consulta;

import engenhariaDeSoftware.demo.domain.paciente.Paciente;
import org.springframework.stereotype.Service;

@Service
public interface ConsultaService {
    Paciente salvar(Paciente paciente);
}
