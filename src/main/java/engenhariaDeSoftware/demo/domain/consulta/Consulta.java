package engenhariaDeSoftware.demo.domain.consulta;

import engenhariaDeSoftware.demo.domain.paciente.Pacient;
import engenhariaDeSoftware.demo.domain.medico.Medic;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "consulta")
public class Consulta implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Pacient pacient;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id", nullable = false)
    private Medic medic;
    
    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;
    
    @Column(name = "observacoes", length = 1000)
    private String observacoes;
    
    @Column(name = "motivo_cancelamento", length = 500)
    private String motivoCancelamento;
    
    @Column(name = "data_confirmacao")
    private LocalDateTime dataConfirmacao;
    
    @Column(name = "data_realizacao")
    private LocalDateTime dataRealizacao;
    
    @Column(name = "data_cancelamento")
    private LocalDateTime dataCancelamento;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusConsulta status;
    
    public Consulta(Pacient pacient, Medic medic, LocalDateTime dataHora) {
        this.pacient = pacient;
        this.medic = medic;
        this.dataHora = dataHora;
        this.status = StatusConsulta.AGENDADA;
    }
    
    public void confirmar() {

        if (this.status == StatusConsulta.AGENDADA) {
            this.status = StatusConsulta.CONFIRMADA;
            this.dataConfirmacao = LocalDateTime.now();
        } else {
            throw new IllegalStateException("Apenas consultas agendadas podem ser confirmadas");
        }
    }
    
    public void realizar() {
        if (this.status == StatusConsulta.CONFIRMADA) {
            this.status = StatusConsulta.REALIZADA;
            this.dataRealizacao = LocalDateTime.now();
        } else {
            throw new IllegalStateException("Apenas consultas confirmadas podem ser realizadas");
        }
    }
    
    public void cancelar(String motivo) {
        if (this.status != StatusConsulta.REALIZADA) {
            this.status = StatusConsulta.CANCELADA;
            this.motivoCancelamento = motivo;
            this.dataCancelamento = LocalDateTime.now();
        } else {
            throw new IllegalStateException("Consultas já realizadas não podem ser canceladas");
        }
    }
    
    public boolean podeSerCancelada() {
        return this.status != StatusConsulta.REALIZADA && 
               this.status != StatusConsulta.CANCELADA;
    }
    
    public boolean podeSerConfirmada() {
        return this.status == StatusConsulta.AGENDADA;
    }
    
    public boolean podeSerRealizada() {
        return this.status == StatusConsulta.CONFIRMADA;
    }
} 