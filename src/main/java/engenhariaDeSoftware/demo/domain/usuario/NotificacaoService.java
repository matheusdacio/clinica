package engenhariaDeSoftware.demo.domain.usuario;

import engenhariaDeSoftware.demo.domain.consulta.Consulta;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificacaoService {
    
    public void notificarAgendamento(Consulta consulta) {
        // TODO: Implementar envio real de e-mail/SMS
        log.info("Notificação de agendamento enviada para: Médico {} e Paciente {}",
                consulta.getMedico().getEmail(), consulta.getPaciente().getEmail());
    }
    
    public void notificarConfirmacao(Consulta consulta) {
        // TODO: Implementar envio real de e-mail/SMS
        log.info("Notificação de confirmação enviada para: Médico {} e Paciente {}",
                consulta.getMedico().getEmail(), consulta.getPaciente().getEmail());
    }
    
    public void notificarRealizacao(Consulta consulta) {
        // TODO: Implementar envio real de e-mail/SMS
        log.info("Notificação de realização enviada para: Médico {} e Paciente {}",
                consulta.getMedico().getEmail(), consulta.getPaciente().getEmail());
    }
    
    public void notificarCancelamento(Consulta consulta) {
        // TODO: Implementar envio real de e-mail/SMS
        log.info("Notificação de cancelamento enviada para: Médico {} e Paciente {}",
                consulta.getMedico().getEmail(), consulta.getPaciente().getEmail());
    }
} 