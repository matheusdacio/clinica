package engenhariaDeSoftware.demo.domain.paciente;

import support.core.service.BaseService;

public interface PacienteService extends BaseService<Paciente, Long> {
    Paciente salvar(Paciente paciente);
}
