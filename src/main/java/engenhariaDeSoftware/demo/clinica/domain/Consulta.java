package engenhariaDeSoftware.demo.clinica.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "consultas")
public class Consulta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;
    
    @ManyToOne
    @JoinColumn(name = "medico_id", nullable = false)
    private Medico medico;
    
    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusConsulta status;
    
    @Column(length = 1000)
    private String observacoes;
    
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
        }
    }
    
    public void realizar() {
        if (this.status == StatusConsulta.CONFIRMADA) {
            this.status = StatusConsulta.REALIZADA;
        }
    }
    
    public void cancelar() {
        if (this.status != StatusConsulta.REALIZADA) {
            this.status = StatusConsulta.CANCELADA;
        }
    }
} 