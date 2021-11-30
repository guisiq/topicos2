package controller;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.primefaces.event.FlowEvent;

import application.Email;
import application.RepositoryException;
import application.Util;
import entities.*;
import repository.*;
import application.Session;

@Named
@ViewScoped
public class RecuperarSenhaController  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String email;
	private String senha;
	private String senha2;
	private String codigo;
	private boolean skip = false ;

	private Usuario usuario;

	private RecuperarSenhaRepository repoRecuperar = new RecuperarSenhaRepository();
	private UsuarioRepo repo = new UsuarioRepo();
	private static String gerarCodico(){
		// gerando codigo aleatorio
		Random r = new Random();
		DecimalFormat format = new DecimalFormat("T-000000");
		return format.format(r.nextInt(1000000));
	}
	
	public void enviarEmail() {
		Usuario usuario;
		try {
			usuario = repo.findByEmail(email);
		} catch (RepositoryException e) {
			Util.addErrorMessage("Email nï¿½o encontrado.");
			return;
		}
		String codigo = gerarCodico();
		
		RecuperarSenha entity = new RecuperarSenha();
		entity.setCodigo(codigo);
		entity.setUsuario(usuario);
		entity.setUtilizado(false);
		// definindo 24 horas de tempo limite
		entity.setDataLimite(LocalDateTime.now().plusDays(1));
		
		
		try {
			repoRecuperar.save(entity);
			Email email = new Email(usuario.getEmail(), 
									"Esqueceu a Senha",
									" \n o seu codigo de recuperacão de senha:"+codigo);
			email.enviar();
			Util.addInfoMessage("O codigo foi enviado para o seu email.");
		} catch (RepositoryException e) {
			e.printStackTrace();
			Util.addErrorMessage("Falha ao enviar o codigo.");
		}
		
	}
	public String onFlowProcess(FlowEvent event) {
        if (skip) {
            skip = false; //reset in case user goes back
            return "confirm";
        }
        else {
            return event.getNewStep();
        }
    }
	private boolean validarCodigo() {
		try {
			return !repoRecuperar.getbycodigo(getCodigo()).isEmpty();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public void mudarSenha(){
		if(validarCodigo()) {
			String hash = Util.hash(getSenha() + usuario.getLogin());
			usuario.setSenha(hash);
			try {
				repo.save(usuario);
			} catch (RepositoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Util.addErrorMessage("Falha ao redefinir senha ");
			}
		}
	}
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getSenha2() {
		return senha2;
	}
	public void setSenha2(String senha2) {
		this.senha2 = senha2;
	}
	/*
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}*/
	
}
