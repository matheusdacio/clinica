package engenhariaDeSoftware.demo.domain.medico;

import engenhariaDeSoftware.demo.domain.paciente.Paciente;
import org.springframework.stereotype.Service;
import support.core.service.BaseService;

public interface MedicoService extends BaseService<Medico, Long> {
    Medico salvar(Medico medico);
}
