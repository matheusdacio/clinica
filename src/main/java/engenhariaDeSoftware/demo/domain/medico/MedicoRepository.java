package engenhariaDeSoftware.demo.domain.medico;

import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.stereotype.Repository;
import support.core.BaseRepository;

@Repository
public interface MedicoRepository extends BaseRepository<Medico, Long> {
}
