package engenhariaDeSoftware.demo.domain.consulta;

import engenhariaDeSoftware.demo.domain.medico.MedicoDto;
import engenhariaDeSoftware.demo.domain.paciente.PacienteDto;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ConsultaDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private PacienteDto paciente;
    private MedicoDto medicoDto;
    private LocalDateTime dataHora;
    private String observacoes;
    private String motivoCancelamento;
    private LocalDateTime dataConfirmacao;
    private LocalDateTime dataRealizacao;
    private LocalDateTime dataCancelamento;
    private String obs;
    private StatusConsulta statusConsulta;
} 