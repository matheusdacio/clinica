package support.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Access;
import javax.persistence.AccessType;
import java.io.Serializable;
import java.time.OffsetDateTime;


@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public abstract class EntityAudit extends EntityAuditCreated implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(
            name = "modified_date"
    )
    @LastModifiedDate
    private OffsetDateTime modifiedDate;
    @Column(
            name = "modified_by"
    )
    @LastModifiedBy
    private String modifiedBy;
    @Version
    private long version;

    public OffsetDateTime getModifiedDate() {
        return this.modifiedDate;
    }

    @Access(AccessType.PROPERTY)
    public void setModifiedDate(OffsetDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getModifiedBy() {
        return this.modifiedBy;
    }

    @Access(AccessType.PROPERTY)
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public long getVersion() {
        return this.version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    protected EntityAudit(final EntityAuditBuilder<?, ?> b) {
        super(b);
        this.modifiedDate = b.modifiedDate;
        this.modifiedBy = b.modifiedBy;
        this.version = b.version;
    }

    public EntityAudit() {
    }

    public EntityAudit(final OffsetDateTime modifiedDate, final String modifiedBy, final long version) {
        this.modifiedDate = modifiedDate;
        this.modifiedBy = modifiedBy;
        this.version = version;
    }

    public abstract static class EntityAuditBuilder<C extends EntityAudit, B extends EntityAuditBuilder<C, B>> extends EntityAuditCreated.EntityAuditCreatedBuilder<C, B> {
        private OffsetDateTime modifiedDate;
        private String modifiedBy;
        private long version;

        protected abstract B self();

        public abstract C build();

        public B modifiedDate(final OffsetDateTime modifiedDate) {
            this.modifiedDate = modifiedDate;
            return (B)this.self();
        }

        public B modifiedBy(final String modifiedBy) {
            this.modifiedBy = modifiedBy;
            return (B)this.self();
        }

        public B version(final long version) {
            this.version = version;
            return (B)this.self();
        }

        public String toString() {
            String var10000 = super.toString();
            return "EntityAudit.EntityAuditBuilder(super=" + var10000 + ", modifiedDate=" + this.modifiedDate + ", modifiedBy=" + this.modifiedBy + ", version=" + this.version + ")";
        }
    }
}