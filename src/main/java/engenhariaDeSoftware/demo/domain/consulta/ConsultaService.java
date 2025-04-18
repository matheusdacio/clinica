package engenhariaDeSoftware.demo.domain.consulta;

import engenhariaDeSoftware.demo.domain.paciente.Paciente;
import org.springframework.stereotype.Service;
import support.core.service.BaseService;

public interface ConsultaService extends BaseService<Consulta, Long> {
    Consulta salvar(Consulta consulta);
}
