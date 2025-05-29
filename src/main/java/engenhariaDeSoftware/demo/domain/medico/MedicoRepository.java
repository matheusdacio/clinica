package engenhariaDeSoftware.demo.domain.medico;

import engenhariaDeSoftware.demo.domain.consulta.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import support.core.BaseRepository;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface MedicoRepository extends BaseRepository<Medico, Long> {
    List<Consulta> findByMedicoId(Long id);
    List<Consulta> findByPacienteId(Long id);
    Consulta findByDataConsulta(String dataConsulta);
    Consulta findByHoraConsulta(String horaConsulta);
    
}