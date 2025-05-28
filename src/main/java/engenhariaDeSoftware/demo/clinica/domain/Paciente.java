package engenhariaDeSoftware.demo.clinica.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "paciente")
@PrimaryKeyJoinColumn(name = "usuario_id")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Paciente extends Usuario implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(nullable = false, unique = true)
    private String cpf;
    
    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;
    
    @Column(name = "plano_saude")
    private String planoSaude;
    
    @Column(name = "numero_plano")
    private String numeroPlano;
    
    @Column
    private String convenio;
    
    @Column(name = "observacoes_medicas", columnDefinition = "TEXT")
    private String observacoesMedicas;
    
    public Paciente(String nome, String email, String senha, String telefone, 
                   String cpf, LocalDate dataNascimento, String planoSaude, 
                   String numeroPlano, String convenio) {
        super();
        setNome(nome);
        setEmail(email);
        setSenha(senha);
        setTelefone(telefone);
        setTipoUsuario(TipoUsuario.PACIENTE);
        setAtivo(true);
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.planoSaude = planoSaude;
        this.numeroPlano = numeroPlano;
        this.convenio = convenio;
    }
    
    public int calcularIdade() {
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }
} 