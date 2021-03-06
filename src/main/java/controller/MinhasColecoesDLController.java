package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import application.RepositoryException;
import application.Session;
import application.Util;
import controller.listen.ColecaoListing;
import entities.*;
import repository.*;

@Named
@ViewScoped
public class MinhasColecoesDLController extends CRUDController<Colecao> implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Colecao> colecoes;
	private List<Colecao> colecoesSelection;
	private ColecaoListing listing;

	
	private Usuario usuarioLog;
	private ColecaoRepo repo = new ColecaoRepo();
	

	


	public List<Colecao> getColecoesSelection() {
		return colecoesSelection;
	}
	public void setColecoesSelection(List<Colecao> colecoesSelection) {
		this.colecoesSelection = colecoesSelection;
	}
	public void openNew() {
		System.out.println("passou por aqui");
		
        this.entity = null;
		getEntity();
        
    }
	public void select(Colecao colecao){
		var livro = (Livro)Session.getInstance().get("livro Colecao");
		colecao.getLivros().add(livro);
		try {
			repo.save(colecao);
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.listing.select(colecao.getId());
	}
	public List<Colecao> getColecoes() {
		if(colecoes == null) {
			try {
				colecoes = repo.getbyAutor(getUsuarioLog());
			}catch (Exception e) {
				Util.addErrorMessage("nao encontramos colecoes");
			}
		}
		return colecoes;
	}
	public void setColecoes(List<Colecao> colecoes) {
		this.colecoes = colecoes;
	}
	public void deleteColecao(Colecao c) {
		   try {
				repo.remove(c);
				//Colecaos.remove(l); 
				Util.addInfoMessage("Colecao removido" );
				PrimeFaces.current().ajax().update("form:messages", "form:dt-Colecaos");
			} catch (RepositoryException e) {
				Util.addErrorMessage("erro ao remover ");
				e.printStackTrace();
			}
	}
	public void deleteColecao() {
        try {
			repo.remove(this.colecoesSelection);
			colecoes.removeAll(colecoesSelection);
			colecoesSelection = null; 
			Util.addInfoMessage("Colecao removido" );
			PrimeFaces.current().ajax().update("form:messages", "form:dt-Colecaos");
		} catch (RepositoryException e) {
			Util.addErrorMessage("erro ao remover ");
			e.printStackTrace();
		}
        
    }
	public void saveColecao() {
		try {

			repo.save(getEntity());
			if (!colecoes.contains(getEntity())) {
				colecoes.add(getEntity());
			}
			PrimeFaces.current().executeScript("PF('manageColecaoDialog').hide()");
			PrimeFaces.current().ajax().update("form:messages", "form:dt-Colecaos");
			Util.addInfoMessage("Colecao Adicionado" );
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
	public ColecaoListing getListing() {
		if (listing == null){
			this.listing = new ColecaoListing();
		}
		return listing;
		
	}
	public void setListing(ColecaoListing listing) {
		this.listing = listing;
		
	}
	@Override
	public Colecao getEntity() {
		if (entity == null){
			this.entity = new Colecao();
			this.entity.setAutor(getUsuarioLog());
		}
		return entity;
	}

	

	

}
