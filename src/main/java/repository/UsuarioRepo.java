package repository;

import javax.persistence.Query;

import application.Message;

import application.RepositoryException;
import entities.Usuario;

public class UsuarioRepo extends Repository<Usuario> {
    @Override
    public Usuario save(Usuario entity) throws RepositoryException {
    	try {
			
			em.getTransaction().begin();
			Usuario  objeto = em.merge(entity);
			em.getTransaction().commit();
			
			return objeto;
		} catch (Exception e) {
			Message.addErrorMessage("Problemas ao executar a funo!");
			e.printStackTrace();
			return null;
		}
    }
    
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

	@SuppressWarnings("unchecked")
	public Usuario findByEmail(String email) throws RepositoryException {
		try { 
			
			//JPQL ou SQL
			Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.email = :email ");
			query.setParameter("email", email);
			
			return (Usuario) query.getSingleResult();
		} catch (Exception e) {
			// mandando pro console o exception gerado
			e.printStackTrace();
			// repassando a excecao para quem vai executar o metodo
			throw new RepositoryException("Problema ao pesquisar usu�rios.");
		}
	}

	
}
