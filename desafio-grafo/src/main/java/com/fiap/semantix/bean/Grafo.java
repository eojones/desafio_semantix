package com.fiap.semantix.bean;

import java.util.ArrayList;
import java.util.List;


public class Grafo {
	private List<Aresta> arestas;
	private List<Vertice> vertices;
	private int numeroNodes;
	
	public Grafo() {
		this.arestas = new ArrayList<Aresta>();
		this.vertices = new ArrayList<Vertice>();
	}

	public Vertice inserirVertice(String nome) {
		Vertice v = new Vertice(nome);
		this.vertices.add(v);
		return v;
	}

	public Aresta inserirAresta(Vertice origem, Vertice destino) {
		Aresta aresta = new Aresta(origem, destino);
		origem.addAdjacencia(aresta);
		this.arestas.add(aresta);
		return aresta;
	}

	public List<Aresta> getArestas() {
		return arestas;
	}

	public List<Vertice> getVertices() {
		return vertices;
	}

	@Override
	public String toString() {
		String classificacao = "";
		for (Vertice vertice : vertices) {
			classificacao += vertice.getNome() + " -> ";

			for (Aresta a : vertice.getAdjacencias()) {
				Vertice v = a.getDestino();
				classificacao += v.getNome() + ", ";
			}
			classificacao += "\n";
		}
		return classificacao;
	}

	public int getNumeroNodes() {
		return numeroNodes;
	}

	public void setNumeroNodes(int numeroNodes) {
		this.numeroNodes = numeroNodes;
	}
}
