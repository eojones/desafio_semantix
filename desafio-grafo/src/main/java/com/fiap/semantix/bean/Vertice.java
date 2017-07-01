package com.fiap.semantix.bean;

import java.util.ArrayList;
import java.util.List;


public class Vertice {

	private String nome;
	private List<Aresta> adjacencias;

	public Vertice(String n) {
		this.nome = n;
		this.adjacencias = new ArrayList<Aresta>();
	}

	public void addAdjacencia(Aresta aresta) {
		this.adjacencias.add(aresta);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Aresta> getAdjacencias() {
		return adjacencias;
	}

	public void setAdjacencias(List<Aresta> adjacencias) {
		this.adjacencias = adjacencias;
	}
}
