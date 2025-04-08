package engenhariaDeSoftware.demo.pessoa;

import org.springframework.stereotype.Service;

@Service
public interface PessoaService {
    Pessoa salvar(Pessoa pessoa);
}
