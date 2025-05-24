package engenhariaDeSoftware.demo.clinica.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
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
    
    @Column(name = "tempo_consulta_minutos", nullable = false)
    private Integer tempoConsultaMinutos;
    
    public Medico(String nome, String email, String senha, String telefone, 
                 String crm, String especialidade, Integer tempoConsultaMinutos) {
        super();
        setNome(nome);
        setEmail(email);
        setSenha(senha);
        setTelefone(telefone);
        setTipoUsuario(TipoUsuario.MEDICO);
        setAtivo(true);
        this.crm = crm;
        this.especialidade = especialidade;
        this.tempoConsultaMinutos = tempoConsultaMinutos;
    }
    
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