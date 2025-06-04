package engenhariaDeSoftware.demo.controller;

import engenhariaDeSoftware.demo.domain.medico.Medico;
import engenhariaDeSoftware.demo.domain.medico.MedicoService;
import engenhariaDeSoftware.demo.domain.medico.MedicoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @PostMapping
    public ResponseEntity<MedicoDto> cadastrar(@RequestBody MedicoDto medicoDto) {
        Medico medico = medicoService.salvar(medicoDto.toEntity());
        return ResponseEntity.ok(new MedicoDto(medico));
    }

    @GetMapping
    public ResponseEntity<Page<MedicoDto>> listar(Pageable paginacao) {
        Page<Medico> medicos = medicoService.listarPagina(paginacao);
        return ResponseEntity.ok(medicos.map(MedicoDto::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoDto> buscarPorId(@PathVariable Long id) {
        Medico medico = medicoService.buscarPorIdOrElseThrow(id);
        return ResponseEntity.ok(new MedicoDto(medico));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicoDto> atualizar(@PathVariable Long id, @RequestBody MedicoDto medicoDto) {
        Medico medico = medicoService.buscarPorIdOrElseThrow(id);
        medicoDto.atualizar(medico);
        medico = medicoService.salvar(medico);
        return ResponseEntity.ok(new MedicoDto(medico));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (medicoService.existe(id)) {
            medicoService.excluir(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/especialidade/{especialidade}")
    public ResponseEntity<Page<MedicoDto>> buscarPorEspecialidade(
            @PathVariable String especialidade,
            Pageable paginacao) {
        Page<Medico> medicos = medicoService.buscarPorEspecialidade(especialidade, paginacao);
        return ResponseEntity.ok(medicos.map(MedicoDto::new));
    }

    @GetMapping("/disponivel")
    public ResponseEntity<Page<MedicoDto>> listarMedicosDisponiveis(
            @RequestParam String data,
            @RequestParam String hora,
            Pageable paginacao) {
        Page<Medico> medicos = medicoService.listarMedicosDisponiveis(data, hora, paginacao);
        return ResponseEntity.ok(medicos.map(MedicoDto::new));
    }
}
