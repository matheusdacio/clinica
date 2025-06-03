package engenhariaDeSoftware.demo.domain.consulta;

import engenhariaDeSoftware.demo.domain.medico.Medico;
import engenhariaDeSoftware.demo.domain.paciente.Paciente;
import jakarta.persistence.*;
import lombok.*;
import support.core.entity.EntityAudit;

import java.io.Serial;
import java.time.LocalDateTime;

@Entity
@Table(name = "consulta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false, of = {"id"})
public class Consulta extends EntityAudit {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "consulta", strategy = GenerationType.TABLE)
    @TableGenerator(name = "consulta")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id")
    private Medico medico;
    
    @Column(name = "data_hora")
    private LocalDateTime dataHora;
    
    @Column(name = "observacoes")
    private String observacoes;
    
    @Column(name = "motivo_cancelamento")
    private String motivoCancelamento;
    
    @Column(name = "data_confirmacao")
    private LocalDateTime dataConfirmacao;
    
    @Column(name = "data_realizacao")
    private LocalDateTime dataRealizacao;
    
    @Column(name = "data_cancelamento")
    private LocalDateTime dataCancelamento;

    @Column(name = "obs")
    private String obs;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusConsulta status;
    
    public Consulta(Paciente pacient, Medico medic, LocalDateTime dataHora) {
        this.paciente = pacient;
        this.medico = medic;
        this.dataHora = dataHora;
        this.status = StatusConsulta.AGENDADA;
    }
}