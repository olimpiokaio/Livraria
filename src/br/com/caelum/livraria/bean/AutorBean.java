package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.livraria.dao.AutorDao;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.tx.Transacional;

@SuppressWarnings("serial")
@Named
@ViewScoped // javax.faces.view.ViewScoped
public class AutorBean implements Serializable {
	/*--------------------------------------------------------------------*/
	/* ATRIBUTOS */
	/*--------------------------------------------------------------------*/
	private Autor autor = new Autor();
	private List<Autor> autores;
	private Integer idAutor;
	
	/*--------------------------------------------------------------------*/
	/* CONSTRUTOR DO CDI*/
	/*--------------------------------------------------------------------*/
	@Inject
	private AutorDao dao; // CDI faz o AutorDao() e injeta
 	
	/*--------------------------------------------------------------------*/
	/* GETTERS AND SETTERS */
	/*--------------------------------------------------------------------*/
	public Autor getAutor() {
		return autor;
	}
	
	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	
	public List<Autor> getAutores() {
		return autores;
	}
	
	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}
	
	public Integer getIdAutor() {
		return idAutor;
	}
	
	public void setIdAutor(Integer idAutor) {
		this.idAutor = idAutor;
	}
	
	/*--------------------------------------------------------------------*/
	/* METODOS */
	/*--------------------------------------------------------------------*/
	@Transacional
	public String gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());
		
		if (this.autor.getId() == null) {
			this.dao.adiciona(this.autor);
		} else {
			this.dao.atualiza(this.autor);
		}
		this.listarAutores();
		return "livro?faces-redirect=true";
	}
	
	public void carregarPorId() {
		this.autor = this.dao.buscaPorId(this.idAutor);
	}
	
	@PostConstruct
	public void listarAutores() {
		this.autores = this.dao.listaTodos();
		this.autores.size();
	}
	
	@Transacional
	public void deletarAutor(Autor autorTabela) {
		this.dao.remove(autorTabela);
		this.listarAutores();
	}
	
	public void editarAutor(Autor autorTabela) {
		this.autor = autorTabela;
	}
	
}