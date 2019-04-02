package automoveis.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;


public class JpaUtil {
	
	private static EntityManagerFactory factory;
	static {
		factory = Persistence.createEntityManagerFactory("automoveisPU");
		
	}

	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}
}
