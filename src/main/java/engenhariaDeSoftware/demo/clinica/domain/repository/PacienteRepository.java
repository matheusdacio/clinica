package engenhariaDeSoftware.demo.clinica.domain.repository;

import engenhariaDeSoftware.demo.clinica.domain.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    
    Optional<Paciente> findByCpf(String cpf);
    
    Optional<Paciente> findByEmail(String email);
    
    @Query("SELECT p FROM Paciente p WHERE p.ativo = true")
    List<Paciente> findAllAtivos();
    
    @Query("SELECT p FROM Paciente p WHERE p.ativo = true AND p.convenio = :convenio")
    List<Paciente> findByConvenio(String convenio);
    
    @Query("SELECT p FROM Paciente p WHERE p.ativo = true AND p.planoSaude = :planoSaude")
    List<Paciente> findByPlanoSaude(String planoSaude);
} 