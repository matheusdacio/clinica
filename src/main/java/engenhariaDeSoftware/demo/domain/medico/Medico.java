package engenhariaDeSoftware.demo.domain.medico;

import jakarta.persistence.*;
import lombok.*;
import support.core.entity.EntityAudit;

import java.io.Serial;

@Entity
@Table(name = "medico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false,of = {"id"})
public abstract class Medico extends EntityAudit {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "medico", strategy = GenerationType.TABLE)
    @TableGenerator(name = "medico")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "crm")
    private String crm;

    @Enumerated(EnumType.STRING)
    @Column(name = "especialidade")
    private Especialidade especialidade;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "email")
    private String email;
}