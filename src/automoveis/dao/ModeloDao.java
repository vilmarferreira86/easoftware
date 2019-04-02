package automoveis.dao;



import javax.persistence.EntityManager;

import automoveis.util.JpaUtil;
import automoveis.entidades.Modelo;

public class ModeloDao {
	
	private EntityManager manager = JpaUtil.getEntityManager();

	public Modelo getById(int id) {
		try {
			return this.manager.find(Modelo.class, id);
		} finally {
			this.manager.close();
		}

	}

}
