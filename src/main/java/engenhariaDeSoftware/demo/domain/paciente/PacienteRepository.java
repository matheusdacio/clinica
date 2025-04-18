package engenhariaDeSoftware.demo.domain.paciente;

import org.springframework.stereotype.Repository;
import support.core.BaseRepository;

@Repository
public interface PacienteRepository extends BaseRepository<Paciente, Long> {
    Paciente findByCpf(String cpf);
    Paciente findByEmail(String email);
    Paciente findByTelefone(String telefone);
    Paciente findByNome(String nome);

}
