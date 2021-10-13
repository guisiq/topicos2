package entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
@Entity
public class Livro extends DefaultEntity {
    private String titulo;
    private String autor;
    private String sinopse;
    private int edicao;
    @ManyToOne
    private Usuario usuario;
    @Enumerated(EnumType.ORDINAL)
    private Genero genero;
    @OneToMany
    private List<Capitulo> capitulos;

    
    public Livro(String titulo, String autor, String sinopse, Usuario usuario, Genero genero,
            List<Capitulo> capitulos) {
        this.titulo = titulo;
        this.autor = autor;
        this.sinopse = sinopse;
        this.usuario = usuario;
        this.genero = genero;
        this.capitulos = capitulos;
    }

    public Livro() {
        super();
    }

    public int getEdicao() {
        return edicao;
    }

    public void setEdicao(int edicao) {
        this.edicao = edicao;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Genero getGenero() {
        return genero;
    }
    public void setGenero(Genero genero) {
        this.genero = genero;
    }
    public List<Capitulo> getCapitulos() {
        return capitulos;
    }
    public void setCapitulos(List<Capitulo> capitulos) {
        this.capitulos = capitulos;
    }
    
}
