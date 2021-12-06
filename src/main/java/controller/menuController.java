package controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import application.Session;
import application.Util;
import repository.LivroRepo;

@Named
@RequestScoped
public class menuController {
	private String filtro;
	private LivroRepo repo;

	
  
	public String encerrarSessao() {
		Session.getInstance().invalidateSession();
		System.out.println("passou por aqui");
        Util.redirect("/topicos2/login.xhtml");
		return "/topicos2/login.xhtml?faces-redirect=true" ;
	}
    public void perfil() {
		System.out.println("passou por aqui");
        Util.redirect("/topicos2/pages/controle_de_usuario.xhtml");
		//return "/topicos2/login.xhtml?faces-redirect=true" ;
	}
    public void homeLivros() {
		System.out.println("passou por aqui 2 ");
		System.out.println(filtro);
		Session.getInstance().set("filtroLivro", filtro);
        Util.redirect("/topicos2/homelivros.xhtml");
	}

	public String getFiltro() {
		return filtro;
	}
	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
}
