package controller;

import entities.DefaultEntity;
import entities.Perfil;
import entities.Usuario;

public class cadastroController extends CRUDController<Usuario> {

    @Override
    public Usuario getEntity() {
        if (entity == null) {
            entity = new Usuario();
            entity.setPerfil(Perfil.COMUN);
            entity.setAtivo(true);
        } else {
            
        }
        return null;
    }
}
