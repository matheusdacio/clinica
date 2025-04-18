package engenhariaDeSoftware.demo.domain.medico;

import engenhariaDeSoftware.demo.domain.paciente.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import support.core.service.AbstractService;

@Service
public class MedicoServiceImpl extends AbstractService<Medico, Long, MedicoRepository> implements MedicoService {

    @Autowired
    public MedicoServiceImpl(MedicoRepository repo) {
        super(repo);
    }
}
