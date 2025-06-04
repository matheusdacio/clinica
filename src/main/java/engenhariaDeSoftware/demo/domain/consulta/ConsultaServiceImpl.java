package engenhariaDeSoftware.demo.domain.consulta;

import engenhariaDeSoftware.demo.domain.medico.Medico;
import engenhariaDeSoftware.demo.domain.medico.MedicoRepository;
import engenhariaDeSoftware.demo.domain.paciente.Paciente;
import engenhariaDeSoftware.demo.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import support.core.exception.ConsultaConflitanteException;
import support.core.exception.EntidadeNaoEncontradaException;
import support.core.service.AbstractService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ConsultaServiceImpl extends AbstractService<Consulta, Long, ConsultaRepository> implements ConsultaService {

    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;

    @Autowired
    public ConsultaServiceImpl(ConsultaRepository repo, MedicoRepository medicoRepository, 
            PacienteRepository pacienteRepository) {
        super(repo);
        this.medicoRepository = medicoRepository;
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    @Transactional
    public Consulta agendar(Consulta consulta) {
        Medico medico = medicoRepository.findById(consulta.get)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Médico não encontrado"));

        Paciente paciente = pacienteRepository.findById(consulta.getPaciente().getId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Paciente não encontrado"));

        validarDisponibilidade(medico, consulta.getDataHora());

        consulta.setMedico(medico);
        consulta.setPaciente(paciente);
        consulta.setStatus(StatusConsulta.AGENDADA);
        
        return repo.save(consulta);
    }

    @Override
    @Transactional
    public Consulta confirmar(Long id) {
        Consulta consulta = buscarPorIdOrElseThrow(id);
        consulta.setStatus(StatusConsulta.CONFIRMADA);
        consulta.setDataConfirmacao(LocalDateTime.now());
        
        return repo.save(consulta);
    }

    @Override
    @Transactional
    public Consulta cancelar(Long id, String motivoCancelamento) {
        Consulta consulta = buscarPorIdOrElseThrow(id);
        consulta.setStatus(StatusConsulta.CANCELADA);
        consulta.setMotivoCancelamento(motivoCancelamento);
        consulta.setDataCancelamento(LocalDateTime.now());
        
        return repo.save(consulta);
    }

    @Override
    public List<Consulta> listarConsultasPorMedicoId(Long medicoId) {
        Medico medico = medicoRepository.findById(medicoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Médico não encontrado"));
        return repo.findByMedicoAndStatusNot(medico, StatusConsulta.CANCELADA);
    }

    @Override
    public List<Consulta> listarConsultasPorPacienteId(Long pacienteId) {
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Paciente não encontrado"));
        return repo.findByPacienteAndStatusNot(paciente, StatusConsulta.CANCELADA);
    }

    @Override
    public List<Consulta> listarProximasConsultasPorMedicoId(Long medicoId) {
        Medico medico = medicoRepository.findById(medicoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Médico não encontrado"));
        return repo.findProximasConsultasMedico(medico, LocalDateTime.now());
    }

    @Override
    public List<Consulta> listarProximasConsultasPorPacienteId(Long pacienteId) {
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Paciente não encontrado"));
        return repo.findProximasConsultasPaciente(paciente, LocalDateTime.now());
    }

    @Override
    public boolean verificarDisponibilidade(Long medicoId, String data, String hora) {
        Medico medico = medicoRepository.findById(medicoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Médico não encontrado"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime dataHora = LocalDateTime.parse(data + " " + hora, formatter);

        return !repo.existsByMedicoAndDataHoraAndStatusNot(medico, dataHora, StatusConsulta.CANCELADA);
    }

    private void validarDisponibilidade(Medico medico, LocalDateTime dataHora) {
        if (repo.existsByMedicoAndDataHoraAndStatusNot(medico, dataHora, StatusConsulta.CANCELADA)) {
            throw new ConsultaConflitanteException("Já existe uma consulta agendada para este horário");
        }
    }
}
