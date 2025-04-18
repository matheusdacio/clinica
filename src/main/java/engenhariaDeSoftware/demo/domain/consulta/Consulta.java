package engenhariaDeSoftware.demo.domain.consulta;

import jakarta.persistence.Table;
import lombok.*;
import support.core.entity.EntityAudit;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@Table(name = "consulta")
@Builder
public class Consulta extends EntityAudit {
    @Override
    public Object getId() {
        return null;
    }

    @Override
    public void setId(Object id) {

    }
}
