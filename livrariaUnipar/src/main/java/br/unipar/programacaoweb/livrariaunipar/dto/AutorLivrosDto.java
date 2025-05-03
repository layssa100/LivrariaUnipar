package br.unipar.programacaoweb.livrariaunipar.dto;

import br.unipar.programacaoweb.livrariaunipar.model.Autor;
import br.unipar.programacaoweb.livrariaunipar.model.Livro;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
public class AutorLivrosDto {
    private int id;
    private String nome;
    private String nacionalidade;
    private Date dataNascimento;
    private String email;
    private List<Livro> livros;

    public AutorLivrosDto(Autor autor) {
        this.id = autor.getId();
        this.nome = autor.getNome();
        this.nacionalidade = autor.getNacionalidade();
        this.dataNascimento= autor.getDataNascimento();
        this.email = autor.getEmail();
        this.livros = autor.getLivros();
    }
}
