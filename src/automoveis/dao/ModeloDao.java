package automoveis.dao;

import java.util.HashMap;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

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

	public void adicionar(Modelo modelo) {
		try {

			EntityManager manager = JpaUtil.getEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();

			manager.persist(modelo);
			transaction.commit();
			manager.close();

		}

		catch (PersistenceException e) {
			System.out.println(e.getMessage());
		}

	}

	public void update(Modelo modelo) {
		try {
			EntityManager manager = JpaUtil.getEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();

			manager.merge(modelo);
			transaction.commit();
			manager.close();
			
		}catch(PersistenceException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void remove(Modelo modelo) {
		try {
			EntityManager manager = JpaUtil.getEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			Object o = manager.merge(modelo);
			transaction.begin();
			manager.remove(o);
			transaction.commit();
			manager.close();
		}catch(PersistenceException e) {
			System.out.println(e.getMessage());
		}
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
	
	public List<Modelo> getModeloPorMarca(HashMap<String, Object> params) {
		EntityManager manager = JpaUtil.getEntityManager();
		try {
			TypedQuery<Modelo> query = manager.createQuery("from Modelo m WHERE m.marca.id=:marca", Modelo.class);
			query.setParameter("marca", params.get("marca"));
			return query.getResultList();
		} finally {
			manager.close();
		}
	}

}
