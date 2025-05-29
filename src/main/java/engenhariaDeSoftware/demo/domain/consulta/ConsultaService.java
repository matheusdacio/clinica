package engenhariaDeSoftware.demo.domain.consulta;

import support.core.service.BaseService;

import java.util.List;

public interface ConsultaService extends BaseService<Consulta, Long> {

    List<Consulta> listarConsultasPorMedicoId(Long medicoId);
    List<Consulta> listarConsultasPorPacienteId(Long pacienteId);
    List<Consulta> listarProximasConsultasPorMedicoId(Long medicoId);
    List<Consulta> listarProximasConsultasPorPacienteId(Long pacienteId);

} 