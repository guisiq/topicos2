package repository;

import entities.Usuario;
import java.util.List;
import javax.persistence.Query;

import application.RepositoryException;

public class UsuarioRepo extends Repository<Usuario> {
    
    public Usuario obterUm(int id) {
		Usuario usu = em.find(Usuario.class,id);
		
		return usu;
	}
	
	public Usuario validaLogin(Usuario usu) {
		
		Usuario usuarioLogado = null;
		
		Query query = em.createQuery("SELECT u FROM Usuario u WHERE  u.login LIKE :login AND u.senha LIKE :senha");
		
		/*
		usuarioLogado = (Usuario) em.createQuery(sql.toString(),Usuario.class).setParameter("email", usu.getEmail())
		.setParameter("senha", usu.getSenha()).getSingleResult();
		
		
		var query =  em.createQuery(sql.toString(),Usuario.class);
        */
        query.setParameter("login", usu.getLogin());
		query.setParameter("senha", usu.getSenha());
        try {
        	usuarioLogado = (Usuario)query.getSingleResult();			
		} catch (Exception e) {
			System.out.println("usuario nao encontrado ");
			System.out.println(usu.getLogin());
			System.out.println(usu.getSenha());
		}
		
		return usuarioLogado;

	}
}
