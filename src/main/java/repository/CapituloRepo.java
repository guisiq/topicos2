package repository;
import entities.Capitulo;
import entities.Livro;
import entities.Usuario;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import application.JPAUtil;
import application.RepositoryException;

public class CapituloRepo extends Repository<Capitulo>{
    public Capitulo save(Capitulo capitulo,int livroid) throws RepositoryException {
		try { 
			var em = getEntityManager();
			
			if(!em.getTransaction().isActive()) 
		        em.getTransaction().begin();
			//em.persist(capitulo);
			
			
			//em.getTransaction().commit();
			//em.close();
			
			Capitulo e = em.merge(capitulo);
			var insertList = em.createNativeQuery("INSERT INTO livro_capitulo (livro_id, capitulos_id) VALUES (:livroId, :capituloId) ");
			insertList.setParameter("livroId", livroid);
			insertList.setParameter("capituloId",capitulo.getId());
			insertList.executeUpdate();
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
	
    public List<Capitulo> getAll() throws RepositoryException {
		Class<?> clazz = Livro.class;
        try { 
			String entityName = clazz.getSimpleName();
			// jpql
			Query query = em.createQuery("SELECT u FROM Capitulo u");
			return query.getResultList();
		} catch (Exception e) {
			System.out.println("Erro ao executar o getAll");
			e.printStackTrace();
			throw new RepositoryException("Erro ao Consultar no banco.");
		}

	}   
    
    
}
