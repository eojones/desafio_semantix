package com.fiap.semantix.main;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.fiap.semantix.bean.Aresta;
import com.fiap.semantix.bean.Grafo;
import com.fiap.semantix.bean.Vertice;

public class ClosenessCentrality {

	private Grafo grafo;

	public ClosenessCentrality(Grafo g) {
		this.grafo = g;
	}

	private int calcularDistancia(String nome) {
		int distancia = 0;

		for (Vertice v : grafo.getVertices()) {
			for (Aresta a : v.getAdjacencias()) {
				if (!a.getDestino().getNome().equalsIgnoreCase(nome))
					distancia += 1;
			}
		}
		return distancia;
	}
	
	private double calcularProximidade(String nome) {
		double proximidade = 0;
		double distancia = calcularDistancia(nome);
		double nrNodes = grafo.getNumeroNodes();
		proximidade = (nrNodes -1)/ distancia;
		return proximidade;
	}
	
	public Map<String, Double> classificarNodes(){
		Map<String, Double> classificacao = new HashMap<String, Double>();
		
		for (Vertice v : grafo.getVertices()) {
			classificacao.put(v.getNome(), calcularProximidade(v.getNome()));
		}
		
		Map<String, Double> classificacaoOrdenada = sortByValue(classificacao);
		return classificacaoOrdenada;
	}
	
	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
	    return map.entrySet()
	              .stream()
	              .sorted(Map.Entry.comparingByValue(/*Collections.reverseOrder()*/))
	              .collect(Collectors.toMap(
	                Map.Entry::getKey, 
	                Map.Entry::getValue, 
	                (e1, e2) -> e1, 
	                LinkedHashMap::new
	              ));
	}
}
