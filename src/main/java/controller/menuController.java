package controller;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import application.RepositoryException;
import application.Session;
import application.Util;
import entities.Livro;
import repository.LivroRepo;

@Named
@RequestScoped
public class menuController {
	private String filtro;
	private LivroRepo repo;
	private Livro livro;

	
  
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
    public List<Livro> completLivro(String  pesquisa) {
    	filtro = pesquisa;
    	repo = new LivroRepo();
    	try {
			return repo.getbyTitulo(filtro);
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }
    
    public void gerenciarLivro() {
    	if(!(livro == null)) {
    		Util.redirect("/topicos2/livros.xhtml?idlivro=" + livro.getId());    		
    	}else {
    		homeLivros();
    	}

	}
    public void homeLivros() {
		System.out.println("passou por aqui 2 ");
		System.out.println(filtro);
		Session.getInstance().set("filtroLivro", filtro);
        Util.redirect("/topicos2/homelivros.xhtml");
	}
    public void meuslivro() {
    	Util.redirect("/topicos2/meuslivros.xhtml");
    }
    public void minhasColecoes() {
    	Util.redirect("/topicos2/minhasColecoes.xhtml");
    }

	public String getFiltro() {
		return filtro;
	}
	
	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	
	
}
