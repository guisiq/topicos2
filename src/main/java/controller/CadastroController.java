package controller;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import entities.DefaultEntity;
import entities.Perfil;
import entities.Usuario;
@Named
@ViewScoped
public class CadastroController extends CRUDController<Usuario> {

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
}
