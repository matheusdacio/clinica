package engenhariaDeSoftware.demo.controller;

import engenhariaDeSoftware.demo.domain.consulta.ConsultaDto;
import engenhariaDeSoftware.demo.domain.consulta.ConsultaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {
    
    private final ConsultaService consultaService;
    private final ConsultaMapper consultaMapper;

    public ConsultaController(ConsultaService consultaService, ConsultaMapper consultaMapper) {
        this.consultaService = consultaService;
        this.consultaMapper = consultaMapper;
    }

    @PostMapping(value = "/{id}/agendar")
    public ResponseEntity<ConsultaDto> agendar(@PathVariable Long id, @RequestBody ConsultaDto consultaDto) {
        var consulta = consultaService.agendar(consultaMapper.toConsultaEntity(consultaDto));
        return ResponseEntity.ok(consultaMapper.toConsultaDto(consulta));
    }
    
    @PostMapping("/{id}/confirmar")
    public ResponseEntity<ConsultaDto> confirmar(@PathVariable Long id) {
        var consulta = consultaService.confirmar(id);
        return ResponseEntity.ok(consultaMapper.toConsultaDto(consulta));
    }
    
    @PostMapping("/{id}/cancelar")
    public ResponseEntity<ConsultaDto> cancelar(@PathVariable Long id, @RequestBody String motivoCancelamento) {
        var consulta = consultaService.cancelar(id, motivoCancelamento);
        return ResponseEntity.ok(consultaMapper.toConsultaDto(consulta));
    }
    
    @GetMapping(value = "/medico/{medicoId}")
    public ResponseEntity<List<ConsultaDto>> listarConsultasMedico(@PathVariable Long medicoId) {
        var consultas = consultaService.listarConsultasPorMedicoId(medicoId);
        return ResponseEntity.ok(consultas.stream().map(consultaMapper::toConsultaDto).toList());
    }
    
    @GetMapping(value = "/paciente/{pacienteId}")
    public ResponseEntity<List<ConsultaDto>> listarConsultasPaciente(@PathVariable Long pacienteId) {
        var consultas = consultaService.listarConsultasPorPacienteId(pacienteId);
        return ResponseEntity.ok(consultas.stream().map(consultaMapper::toConsultaDto).toList());
    }
    
    @GetMapping(value = "/medico/{medicoId}/proximas")
    public ResponseEntity<List<ConsultaDto>> listarProximasConsultasMedico(@PathVariable Long medicoId) {
        var consultas = consultaService.listarProximasConsultasPorMedicoId(medicoId);
        return ResponseEntity.ok(consultas.stream().map(consultaMapper::toConsultaDto).toList());
    }
    
    @GetMapping(value = "/paciente/{pacienteId}/proximas")
    public ResponseEntity<List<ConsultaDto>> listarProximasConsultasPaciente(@PathVariable Long pacienteId) {
        var consultas = consultaService.listarProximasConsultasPorPacienteId(pacienteId);
        return ResponseEntity.ok(consultas.stream().map(consultaMapper::toConsultaDto).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaDto> buscarPorId(@PathVariable Long id) {
        var consulta = consultaService.buscarPorIdOrElseThrow(id);
        return ResponseEntity.ok(consultaMapper.toConsultaDto(consulta));
    }

    @GetMapping("/disponibilidade")
    public ResponseEntity<Boolean> verificarDisponibilidade(
            @RequestParam Long medicoId,
            @RequestParam String data,
            @RequestParam String hora) {
        boolean disponivel = consultaService.verificarDisponibilidade(medicoId, data, hora);
        return ResponseEntity.ok(disponivel);
    }
}