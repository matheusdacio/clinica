package engenhariaDeSoftware.demo.domain.consulta;

import engenhariaDeSoftware.demo.domain.paciente.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultaServiceImpl implements ConsultaRepository {

    @Autowired
    public ConsultaServiceImpl(ConsultaRepository repo, Paciente paciente) {
        super();
    }

    @Override
    public Paciente salvar(Paciente entity) {
        entity = repo.salvar(entity);
        return entity;
    }
}
