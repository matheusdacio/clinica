package engenhariaDeSoftware.demo.domain.consulta;

import support.core.service.BaseService;

import java.util.List;

public interface ConsultaService extends BaseService<Consulta, Long> {

    Consulta agendar(Consulta consulta);
    Consulta confirmar(Long id);
    Consulta cancelar(Long id, String motivoCancelamento);
    List<Consulta> listarConsultasPorMedicoId(Long medicoId);
    List<Consulta> listarConsultasPorPacienteId(Long pacienteId);
    List<Consulta> listarProximasConsultasPorMedicoId(Long medicoId);
    List<Consulta> listarProximasConsultasPorPacienteId(Long pacienteId);
    boolean verificarDisponibilidade(Long medicoId, String data, String hora);

} 