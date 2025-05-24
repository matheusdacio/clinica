package engenhariaDeSoftware.demo.clinica.domain.repository;

import engenhariaDeSoftware.demo.clinica.domain.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    
    Optional<Medico> findByCrm(String crm);
    
    Optional<Medico> findByEmail(String email);
    
    List<Medico> findByEspecialidade(String especialidade);
    
    @Query("SELECT m FROM Medico m WHERE m.ativo = true AND m.especialidade = :especialidade")
    List<Medico> findMedicosAtivosPorEspecialidade(String especialidade);
    
    @Query("SELECT m FROM Medico m WHERE m.ativo = true AND EXISTS " +
           "(SELECT h FROM m.horariosDisponiveis h WHERE h = :horario)")
    List<Medico> findMedicosDisponiveisPorHorario(String horario);
    
    @Query("SELECT m FROM Medico m WHERE m.ativo = true AND m.id NOT IN " +
           "(SELECT c.medico.id FROM Consulta c WHERE c.dataHora = :dataHora AND c.status != 'CANCELADA')")
    List<Medico> findMedicosDisponiveisPorDataHora(LocalDateTime dataHora);
} 