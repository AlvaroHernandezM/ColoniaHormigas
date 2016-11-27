package coloniaHormigas;

import gui.VentanaPrincipal;

import java.util.ArrayList;

public class Test {
	
	public static void main(String[] args) {
		//Vertice[] vertices = {new Vertice(Constantes.CASA), new Vertice("B"), new Vertice("C"), new Vertice("D"),  new Vertice("E"), new Vertice("F"), new Vertice("G"), new Vertice("H"), new Vertice(Constantes.COMIDA)};
//		Arco[][] matriz= {//inicializamos la matriz de adyacencia
//						//A					B				C			D				E				F			G			H			I				
//				/*A*/{new Arco(), 		new Arco(1,3),	new Arco(), 	new Arco(), 	new Arco(), 	new Arco(), 	new Arco(), 	new Arco(), 	new Arco()},
//				/*B*/{new Arco(1,7), 	new Arco(), 	new Arco(1,2), 	new Arco(1,8), 	new Arco(), 	new Arco(), 	new Arco(), 	new Arco(), 	new Arco()},
//				/*C*/{new Arco(), 		new Arco(1,6),	new Arco(), 	new Arco(), 	new Arco(1,6), 	new Arco(), 	new Arco(), 	new Arco(), 	new Arco()},
//				/*D*/{new Arco(), 		new Arco(1,8),	new Arco(), 	new Arco(), 	new Arco(1,2), 	new Arco(), 	new Arco(), 	new Arco(), 	new Arco()},
//				/*E*/{new Arco(), 		new Arco(), 	new Arco(1,2), 	new Arco(1,5), 	new Arco(), 	new Arco(1,4), 	new Arco(1,4),	new Arco(), 	new Arco()},
//				/*F*/{new Arco(), 		new Arco(), 	new Arco(), 	new Arco(), 	new Arco(1,7), 	new Arco(), 	new Arco(), 	new Arco(1,7), 	new Arco()},				
//				/*G*/{new Arco(),		new Arco(), 	new Arco(),		new Arco(), 	new Arco(1,3), 	new Arco(), 	new Arco(), 	new Arco(1,3), 	new Arco()},						
//				/*H*/{new Arco(),		new Arco(), 	new Arco(), 	new Arco(), 	new Arco(), 	new Arco(), 	new Arco(1,8), 	new Arco(), 	new Arco(1,6)},				
//				/*I*/{new Arco(),		new Arco(), 	new Arco(), 	new Arco(), 	new Arco(), 	new Arco(), 	new Arco(), 	new Arco(1,5 ), new Arco()}
//					
//		};
		
		ArrayList<Vertice> listaVertices = new ArrayList<Vertice>();
		listaVertices.add(new Vertice(Constantes.CASA));
		listaVertices.add(new Vertice("B"));
		listaVertices.add(new Vertice("C"));
		listaVertices.add(new Vertice("D"));
		listaVertices.add(new Vertice("E"));
		listaVertices.add(new Vertice("F"));
		listaVertices.add(new Vertice("G"));
		listaVertices.add(new Vertice("H"));
		listaVertices.add(new Vertice(Constantes.COMIDA));
		
		
		ArrayList<ArrayList<Arco>> listaArcos = new ArrayList<ArrayList<Arco>>();
		ArrayList<Arco> A = new ArrayList<Arco>();
		ArrayList<Arco> B = new ArrayList<Arco>();
		ArrayList<Arco> C = new ArrayList<Arco>();	
		ArrayList<Arco> D = new ArrayList<Arco>();
		ArrayList<Arco> E = new ArrayList<Arco>();
		ArrayList<Arco> F = new ArrayList<Arco>();
		ArrayList<Arco> G = new ArrayList<Arco>();
		ArrayList<Arco> H = new ArrayList<Arco>();
		ArrayList<Arco> I = new ArrayList<Arco>();
		
		
		//A							B							C						D							E					F						G					H					I
		A.add(new Arco());		A.add(new Arco(1,3)); 	A.add(new Arco());		A.add(new Arco()); 		A.add(new Arco()); 		A.add(new Arco());		A.add(new Arco()); 		A.add(new Arco()); 		A.add(new Arco());
		B.add(new Arco(1,7));	B.add(new Arco());		B.add(new Arco(1,2));	B.add(new Arco(1,8));	B.add(new Arco());		B.add(new Arco());		B.add(new Arco());		B.add(new Arco());		B.add(new Arco());
		C.add(new Arco());		C.add(new Arco(1,6));	C.add(new Arco());		C.add(new Arco());		C.add(new Arco(1,6));	C.add(new Arco());		C.add(new Arco());		C.add(new Arco());		C.add(new Arco());
		D.add(new Arco());		D.add(new Arco(1,8));	D.add(new Arco());		D.add(new Arco());		D.add(new Arco(1,2));	D.add(new Arco());		D.add(new Arco());		D.add(new Arco());		D.add(new Arco());
		E.add(new Arco());		E.add(new Arco());		E.add(new Arco(1,2));	E.add(new Arco(1,5));	E.add(new Arco());		E.add(new Arco(1,4));	E.add(new Arco(1,4));	E.add(new Arco());		E.add(new Arco());
		F.add(new Arco());		F.add(new Arco());		F.add(new Arco());		F.add(new Arco());		F.add(new Arco(1,7));	F.add(new Arco());		F.add(new Arco());		F.add(new Arco(1,7));	F.add(new Arco());
		G.add(new Arco());		G.add(new Arco());		G.add(new Arco());		G.add(new Arco());		G.add(new Arco(1,3));	G.add(new Arco());		G.add(new Arco());		G.add(new Arco(1,3));	G.add(new Arco());
		H.add(new Arco());		H.add(new Arco());		H.add(new Arco());		H.add(new Arco());		H.add(new Arco());		H.add(new Arco());		H.add(new Arco(1,8));	H.add(new Arco());		H.add(new Arco(1,6));
		I.add(new Arco());		I.add(new Arco());		I.add(new Arco());		I.add(new Arco());		I.add(new Arco());		I.add(new Arco());		I.add(new Arco());		I.add(new Arco(1,5 ));	I.add(new Arco());

		
		listaArcos.add(A);
		listaArcos.add(B);
		listaArcos.add(C);
		listaArcos.add(D);
		listaArcos.add(E);
		listaArcos.add(F);
		listaArcos.add(G);
		listaArcos.add(H);
		listaArcos.add(I);
		
		
		//Grafo grafo = new Grafo(listaVertices, listaArcos);
		//Colonia colonia = new Colonia(grafo, null);
		//colonia.buscarCamino();
		
		VentanaPrincipal ventana = new VentanaPrincipal();
		ventana.setVisible(true);
		
		Thread hilo = new Thread(new Runnable() {//limpia la pantalla cada 5 segundos
			public void run() {
				ventana.repaint();
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}); 
		hilo.start();
	}

}

