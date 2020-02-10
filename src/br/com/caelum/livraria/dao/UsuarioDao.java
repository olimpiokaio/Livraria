package br.com.caelum.livraria.dao;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.caelum.livraria.modelo.Usuario;

@SuppressWarnings("serial")
public class UsuarioDao implements Serializable {
	
	@Inject
	EntityManager manager;
	
	public boolean existe(Usuario usuario) {
		
		String jpql = "select u from Usuario u where u.email = :pEmail and u.senha = :pSenha";
		TypedQuery<Usuario> query = manager.createQuery(jpql, Usuario.class);
		query.setParameter("pEmail", usuario.getEmail());
		query.setParameter("pSenha", usuario.getSenha());
		
		Usuario resposta;
		try {
			resposta = query.getSingleResult();			
		} catch(NoResultException ex) {
			System.err.println("ERRO TRATADO: " + ex);
			return false;
		}
		
		return resposta != null;
	}
	
}
