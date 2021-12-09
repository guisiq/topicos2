package controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.primefaces.event.SelectEvent;

import application.JPAUtil;
import application.RepositoryException;
import application.Session;
import application.Util;
import controller.listen.ColecaoListing;
import application.JPAUtil;
import entities.Colecao;
import entities.Livro;
import repository.*;

@Named
@ViewScoped
public class HomeLivrosController extends CRUDController<Livro> implements Serializable{

	private static final long serialVersionUID = 6022204328275496136L;
	private List<Livro> livros;
    LivroRepo repo = new LivroRepo();

	public void  abrirColecaoListing(Livro livro) {
		Session.getInstance().set("livro Colecao", livro);
		ColecaoListing listing = new ColecaoListing();
		listing.open();
		System.out.println("Abrir Estado List1");

	}

	public void obterColecaoListing(SelectEvent<Colecao> event) {
		System.out.println(event.getObject());

	}

	public void ler(Livro livro) {
		Util.redirect("/topicos2/livros.xhtml?idlivro="+livro.getId());
	}
	 
	public void cadastrar() {
        try {
			repo.save(entity);
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setEntity(entity);
	}
    
	public List<Livro> getLivros() {
		
		try {
			String filtro = (String) Session.getInstance().get("filtroLivro");
			//livros = repo.getbyTituloCase(filtro);
			return livros = repo.getbyTitulo(filtro);
			/*if(livros.size() == 0 ) {
				return livros = repo.getAll();
			}else {
				return livros;
			}*/
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Util.addErrorMessage("");
			return null;
		}
	}
	
    public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}

	public void editar(Livro livro) {
		this.setEntity(livro);
	}
	
    @Override
    public Livro getEntity() {
        if (entity == null)
        entity = new Livro();
        return entity;
    }

    @Override
    public void limpar() {
        setEntity(null);
    }
    
}

