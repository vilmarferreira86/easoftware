package automoveis.controller;

import java.security.spec.ECFieldF2m;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.jws.WebParam.Mode;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import automoveis.dao.ModeloDao;
import automoveis.entidades.Marca;
import automoveis.entidades.Modelo;
import automoveis.util.JpaUtil;

@ManagedBean
@SessionScoped
public class ModeloController {
	private Modelo modelo = new Modelo();
	private ModeloDao moDao = new ModeloDao();

	public ModeloController() {
	}

	public ModeloController(Modelo modelo) {
		super();
		this.modelo = modelo;
	}

	public void adicionar() {
		try {
			moDao.adicionar(this.modelo);
			this.modelo = new Modelo();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Modelo cadastrado com sucesso!", null));
		}

		catch (PersistenceException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}

	}

	// METODO UPDATE
	public String update() {
		try {
			moDao.update(this.modelo);
			this.modelo = new Modelo();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Modelo atualizada com sucesso!", null));

		} catch (PersistenceException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}

		return "modelo";
	}

	// METODO REMOVER
	public String remove() {
		try {
			moDao.remove(this.modelo);
			this.modelo = new Modelo();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Modelo removido com sucesso!", null));
		} catch (PersistenceException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}

		return "modelo";
	}

	public String editar() {
		return "editModelo";
	}

	public String cancela() {
		this.modelo = new Modelo();
		return "modelo";
	}

	public List<Modelo> getTodas() {
		List<Modelo> list = null;
		try {
			list = moDao.getTodas();
		}catch(Exception e) {
			
		}
		return list;

	}

	

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public ModeloDao getMoDao() {
		return moDao;
	}

	public void setMoDao(ModeloDao moDao) {
		this.moDao = moDao;
	}

}
