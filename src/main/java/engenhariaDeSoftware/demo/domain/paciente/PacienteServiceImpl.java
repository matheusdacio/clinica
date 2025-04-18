package engenhariaDeSoftware.demo.domain.paciente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import support.core.service.AbstractService;

@Service
public class PacienteServiceImpl extends AbstractService<Paciente, Long, PacienteRepository> implements PacienteService {

    @Autowired
    public PacienteServiceImpl(PacienteRepository repo) {
        super(repo);
    }
}
