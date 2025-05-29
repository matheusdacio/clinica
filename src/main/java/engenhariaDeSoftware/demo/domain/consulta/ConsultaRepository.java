package engenhariaDeSoftware.demo.domain.consulta;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import support.core.BaseRepository;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface ConsultaRepository extends BaseRepository<Consulta, Long> {
    List<Consulta> findByMedicoId(Long id);
    List<Consulta> findByPacienteId(Long id);
    Consulta findByDataConsulta(String dataConsulta);
    Consulta findByHoraConsulta(String horaConsulta);
}
