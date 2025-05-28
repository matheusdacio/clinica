package engenhariaDeSoftware.demo.clinica.domain;

import engenhariaDeSoftware.demo.clinica.shared.domain.EntityAudit;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "consulta")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Consulta extends EntityAudit implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id", nullable = false)
    private Medico medico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    @Column(name = "motivo", nullable = false)
    private String motivo;

    @Column(name = "observacoes", columnDefinition = "TEXT")
    private String observacoes;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusConsulta status;

    @Column(name = "motivo_cancelamento")
    private String motivoCancelamento;

    @Column(name = "data_confirmacao")
    private LocalDateTime dataConfirmacao;

    @Column(name = "data_realizacao")
    private LocalDateTime dataRealizacao;

    @Column(name = "data_cancelamento")
    private LocalDateTime dataCancelamento;

    public enum StatusConsulta {
        AGENDADA,
        CONFIRMADA,
        REALIZADA,
        CANCELADA
    }

    public void confirmar() {
        if (!podeSerConfirmada()) {
            throw new IllegalStateException("Consulta não pode ser confirmada no estado atual");
        }
        this.status = StatusConsulta.CONFIRMADA;
        this.dataConfirmacao = LocalDateTime.now();
    }

    public void realizar() {
        if (!podeSerRealizada()) {
            throw new IllegalStateException("Consulta não pode ser realizada no estado atual");
        }
        this.status = StatusConsulta.REALIZADA;
        this.dataRealizacao = LocalDateTime.now();
    }

    public void cancelar(String motivo) {
        if (!podeSerCancelada()) {
            throw new IllegalStateException("Consulta não pode ser cancelada no estado atual");
        }
        this.status = StatusConsulta.CANCELADA;
        this.motivoCancelamento = motivo;
        this.dataCancelamento = LocalDateTime.now();
    }

    public boolean podeSerCancelada() {
        return status == StatusConsulta.AGENDADA || status == StatusConsulta.CONFIRMADA;
    }

    public boolean podeSerConfirmada() {
        return status == StatusConsulta.AGENDADA;
    }

    public boolean podeSerRealizada() {
        return status == StatusConsulta.CONFIRMADA;
    }
} 