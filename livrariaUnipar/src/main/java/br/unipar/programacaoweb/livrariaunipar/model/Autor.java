package br.unipar.programacaoweb.livrariaunipar.model;

import br.unipar.programacaoweb.livrariaunipar.model.Livro;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter

public class Autor {

    @id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String nacionalidade;
    private Date dataNascimento;
    private String email;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Livro> livros = new ArrayList<>();
}
