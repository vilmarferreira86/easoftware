package automoveis.dao;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import automoveis.entidades.Automovel;
import automoveis.util.JpaUtil;

public class VeiculoDao {
	
	public void adicionar(Automovel automovel) {
		try {

			EntityManager manager = JpaUtil.getEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();

			manager.persist(automovel);
			transaction.commit();
			manager.close();

			
		}

		catch (PersistenceException e) {
			System.out.println(e.getMessage());
		}

	}
	
	public void update(Automovel automovel) {
		try {

			EntityManager manager = JpaUtil.getEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();

			manager.merge(automovel);
			transaction.commit();
			manager.close();
		}

		catch (PersistenceException e) {
			System.out.println(e.getMessage());
		}

	}

	
	public void remove(Automovel automovel) {
		try {

			EntityManager manager = JpaUtil.getEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			Object o = manager.merge(automovel);
			transaction.begin();
			manager.remove(o);
			transaction.commit();
			manager.close();
		}

		catch (PersistenceException e) {
			System.out.println(e.getMessage());
		}

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
}
