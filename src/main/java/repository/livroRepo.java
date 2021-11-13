package repository;
import entities.Livro;
import entities.Usuario;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import application.JPAUtil;
import application.RepositoryException;

public class livroRepo extends Repository<Livro>{
    
    public List<Livro> getAll() throws RepositoryException {
		Class clazz = Livro.class;
        try { 
			String entityName = clazz.getSimpleName();
			// jpql
			Query query = em.createQuery("SELECT u FROM "+entityName+" u");
			return query.getResultList();
		} catch (Exception e) {
			System.out.println("Erro ao executar o getAll");
			e.printStackTrace();
			throw new RepositoryException("Erro ao Consultar no banco.");
		}

	}   
    public List<Livro> getbyAutor(Usuario autor) throws RepositoryException {
		Class clazz = Livro.class;
        try { 
			/*
			
			String entityName = clazz.getSimpleName();
			// jpql
			Query query = em.createQuery("SELECT u FROM "+entityName+" u WHERE u.autor ="+ autor);
			return query.getResultList();
			
			*/
			List<Livro> retorno = getAll();
			retorno.removeIf( l -> {
				if (l != null) {	
					return !(l.getId() == autor.getId()); 
				}else{
					return true;
				}
			});
			return retorno;
		} catch (Exception e) {
			System.out.println("Erro ao executar o getAll");
			e.printStackTrace();
			throw new RepositoryException("Erro ao Consultar no banco.");
		}

	}   
}
