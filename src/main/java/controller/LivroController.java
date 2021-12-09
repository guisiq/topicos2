package controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import application.RepositoryException;
import application.Session;
import application.Util;
import entities.Capitulo;
import entities.Livro;
import repository.LivroRepo;


@Named
@ViewScoped
public class LivroController  implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer livroid;
	private Livro livro;
	private Capitulo capitulo;
	private LivroRepo repo = new LivroRepo() ;

	

	public Capitulo getCapitulo() {
		if(capitulo == null) {
			capitulo = livro.getCapitulos().size() > 0 ? livro.getCapitulos().get(0):null;
		}
		return capitulo;
	}
	public void setCapitulo(Capitulo capitulo) {
		this.capitulo = capitulo;
	}
	public Integer getLivroid() {
		
		return livroid;
	}
	public void procimoCapitulo() {
		
		var idiceProcimo = getLivro().getCapitulos().indexOf(capitulo)+1;
		if((idiceProcimo > getLivro().getCapitulos().size()-1)) {
			capitulo = livro.getCapitulos().get(0);			
		}else {
			capitulo = livro.getCapitulos().get(idiceProcimo);						
		}
		
	}
	public void capituloAnterior() {
		
		var idiceAnterior = getLivro().getCapitulos().indexOf(capitulo)-1;
		if((idiceAnterior < getLivro().getCapitulos().size())) {
			capitulo = livro.getCapitulos().get(idiceAnterior);			
		}
	}
	public void setLivroid(Integer livroid) {
		this.livroid = livroid;
	}
	public Livro getLivro() {
		if(livro == null){
			try {
				livro = repo.findById(livroid);
			} catch (RepositoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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
