package controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import application.RepositoryException;
import application.Session;
import application.Util;
import entities.*;
import repository.*;

@Named
@ViewScoped
public class MeusLivrosController extends CRUDController<Livro> implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Livro> livros;
	private List<Livro> livrosSelection;
	private List<Genero> generos;
	private InputStream fotoInputStream = null;
	

	private Usuario usuarioLog;
	private LivroRepo repo = new LivroRepo();
	

	


	public void upload(FileUploadEvent event) {
		UploadedFile uploadFile = event.getFile();
		System.out.println("nome arquivo: " + uploadFile.getFileName());
		System.out.println("tipo: " + uploadFile.getContentType());
		System.out.println("tamanho: " + uploadFile.getSize());

		if (uploadFile.getContentType().equals("image/png")) {
			try {
				fotoInputStream = uploadFile.getInputStream();
				System.out.println("inputStream: " + uploadFile.getInputStream().toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Util.addInfoMessage("Upload realizado com sucesso.");
		} else {
			Util.addErrorMessage("O tipo da image deve ser png.");
		}

	}
	public void openNew() {
		System.out.println("passou por aqui");
		
        this.entity = null;
		getEntity();
        
    }
	public void deletelivro(Livro l) {
		   try {
				repo.remove(l);
				//livros.remove(l); 
				Util.addInfoMessage("livro removido" );
				PrimeFaces.current().ajax().update("form:messages", "form:dt-livros");
			} catch (RepositoryException e) {
				Util.addErrorMessage("erro ao remover ");
				e.printStackTrace();
			}
	}
	public void deletelivro() {
        try {
			repo.remove(this.livrosSelection);
			livros.removeAll(livrosSelection);
			livrosSelection = null; 
			Util.addInfoMessage("livro removido" );
			PrimeFaces.current().ajax().update("form:messages", "form:dt-livros");
		} catch (RepositoryException e) {
			Util.addErrorMessage("erro ao remover ");
			e.printStackTrace();
		}
        
    }
/*	
	exemplo da documentacao 
	public void saveProduct() {
        if (this.selectedProduct.getCode() == null) {
            this.selectedProduct.setCode(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9));
            this.products.add(this.selectedProduct);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Added"));
        }
        else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Updated"));
        }

        PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }
*/
	public void savelivro() {
		try {

			repo.save(getEntity());
			if (!livros.contains(getEntity())) {
				livros.add(getEntity());
			}
			if (getFotoInputStream() != null) {
				// salvando a imagem
				if (!Util.saveImageUsuario(fotoInputStream, "png", getEntity().getId())) {
					Util.addErrorMessage("Erro ao salvar. N�o foi poss�vel salvar a imagem do usu�rio.");
					return;
				}
			}
			limpar();
			//adicionando mensagens eexecutando eventos 
			PrimeFaces.current().executeScript("PF('managelivroDialog').hide()");
			PrimeFaces.current().ajax().update("form:messages", "form:dt-livros");
			Util.addInfoMessage("livro Adicionado" );
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	
	public InputStream getFotoInputStream() {
		return fotoInputStream;
	}
	public void setFotoInputStream(InputStream fotoInputStream) {
		this.fotoInputStream = fotoInputStream;
	}
	public List<Genero> getGeneros() {
		if(generos == null){
			this.generos = new ArrayList<Genero>();
			for (int i = 0; i < Genero.values().length; i++) {
				generos.add(Genero.values()[i]);
			}
		}
		return generos;
	}

	public void setGeneros(List<Genero> generos) {
		this.generos = generos;
	}

	public List<Livro> getLivrosSelection() {
		return livrosSelection;
	}

	public void setLivrosSelection(List<Livro> livrosSelection) {
		this.livrosSelection = livrosSelection;
	}

	public List<Livro> getLivros() {
		try {
			livros = repo.getbyAutor(getUsuarioLog());
			// muda para usuario
		}catch (Exception e) {
			Util.addErrorMessage("nao encontramos livros");
		}
		return livros;
	}

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}

	public Usuario getUsuarioLog() {
		if (usuarioLog == null) {
			try {
				usuarioLog = (Usuario)Session.getInstance().get("usuarioLogado");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return usuarioLog;
	}
	
	public void setUsuarioLog(Usuario usuarioLog) {
		this.usuarioLog = usuarioLog;
	}
	@Override
	public Livro getEntity() {
		if (entity == null){
			this.entity = new Livro();
			this.entity.setGenero(Genero.INDEFINIDO);
			this.entity.setUsuario(getUsuarioLog());
			this.entity.setAutor(getUsuarioLog().getNome());
		}
		return entity;
	}

	

	

}
