package engenhariaDeSoftware.demo.clinica.shared.service;

import engenhariaDeSoftware.demo.clinica.shared.exception.EntidadeNaoEncontradaException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public abstract class AbstractService<T, ID, R extends JpaRepository<T, ID>> implements BaseService<T, ID> {

    protected final R repository;

    protected AbstractService(R repository) {
        this.repository = repository;
    }

    @Override
    public T salvar(T entity) {
        return repository.save(entity);
    }

    @Override
    public void excluir(ID id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<T> buscarPorId(ID id) {
        return repository.findById(id);
    }

    @Override
    public T buscarPorIdOrElseThrow(ID id) {
        return buscarPorId(id).orElseThrow(() -> new EntidadeNaoEncontradaException("Entidade não encontrada com id: " + id));
    }

    @Override
    public Page<T> listar(Pageable pageable) {
        return repository.findAll(pageable);
    }
} 