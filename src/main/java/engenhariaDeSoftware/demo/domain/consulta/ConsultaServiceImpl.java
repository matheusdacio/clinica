package engenhariaDeSoftware.demo.domain.consulta;

import engenhariaDeSoftware.demo.domain.medico.MedicoRepository;
import engenhariaDeSoftware.demo.domain.usuario.NotificacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import support.core.exception.ConsultaConflitanteException;
import support.core.exception.EntidadeNaoEncontradaException;
import support.core.service.AbstractService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ConsultaServiceImpl extends AbstractService<Consulta, Long, ConsultaRepository> implements ConsultaService {

    private final MedicoRepository medicoRepository;
    private final PacienteRepositor pacienteRepositor;
    private final NotificacaoService notificacaoService;

    @Autowired
    public ConsultaServiceImpl(ConsultaRepository repo, MedicoRepository medicoRepository, PacienteRepositor pacienteRepositor, NotificacaoService notificacaoService) {
        super(repo);
        this.medicoRepository = medicoRepository;
        this.pacienteRepositor = pacienteRepositor;
        this.notificacaoService = notificacaoService;
    }

    @Transactional
    public Consulta agendar(Consulta consulta) {
        Medic medic = medicoRepository.findById(consulta.)
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
    public Consulta cancelar(Long id, Consulta consulta) {
        Consulta consulta = consultaRepositor.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Consulta não encontrada"));

        consulta.cancelar(dto.getMotivoCancelamento());
        consulta = consultaRepositor.save(consulta);
        notificacaoService.notificarCancelamento(consulta);

        return consulta;
    }

    public List<Consulta> listarConsultasPorMedicoId(Long medicoId) {
        Medic medic = medicoRepository.findById(medicoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Médico não encontrado"));
        return consultaRepositor.findByMedicoAndStatus(medic, status, pageable);
    }

    public List<Consulta> listarConsultasPorPacienteId(Long pacienteId) {
        Pacient pacient = pacienteRepositor.findById(pacienteId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Paciente não encontrado"));
        return consultaRepositor.findByPacienteAndStatus(pacient, status, pageable);
    }

    public List<Consulta> listarProximasConsultasPorMedicoId(Long medicoId) {
        Medic medic = medicoRepository.findById(medicoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Médico não encontrado"));
        return consultaRepositor.findProximasConsultasMedico(medic, LocalDateTime.now());
    }

    public List<Consulta> listarProximasConsultasPorPacienteId(Long pacienteId) {
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
