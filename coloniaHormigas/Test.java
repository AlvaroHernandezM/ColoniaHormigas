package coloniaHormigas;

public class Test {
	
	public static void main(String[] args) {
		Vertice[] vertices = {new Vertice(Constantes.CASA), new Vertice("B"), new Vertice("C"), new Vertice("D"),  new Vertice("E"), new Vertice("F"), new Vertice("G"), new Vertice("H"), new Vertice(Constantes.COMIDA)};
		Arco[][] matriz= {//inicializamos la matriz de adyacencia
						//A					B				C			D				E				F			G			H			I				
				/*A*/{new Arco(), 		new Arco(1,3),	new Arco(), 	new Arco(), 	new Arco(), 	new Arco(), 	new Arco(), 	new Arco(), 	new Arco()},
				/*B*/{new Arco(1,7), 	new Arco(), 	new Arco(1,2), 	new Arco(1,8), 	new Arco(), 	new Arco(), 	new Arco(), 	new Arco(), 	new Arco()},
				/*C*/{new Arco(), 		new Arco(1,6),	new Arco(), 	new Arco(), 	new Arco(1,6), 	new Arco(), 	new Arco(), 	new Arco(), 	new Arco()},
				/*D*/{new Arco(), 		new Arco(1,8),	new Arco(), 	new Arco(), 	new Arco(1,2), 	new Arco(), 	new Arco(), 	new Arco(), 	new Arco()},
				/*E*/{new Arco(), 		new Arco(), 	new Arco(1,2), 	new Arco(1,5), 	new Arco(), 	new Arco(1,4), 	new Arco(1,4),	new Arco(), 	new Arco()},
				/*F*/{new Arco(), 		new Arco(), 	new Arco(), 	new Arco(), 	new Arco(1,7), 	new Arco(), 	new Arco(), 	new Arco(1,7), 	new Arco()},				
				/*G*/{new Arco(),		new Arco(), 	new Arco(),		new Arco(), 	new Arco(1,3), 	new Arco(), 	new Arco(), 	new Arco(1,3), 	new Arco()},						
				/*H*/{new Arco(),		new Arco(), 	new Arco(), 	new Arco(), 	new Arco(), 	new Arco(), 	new Arco(1,8), 	new Arco(), 	new Arco(1,6)},				
				/*I*/{new Arco(),		new Arco(), 	new Arco(), 	new Arco(), 	new Arco(), 	new Arco(), 	new Arco(), 	new Arco(1,5 ), new Arco()}
					
		};
		
		Grafo grafo = new Grafo(vertices, matriz);
		Colonia colonia = new Colonia(grafo);
		colonia.buscarCamino();
	}

}
