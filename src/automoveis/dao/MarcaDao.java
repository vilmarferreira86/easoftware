package automoveis.dao;



import javax.persistence.EntityManager;

import automoveis.util.JpaUtil;
import automoveis.entidades.Marca;

public class MarcaDao {
	
	private EntityManager manager = JpaUtil.getEntityManager();

	public Marca getById(int id) {
		try {
			return this.manager.find(Marca.class, id);
		} finally {
			this.manager.close();
		}

	}

}
