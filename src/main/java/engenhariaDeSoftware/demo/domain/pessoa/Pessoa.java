package engenhariaDeSoftware.demo.domain.pessoa;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import support.core.entity.EntityAudit;

@Data
@Entity
@Table(name = "pessoa")
@EqualsAndHashCode(callSuper = true)
public class Pessoa extends EntityAudit {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = false)
    private String email;
    
    @Override
    public Object getId() {
        return id;
    }
    
    @Override
    public void setId(Object id) {
        this.id = (Long) id;
    }
} 