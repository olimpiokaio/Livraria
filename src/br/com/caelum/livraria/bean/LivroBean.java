package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.livraria.dao.AutorDao;
import br.com.caelum.livraria.dao.LivroDao;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.modelo.Livro;
import br.com.caelum.livraria.tx.Transacional;

@Named
@ViewScoped
@SuppressWarnings("serial")
public class LivroBean implements Serializable{

	private Livro livro = new Livro();
	private Integer idAutor;
	private List<Livro> listaLivro;
	private Integer idLivro;
	
	@Inject
	private LivroDao livroDao;
	
	@Inject
	private AutorDao autorDao;
	
	@Inject
	private FacesContext context;
	
	/***************************************************************************/
	/*-- GETTES AND SETTES --*/
	/***************************************************************************/
	public Integer getIdLivro() {
		return idLivro;
	}
	
	public void setIdLivro(Integer idLivro) {
		this.idLivro = idLivro;
	}
	
	public void setIdAutor(Integer idAutor) {
		this.idAutor = idAutor;
	}
	
	public void setListaLivro(List<Livro> listaLivro) {
		this.listaLivro = listaLivro;
	}
	
	public List<Livro> getListaLivro() {
		this.listarLivros();
		return listaLivro;
	}
	
	public Integer getIdAutor() {
		return idAutor;
	}

	public Livro getLivro() {
		return livro;
	}
	
	public List<Autor> getAutores() {
		return this.autorDao.listaTodos();
	}
	
	public List<Autor> getAutoresLivros() {
		return this.livro.getAutores();
	}
	
	/***************************************************************************/
	/*-- METODOS ESPECIAIS --*/
	/***************************************************************************/
	@PostConstruct
	public void listarLivros() {
		if (this.listaLivro == null) {
			this.listaLivro =  this.livroDao.listaTodos();
		}
	}
	
	public void gravarAutor() {
		Autor autor = this.autorDao.buscaPorId(this.idAutor);
		this.livro.adicionaAutor(autor);
	}

	@Transacional
	public void gravar() {
		System.out.println("Gravando livro " + this.livro.getTitulo());
		if(this.livro.getId() == null) {
			if (this.livro.getAutores().isEmpty()) {
				//throw new RuntimeException("Livro deve ter pelo menos um Autor.");
				context.addMessage("autor", new FacesMessage("Livro deve ter pelo menos um Autor"));
				return;
			}
			this.livroDao.adiciona(this.livro);
		} else {
			if (this.livro.getAutores().isEmpty()) {
				//throw new RuntimeException("Livro deve ter pelo menos um Autor.");
				context.addMessage("autor", new FacesMessage("Livro deve ter pelo menos um Autor"));
				return;
			} else {
				this.livroDao.atualiza(this.livro);
			}
		}
		// atualizar lista de livros
		atualizarLivros();
	}
	
	public void buscarPorId() {
		Livro livroPorId = new Livro();
		livroPorId = this.livroDao.buscaPorId(idLivro);
		if (livroPorId != null) {
			this.livro = livroPorId;
		}
	}
	
	public void alterarLivro(Livro livro) {
		System.out.println("Editando livro");
		this.livro = this.livroDao.buscaPorId(livro.getId());
		// atualizar lista de livros
		atualizarLivros();
	}
	
	@Transacional
	public void removerLivro(Livro livro) {
		this.livroDao.remove(livro);
		// atualizar lista de livros
		atualizarLivros();
	}
	
	public void atualizarLivros() {
		// atualizar lista de livros
		this.listaLivro =  this.livroDao.listaTodos();
	}

	public String formAutor() {
		System.out.println("Chamada formulario do autor");
		return "autor?faces-redirect=true";
	}
	
	public void removerAutor(Autor autor) {
		this.livro.removerAutor(autor);
	}

	/***************************************************************************/
	/*-- VALIDADORES --*/
	/***************************************************************************/
	public void comecaComDigitoUm(FacesContext fc, UIComponent compenent, Object value) throws ValidatorException {
		String valor = value.toString();
		
		if (!valor.startsWith("1")) {
			throw new ValidatorException(new FacesMessage("CDI deve começar com o numero 1"));
		}
	}
	
}















