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
public class GerenciamentoDeUsuarioController extends CRUDController<Usuario> implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Usuario> usuarios;

	private UsuarioRepo repo = new UsuarioRepo();
	
	public List<Usuario> getUsuarios() {
		if (usuarios == null) {
			try{
				usuarios = repo.getAll();
			} catch (RepositoryException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	public void promover(Usuario usuario) {
		try {
			usuario.setPerfil(Perfil.MODERADOR);
			repo.save(usuario);
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void bloquear(Usuario usuario) {
		try {
			usuario.setAtivo(!usuario.isAtivo());
			repo.save(usuario);
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



	@Override
	public Usuario getEntity() {
		if (entity == null){
			entity = new Usuario();
		}
		return entity;
	}
	

}
