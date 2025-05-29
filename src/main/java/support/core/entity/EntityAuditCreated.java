package support.core.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class EntityAuditCreated implements BaseEntity, Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "created_date",
            nullable = false,
            updatable = false)
    @CreatedDate
    private OffsetDateTime createdDate;
    @Column(name = "created_by",
            nullable = false,
            updatable = false)
    @CreatedBy
    private String createdBy;

    public OffsetDateTime getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(OffsetDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String toString() {
        return Objects.nonNull(this.getId()) ? this.getClass().getSimpleName().concat("[id=").concat(this.getId().toString()).concat("]") : super.toString();
    }

    protected EntityAuditCreated(final EntityAuditCreatedBuilder<?, ?> b) {
        this.createdDate = b.createdDate;
        this.createdBy = b.createdBy;
    }

    public EntityAuditCreated() {
    }

    public EntityAuditCreated(final OffsetDateTime createdDate, final String createdBy) {
        this.createdDate = createdDate;
        this.createdBy = createdBy;
    }

    public abstract static class EntityAuditCreatedBuilder<C extends EntityAuditCreated, B extends EntityAuditCreatedBuilder<C, B>> {
        private OffsetDateTime createdDate;
        private String createdBy;

        protected abstract B self();

        public abstract C build();

        public B createdDate(final OffsetDateTime createdDate) {
            this.createdDate = createdDate;
            return (B)this.self();
        }

        public B createdBy(final String createdBy) {
            this.createdBy = createdBy;
            return (B)this.self();
        }

        public String toString() {
            return "EntityAuditCreated.EntityAuditCreatedBuilder(createdDate=" + this.createdDate + ", createdBy=" + this.createdBy + ")";
        }
    }
} 