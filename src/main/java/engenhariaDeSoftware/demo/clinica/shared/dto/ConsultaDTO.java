package engenhariaDeSoftware.demo.clinica.shared.dto;

import engenhariaDeSoftware.demo.clinica.domain.model.Consulta;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

public class ConsultaDTO {
    
    @Data
    public static class Criar {
        @NotNull
        private Long medicoId;
        
        @NotNull
        private Long pacienteId;
        
        @NotNull
        @Future
        private LocalDateTime dataHora;
        
        private String observacoes;
    }
    
    @Data
    public static class Atualizar {
        private String observacoes;
    }
    
    @Data
    public static class Cancelar {
        @NotNull
        private String motivoCancelamento;
    }
    
    @Data
    public static class Resposta {
        private Long id;
        private MedicoDTO.Resposta medico;
        private PacienteDTO.Resposta paciente;
        private LocalDateTime dataHora;
        private Consulta.StatusConsulta status;
        private String observacoes;
        private String motivoCancelamento;
        private LocalDateTime dataConfirmacao;
        private LocalDateTime dataRealizacao;
        private LocalDateTime dataCancelamento;
        
        public static Resposta fromEntity(Consulta consulta) {
            Resposta dto = new Resposta();
            dto.setId(consulta.getId());
            dto.setMedico(MedicoDTO.Resposta.fromEntity(consulta.getMedico()));
            dto.setPaciente(PacienteDTO.Resposta.fromEntity(consulta.getPaciente()));
            dto.setDataHora(consulta.getDataHora());
            dto.setStatus(consulta.getStatus());
            dto.setObservacoes(consulta.getObservacoes());
            dto.setMotivoCancelamento(consulta.getMotivoCancelamento());
            dto.setDataConfirmacao(consulta.getDataConfirmacao());
            dto.setDataRealizacao(consulta.getDataRealizacao());
            dto.setDataCancelamento(consulta.getDataCancelamento());
            return dto;
        }
    }
} 