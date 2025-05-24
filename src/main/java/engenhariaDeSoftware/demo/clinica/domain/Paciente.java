package engenhariaDeSoftware.demo.clinica.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "pacientes")
public class Paciente extends Usuario {
    
    @Column(nullable = false, unique = true)
    private String cpf;
    
    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;
    
    @Column(name = "plano_saude")
    private String planoSaude;
    
    @Column(name = "numero_plano")
    private String numeroPlano;
    
    public Paciente(String nome, String email, String senha, String telefone, 
                   String cpf, LocalDate dataNascimento, String planoSaude, String numeroPlano) {
        super(nome, email, senha, telefone, TipoUsuario.PACIENTE);
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.planoSaude = planoSaude;
        this.numeroPlano = numeroPlano;
    }
} 