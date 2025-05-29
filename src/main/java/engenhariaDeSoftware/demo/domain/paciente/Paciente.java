package engenhariaDeSoftware.demo.domain.paciente;

import jakarta.persistence.Table;
import lombok.*;
import support.core.entity.EntityAudit;

import javax.persistence.*;
import java.io.Serial;
import java.time.LocalDate;

@Entity
@Table(name = "paciente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false, of = {"id"})
public abstract class Paciente extends EntityAudit {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "paciente", strategy = GenerationType.TABLE)
    @TableGenerator(name = "paciente")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "numero_plano")
    private String numeroPlano;

}

