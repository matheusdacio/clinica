package support.core.service;

import support.core.BaseRepository;
import support.core.entity.BaseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Optional;

@Slf4j
public abstract class AbstractService<E extends BaseEntity, K extends Serializable, R extends BaseRepository<E, K>>
        implements BaseService<E, K> {

    protected R repo;

    protected AbstractService(R repo) {
        this.repo = repo;
    }

    protected void validarEntity(E entity) {
    }

    @Override
    @Transactional
    public E salvar(E entity, boolean valida) {
        log.debug("Salvando objeto {} : {}", entity.getClass(), entity);
        E entitySalva = repo.save(entity);
        if (valida) validarEntity(entitySalva);
        return entitySalva;
    }

    @Override
    public E salvar(E entity) {
        return salvar(entity, true);
    }

    @Override
    public Page<E> listarPagina(Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Override
    public Page<E> listarPorParticula(String particula, Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Override
    public Page<E> listarPorSpecification(String params, Pageable pageable) {
        Specification<E> spec = fabricarSpec(params);
        return repo.findAll(spec, pageable);
    }

    @Override
    public Specification<E> fabricarSpec(String params) {
        return null;
    }

    @Override
    @Transactional
    public boolean excluir(K id) {
        if (existe(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<E> buscarPorId(K id) {
        return repo.findById(id);
    }

    @Override
    public E buscarPorIdOrElseThrow(K id) {
        return buscarPorId(id).orElseThrow(() -> 
            new RuntimeException("Registro não encontrado com ID: " + id));
    }

    @Override
    public long quantidadeTotal() {
        return repo.count();
    }

    @Override
    public boolean existe(K id) {
        return repo.existsById(id);
    }

    @Override
    public void flush() {
        repo.flush();
    }

    @Override
    public void refresh(E t) {
        repo.refresh(t);
    }

    @Override
    public OffsetDateTime getDataAtual() {
        return repo.getDataAtual();
    }

    @Override
    public void evictAll() {
        repo.evictAll();
    }
} 