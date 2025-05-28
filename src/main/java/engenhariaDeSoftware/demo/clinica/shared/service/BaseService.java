package engenhariaDeSoftware.demo.clinica.shared.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BaseService<T, ID> {
    T salvar(T entity);
    void excluir(ID id);
    Optional<T> buscarPorId(ID id);
    T buscarPorIdOrElseThrow(ID id);
    Page<T> listar(Pageable pageable);
} 