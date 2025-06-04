package engenhariaDeSoftware.demo.domain.medico;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicoDto {
    private Long id;
    private String nome;
    private String crm;
    private String especialidade;
    private String email;
    private String telefone;
    private Boolean ativo;

    public MedicoDto(Medico medico) {
        this.id = medico.getId();
        this.nome = medico.getNome();
        this.crm = medico.getCrm();
        this.especialidade = medico.getEspecialidade();
        this.email = medico.getEmail();
        this.telefone = medico.getTelefone();
        this.ativo = medico.getAtivo();
    }

    public Medico toEntity() {
        return Medico.builder()
                .id(this.id)
                .nome(this.nome)
                .crm(this.crm)
                .especialidade(this.especialidade)
                .email(this.email)
                .telefone(this.telefone)
                .ativo(this.ativo)
                .build();
    }

    public void atualizar(Medico medico) {
        if (this.nome != null) medico.setNome(this.nome);
        if (this.crm != null) medico.setCrm(this.crm);
        if (this.especialidade != null) medico.setEspecialidade(this.especialidade);
        if (this.email != null) medico.setEmail(this.email);
        if (this.telefone != null) medico.setTelefone(this.telefone);
        if (this.ativo != null) medico.setAtivo(this.ativo);
    }
}