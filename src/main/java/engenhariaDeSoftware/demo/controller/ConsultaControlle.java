package engenhariaDeSoftware.demo.controller;

import engenhariaDeSoftware.demo.domain.consulta.Consulta;
import engenhariaDeSoftware.demo.domain.consulta.ConsultaServic;
import engenhariaDeSoftware.demo.domain.consulta.ConsultaDTO;
import support.core.exception.ConsultaConflitanteException;
import support.core.exception.EntidadeNaoEncontradaException;
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
public class ConsultaControlle {
    
    private final ConsultaServic consultaServic;

    public ConsultaControlle(ConsultaServic consultaServic) {
        this.consultaServic = consultaServic;
    }

    @PostMapping
    public ResponseEntity<ConsultaDTO.Resposta> agendar(@RequestBody @Valid ConsultaDTO.Criar dto) {
        try {
            Consulta consulta = consultaServic.agendar(dto);
            return ResponseEntity.ok(ConsultaDTO.Resposta.fromEntity(consulta));
        } catch (ConsultaConflitanteException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PostMapping("/{id}/confirmar")
    public ResponseEntity<ConsultaDTO.Resposta> confirmar(@PathVariable Long id) {
        try {
            Consulta consulta = consultaServic.confirmar(id);
            return ResponseEntity.ok(ConsultaDTO.Resposta.fromEntity(consulta));
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/{id}/realizar")
    public ResponseEntity<ConsultaDTO.Resposta> realizar(@PathVariable Long id) {
        try {
            Consulta consulta = consultaServic.realizar(id);
            return ResponseEntity.ok(ConsultaDTO.Resposta.fromEntity(consulta));
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/{id}/cancelar")
    public ResponseEntity<ConsultaDTO.Resposta> cancelar(@PathVariable Long id, 
                                                       @RequestBody @Valid ConsultaDTO.Cancelar dto) {
        try {
            Consulta consulta = consultaServic.cancelar(id, dto);
            return ResponseEntity.ok(ConsultaDTO.Resposta.fromEntity(consulta));
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/medico/{medicoId}")
    public ResponseEntity<Page<ConsultaDTO.Resposta>> listarConsultasMedico(
            @PathVariable Long medicoId,
            @RequestParam(required = false)
            Pageable pageable) {
        try {
            Page<Consulta> consultas = consultaServic.listarConsultasMedico(medicoId, pageable);
            return ResponseEntity.ok(consultas.map(ConsultaDTO.Resposta::fromEntity));
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<Page<ConsultaDTO.Resposta>> listarConsultasPaciente(
            @PathVariable Long pacienteId,
            @RequestParam(required = false)
            Pageable pageable) {
        try {
            Page<Consulta> consultas = consultaServic.listarConsultasPaciente(pacienteId, pageable);
            return ResponseEntity.ok(consultas.map(ConsultaDTO.Resposta::fromEntity));
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/medico/{medicoId}/proximas")
    public ResponseEntity<List<ConsultaDTO.Resposta>> listarProximasConsultasMedico(@PathVariable Long medicoId) {
        try {
            List<Consulta> consultas = consultaServic.listarProximasConsultasMedico(medicoId);
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
            List<Consulta> consultas = consultaServic.listarProximasConsultasPaciente(pacienteId);
            return ResponseEntity.ok(consultas.stream()
                    .map(ConsultaDTO.Resposta::fromEntity)
                    .toList());
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }
} 