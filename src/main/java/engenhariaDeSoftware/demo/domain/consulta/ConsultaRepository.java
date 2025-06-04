package engenhariaDeSoftware.demo.domain.consulta;

import engenhariaDeSoftware.demo.domain.medico.Medico;
import engenhariaDeSoftware.demo.domain.paciente.Paciente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import support.core.BaseRepository;

import java.time.LocalDateTime;
import java.util.List;

@RepositoryRestResource(exported = false)
public interface ConsultaRepository extends BaseRepository<Consulta, Long> {
    List<Consulta> findByMedicoId(Long id);
    List<Consulta> findByPacienteId(Long id);
    Consulta findByDataConsulta(String dataConsulta);
    Consulta findByHoraConsulta(String horaConsulta);
    
    List<Consulta> findByMedicoAndStatusNot(Medico medico, StatusConsulta status);
    
    List<Consulta> findByPacienteAndStatusNot(Paciente paciente, StatusConsulta status);
    
    @Query("SELECT c FROM Consulta c WHERE c.medico = :medico AND c.dataHora > :dataHora AND c.status != 'CANCELADA' ORDER BY c.dataHora")
    List<Consulta> findProximasConsultasMedico(@Param("medico") Medico medico, @Param("dataHora") LocalDateTime dataHora);
    
    @Query("SELECT c FROM Consulta c WHERE c.paciente = :paciente AND c.dataHora > :dataHora AND c.status != 'CANCELADA' ORDER BY c.dataHora")
    List<Consulta> findProximasConsultasPaciente(@Param("paciente") Paciente paciente, @Param("dataHora") LocalDateTime dataHora);
    
    boolean existsByMedicoAndDataHoraAndStatusNot(Medico medico, LocalDateTime dataHora, StatusConsulta status);
}
