package support.core;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.io.Serializable;
import java.time.OffsetDateTime;

public interface BaseRepository<E, K extends Serializable>
        extends JpaRepository<E, K>, JpaSpecificationExecutor<E> {

    void refresh(E t);
    void evictAll();
    OffsetDateTime getDataAtual();
}