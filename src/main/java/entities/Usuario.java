package entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
@Entity
public class Usuario extends DefaultEntity {
    private String login;
    private String senha;
    private String nome;
    private String telefone;
    private String email;
    private boolean ativo;
    @Enumerated(EnumType.ORDINAL)
    private Perfil perfil;


    

    public Usuario() {
    }

    public Usuario(String login, String senha, String nome, String telefone, String email, boolean ativo) {
        this.login = login;
        this.senha = senha;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.ativo = ativo;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public boolean isAtivo() {
        return ativo;
    }
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
}
