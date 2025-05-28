package engenhariaDeSoftware.demo.domain.paciente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PacienteRepositor extends JpaRepository<Pacient, Long> {
    
    Optional<Pacient> findByCpf(String cpf);
    
    Optional<Pacient> findByEmail(String email);
    
    @Query("SELECT p FROM Paciente p WHERE p.ativo = true")
    List<Pacient> findAllAtivos();
    
    @Query("SELECT p FROM Paciente p WHERE p.ativo = true AND p.convenio = :convenio")
    List<Pacient> findByConvenio(String convenio);
    
    @Query("SELECT p FROM Paciente p WHERE p.ativo = true AND p.planoSaude = :planoSaude")
    List<Pacient> findByPlanoSaude(String planoSaude);
} 