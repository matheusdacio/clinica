package engenhariaDeSoftware.demo.pessoa;

import engenhariaDeSoftware.demo.pessoa.documento.Documento;
import engenhariaDeSoftware.demo.pessoa.endereco.Endereco;
import jakarta.persistence.Table;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "pessoa")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pessoa {

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

}
