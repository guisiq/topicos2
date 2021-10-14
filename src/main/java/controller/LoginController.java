package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import application.RepositoryException;
import application.Util;
import entities.*;
import repository.*;
import application.Session;

@Named
@ViewScoped
public class LoginController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String login;
	private String senha;
	private Usuario usuario;
	private UsuarioRepo repo = new UsuarioRepo();
	
	public String entrar() {
		
		String hash = Util.hash(getUsuario().getSenha() + getUsuario().getLogin());
		getUsuario().setSenha(hash);
		Usuario usuarioLogado = repo.validaLogin(getUsuario());
		if (usuarioLogado != null) {
			//Adicionar na sessao o objeto usuarioLogado
 			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			context.getSessionMap().put("usuarioLogado", usuarioLogado);
			Session.getInstance().set("usuarioLogado", usuarioLogado);
			
			// redirecionando para o template
			return "ola.xhtml";
			
		}
		Util.addErrorMessage("Login ou senha inv�lido.");
		return null;
		
	}
	public Usuario getUsuario() {
		if (usuario == null) {
			usuario = new Usuario();
			usuario.setPerfil(Perfil.COMUN);
		}
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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

}
