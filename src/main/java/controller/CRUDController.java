package controller;


import application.RepositoryException;
import application.Util;
import entities.*;
import repository.Repository;


public abstract class CRUDController<T extends DefaultEntity> {
	
	protected T entity = null;
	//protected List<T> entitys = null;
	
	public CRUDController() {
		super();
	}

	public abstract T getEntity();

	public void setEntity(T entity) {
		this.entity = entity;
	}

	/* public List<Livro> getentitys() {
		if (entitys == null) {
			UsuarioRepository repo = new UsuarioRepository();
			try {
				entitys = repo.getAll(Usuario.class);
			} catch (RepositoryException e) {
				entitys = new ArrayList<Usuario>();
			}
		}
		return entitys;
	}

	public void setentitys(List<Usuario> entitys) {
		this.entitys = entitys;
	} */
	
	public void limpar() {
		setEntity(null);
	}

	public void salvar() {
		Repository<T> repo = new Repository<T>();
		try {
			repo.save(getEntity());
			limpar();
			Util.addInfoMessage("Usuario salvo com sucesso.");
		} catch (RepositoryException e) {
			Util.addErrorMessage("Problema ao salvar, tente novamente ou entre em contato com a TI.");
		}
		
	}

	public void excluir() {
		Repository<T> repo = new Repository<T>();
		try {
			repo.remove(getEntity());
			limpar();
			Util.addInfoMessage("Usuario removido com sucesso.");
		} catch (RepositoryException e) {
			Util.addErrorMessage("Problema ao remover, tente novamente ou entre em contato com a TI.");
		}
		
	}

}