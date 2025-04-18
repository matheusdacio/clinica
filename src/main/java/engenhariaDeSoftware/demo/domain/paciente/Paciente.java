package engenhariaDeSoftware.demo.domain.paciente;

import engenhariaDeSoftware.demo.domain.Documento;
import engenhariaDeSoftware.demo.domain.endereco.Endereco;
import jakarta.persistence.Table;
import lombok.*;
import support.core.entity.EntityAudit;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "paciente")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Paciente extends EntityAudit {

    @Id
    @GeneratedValue(generator = "conta", strategy = GenerationType.TABLE)
    @TableGenerator(name= "conta")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @JoinColumn(name = "documento_principal_id")
    @OneToOne
    private Documento documentoPrincipal;

    @JoinColumn(name = "endereco_principal_id")
    @OneToOne
    private Endereco enderecoPrincipal;

    @Override
    public Object getId() {
        return null;
    }

    @Override
    public void setId(Object id) {

    }
}
