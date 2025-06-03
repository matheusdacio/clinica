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
    public ConsultaDto agendar(@PathVariable Long id, ConsultaDto consultaDto) {
        return consultaService.salvar(consultaMapper.toConsultaEntity(consultaDto));

    }
    
    @PostMapping("/{id}/confirmar")
    public ResponseEntity<ConsultaDto> confirmar(@PathVariable Long id) {

    }
    
    @PostMapping("/{id}/cancelar")
    public ResponseEntity<ConsultaDto> cancelar(@PathVariable Long id) {
    }
    
    @GetMapping(value = "/medico/{medicoId}")
    public List<ConsultaDto> listarConsultasMedico(@PathVariable Long medicoId) {
        return consultaService.listarConsultasPorMedicoId(medicoId).stream().map(consultaMapper::toConsultaDto).toList();

    }
    
    @GetMapping(value = "/paciente/{pacienteId}")
    public List<ConsultaDto> listarConsultasPaciente(@PathVariable Long pacienteId) {
        return consultaService.listarConsultasPorPacienteId(pacienteId).stream().map(consultaMapper::toConsultaDto).toList();
    }
    
    @GetMapping(value = "/medico/{medicoId}/proximas")
    public List<ConsultaDto> listarProximasConsultasMedico(@PathVariable Long medicoId) {
        return consultaService.listarProximasConsultasPorMedicoId(medicoId).stream().map(consultaMapper::toConsultaDto).toList();
    }
    
    @GetMapping(value = "/paciente/{pacienteId}/proximas")
    public List<ConsultaDto> listarProximasConsultasPaciente(@PathVariable Long pacienteId) {
        return consultaService.listarProximasConsultasPorPacienteId(pacienteId).stream().map(consultaMapper::toConsultaDto).toList();
    }
}