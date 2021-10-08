package entities;

import java.util.List;

import javax.persistence.Entity;
@Entity
public class Colecao extends DefaultEntity{
    private boolean publica;
    private String titulo;
    private Usuario autor;
    private List<Livro> livros;
    
    public Colecao() {
    }
    public Colecao(boolean publica, String titulo, Usuario autor, List<Livro> livros) {
        this.publica = publica;
        this.titulo = titulo;
        this.autor = autor;
        this.livros = livros;
    }
    public boolean isPublica() {
        return publica;
    }
    public void setPublica(boolean publica) {
        this.publica = publica;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public Usuario getAutor() {
        return autor;
    }
    public void setAutor(Usuario autor) {
        this.autor = autor;
    }
    public List<Livro> getLivros() {
        return livros;
    }
    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }
    
    
}
