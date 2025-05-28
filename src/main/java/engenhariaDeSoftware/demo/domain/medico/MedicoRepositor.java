package engenhariaDeSoftware.demo.domain.medico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MedicoRepositor extends JpaRepository<Medic, Long> {
    
    Optional<Medic> findByCrm(String crm);
    
    Optional<Medic> findByEmail(String email);
    
    List<Medic> findByEspecialidade(String especialidade);
    
    @Query("SELECT m FROM Medico m WHERE m.ativo = true AND m.especialidade = :especialidade")
    List<Medic> findMedicosAtivosPorEspecialidade(String especialidade);
    
    @Query("SELECT m FROM Medico m WHERE m.ativo = true AND EXISTS " +
           "(SELECT h FROM m.horariosDisponiveis h WHERE h = :horario)")
    List<Medic> findMedicosDisponiveisPorHorario(String horario);
    
    @Query("SELECT m FROM Medico m WHERE m.ativo = true AND m.id NOT IN " +
           "(SELECT c.medico.id FROM Consulta c WHERE c.dataHora = :dataHora AND c.status != 'CANCELADA')")
    List<Medic> findMedicosDisponiveisPorDataHora(LocalDateTime dataHora);
} 