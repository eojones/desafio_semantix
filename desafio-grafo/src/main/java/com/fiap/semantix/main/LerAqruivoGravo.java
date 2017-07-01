package com.fiap.semantix.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.fiap.semantix.bean.Grafo;
import com.fiap.semantix.bean.Vertice;

public class LerAqruivoGravo {

	public static void main(String[] args) {
		Grafo grafo = new Grafo();

		String caminhoArquivo = JOptionPane.showInputDialog(null, "Digite o caminho do arquivo xls do grafo:  ");

		if (caminhoArquivo == null) {
			System.exit(0);
		}

		try {
			FileInputStream arquivo = new FileInputStream(new File(caminhoArquivo));

			@SuppressWarnings("resource")
			HSSFWorkbook workbook = new HSSFWorkbook(arquivo);

			HSSFSheet sheetGrafo = workbook.getSheetAt(0);

			Iterator<Row> rowIterator = sheetGrafo.iterator();
			int countVertices = 0;
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				Vertice origem = null, destino = null;
				
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					String nome = "";
					
					
					switch (cell.getColumnIndex()) {
					case 0:
						nome = String.valueOf(cell.getNumericCellValue());
						if (!VerificarVertice(nome, grafo.getVertices())) {
							origem = grafo.inserirVertice(nome);
							countVertices++;
						}
						else 
							origem = grafo.getVertices()
									.stream()
									.filter(v -> v.getNome().equalsIgnoreCase(String.valueOf(cell.getNumericCellValue())))
									.findFirst()
									.get();

						break;
					case 1:
						nome = String.valueOf(cell.getNumericCellValue());
						if (!VerificarVertice(nome, grafo.getVertices())) {
							destino = grafo.inserirVertice(nome);
							countVertices++;
						}
						else
							destino = grafo.getVertices()
									.stream()
									.filter(v -> v.getNome().equalsIgnoreCase(String.valueOf(cell.getNumericCellValue())))
									.findFirst()
									.get();
						break;
					}
				}
				if (origem != null && destino != null) {
					grafo.inserirAresta(origem, destino);
				}
			}
			Collections.sort(grafo.getVertices(), (a,b) -> a.getNome().compareTo(b.getNome()));
			grafo.setNumeroNodes(countVertices);
			System.out.println(grafo);
			System.out.println("Número de vértices: "+ countVertices);
			System.out.println("*************************************************************************************");
			System.out.println("Classificação do menor influenciador para o maior: ");
			System.out.println();

			Map<String, Double> resultado =  new ClosenessCentrality(grafo).classificarNodes();
			
			for (Map.Entry<String, Double> r : resultado.entrySet()) {
				System.out.println(r.getKey() +" - > "+ r.getValue());
			}

		} catch (IOException e) {
			System.out.println("Deu erro: " + e.getMessage());
		}
	}

	public static boolean VerificarVertice(String nome, List<Vertice> vertices) {
		for (Vertice vertice : vertices) {
			if (nome.equalsIgnoreCase(vertice.getNome()))
				return true;
		}
		return false;
	}
}
