package engenhariaDeSoftware.demo.paciente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacienteServiceImpl implements PacienteRepository {

    @Autowired
    public PacienteServiceImpl(PacienteRepository repo, Paciente paciente) {
        super();
    }

    @Override
    public Paciente salvar(Paciente entity) {
        entity = repo.salvar(entity);
        return entity;
    }
}
