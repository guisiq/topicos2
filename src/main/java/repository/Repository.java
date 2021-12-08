package repository;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import application.JPAUtil;
import application.RepositoryException;

public class Repository<T> {
	
	protected EntityManager em = null;
	
	public Repository() {
		em = JPAUtil.getEntityManager();
	}
	
	public T save(T entity) throws RepositoryException {
		try { 
			var em = getEntityManager();
			
			if(!em.getTransaction().isActive()) 
		        em.getTransaction().begin();
				em.persist(entity);
				
				//em.getTransaction().commit();
				//em.close();
				
				T e = em.merge(entity);
				em.getTransaction().commit();
				em.close();
				return e;
		} catch (Exception e) {
			System.out.println("Erro ao executar o save");
			System.out.println(e);
			e.printStackTrace();
			throw new RepositoryException("Erro ao Salvar");
		}
	}
	
	public void remove(T entity) throws RepositoryException {
		try { 
			getEntityManager().getTransaction().begin();
			T o = getEntityManager().merge(entity);
			getEntityManager().remove(o);
			getEntityManager().getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Erro ao executar o remove");
			e.printStackTrace();
			throw new RepositoryException("Erro ao Remover");
		}

	}
	
	public void remove(List<T> entitys) throws RepositoryException {
		for (T t : entitys) {
			remove(t);
		}
	}
	
	public List<T> getAll(Class clazz) throws RepositoryException {
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
	
	protected EntityManager getEntityManager() {
		return em;
	}

	public T findById(int id) throws RepositoryException {
		try {
			// obtendo o tipo da classe de forma generica (a classe deve ser publica)
			final ParameterizedType type = 	(ParameterizedType) getClass().getGenericSuperclass();
			Class<T> tClass = (Class<T>) (type).getActualTypeArguments()[0];
			
			T t = (T) getEntityManager().find(tClass, id);
			return t;
		} catch (Exception e) {
			System.out.println("Erro ao executar o m�todo find do Repository");
			e.printStackTrace();
			throw new RepositoryException("Erro ao buscar os dados");
		}
	}
	

}
