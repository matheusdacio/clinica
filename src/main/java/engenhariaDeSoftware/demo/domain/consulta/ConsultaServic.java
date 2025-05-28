package engenhariaDeSoftware.demo.domain.consulta;

import engenhariaDeSoftware.demo.domain.usuario.NotificacaoService;
import engenhariaDeSoftware.demo.domain.paciente.Pacient;
import engenhariaDeSoftware.demo.domain.medico.Medic;
import engenhariaDeSoftware.demo.domain.medico.MedicoRepositor;
import engenhariaDeSoftware.demo.domain.paciente.PacienteRepositor;
import support.core.exception.ConsultaConflitanteException;
import support.core.exception.EntidadeNaoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultaServic {
    
    private final ConsultaRepositor consultaRepositor;
    private final MedicoRepositor medicoRepositor;
    private final PacienteRepositor pacienteRepositor;
    private final NotificacaoService notificacaoService;

    public ConsultaServic(ConsultaRepositor consultaRepositor, MedicoRepositor medicoRepositor, PacienteRepositor pacienteRepositor, NotificacaoService notificacaoService) {
        this.consultaRepositor = consultaRepositor;
        this.medicoRepositor = medicoRepositor;
        this.pacienteRepositor = pacienteRepositor;
        this.notificacaoService = notificacaoService;
    }

    @Transactional
    public Consulta agendar(ConsultaDTO.Criar dto) {
        Medic medic = medicoRepositor.findById(dto.getMedicoId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Médico não encontrado"));
        
        Pacient pacient = pacienteRepositor.findById(dto.getPacienteId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Paciente não encontrado"));
        
        validarDisponibilidade(medic, pacient, dto.getDataHora());
        
        Consulta consulta = new Consulta(pacient, medic, dto.getDataHora());
        consulta.setObservacoes(dto.getObservacoes());
        
        consulta = consultaRepositor.save(consulta);
        notificacaoService.notificarAgendamento(consulta);
        
        return consulta;
    }
    
    @Transactional
    public Consulta confirmar(Long id) {
        Consulta consulta = consultaRepositor.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Consulta não encontrada"));
        
        consulta.confirmar();
        consulta = consultaRepositor.save(consulta);
        notificacaoService.notificarConfirmacao(consulta);
        
        return consulta;
    }
    
    @Transactional
    public Consulta realizar(Long id) {
        Consulta consulta = consultaRepositor.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Consulta não encontrada"));
        
        consulta.realizar();
        consulta = consultaRepositor.save(consulta);
        notificacaoService.notificarRealizacao(consulta);
        
        return consulta;
    }
    
    @Transactional
    public Consulta cancelar(Long id, ConsultaDTO.Cancelar dto) {
        Consulta consulta = consultaRepositor.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Consulta não encontrada"));
        
        consulta.cancelar(dto.getMotivoCancelamento());
        consulta = consultaRepositor.save(consulta);
        notificacaoService.notificarCancelamento(consulta);
        
        return consulta;
    }
    
    public Page<Consulta> listarConsultasMedico(Long medicoId, StatusConsulta status, Pageable pageable) {
        Medic medic = medicoRepositor.findById(medicoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Médico não encontrado"));
        return consultaRepositor.findByMedicoAndStatus(medic, status, pageable);
    }
    
    public Page<Consulta> listarConsultasPaciente(Long pacienteId, StatusConsulta status, Pageable pageable) {
        Pacient pacient = pacienteRepositor.findById(pacienteId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Paciente não encontrado"));
        return consultaRepositor.findByPacienteAndStatus(pacient, status, pageable);
    }
    
    public List<Consulta> listarProximasConsultasMedico(Long medicoId) {
        Medic medic = medicoRepositor.findById(medicoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Médico não encontrado"));
        return consultaRepositor.findProximasConsultasMedico(medic, LocalDateTime.now());
    }
    
    public List<Consulta> listarProximasConsultasPaciente(Long pacienteId) {
        Pacient pacient = pacienteRepositor.findById(pacienteId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Paciente não encontrado"));
        return consultaRepositor.findProximasConsultasPaciente(pacient, LocalDateTime.now());
    }
    
    private void validarDisponibilidade(Medic medic, Pacient pacient, LocalDateTime dataHora) {
        if (!medic.isAtivo()) {
            throw new IllegalStateException("Médico não está ativo");
        }
        
        if (!pacient.isAtivo()) {
            throw new IllegalStateException("Paciente não está ativo");
        }
        
        List<Consulta> consultasMedico = consultaRepositor.findConsultasConflitantes(medic, dataHora);
        if (!consultasMedico.isEmpty()) {
            throw new ConsultaConflitanteException("Médico já possui consulta agendada neste horário");
        }
        
        List<Consulta> consultasPaciente = consultaRepositor.findConsultasPacienteConflitantes(pacient, dataHora);
        if (!consultasPaciente.isEmpty()) {
            throw new ConsultaConflitanteException("Paciente já possui consulta agendada neste horário");
        }
    }
} 