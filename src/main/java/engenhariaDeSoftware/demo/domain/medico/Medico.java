package engenhariaDeSoftware.demo.domain.medico;

import jakarta.persistence.Table;
import lombok.*;
import support.core.entity.EntityAudit;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@Table(name = "medico")
@Builder
public class Medico extends EntityAudit {
    @Override
    public Object getId() {
        return null;
    }

    @Override
    public void setId(Object id) {

    }
}
