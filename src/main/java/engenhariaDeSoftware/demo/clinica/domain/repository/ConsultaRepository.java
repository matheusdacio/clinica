package engenhariaDeSoftware.demo.clinica.domain.repository;

import engenhariaDeSoftware.demo.clinica.domain.model.Consulta;
import engenhariaDeSoftware.demo.clinica.domain.model.Medico;
import engenhariaDeSoftware.demo.clinica.domain.model.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    
    @Query("SELECT c FROM Consulta c WHERE c.medico = :medico AND c.dataHora BETWEEN :inicio AND :fim")
    List<Consulta> findByMedicoAndPeriodo(Medico medico, LocalDateTime inicio, LocalDateTime fim);
    
    @Query("SELECT c FROM Consulta c WHERE c.paciente = :paciente AND c.dataHora BETWEEN :inicio AND :fim")
    List<Consulta> findByPacienteAndPeriodo(Paciente paciente, LocalDateTime inicio, LocalDateTime fim);
    
    @Query("SELECT c FROM Consulta c WHERE c.medico = :medico AND c.dataHora = :dataHora AND c.status != 'CANCELADA'")
    List<Consulta> findConsultasConflitantes(Medico medico, LocalDateTime dataHora);
    
    @Query("SELECT c FROM Consulta c WHERE c.paciente = :paciente AND c.dataHora = :dataHora AND c.status != 'CANCELADA'")
    List<Consulta> findConsultasPacienteConflitantes(Paciente paciente, LocalDateTime dataHora);
    
    @Query("SELECT c FROM Consulta c WHERE c.medico = :medico AND c.status = :status")
    Page<Consulta> findByMedicoAndStatus(Medico medico, Consulta.StatusConsulta status, Pageable pageable);
    
    @Query("SELECT c FROM Consulta c WHERE c.paciente = :paciente AND c.status = :status")
    Page<Consulta> findByPacienteAndStatus(Paciente paciente, Consulta.StatusConsulta status, Pageable pageable);
    
    @Query("SELECT c FROM Consulta c WHERE c.medico = :medico AND c.dataHora >= :hoje ORDER BY c.dataHora")
    List<Consulta> findProximasConsultasMedico(Medico medico, LocalDateTime hoje);
    
    @Query("SELECT c FROM Consulta c WHERE c.paciente = :paciente AND c.dataHora >= :hoje ORDER BY c.dataHora")
    List<Consulta> findProximasConsultasPaciente(Paciente paciente, LocalDateTime hoje);
} 