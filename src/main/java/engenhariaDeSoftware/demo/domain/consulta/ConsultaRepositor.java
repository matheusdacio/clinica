package engenhariaDeSoftware.demo.domain.consulta;

import engenhariaDeSoftware.demo.domain.paciente.Pacient;
import engenhariaDeSoftware.demo.domain.medico.Medic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ConsultaRepositor extends JpaRepository<Consulta, Long> {
    
    @Query("SELECT c FROM Consulta c WHERE c.medico = :medico AND c.dataHora BETWEEN :inicio AND :fim")
    List<Consulta> findByMedicoAndPeriodo(Medic medic, LocalDateTime inicio, LocalDateTime fim);
    
    @Query("SELECT c FROM Consulta c WHERE c.paciente = :paciente AND c.dataHora BETWEEN :inicio AND :fim")
    List<Consulta> findByPacienteAndPeriodo(Pacient pacient, LocalDateTime inicio, LocalDateTime fim);
    
    @Query("SELECT c FROM Consulta c WHERE c.medico = :medico AND c.dataHora = :dataHora AND c.status != 'CANCELADA'")
    List<Consulta> findConsultasConflitantes(Medic medic, LocalDateTime dataHora);
    
    @Query("SELECT c FROM Consulta c WHERE c.paciente = :paciente AND c.dataHora = :dataHora AND c.status != 'CANCELADA'")
    List<Consulta> findConsultasPacienteConflitantes(Pacient pacient, LocalDateTime dataHora);
    
    @Query("SELECT c FROM Consulta c WHERE c.medico = :medico AND c.status = :status")
    Page<Consulta> findByMedicoAndStatus(Medic medic, Consulta.StatusConsulta status, Pageable pageable);
    
    @Query("SELECT c FROM Consulta c WHERE c.paciente = :paciente AND c.status = :status")
    Page<Consulta> findByPacienteAndStatus(Pacient pacient, Consulta.StatusConsulta status, Pageable pageable);
    
    @Query("SELECT c FROM Consulta c WHERE c.medico = :medico AND c.dataHora >= :hoje ORDER BY c.dataHora")
    List<Consulta> findProximasConsultasMedico(Medic medic, LocalDateTime hoje);
    
    @Query("SELECT c FROM Consulta c WHERE c.paciente = :paciente AND c.dataHora >= :hoje ORDER BY c.dataHora")
    List<Consulta> findProximasConsultasPaciente(Pacient pacient, LocalDateTime hoje);
} 