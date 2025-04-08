package engenhariaDeSoftware.demo.pessoa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaServiceImpl implements PessoaRepository {

    @Autowired
    public PessoaServiceImpl(PessoaRepository repo, Pessoa pessoa) {
        super();
    }

    @Override
    public Pessoa salvar(Pessoa entity) {
        entity = repo.salvar(entity);
        return entity;
    }
}
