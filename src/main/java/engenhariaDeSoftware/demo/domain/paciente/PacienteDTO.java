package engenhariaDeSoftware.demo.domain.paciente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;

import java.time.LocalDate;

public class PacienteDTO {
    
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
        private String cpf;
        
        @NotNull
        @Past
        private LocalDate dataNascimento;
        
        private String planoSaude;
        private String numeroPlano;
        private String convenio;
        private String observacoesMedicas;
    }
    
    @Data
    public static class Atualizar {
        private String nome;
        private String telefone;
        private String planoSaude;
        private String numeroPlano;
        private String convenio;
        private String observacoesMedicas;
    }
    
    @Data
    public static class Resposta {
        private Long id;
        private String nome;
        private String email;
        private String telefone;
        private String cpf;
        private LocalDate dataNascimento;
        private String planoSaude;
        private String numeroPlano;
        private String convenio;
        private String observacoesMedicas;
        private boolean ativo;
        
        public static Resposta fromEntity(Pacient pacient) {
            Resposta dto = new Resposta();
            dto.setId(pacient.getId());
            dto.setNome(pacient.getNome());
            dto.setEmail(pacient.getEmail());
            dto.setTelefone(pacient.getTelefone());
            dto.setCpf(pacient.getCpf());
            dto.setDataNascimento(pacient.getDataNascimento());
            dto.setPlanoSaude(pacient.getPlanoSaude());
            dto.setNumeroPlano(pacient.getNumeroPlano());
            dto.setConvenio(pacient.getConvenio());
            dto.setObservacoesMedicas(pacient.getObservacoesMedicas());
            dto.setAtivo(pacient.isAtivo());
            return dto;
        }
    }
} 