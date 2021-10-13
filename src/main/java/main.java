import application.RepositoryException;
import application.Util;

import java.util.List;
import java.util.ArrayList;

import entities.Genero;
import entities.Livro;
import repository.*;
public class main {
    public static void main(String[] args) throws RepositoryException {
        livroRepo repo = new livroRepo();
        //repo.save(new Livro("teste1","aaaaa","a historia do server com vontade propria",null,Genero.ACAO,null));
        List<Livro> livros = repo.getAll();
        try {
            livros = repo.getbyAutor("eu");
            repo.remove(livros.get(1));
        }catch (Exception e) {
            Util.addErrorMessage("nao encontramos livros");
        }
        
    }
}
