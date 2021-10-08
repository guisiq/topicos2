package entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
@Entity
public class Comentario extends DefaultEntity{
    private String texto;
    private Livro livro;
    private LocalDateTime dataPublicacao ;

    
    public Comentario() {
    }
    public Comentario(String texto, entities.Livro livro, LocalDateTime dataPublicacao) {
        this.texto = texto;
        this.livro = livro;
        this.dataPublicacao = dataPublicacao;
    }
    public String getTexto() {
        return texto;
    }
    public void setTexto(String texto) {
        this.texto = texto;
    }
    public Livro getLivro() {
        return livro;
    }
    public void setLivro(Livro livro) {
        this.livro = livro;
    }
    public LocalDateTime getDataPublicacao() {
        return dataPublicacao;
    }
    public void setDataPublicacao(LocalDateTime dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    
    
}
