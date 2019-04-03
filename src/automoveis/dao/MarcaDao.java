package automoveis.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import automoveis.util.JpaUtil;
import automoveis.entidades.Marca;

public class MarcaDao {

	private EntityManager manager = JpaUtil.getEntityManager();

	// BUSCA POR ID
	public Marca getById(int id) {
		try {
			return this.manager.find(Marca.class, id);
		} finally {
			this.manager.close();
		}

	}
	
	// BUSCA TODAS AS MARCAS
	public List<Marca> getTodas() {
		EntityManager manager = JpaUtil.getEntityManager();
		try {
			TypedQuery<Marca> query = manager.createQuery("from Marca", Marca.class);
			return query.getResultList();
		} finally {
			manager.close();
		}

	}
	// Adicionar marca
	public void adicionar(Marca marca) {
		try {

			EntityManager manager = JpaUtil.getEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();

			manager.persist(marca);
			transaction.commit();
			manager.close();
			System.out.println("Marca cadastrada com sucesso!");
		} catch (PersistenceException e) {
			System.out.println("Erro ao adicionar ->"+e.getMessage());
		}
	}
	
	// Alterar marca
	public void update(Marca marca) {
		try {
			EntityManager manager = JpaUtil.getEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			manager.merge(marca);
			transaction.commit();
			manager.close();
			System.out.println("Marca alterada com sucesso!");
		}catch (PersistenceException e) {
			System.out.println("Erro ao alterar ->"+e.getMessage());
		}
	}
	
	// Remover marca
	public void remove(Marca marca) {
		try {
			EntityManager manager = JpaUtil.getEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			Object o = manager.merge(marca);
			transaction.begin();
			manager.remove(o);
			transaction.commit();
			manager.close();
			System.out.println("Marca removida com sucesso!");
		}catch (PersistenceException e) {
			System.out.println("Erro ao remover ->"+e.getMessage());
		}
	}

}
