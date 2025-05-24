package engenhariaDeSoftware.demo.clinica.infrastructure.config;

import engenhariaDeSoftware.demo.clinica.domain.model.Medico;
import engenhariaDeSoftware.demo.clinica.domain.model.Paciente;
import engenhariaDeSoftware.demo.clinica.domain.repository.MedicoRepository;
import engenhariaDeSoftware.demo.clinica.domain.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class DadosIniciaisConfig {

    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner inicializarDados() {
        return args -> {
            // Criar médicos de exemplo
            if (medicoRepository.count() == 0) {
                Medico medico1 = new Medico();
                medico1.setNome("Dr. João Silva");
                medico1.setEmail("joao.silva@clinica.com");
                medico1.setSenha(passwordEncoder.encode("123456"));
                medico1.setTelefone("(11) 99999-9999");
                medico1.setCrm("12345-SP");
                medico1.setEspecialidade("Cardiologia");
                medico1.setTempoConsultaMinutos(30);
                medico1.setHorariosDisponiveis(Set.of("08:00", "09:00", "10:00", "14:00", "15:00", "16:00"));
                medico1.setAtivo(true);
                medicoRepository.save(medico1);

                Medico medico2 = new Medico();
                medico2.setNome("Dra. Maria Santos");
                medico2.setEmail("maria.santos@clinica.com");
                medico2.setSenha(passwordEncoder.encode("123456"));
                medico2.setTelefone("(11) 98888-8888");
                medico2.setCrm("54321-SP");
                medico2.setEspecialidade("Pediatria");
                medico2.setTempoConsultaMinutos(40);
                medico2.setHorariosDisponiveis(Set.of("08:30", "09:30", "10:30", "14:30", "15:30", "16:30"));
                medico2.setAtivo(true);
                medicoRepository.save(medico2);
            }

            // Criar pacientes de exemplo
            if (pacienteRepository.count() == 0) {
                Paciente paciente1 = new Paciente();
                paciente1.setNome("Ana Oliveira");
                paciente1.setEmail("ana.oliveira@email.com");
                paciente1.setSenha(passwordEncoder.encode("123456"));
                paciente1.setTelefone("(11) 97777-7777");
                paciente1.setCpf("123.456.789-00");
                paciente1.setDataNascimento(LocalDate.of(1985, 5, 15));
                paciente1.setPlanoSaude("Unimed");
                paciente1.setNumeroPlano("123456789");
                paciente1.setConvenio("Unimed");
                paciente1.setObservacoesMedicas("Alérgica a penicilina");
                paciente1.setAtivo(true);
                pacienteRepository.save(paciente1);

                Paciente paciente2 = new Paciente();
                paciente2.setNome("Carlos Souza");
                paciente2.setEmail("carlos.souza@email.com");
                paciente2.setSenha(passwordEncoder.encode("123456"));
                paciente2.setTelefone("(11) 96666-6666");
                paciente2.setCpf("987.654.321-00");
                paciente2.setDataNascimento(LocalDate.of(1990, 8, 20));
                paciente2.setPlanoSaude("Amil");
                paciente2.setNumeroPlano("987654321");
                paciente2.setConvenio("Amil");
                paciente2.setObservacoesMedicas("Hipertenso");
                paciente2.setAtivo(true);
                pacienteRepository.save(paciente2);
            }
        };
    }
} 