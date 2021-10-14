package entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
@Entity
public class Colecao extends DefaultEntity{
    private boolean publica;
    private String titulo;
    private String descricao;
    @ManyToOne
    private Usuario autor;
    @ManyToMany
    private List<Livro> livros;
    //#region construtores
    public Colecao() {
    }
    public Colecao(boolean publica, String titulo, Usuario autor, List<Livro> livros) {
        this.publica = publica;
        this.titulo = titulo;
        this.autor = autor;
        this.livros = livros;
    }
    //#endregion
    //#region geters e seters 
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
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
    //#endregion
    
    
}
