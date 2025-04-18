package engenhariaDeSoftware.demo.domain.pessoa;

import support.core.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaRepository extends BaseRepository<Pessoa, Long> {
    Optional<Pessoa> findByEmail(String email);
} 