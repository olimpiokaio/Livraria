package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.caelum.livraria.modelo.Venda;

@Named
@ViewScoped
@SuppressWarnings("serial")
public class VendaBean implements Serializable {

	private BarChartModel vendasBean;

	@Inject
	private EntityManager manager;
	
	/*******************************************/
	/*********** GETS AND SETS ***************/
	/*******************************************/
	public BarChartModel getVendasBean() {
		this.vendasBean = initBarModel();
		return vendasBean;
	}

	/*******************************************/
	/*********** METODOS ESPECIAIS *************/
	/*******************************************/
	
	/* DEVERIAMOS CRIAR UM DAO PARA ISSO! */
	public List<Venda> getVendas() {
		List<Venda> vendas = this.manager.createQuery("select v from Venda v", Venda.class).getResultList();
		return vendas;
	}

	private BarChartModel initBarModel() {
		BarChartModel model = new BarChartModel();
		model.setAnimate(true);
		
		ChartSeries vendaSerie = new ChartSeries();
		vendaSerie.setLabel("2019");

		List<Venda> listaVenda = this.getVendas();
		for (Venda venda : listaVenda) {
			vendaSerie.set(venda.getLivro().getTitulo(), venda.getQuantidade());
		}
		
		model.addSeries(vendaSerie);
		model.setTitle("Vendas de livros");
		model.setLegendPosition("ne");
		model.setShowPointLabels(true);
		
		return model;
	}

}
