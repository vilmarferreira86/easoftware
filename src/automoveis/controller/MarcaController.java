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

import automoveis.dao.MarcaDao;
import automoveis.entidades.Marca;
import automoveis.util.JpaUtil;

@ManagedBean
@SessionScoped
public class MarcaController {
	private Marca marca = new Marca();
	private MarcaDao mdao = new MarcaDao();

	public MarcaController() {
	}

	public MarcaController(Marca m) {
		super();
		this.marca = m;
	}

	public void adicionar() {
		try {
			mdao.adicionar(this.marca);
			this.marca = new Marca();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Marca cadastrada com sucesso!", null));
		}

		catch (PersistenceException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}

	}

	// METODO UPDATE
	public String update() {
		try {
			mdao.update(this.marca);
			this.marca = new Marca();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Marca atualizada com sucesso!", null));

		} catch (PersistenceException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}

		return "marca";
	}

	// METODO REMOVER
	public String remove() {
		try {
			mdao.remove(this.marca);
			this.marca = new Marca();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Marca removida com sucesso!", null));
		} catch (PersistenceException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}

		return "coordenadoria";
	}

	public String editar() {
		return "editMarca";
	}

	public List<Marca> getTodas() {
		List<Marca> list = null;
		try {
			list = mdao.getTodas();
			return list;
		}catch(Exception e) {
			
		}
		return list;

	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public MarcaDao getMdao() {
		return mdao;
	}

	public void setMdao(MarcaDao mdao) {
		this.mdao = mdao;
	}

}
