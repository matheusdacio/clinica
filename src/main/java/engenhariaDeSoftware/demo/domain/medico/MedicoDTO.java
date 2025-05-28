package engenhariaDeSoftware.demo.domain.medico;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.Set;

public class MedicoDTO {
    
    @Data
    public static class Criar {
        @NotBlank
        private String nome;
        
        @NotBlank
        @Email
        private String email;
        
        @NotBlank
        private String senha;
        
        @NotBlank
        private String telefone;
        
        @NotBlank
        private String crm;
        
        @NotBlank
        private String especialidade;
        
        @NotNull
        @Positive
        private Integer tempoConsultaMinutos;
        
        private Set<String> horariosDisponiveis;
    }
    
    @Data
    public static class Atualizar {
        private String nome;
        private String telefone;
        private String especialidade;
        private Integer tempoConsultaMinutos;
        private Set<String> horariosDisponiveis;
    }
    
    @Data
    public static class Resposta {
        private Long id;
        private String nome;
        private String email;
        private String telefone;
        private String crm;
        private String especialidade;
        private Integer tempoConsultaMinutos;
        private Set<String> horariosDisponiveis;
        private boolean ativo;
        
        public static Resposta fromEntity(Medic medic) {
            Resposta dto = new Resposta();
            dto.setId(medic.getId());
            dto.setNome(medic.getNome());
            dto.setEmail(medic.getEmail());
            dto.setTelefone(medic.getTelefone());
            dto.setCrm(medic.getCrm());
            dto.setEspecialidade(medic.getEspecialidade());
            dto.setTempoConsultaMinutos(medic.getTempoConsultaMinutos());
            dto.setHorariosDisponiveis(medic.getHorariosDisponiveis());
            dto.setAtivo(medic.isAtivo());
            return dto;
        }
    }
} 