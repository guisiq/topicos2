package controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import application.RepositoryException;
import application.Util;
import entities.Perfil;
import entities.Usuario;
import repository.Repository;
@Named
@ViewScoped
public class CadastroController extends CRUDController<Usuario>  implements Serializable{

	private static final long serialVersionUID = -932604672019115996L;

	@Override
    public Usuario getEntity() {
        if (entity == null) {
            entity = new Usuario();
            entity.setPerfil(Perfil.COMUN);
            entity.setAtivo(true);
        } else {
            
        }
        return entity ;
    }

    public String cadastrar() {
		Repository<Usuario> repo = new Repository<Usuario>();
		try {
            getEntity().setSenha(Util.hash(getEntity().getSenha() + getEntity().getLogin()));
			repo.save(getEntity());
			limpar();
			Util.addInfoMessage("Usuario salvo com sucesso.");
            return "login.xhtml?faces-redirect=true" ;
		} catch (RepositoryException e) {
			Util.addErrorMessage("Problema ao salvar, tente novamente ou entre em contato com a TI.");
            return "cadastro.xhtml";
		}
		
	}
}
