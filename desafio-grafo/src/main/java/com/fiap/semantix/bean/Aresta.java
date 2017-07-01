package com.fiap.semantix.bean;

public class Aresta {

	private Vertice origem;
	private Vertice destino;

	public Aresta(Vertice o, Vertice d) {
		this.origem = o;
		this.destino = d;
	}

	public Vertice getOrigem() {
		return origem;
	}

	public void setOrigem(Vertice origem) {
		this.origem = origem;
	}

	public Vertice getDestino() {
		return destino;
	}

	public void setDestino(Vertice destino) {
		this.destino = destino;
	}

}
