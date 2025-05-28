package engenhariaDeSoftware.demo.domain.paciente;

import engenhariaDeSoftware.demo.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "pacientes")
public class Pacient extends Usuario {
    
    @Column(nullable = false, unique = true)
    private String cpf;
    
    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;
    
    @Column(name = "plano_saude")
    private String planoSaude;
    
    @Column(name = "numero_plano")
    private String numeroPlano;
    
    @Column(name = "convenio")
    private String convenio;
    
    @Column(name = "observacoes_medicas", length = 1000)
    private String observacoesMedicas;
    
    public Pacient(String nome, String email, String senha, String telefone,
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
        return LocalDate.now().getYear() - dataNascimento.getYear();
    }
} 