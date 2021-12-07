package controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.file.UploadedFile;

import application.JPAUtil;
import application.RepositoryException;
import application.Session;
import application.Util;
//import controller.listing.EstadoListingSQL;
//import controller.listing.PessoaFisicaListing;

import entities.Perfil;
import entities.Usuario;
import repository.Repository;
import repository.UsuarioRepo;

@Named
@ViewScoped
public class UsuarioController extends CRUDController<Usuario> implements Serializable {

	private static final long serialVersionUID = 8004269176157113667L;
	
	private InputStream fotoInputStream = null;
	
	public void upload(FileUploadEvent event) {
		UploadedFile uploadFile = event.getFile();
		System.out.println("nome arquivo: " + uploadFile.getFileName());
		System.out.println("tipo: " + uploadFile.getContentType());
		System.out.println("tamanho: " + uploadFile.getSize());

		if (uploadFile.getContentType().equals("image/png")) {
			try {
				fotoInputStream = uploadFile.getInputStream();
				System.out.println("inputStream: " + uploadFile.getInputStream().toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Util.addInfoMessage("Upload realizado com sucesso.");
		} else {
			Util.addErrorMessage("O tipo da image deve ser png.");
		}

	}
	
	
	public Perfil[] getListaPerfil() {
		return Perfil.values();
	}
	
	

	@Override
	public void salvar() {
		UsuarioRepo repo = new UsuarioRepo();
		try {
			System.out.println(getEntity());
			repo.save(getEntity());
			limpar();
			Util.addInfoMessage("Usuario salvo com sucesso.");
		} catch (RepositoryException e) {
			Util.addErrorMessage("Problema ao salvar, tente novamente ou entre em contato com a TI.");
		}
		
	}
	
	@Override
	public Usuario getEntity() {
		if (entity == null) {
			entity = (Usuario)Session.getInstance().get("usuarioLogado");
		}
		return entity;
	}

	@Override
	public void limpar() {
		super.limpar();
		
	}

	public void editar(Integer id) {
		EntityManager em = JPAUtil.getEntityManager();
		setEntity(em.find(Usuario.class, id));
		
		
	}

	public InputStream getFotoInputStream() {
		return fotoInputStream;
	}

	public void setFotoInputStream(InputStream fotoInputStream) {
		this.fotoInputStream = fotoInputStream;
	}

}
