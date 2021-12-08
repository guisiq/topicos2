package controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import application.RepositoryException;
import application.Session;
import application.Util;
import entities.*;
import repository.*;

@Named
@ViewScoped
public class MeusCapitulosController extends CRUDController<Capitulo> implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Capitulo> capitulos;
	private List<Capitulo> capitulosSelection;


	private Usuario usuarioLog;
	private Livro livro;
	private CapituloRepo repo = new CapituloRepo();
	

	


	
	public void CapituloRemove() {
		getCapitulos().remove(getEntity());
	}
	public void CapituloAterior() {
		int indice = getCapitulos().indexOf(getEntity())-1;
		if(indice >= 0) {
			entity = getCapitulos().get(indice);			
		}
	}
	public void CapituloProcimo() {
		int indice = getCapitulos().indexOf(getEntity())+1;
		if(indice  >= getCapitulos().size() ) {
			entity = new Capitulo();			
		}
	}
	
	public void deleteCapitulo(Capitulo c) {
		   try {

				repo.remove(c);
				//CapituloCapitulos.remove(l); 
				Util.addInfoMessage("Capitulo removido" );
				PrimeFaces.current().ajax().update("form:messages", "form:dt-CapituloCapitulos");
			} catch (RepositoryException e) {
				Util.addErrorMessage("erro ao remover ");
				e.printStackTrace();
			}
	}


	public void saveCapitulo() {
		try {

			repo.save(getEntity(),livro.getId());
			if (!capitulos.contains(getEntity())) {
				capitulos.add(getEntity());
			}

			//adicionando mensagens eexecutando eventos 
			PrimeFaces.current().executeScript("PF('manageCapituloDialog').hide()");
			PrimeFaces.current().ajax().update("form:messages", "form:dt-Capitulos");
			Util.addInfoMessage("Capitulo Adicionado" );
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public Usuario getUsuarioLog() {
		if (usuarioLog == null) {
			try {
				usuarioLog = (Usuario)Session.getInstance().get("usuarioLogado");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return usuarioLog;
	}
	
	public void setUsuarioLog(Usuario usuarioLog) {
		this.usuarioLog = usuarioLog;
	}
	
	public List<Capitulo> getCapitulos() {
		if (this.capitulos == null){
			this.capitulos = getLivro().getCapitulos();
		}
		return this.capitulos;
	}
	public void setCapitulos(List<Capitulo> capitulos) {
		this.capitulos = capitulos;
	}
	
	public List<Capitulo> getCapitulosSelection() {
		return capitulosSelection;
	}
	public void setCapitulosSelection(List<Capitulo> capitulosSelection) {
		this.capitulosSelection = capitulosSelection;
	}
	
	public Livro getLivro() {
		if (livro == null){
			this.livro = (Livro)Session.getInstance().get("livro");
			
		}
		return livro;
	}
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	
	@Override
	public Capitulo getEntity() {
		if (entity == null){
			if(getCapitulos().size() > 0 ) {				
				this.entity = getCapitulos().get(0);
			}else {
				this.entity = new Capitulo();
			}
		}
		return entity;
	}
	

}
