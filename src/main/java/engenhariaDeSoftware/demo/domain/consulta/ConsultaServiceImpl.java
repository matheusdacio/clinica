package engenhariaDeSoftware.demo.domain.consulta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import support.core.service.AbstractService;

@Service
public class ConsultaServiceImpl extends AbstractService<Consulta, Long, ConsultaRepository> implements ConsultaService {

    @Autowired
    public ConsultaServiceImpl(ConsultaRepository repo) {
        super(repo);
    }

}
