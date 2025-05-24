package engenhariaDeSoftware.demo.clinica.shared.dto;

import engenhariaDeSoftware.demo.clinica.domain.model.Paciente;
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
        
        public static Resposta fromEntity(Paciente paciente) {
            Resposta dto = new Resposta();
            dto.setId(paciente.getId());
            dto.setNome(paciente.getNome());
            dto.setEmail(paciente.getEmail());
            dto.setTelefone(paciente.getTelefone());
            dto.setCpf(paciente.getCpf());
            dto.setDataNascimento(paciente.getDataNascimento());
            dto.setPlanoSaude(paciente.getPlanoSaude());
            dto.setNumeroPlano(paciente.getNumeroPlano());
            dto.setConvenio(paciente.getConvenio());
            dto.setObservacoesMedicas(paciente.getObservacoesMedicas());
            dto.setAtivo(paciente.isAtivo());
            return dto;
        }
    }
} 