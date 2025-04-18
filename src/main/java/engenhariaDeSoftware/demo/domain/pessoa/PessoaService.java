package engenhariaDeSoftware.demo.domain.pessoa;

import support.core.service.BaseService;

public interface PessoaService extends BaseService<Pessoa, Long> {
    // Métodos específicos para Pessoa podem ser adicionados aqui
    Pessoa buscarPorEmail(String email);
} 