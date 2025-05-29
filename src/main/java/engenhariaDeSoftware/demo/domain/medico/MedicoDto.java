package engenhariaDeSoftware.demo.domain.medico;

import java.io.Serial;
import java.io.Serializable;

public class MedicoDto implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 1L;

        private String nome;
        private String email;
        private String telefone;
        private String crm;
        private String especialidade;
}