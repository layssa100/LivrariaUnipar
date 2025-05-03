package br.unipar.programacaoweb.livrariaunipar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import br.unipar.programacaoweb.livrariaunipar.model.Autor;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository  extends JpaRepository<Autor, Integer> {
        List<Autor> findByNomeContainingIgnoreCase(String nome);
    }
