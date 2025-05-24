package engenhariaDeSoftware.demo.clinica.domain.model;

import engenhariaDeSoftware.demo.clinica.shared.model.EntityAudit;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "consultas")
public class Consulta extends EntityAudit {
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id", nullable = false)
    private Medico medico;
    
    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusConsulta status;
    
    @Column(length = 1000)
    private String observacoes;
    
    @Column(name = "motivo_cancelamento", length = 500)
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
    
    public Consulta(Paciente paciente, Medico medico, LocalDateTime dataHora) {
        this.paciente = paciente;
        this.medico = medico;
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