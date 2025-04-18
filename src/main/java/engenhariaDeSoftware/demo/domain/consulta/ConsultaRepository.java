package engenhariaDeSoftware.demo.domain.consulta;

import support.core.BaseRepository;

import java.util.Optional;

public interface ConsultaRepository extends BaseRepository<Consulta, Long> {
    Optional<Consulta> findById(Long id);
    Consulta findByMedicoId(Long id);
    Consulta findByPacienteId(Long id);
    Consulta findByDataConsulta(String dataConsulta);
    Consulta findByHoraConsulta(String horaConsulta);

}
