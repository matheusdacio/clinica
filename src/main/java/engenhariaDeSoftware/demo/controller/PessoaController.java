package engenhariaDeSoftware.demo.controller;

//import br.com.pessoa_api.pessoa_api.dto.DadosAtualizacaoPessoaDto;
//import br.com.pessoa_api.pessoa_api.dto.DadosCadastroPessoaDto;
//import br.com.pessoa_api.pessoa_api.dto.DadosDetalhamentoPessoaDto;
//import br.com.pessoa_api.pessoa_api.dto.DadosListagemPessoaDto;
//import br.com.pessoa_api.pessoa_api.model.Pessoa;
//import br.com.pessoa_api.pessoa_api.repository.PessoaRepository;
//import br.com.pessoa_api.pessoa_api.service.PessoaService;
//import br.com.pessoa_api.pessoa_api.validadores.ValidacaoException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("pessoas")
public class PessoaController {
//
//    @Autowired
//    private PessoaRepository pessoaRepository;
//
//    @Autowired
//    private PessoaService pessoaService;
//
//    @PostMapping
//    @Transactional
//    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroPessoaDto dto) {
//        try {
//            this.pessoaService.validarCadastro(dto);
//            return ResponseEntity.ok("Pessoa Cadastrada com Sucesso!!");
//        } catch (ValidacaoException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }
//
//    @GetMapping
//    @Transactional
//    public ResponseEntity<Page<DadosListagemPessoaDto>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
//        var page = pessoaRepository.findAll(paginacao).map(DadosListagemPessoaDto::new);
//        return ResponseEntity.ok(page);
//    }
//
//    @PutMapping
//    @Transactional
//    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoPessoaDto dto) {
//        var pessoa = pessoaRepository.getReferenceById(dto.idPessoa());
//        pessoa.atualizarPessoa(dto);
//        return ResponseEntity.ok(new DadosDetalhamentoPessoaDto(pessoa));
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
//        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
//        return pessoa.map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    @DeleteMapping("/{id}")
//    @Transactional
//    public ResponseEntity<Void> excluir(@PathVariable Long id) {
//        if (pessoaService.findByExist(id)) {
//            pessoaService.deletar(id);
//            return ResponseEntity.noContent().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

}
