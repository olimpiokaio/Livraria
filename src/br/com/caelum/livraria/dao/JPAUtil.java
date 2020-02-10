package br.com.caelum.livraria.dao;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	
	private static EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("livraria_jsf");
	
	@Produces
	@RequestScoped // gera uma entityManager a cada requisição
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	// Disposes fecha o entityManager ao fim da requisição
	public void close(@Disposes EntityManager em) {
		em.close();
	}
	
}
