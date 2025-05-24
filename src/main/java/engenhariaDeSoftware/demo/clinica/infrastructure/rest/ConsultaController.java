package engenhariaDeSoftware.demo.clinica.infrastructure.rest;

import engenhariaDeSoftware.demo.clinica.domain.model.Consulta;
import engenhariaDeSoftware.demo.clinica.domain.service.ConsultaService;
import engenhariaDeSoftware.demo.clinica.shared.dto.ConsultaDTO;
import engenhariaDeSoftware.demo.clinica.shared.exception.ConsultaConflitanteException;
import engenhariaDeSoftware.demo.clinica.shared.exception.EntidadeNaoEncontradaException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consultas")
@RequiredArgsConstructor
public class ConsultaController {
    
    private final ConsultaService consultaService;
    
    @PostMapping
    public ResponseEntity<ConsultaDTO.Resposta> agendar(@RequestBody @Valid ConsultaDTO.Criar dto) {
        try {
            Consulta consulta = consultaService.agendar(dto);
            return ResponseEntity.ok(ConsultaDTO.Resposta.fromEntity(consulta));
        } catch (ConsultaConflitanteException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PostMapping("/{id}/confirmar")
    public ResponseEntity<ConsultaDTO.Resposta> confirmar(@PathVariable Long id) {
        try {
            Consulta consulta = consultaService.confirmar(id);
            return ResponseEntity.ok(ConsultaDTO.Resposta.fromEntity(consulta));
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/{id}/realizar")
    public ResponseEntity<ConsultaDTO.Resposta> realizar(@PathVariable Long id) {
        try {
            Consulta consulta = consultaService.realizar(id);
            return ResponseEntity.ok(ConsultaDTO.Resposta.fromEntity(consulta));
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/{id}/cancelar")
    public ResponseEntity<ConsultaDTO.Resposta> cancelar(@PathVariable Long id, 
                                                       @RequestBody @Valid ConsultaDTO.Cancelar dto) {
        try {
            Consulta consulta = consultaService.cancelar(id, dto);
            return ResponseEntity.ok(ConsultaDTO.Resposta.fromEntity(consulta));
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/medico/{medicoId}")
    public ResponseEntity<Page<ConsultaDTO.Resposta>> listarConsultasMedico(
            @PathVariable Long medicoId,
            @RequestParam(required = false) Consulta.StatusConsulta status,
            Pageable pageable) {
        try {
            Page<Consulta> consultas = consultaService.listarConsultasMedico(medicoId, status, pageable);
            return ResponseEntity.ok(consultas.map(ConsultaDTO.Resposta::fromEntity));
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<Page<ConsultaDTO.Resposta>> listarConsultasPaciente(
            @PathVariable Long pacienteId,
            @RequestParam(required = false) Consulta.StatusConsulta status,
            Pageable pageable) {
        try {
            Page<Consulta> consultas = consultaService.listarConsultasPaciente(pacienteId, status, pageable);
            return ResponseEntity.ok(consultas.map(ConsultaDTO.Resposta::fromEntity));
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/medico/{medicoId}/proximas")
    public ResponseEntity<List<ConsultaDTO.Resposta>> listarProximasConsultasMedico(@PathVariable Long medicoId) {
        try {
            List<Consulta> consultas = consultaService.listarProximasConsultasMedico(medicoId);
            return ResponseEntity.ok(consultas.stream()
                    .map(ConsultaDTO.Resposta::fromEntity)
                    .toList());
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/paciente/{pacienteId}/proximas")
    public ResponseEntity<List<ConsultaDTO.Resposta>> listarProximasConsultasPaciente(@PathVariable Long pacienteId) {
        try {
            List<Consulta> consultas = consultaService.listarProximasConsultasPaciente(pacienteId);
            return ResponseEntity.ok(consultas.stream()
                    .map(ConsultaDTO.Resposta::fromEntity)
                    .toList());
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }
} 