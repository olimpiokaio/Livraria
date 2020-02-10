package br.com.caelum.livraria.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
@SuppressWarnings("serial")
public class temaBean implements Serializable {
	
	private String tema = "vader";

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public String[] getTemas() {
		return new String[] { 
			"afterdark", "afternoon", "afterwork", "aristo",
			"black-tie", "blitzer", "bluesky", "bootstrap", "casablanca",
			"cupertino", "cruze", "dark-hive", "delta", "dot-luv",
			"eggplant", "excite-bike", "flick", "glass-x", "home",
			"hot-sneaks", "humanity", "le-frog", "midnight", "mint-choc",
			"overcast", "pepper-grinder", "redmond", "rocket", "sam",
			"smoothness", "south-street", "start", "sunny", "swanky-purse",
			"trontastic", "ui-darkness", "ui-lightness", "vader" };
		}
}
