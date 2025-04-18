package engenhariaDeSoftware.demo.domain.medico;

import engenhariaDeSoftware.demo.domain.paciente.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicoServiceImpl implements MedicoRepository {

    @Autowired
    public MedicoServiceImpl(MedicoRepository repo, Paciente paciente) {
        super();
    }

    @Override
    public Paciente salvar(Paciente entity) {
        entity = repo.salvar(entity);
        return entity;
    }
}
