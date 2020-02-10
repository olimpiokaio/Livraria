package br.com.caelum.livraria.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.faces.context.FacesContext;

import br.com.caelum.livraria.dao.UsuarioDao;
import br.com.caelum.livraria.modelo.Usuario;

@Named
@ViewScoped
@SuppressWarnings("serial")
public class LoginBean implements Serializable {

	/*--------------------------------------------------------------------*/
	/* ATRIBUTOS */
	/*--------------------------------------------------------------------*/
	private Usuario usuario = new Usuario();
	
	@Inject
	UsuarioDao dao;
	
	@Inject
	FacesContext context;
	
	/*--------------------------------------------------------------------*/
	/* GETTERS AND SETTERS */
	/*--------------------------------------------------------------------*/
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	/*--------------------------------------------------------------------*/
	/* METODOS */
	/*--------------------------------------------------------------------*/

	public String efetuaLogin() {
		System.out.println("Fazendo login do usuario " + usuario.getEmail());

		boolean existe = dao.existe(this.usuario);
		if (existe) {
			context.getExternalContext().getSessionMap().put("usuarioLogado", this.usuario);
			return "livro?faces-redirect=true";
		}
		
		// para a messagem ficar em memoria por duas requisições e ser ixibida na tela
		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage(null, new FacesMessage("Usuário não encontrado!"));
		
		/*
		 * form:idmessage -> isso e a forma de se chamar o id de uma menssagem dentro do form
		 * para pegar sua referencia 
		 */
		return "login?faces-redirect=true";
	}
	
	public String deslogar() {
		context.getExternalContext().getSessionMap().remove("usuarioLogado");
		return "login?faces-redirect=true";
	}
	
}









