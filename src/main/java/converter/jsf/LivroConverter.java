package converter.jsf;


import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import entities.Livro;
import repository.LivroRepo;


@FacesConverter(forClass = Livro.class)
public class LivroConverter implements Converter<Livro> {

	@Override
	public Livro getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.isEmpty())
			return null;
		
		LivroRepo repo = new LivroRepo();
		try {
			return repo.findById(Integer.valueOf(value.trim()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Livro estado) {
		if (estado == null || estado.getId() == null)
			return null;
		
		return estado.getId().toString();
	}

}
