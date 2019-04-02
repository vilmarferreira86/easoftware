package automoveis.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import automoveis.entidades.Marca;
import automoveis.entidades.Modelo;
import automoveis.util.JpaUtil;

@ManagedBean
@SessionScoped
public class ModeloController {
	private Modelo modelo = new Modelo();
	public ModeloController() {}
	
	public ModeloController(Modelo modelo) {
		super();
		this.modelo = modelo;
	}
	
	public void adicionar() {
		try {

			EntityManager manager = JpaUtil.getEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();

			manager.persist(modelo);
			transaction.commit();
			manager.close();

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
			EntityManager manager = JpaUtil.getEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();

			manager.merge(modelo);
			transaction.commit();
			manager.close();
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

			EntityManager manager = JpaUtil.getEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			Object o = manager.merge(modelo);
			transaction.begin();
			manager.remove(o);
			transaction.commit();
			manager.close();
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
		EntityManager manager = JpaUtil.getEntityManager();
		try {
			TypedQuery<Modelo> query = manager.createQuery("from Modelo", Modelo.class);
			return query.getResultList();
		} finally {
			manager.close();
		}

	}
	
	public List<Modelo> getModeloPorMarca(){
		EntityManager manager = JpaUtil.getEntityManager();
		try {
			TypedQuery<Modelo> query = manager.createQuery("from Modelo m WHERE m.marca.id=:id", Modelo.class);
			query.setParameter("id", this.modelo.getMarca().getId());
			return query.getResultList();
		}finally {
			manager.close();
		}
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}
	
	
}
