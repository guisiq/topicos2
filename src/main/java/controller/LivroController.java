package controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;

import application.JPAUtil;
import application.RepositoryException;
import application.JPAUtil;
import entities.Livro;
import repository.*;

@Named
@ViewScoped
public class LivroController extends CRUDController<Livro> implements Serializable{

	private static final long serialVersionUID = 6022204328275496136L;
	private List<Livro> livros;
    Repository<Livro> repo = new Repository<Livro>();
	
    public List<Livro> getLivros() {
		return livros;
	}
	
    public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}

	public void editar(Livro livro) {
		this.setEntity(livro);
	}
	public String redirecionar() {
		return "cadastro.xhtml?faces-redirect=true";
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

