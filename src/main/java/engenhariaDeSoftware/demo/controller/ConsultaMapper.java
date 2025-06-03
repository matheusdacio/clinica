package engenhariaDeSoftware.demo.controller;

import engenhariaDeSoftware.demo.domain.consulta.Consulta;
import engenhariaDeSoftware.demo.domain.consulta.ConsultaDto;
import org.springframework.stereotype.Component;

@Component
public class ConsultaMapper {

    public ConsultaDto toConsultaDto(Consulta consulta) {
        ConsultaDto consultaDto = ConsultaDto.builder()
                .id(consulta.getId())
                .dataConsulta(consulta.getDataConsulta())
                .horaConsulta(consulta.getHoraConsulta())
                .medicoId(consulta.getMedico().getId())
                .pacienteId(consulta.getPaciente().getId())
                .status(consulta.getStatus())
                .build();
        return consultaDto;
    }

    public Consulta toConsultaEntity(ConsultaDto consultaDto) {
        return Consulta.builder()
                .id(consultaDto.getId())
                .dataConsulta(consultaDto.getDataConsulta())
                .horaConsulta(consultaDto.getHoraConsulta())
                .medicoId(consultaDto.getMedicoId())
                .pacienteId(consultaDto.getPacienteId())
                .status(consultaDto.getStatus())
                .build();
    }
}
