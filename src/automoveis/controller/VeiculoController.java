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

import automoveis.entidades.Automovel;
import automoveis.util.JpaUtil;

@ManagedBean
@SessionScoped
public class VeiculoController {
	private Automovel automovel = new Automovel();
	
	public VeiculoController() {}
	
	public VeiculoController(Automovel auto) {
		super();
		this.automovel = auto;
		
	}
	
	public void adicionar() {
		try {

			EntityManager manager = JpaUtil.getEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();

			manager.persist(automovel);
			transaction.commit();
			manager.close();

			this.automovel = new Automovel();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Veículo cadastrado com sucesso!", null));
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

			manager.merge(automovel);
			transaction.commit();
			manager.close();
			this.automovel = new Automovel();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Veículo atualizado com sucesso!", null));

		} catch (PersistenceException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}

		return "veiculo";
	}

	// METODO REMOVER
	public String remove() {
		try {

			EntityManager manager = JpaUtil.getEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			Object o = manager.merge(automovel);
			transaction.begin();
			manager.remove(o);
			transaction.commit();
			manager.close();
			this.automovel = new Automovel();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Veículo removido com sucesso!", null));
		} catch (PersistenceException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}

		return "veiculo";
	}
	
	public String editar() {
		return "editVeiculo";
	}
	
	public String cancela() {
		this.automovel = new Automovel();
		return "veiculo";
	}

	public List<Automovel> getTodas() {
		EntityManager manager = JpaUtil.getEntityManager();
		try {
			TypedQuery<Automovel> query = manager.createQuery("from Automovel", Automovel.class);
			return query.getResultList();
		} finally {
			manager.close();
		}

	}

	public Automovel getAutomovel() {
		return automovel;
	}

	public void setAutomovel(Automovel automovel) {
		this.automovel = automovel;
	}
	
	

}
