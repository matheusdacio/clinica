package engenhariaDeSoftware.demo.controller;

import engenhariaDeSoftware.demo.domain.consulta.Consulta;
import engenhariaDeSoftware.demo.domain.consulta.ConsultaService;
import engenhariaDeSoftware.demo.domain.consulta.ConsultaDto;
import support.core.exception.ConsultaConflitanteException;
import support.core.exception.EntidadeNaoEncontradaException;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {
    
    private final ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @PostMapping
    public ResponseEntity<ConsultaDto.Resposta> agendar(@RequestBody @Valid ConsultaDto.Criar dto) {
        try {
            Consulta consulta = consultaService.agendar(dto);
            return ResponseEntity.ok(ConsultaDto.Resposta.fromEntity(consulta));
        } catch (ConsultaConflitanteException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PostMapping("/{id}/confirmar")
    public ResponseEntity<ConsultaDto.Resposta> confirmar(@PathVariable Long id) {
        try {
            Consulta consulta = consultaService.confirmar(id);
            return ResponseEntity.ok(ConsultaDto.Resposta.fromEntity(consulta));
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/{id}/realizar")
    public ResponseEntity<ConsultaDto.Resposta> realizar(@PathVariable Long id) {
        try {
            Consulta consulta = consultaService.realizar(id);
            return ResponseEntity.ok(ConsultaDto.Resposta.fromEntity(consulta));
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/{id}/cancelar")
    public ResponseEntity<ConsultaDto.Resposta> cancelar(@PathVariable Long id,
                                                         @RequestBody @Valid ConsultaDto.Cancelar dto) {
        try {
            Consulta consulta = consultaService.cancelar(id, dto);
            return ResponseEntity.ok(ConsultaDto.Resposta.fromEntity(consulta));
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/medico/{medicoId}")
    public ResponseEntity<Page<ConsultaDto.Resposta>> listarConsultasMedico(
            @PathVariable Long medicoId,
            @RequestParam(required = false) {
        try {
            Page<Consulta> consultas = consultaService.listarConsultasMedico(medicoId);
            return ResponseEntity.ok(consultas.map(ConsultaDto.Resposta::fromEntity));
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<Page<ConsultaDto.Resposta>> listarConsultasPaciente(
            @PathVariable Long pacienteId,
            @RequestParam(required = false)
            Pageable pageable) {
        try {
            Page<Consulta> consultas = consultaService.listarConsultasPaciente(pacienteId);
            return ResponseEntity.ok(consultas.map(ConsultaDto.Resposta::fromEntity));
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/medico/{medicoId}/proximas")
    public ResponseEntity<List<ConsultaDto.Resposta>> listarProximasConsultasMedico(@PathVariable Long medicoId) {
        try {
            List<Consulta> consultas = consultaService.listarProximasConsultasMedico(medicoId);
            return ResponseEntity.ok(consultas.stream()
                    .map(ConsultaDto.Resposta::fromEntity)
                    .toList());
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/paciente/{pacienteId}/proximas")
    public ResponseEntity<List<ConsultaDto.Resposta>> listarProximasConsultasPaciente(@PathVariable Long pacienteId) {
        try {
            List<Consulta> consultas = consultaService.listarProximasConsultasPaciente(pacienteId);
            return ResponseEntity.ok(consultas.stream()
                    .map(ConsultaDto.Resposta::fromEntity)
                    .toList());
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }
} 