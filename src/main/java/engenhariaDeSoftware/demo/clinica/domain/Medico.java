package engenhariaDeSoftware.demo.clinica.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "medicos")
public class Medico extends Usuario {
    
    @Column(nullable = false, unique = true)
    private String crm;
    
    @Column(name = "especialidade", nullable = false)
    private String especialidade;
    
    @ElementCollection
    @CollectionTable(name = "medico_horarios", joinColumns = @JoinColumn(name = "medico_id"))
    @Column(name = "horario")
    private Set<String> horariosDisponiveis = new HashSet<>();
    
    public Medico(String nome, String email, String senha, String telefone, 
                 String crm, String especialidade) {
        super(nome, email, senha, telefone, TipoUsuario.MEDICO);
        this.crm = crm;
        this.especialidade = especialidade;
    }
    
    public void adicionarHorario(String horario) {
        this.horariosDisponiveis.add(horario);
    }
    
    public void removerHorario(String horario) {
        this.horariosDisponiveis.remove(horario);
    }
} 