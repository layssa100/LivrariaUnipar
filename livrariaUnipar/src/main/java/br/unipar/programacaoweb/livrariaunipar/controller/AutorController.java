package br.unipar.programacaoweb.livrariaunipar.controller;

import br.unipar.programacaoweb.livrariaunipar.dto.AutorLivrosDto;
import br.unipar.programacaoweb.livrariaunipar.model.Autor;
import br.unipar.programacaoweb.livrariaunipar.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autor")
public class AutorController {
    private final AutorService autorService;
    private AutorController autorController;

            @Autowired
    public AutorController(AutorController autorController, AutorService autorService) {this.autorController = autorController;
                this.autorService = autorService;
            }

    @GetMapping("/listar")
    public ResponseEntity<List<Autor>> listarTodos(){
        List<Autor> autores = autorService.listarTodos();
        if (autores.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(autores);
    }

    @GetMapping("/buscar/{id}")
    public  ResponseEntity<Autor> buscarAutorPorId(@PathVariable Long id) {
        Autor autor = autorService.buscarPorId(id);
        if (autor == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(autor);
    }

    @GetMapping("/buscar/autor/{nome}")
    public ResponseEntity<List<Autor>> buscarAutorNome(@PathVariable String nome){
        List<Autor> autor = autorService.buscarAutorNome(nome);
        if (autor.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(autor);
    }

    @PostMapping("/salvar")
        public ResponseEntity<Autor> salvarAutor (@RequestBody Autor autor){
        Autor autorSalvo = autorService.salvar(autor);
        return  ResponseEntity.status(HttpStatus.CREATED).body(autorSalvo);
        }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Autor> editarAutor(@PathVariable Long id,
                                             @RequestBody Autor autor) {
        Autor autorAtual = autorService.buscarPorId(id);
        if(autorAtual == null){
            return ResponseEntity.notFound().build();
        }

        autorAtual.setNome(autor.getNome());
        autorAtual.setNacionalidade(autor.getNacionalidade());
        autorAtual.setDataNascimento(autor.getDataNascimento());
        autorAtual.setEmail(autor.getEmail());

        return ResponseEntity.ok(autorService.salvar(autorAtual));
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> excluirAutor(@PathVariable Long id) {
        Autor autor = autorService.buscarPorId(id);
        if (autor == null) {
            return ResponseEntity.notFound().build();
        }
        autorService.excluir(id);

        return ResponseEntity.noContent().build();
    }
    @GetMapping("/com-livros")
    public ResponseEntity<List<AutorLivrosDto>> listarAutoresComLivros() {
        List<Autor> autores = autorService.listarTodosComLivros();
        List<AutorLivrosDto> dtos = autores.stream()
                .map(AutorLivrosDto::new)
                .toList();
        return ResponseEntity.ok(dtos);
    }

}


