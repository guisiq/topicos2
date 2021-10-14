package repository;

import entities.Colecao;
import java.util.List;

import application.RepositoryException;

public class ColecaoRepo extends Repository<Colecao>{
  
    public List<Colecao> getAll() throws RepositoryException {
		Class clazz = Colecao.class;
        return getAll(clazz);
	} 
    public List<Colecao> getbyAutor(String autor) throws RepositoryException {
        try { 
			/*
			
			String entityName = clazz.getSimpleName();
			// jpql
			Query query = em.createQuery("SELECT u FROM "+entityName+" u WHERE u.autor ="+ autor);
			return query.getResultList();
			
			*/
			List<Colecao> retorno = getAll();
			retorno.removeIf( c -> {
				if (c.getAutor() != null) {	
					return !c.getAutor().getNome().equals(autor); 
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
