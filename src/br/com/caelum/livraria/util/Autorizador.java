package br.com.caelum.livraria.util;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.caelum.livraria.modelo.Usuario;

public class Autorizador implements PhaseListener {
	
	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent event) {
		
		FacesContext context = event.getFacesContext();
		String nomePagina = context.getViewRoot().getViewId();
		
		System.out.println(nomePagina);
		
		if ("/login.xhtml".equals(nomePagina)) {
			return;
		}
		
		Usuario usuarioSessao = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");
		
		if (usuarioSessao != null) {
			return;
		}
		
		// redirecionamento para login.xhtml
		NavigationHandler handler = context.getApplication().getNavigationHandler();
		handler.handleNavigation(context, null, "login?faces-redirect=true");
		context.renderResponse();
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public PhaseId getPhaseId() {
		// TODO Auto-generated method stub
		
		return PhaseId.RESTORE_VIEW;
	}

}
