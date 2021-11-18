package repository;

import java.util.List;

import javax.persistence.Query;

import application.RepositoryException;
import entities.RecuperarSenha;

public class RecuperarSenhaRepository extends Repository<RecuperarSenha> {
	
    public List<RecuperarSenha> getbycodigo(String codigo) throws RepositoryException {
		try { 
			Query query = em.createQuery("SELECT u FROM RecuperarSenha u"+
                                        "WHERE U.codigo = :codigo"+
                                        "ORDER BY u.dataLimite");
            query.setParameter("codigo", codigo);
			return query.getResultList();
		} catch (Exception e) {
			System.out.println("Erro ao executar o getAll");
			e.printStackTrace();
			throw new RepositoryException("Erro ao Consultar no banco.");
		}

	}

    public List<RecuperarSenha> getAll() throws RepositoryException {
		try { 
			Query query = em.createQuery("SELECT u FROM RecuperarSenha u"+
                                        "ORDER BY u.dataLimite");
            
			return query.getResultList();
		} catch (Exception e) {
			System.out.println("Erro ao executar o getAll");
			e.printStackTrace();
			throw new RepositoryException("Erro ao Consultar no banco.");
		}

	}
}
