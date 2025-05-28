package engenhariaDeSoftware.demo.clinica.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "medico")
@PrimaryKeyJoinColumn(name = "usuario_id")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Medico extends Usuario implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(nullable = false, unique = true)
    private String crm;
    
    @Column(nullable = false)
    private String especialidade;
    
    @Column(name = "tempo_consulta_minutos", nullable = false)
    private Integer tempoConsultaMinutos;
    
    @ElementCollection
    @CollectionTable(name = "medico_horarios", joinColumns = @JoinColumn(name = "medico_id"))
    @Column(name = "horario")
    private Set<String> horariosDisponiveis = new HashSet<>();
    
    public void adicionarHorario(String horario) {
        this.horariosDisponiveis.add(horario);
    }
    
    public void removerHorario(String horario) {
        this.horariosDisponiveis.remove(horario);
    }
    
    public boolean estaDisponivel(String horario) {
        return this.horariosDisponiveis.contains(horario);
    }
} 