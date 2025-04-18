package engenhariaDeSoftware.demo.domain.pessoa;

import support.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PessoaServiceImpl extends AbstractService<Pessoa, Long, PessoaRepository> implements PessoaService {
    
    public PessoaServiceImpl(PessoaRepository repository) {
        super(repository);
    }

    @Override
    public Pessoa buscarPorEmail(String email) {
        return repo.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Pessoa não encontrada com email: " + email));
    }

    @Override
    protected void validarEntity(Pessoa entity) {
        // Implementar validações específicas para Pessoa
        if (entity.getNome() == null || entity.getNome().trim().isEmpty()) {
            throw new RuntimeException("Nome da pessoa é obrigatório");
        }
        if (entity.getEmail() == null || entity.getEmail().trim().isEmpty()) {
            throw new RuntimeException("Email da pessoa é obrigatório");
        }
    }
} 