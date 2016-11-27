package coloniaHormigas;

import gui.VentanaPrincipal;

public class Colonia {
	
	Grafo grafo;
	VentanaPrincipal ventana;
	int numHormigas;
	
	public Colonia(Grafo grafo, VentanaPrincipal ventana, int numHormigas){
		this.grafo = grafo;
		this.ventana = ventana;
		this.numHormigas = numHormigas;
	}
	
	public void buscarCamino(){
		for (int i = 0; i < this.numHormigas; i++) {
			Hormiga hormiga = new Hormiga(this.grafo, this.grafo.getVertice("Casa"), ventana);
			hormiga.start();
			try {
				Thread.sleep(800);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}

}
