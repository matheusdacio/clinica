package support.core;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import jakarta.persistence.EntityManager;
import java.io.Serializable;
import java.time.OffsetDateTime;

public class CustomRepositoryImpl<E, K extends Serializable>
        extends SimpleJpaRepository<E, K> implements BaseRepository<E, K> {

    private final EntityManager entityManager;

    public CustomRepositoryImpl(JpaEntityInformation<E, K> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public void refresh(E t) {
        entityManager.refresh(t);
    }

    @Override
    public void evictAll() {
        entityManager.getEntityManagerFactory().getCache().evictAll();
    }

    @Override
    public OffsetDateTime getDataAtual() {
        return (OffsetDateTime) entityManager
                .createNativeQuery("SELECT CURRENT_TIMESTAMP")
                .getSingleResult();
    }
}
