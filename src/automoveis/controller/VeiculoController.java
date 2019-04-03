package automoveis.controller;

import java.util.HashMap;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.primefaces.event.FileUploadEvent;

import automoveis.dao.ModeloDao;
import automoveis.dao.VeiculoDao;
import automoveis.entidades.Automovel;
import automoveis.entidades.Marca;
import automoveis.entidades.Modelo;
import automoveis.util.JpaUtil;

@ManagedBean
@SessionScoped
public class VeiculoController {
	private Automovel automovel = new Automovel();
	private VeiculoDao vDao = new VeiculoDao();
	private Marca marca;
	private List<Modelo> modelos;
	private ModeloDao mdao = new ModeloDao();

	public VeiculoController() {
	}

	public VeiculoController(Automovel auto) {
		super();
		this.automovel = auto;

	}

	public void adicionar() {
		try {
			System.out.println("Foto->" + this.automovel.getFoto());
			vDao.adicionar(this.automovel);
			this.automovel = new Automovel();
			this.marca = new Marca();
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
			vDao.update(this.automovel);
			this.automovel = new Automovel();
			this.marca = new Marca();
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

			vDao.remove(this.automovel);
			this.automovel = new Automovel();
			this.marca = new Marca();
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
		this.marca = new Marca();
		return "veiculo";
	}

	public List<Automovel> getTodas() {
		List<Automovel> list = null;
		try {
			list = vDao.getTodas();
		} catch (Exception e) {

		}
		return list;

	}

	@SuppressWarnings("unchecked")
	public void updateComboModelo() {
		HashMap<String, Object> params = new HashMap<>();
		params.put("marca", marca.getId());
		modelos = mdao.getModeloPorMarca(params);
	}

	public void upload(FileUploadEvent event) {
		this.automovel.setFoto(event.getFile().getContents());
		FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public Automovel getAutomovel() {
		return automovel;
	}

	public void setAutomovel(Automovel automovel) {
		this.automovel = automovel;
	}

	public VeiculoDao getvDao() {
		return vDao;
	}

	public void setvDao(VeiculoDao vDao) {
		this.vDao = vDao;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public List<Modelo> getModelos() {
		return modelos;
	}

	public void setModelos(List<Modelo> modelos) {
		this.modelos = modelos;
	}

	public ModeloDao getMdao() {
		return mdao;
	}

	public void setMdao(ModeloDao mdao) {
		this.mdao = mdao;
	}

}
