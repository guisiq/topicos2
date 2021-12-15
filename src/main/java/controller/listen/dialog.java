package controller.listen;


import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.primefaces.PrimeFaces;

import application.RepositoryException;
import repository.Repository;
import application.RepositoryException;






public abstract class dialog<T> implements Serializable {

	private static final long serialVersionUID = 7641180780489288293L;
	private String page;
	private Repository<T> repository;
	private List<T> list;

	public dialog(String page, Repository<T> repository) {
		super();
		this.page = page;
		this.repository = repository;
	}

	public void open() {
		open( this.page); 
	} 
	public static void open(String page) { 
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", true);
		options.put("resizable", true);
		options.put("width", "80%");
		options.put("height", "90%");
		options.put("contentWidth", "100%");
		options.put("contentHeight", "100%");
	
		PrimeFaces.current().dialog().openDynamic(page, options, null);
		
	}
	
	public void select(int id) {
		T obj = null;
		try {
			obj = repository.findById(id);
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
		PrimeFaces.current().dialog().closeDynamic(obj);
	}
	
	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

}