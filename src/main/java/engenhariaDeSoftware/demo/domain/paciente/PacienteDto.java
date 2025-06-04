package engenhariaDeSoftware.demo.domain.paciente;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PacienteDto {
    private Long id;
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private String numeroPlano;
    private String email;
    private String telefone;
    private Boolean ativo;

    public PacienteDto(Paciente paciente) {
        this.id = paciente.getId();
        this.nome = paciente.getNome();
        this.cpf = paciente.getCpf();
        this.dataNascimento = paciente.getDataNascimento();
        this.numeroPlano = paciente.getNumeroPlano();
        this.email = paciente.getEmail();
        this.telefone = paciente.getTelefone();
        this.ativo = paciente.getAtivo();
    }

    public Paciente toEntity() {
        return Paciente.builder()
                .id(this.id)
                .nome(this.nome)
                .cpf(this.cpf)
                .dataNascimento(this.dataNascimento)
                .numeroPlano(this.numeroPlano)
                .email(this.email)
                .telefone(this.telefone)
                .ativo(this.ativo)
                .build();
    }

    public void atualizar(Paciente paciente) {
        if (this.nome != null) paciente.setNome(this.nome);
        if (this.cpf != null) paciente.setCpf(this.cpf);
        if (this.dataNascimento != null) paciente.setDataNascimento(this.dataNascimento);
        if (this.numeroPlano != null) paciente.setNumeroPlano(this.numeroPlano);
        if (this.email != null) paciente.setEmail(this.email);
        if (this.telefone != null) paciente.setTelefone(this.telefone);
        if (this.ativo != null) paciente.setAtivo(this.ativo);
    }
}