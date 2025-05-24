package engenhariaDeSoftware.demo.clinica.domain.service;

import engenhariaDeSoftware.demo.clinica.domain.model.Consulta;
import engenhariaDeSoftware.demo.clinica.domain.model.Medico;
import engenhariaDeSoftware.demo.clinica.domain.model.Paciente;
import engenhariaDeSoftware.demo.clinica.domain.repository.ConsultaRepository;
import engenhariaDeSoftware.demo.clinica.domain.repository.MedicoRepository;
import engenhariaDeSoftware.demo.clinica.domain.repository.PacienteRepository;
import engenhariaDeSoftware.demo.clinica.shared.dto.ConsultaDTO;
import engenhariaDeSoftware.demo.clinica.shared.exception.ConsultaConflitanteException;
import engenhariaDeSoftware.demo.clinica.shared.exception.EntidadeNaoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultaService {
    
    private final ConsultaRepository consultaRepository;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;
    private final NotificacaoService notificacaoService;
    
    @Transactional
    public Consulta agendar(ConsultaDTO.Criar dto) {
        Medico medico = medicoRepository.findById(dto.getMedicoId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Médico não encontrado"));
        
        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Paciente não encontrado"));
        
        validarDisponibilidade(medico, paciente, dto.getDataHora());
        
        Consulta consulta = new Consulta(paciente, medico, dto.getDataHora());
        consulta.setObservacoes(dto.getObservacoes());
        
        consulta = consultaRepository.save(consulta);
        notificacaoService.notificarAgendamento(consulta);
        
        return consulta;
    }
    
    @Transactional
    public Consulta confirmar(Long id) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Consulta não encontrada"));
        
        consulta.confirmar();
        consulta = consultaRepository.save(consulta);
        notificacaoService.notificarConfirmacao(consulta);
        
        return consulta;
    }
    
    @Transactional
    public Consulta realizar(Long id) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Consulta não encontrada"));
        
        consulta.realizar();
        consulta = consultaRepository.save(consulta);
        notificacaoService.notificarRealizacao(consulta);
        
        return consulta;
    }
    
    @Transactional
    public Consulta cancelar(Long id, ConsultaDTO.Cancelar dto) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Consulta não encontrada"));
        
        consulta.cancelar(dto.getMotivoCancelamento());
        consulta = consultaRepository.save(consulta);
        notificacaoService.notificarCancelamento(consulta);
        
        return consulta;
    }
    
    public Page<Consulta> listarConsultasMedico(Long medicoId, Consulta.StatusConsulta status, Pageable pageable) {
        Medico medico = medicoRepository.findById(medicoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Médico não encontrado"));
        return consultaRepository.findByMedicoAndStatus(medico, status, pageable);
    }
    
    public Page<Consulta> listarConsultasPaciente(Long pacienteId, Consulta.StatusConsulta status, Pageable pageable) {
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Paciente não encontrado"));
        return consultaRepository.findByPacienteAndStatus(paciente, status, pageable);
    }
    
    public List<Consulta> listarProximasConsultasMedico(Long medicoId) {
        Medico medico = medicoRepository.findById(medicoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Médico não encontrado"));
        return consultaRepository.findProximasConsultasMedico(medico, LocalDateTime.now());
    }
    
    public List<Consulta> listarProximasConsultasPaciente(Long pacienteId) {
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Paciente não encontrado"));
        return consultaRepository.findProximasConsultasPaciente(paciente, LocalDateTime.now());
    }
    
    private void validarDisponibilidade(Medico medico, Paciente paciente, LocalDateTime dataHora) {
        if (!medico.isAtivo()) {
            throw new IllegalStateException("Médico não está ativo");
        }
        
        if (!paciente.isAtivo()) {
            throw new IllegalStateException("Paciente não está ativo");
        }
        
        List<Consulta> consultasMedico = consultaRepository.findConsultasConflitantes(medico, dataHora);
        if (!consultasMedico.isEmpty()) {
            throw new ConsultaConflitanteException("Médico já possui consulta agendada neste horário");
        }
        
        List<Consulta> consultasPaciente = consultaRepository.findConsultasPacienteConflitantes(paciente, dataHora);
        if (!consultasPaciente.isEmpty()) {
            throw new ConsultaConflitanteException("Paciente já possui consulta agendada neste horário");
        }
    }
} 