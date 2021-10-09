package entities;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import entities.Comentario;
import entities.Usuario;
import entities.Livro;
@Entity
public class Leitura extends DefaultEntity{
    private boolean curtida;
    private boolean completo;
    private int capitulosLidos;
    @OneToOne
    private Usuario usuario;
    @ManyToOne
    private Livro livro;
    private List<Comentario> comentarios;

    
    public Leitura() {
    }
    public Leitura(boolean curtida, boolean completo, int capitulosLidos, Usuario usuario, entities.Livro livro,
            List<Comentario> comentarios) {
        this.curtida = curtida;
        this.completo = completo;
        this.capitulosLidos = capitulosLidos;
        this.usuario = usuario;
        this.livro = livro;
        this.comentarios = comentarios;
    }
    public boolean isCurtida() {
        return curtida;
    }
    public void setCurtida(boolean curtida) {
        this.curtida = curtida;
    }
    public boolean isCompleto() {
        return completo;
    }
    public void setCompleto(boolean completo) {
        this.completo = completo;
    }
    public int getCapitulosLidos() {
        return capitulosLidos;
    }
    public void setCapitulosLidos(int capitulosLidos) {
        this.capitulosLidos = capitulosLidos;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Livro getLivro() {
        return livro;
    }
    public void setLivro(Livro livro) {
        this.livro = livro;
    }
    public List<Comentario> getComentarios() {
        return comentarios;
    }
    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }
    
}
