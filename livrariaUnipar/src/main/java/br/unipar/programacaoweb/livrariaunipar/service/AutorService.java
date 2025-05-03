package br.unipar.programacaoweb.livrariaunipar.service;

import br.unipar.programacaoweb.livrariaunipar.model.Autor;
import br.unipar.programacaoweb.livrariaunipar.repository.AutorRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.hibernate.Hibernate;

import java.util.List;

@Service
public class AutorService {

    private final AutorRepository autorRepository;
    public AutorService(AutorRepository autorRepository) {this.autorRepository = autorRepository;}

    public Autor salvar(Autor autor) {
        return autorRepository.save(autor);
    }

    public Autor buscarPorId(Long id) {
        return autorRepository.findById(id).orElse(null);
    }

    public List<Autor> buscarAutorNome(String nome){
        return autorRepository.findByNomeContainingIgnoreCase(nome);
    }

    public List<Autor> listarTodos() {
        return autorRepository.findAll();
    }

    @Transactional
    public List<Autor> listarTodosComLivros() {

                List<Autor> autores = autorRepository.findAll();

        autores.forEach(autor -> Hibernate.initialize(autor.getLivros()));
        return autores;
    }

    public Autor editar(Long id, Autor novoAutor) {
        Autor autorExistente = buscarPorId(id);
        if (autorExistente != null) {
            autorExistente.setNome(novoAutor.getNome());
            autorExistente.setNacionalidade(novoAutor.getNacionalidade());
            autorExistente.setDataNascimento(novoAutor.getDataNascimento());
            autorExistente.setEmail(novoAutor.getEmail());
            return autorRepository.save(autorExistente);
        }
        return null;
    }

    public void excluir(Long id) {
        autorRepository.deleteById(id);
    }

}
