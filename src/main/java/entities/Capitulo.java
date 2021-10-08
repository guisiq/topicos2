package entities;

import javax.persistence.Entity;

@Entity
public class Capitulo extends DefaultEntity{
    private String titulo;
    private String texto;

    

    public Capitulo() {
    }

    public Capitulo(String titulo, String texto) {
        this.titulo = titulo;
        this.texto = texto;
    }
    
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getTexto() {
        return texto;
    }
    public void setTexto(String texto) {
        this.texto = texto;
    }
    
}
