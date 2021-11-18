package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import application.RepositoryException;
import application.Util;
import entities.*;
import repository.*;
import application.Session;

@Named
@RequestScoped
public class menuController {
    public String encerrarSessao() {
		Session.getInstance().invalidateSession();
		System.out.println("passou por aqui");
        Util.redirect("/topicos2/login.xhtml");
		return "/topicos2/login.xhtml?faces-redirect=true" ;
	}
}
