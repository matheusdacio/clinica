package engenhariaDeSoftware.demo.domain.consulta;

import support.core.service.BaseService;

public interface ConsultaService extends BaseService<Consulta, Long> {
    Consulta salvar(Consulta consulta);
}
