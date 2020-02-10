package br.com.caelum.livraria.tx;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

@Transacional
@Interceptor
@SuppressWarnings("serial")
public class GerenciadorDeTransacao implements Serializable {
	
	@Inject
	EntityManager manager;
	
	@AroundInvoke
	public Object executaTX(InvocationContext contexto) throws Exception {
		// abre transação
		manager.getTransaction().begin();
		System.out.println("Abrindo tx");
		
		// chamas os Daos que precisam de um TX
		Object resultado = contexto.proceed();
		
		// fecha transação
		manager.getTransaction().commit();
		System.out.println("Fechando tx");
		
		return resultado;
	}
}
