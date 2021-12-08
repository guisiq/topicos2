package entities;


import java.util.ArrayList;
import java.util.List;

public enum Perfil {
	COMUN(0, "comun"), 
	MODERADOR(1, "moderador");		   
	
	private Integer id;
	private String label;
	private List<String> paginasComPermissao;
	
	Perfil(Integer id, String label) {
		this.id = id;
		this.label = label;
		paginasComPermissao = new ArrayList<String>();
		
		// acesso para todos os usuarios
		paginasComPermissao.add("/topicos2/pages/meusLivros.xhtml");
		paginasComPermissao.add("/topicos2/pages/milhasColecoes.xhtml");
		paginasComPermissao.add("/topicos2/pages/milhasColecoesDL.xhtml");
		paginasComPermissao.add("/topicos2/pages/controle_de_usuario.xhtml");
		paginasComPermissao.add("/topicos2/pages/meusCapitulos.xhtml");
		
		switch (id) {
		case 1:
			paginasComPermissao.add("/topicos2/pages/controle_de_usuario.xhtml");			
			break;
		default:
			break;
		}
	}
	
	public List<String> getPaginasComPermissao() {
		return paginasComPermissao;
	}
	
	public static Perfil valueOf(Integer id) {
		if (id == null)
			return null;
		for (Perfil perfil : Perfil.values()) {
			if (perfil.getId().equals(id))
				return perfil;
		}
		return null;
	}
	
	public String getLabel() {
		return label;
	}
	
	public Integer getId() {
		return id;
	}
}
