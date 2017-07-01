# desafio_semantix
Solução baseada no prossuposto que o grafo é orientado(direcional), com base na análise do arquivo enviado.
Linguagem utilizada: JAVA (v8)
Transformei o arquivo recebido em um arquivo de texto (.TXT) e extraí os dados numa planilha de excel (XLS), formado por duas coluas. A primeira coluna do vértice de origem e a segunda coluna do vértice de destino.

Utilizei a biblioteca apache APOI v3.16 para fazer a leitura dos dados no excel. A partir daí fiz a relação de qual vetor se conectava com qual. Depois de descobrir as relações entre os vetores, pude traçar a distancia de cada vetor em relação aos outros do grafo e, por fim, calcular a proximidade de cada um (ordenada do menor influenciador para o maior influenciador).
Não utilizei a métrica de grau de cada vértice.
