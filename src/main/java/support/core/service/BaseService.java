package support.core.service;

import support.core.entity.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Optional;

public interface BaseService<T extends BaseEntity, K extends Serializable> {

    T salvar(T entity, boolean valida);

    T salvar(T entity);

    Page<T> listarPagina(Pageable pageable);

    Page<T> listarPorParticula(String particula, Pageable pageable);

    Page<T> listarPorSpecification(String params, Pageable pageable);

    Specification<T> fabricarSpec(String params);

    boolean excluir(K id);

    Optional<T> buscarPorId(K id);

    T buscarPorIdOrElseThrow(K id);

    long quantidadeTotal();

    boolean existe(K id);

    void flush();

    void refresh(T t);

    OffsetDateTime getDataAtual();

    void evictAll();
} 