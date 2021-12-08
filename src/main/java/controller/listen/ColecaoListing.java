package controller.listen;


import java.util.ArrayList;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import entities.Colecao;
import repository.ColecaoRepo;
import application.RepositoryException;
@Named
@ViewScoped
public class ColecaoListing extends dialog<Colecao> {

	private static final long serialVersionUID = -4837662985103408066L;


	public ColecaoListing() {
		super("pages/milhasColecoesDL", new ColecaoRepo());
	}


}
