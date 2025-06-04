package engenhariaDeSoftware.demo.controller;

import engenhariaDeSoftware.demo.domain.paciente.Paciente;
import engenhariaDeSoftware.demo.domain.paciente.PacienteService;
import engenhariaDeSoftware.demo.domain.paciente.PacienteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<PacienteDto> cadastrar(@RequestBody PacienteDto pacienteDto) {
        Paciente paciente = pacienteService.salvar(pacienteDto.toEntity());
        return ResponseEntity.ok(new PacienteDto(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<PacienteDto>> listar(Pageable paginacao) {
        Page<Paciente> pacientes = pacienteService.listarPagina(paginacao);
        return ResponseEntity.ok(pacientes.map(PacienteDto::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDto> buscarPorId(@PathVariable Long id) {
        Paciente paciente = pacienteService.buscarPorIdOrElseThrow(id);
        return ResponseEntity.ok(new PacienteDto(paciente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteDto> atualizar(@PathVariable Long id, @RequestBody PacienteDto pacienteDto) {
        Paciente paciente = pacienteService.buscarPorIdOrElseThrow(id);
        pacienteDto.atualizar(paciente);
        paciente = pacienteService.salvar(paciente);
        return ResponseEntity.ok(new PacienteDto(paciente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (pacienteService.existe(id)) {
            pacienteService.excluir(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<PacienteDto> buscarPorCpf(@PathVariable String cpf) {
        Paciente paciente = pacienteService.buscarPorCpf(cpf);
        return ResponseEntity.ok(new PacienteDto(paciente));
    }

    @GetMapping("/plano/{numeroPlano}")
    public ResponseEntity<Page<PacienteDto>> buscarPorPlano(
            @PathVariable String numeroPlano,
            Pageable paginacao) {
        Page<Paciente> pacientes = pacienteService.buscarPorPlano(numeroPlano, paginacao);
        return ResponseEntity.ok(pacientes.map(PacienteDto::new));
    }
}
