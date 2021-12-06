package controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import application.RepositoryException;
import application.Session;
import application.Util;
import entities.Livro;
import repository.LivroRepo;

@Named
@RequestScoped
public class LivrosController {
	private Livro livro;
	private LivroRepo repo = new LivroRepo() ;
	public Livro getLivro() {
		return livro;
	}
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	
	public void setLivro(int id) {
		try {
			this.livro = repo.findById(id);
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			this.livro = null;
		}
	}
	
	
  
}
