package coloniaHormigas;

import java.util.ArrayList;

public class Grafo {
	
	Vertice[] vertices;
	Arco[][] arcos;
	
	public Grafo(Vertice[] vertices, Arco[][] arcos){
		if (vertices.length == arcos.length && arcos.length  == arcos[0].length) {
			this.vertices = vertices;
			this.arcos = arcos;
		}else{
			System.out.println("Existe un proble con las matrices dadas");
			this.vertices = null;
			this.arcos = null;
		}
		
	}

	public ArrayList<String> getPosiblesCamigos(Vertice posicion){
		int indexPosicion = -1;
		ArrayList<String> caminos = new ArrayList<String>();
		for (int i = 0; i < vertices.length; i++) {
			if (vertices[i].getNombre().equals(posicion.getNombre())) {
				indexPosicion = i;
				break;
			}
		}
		
		for (int i = 0; i < arcos[0].length; i++) {
			if (arcos[indexPosicion][i].getAdyacencia() == 1) {
				caminos.add(vertices[i].getNombre());
			}
		}
		
		return caminos;
	}
	

	
	public Vertice getVertice(String nombre){
		for (int i = 0; i < this.vertices.length ; i++) {
			if (vertices[i].getNombre().equals(nombre)) {
				return vertices[i]; 
			}
		}
		return null;
	}
	
	public Arco getArco(String origen, String destino){
		int indexOrigen = -1;
		int indexDestino = -1;
		for (int i = 0; i < vertices.length; i++) {
			if (vertices[i].getNombre().equals(origen)) {
				indexOrigen = i;
			}			
			if (vertices[i].getNombre().equals(destino)) {
				indexDestino = i;
			}
		}
		return this.arcos[indexOrigen][indexDestino];
	}
	
	public Vertice getVertice(int index){
		return this.vertices[index];
	}
	
	public void mostrarCaminos(){
		for (int i = 0; i < vertices.length; i++) {
			System.out.println("Fermonoas en " + vertices[i].getNombre()+ ": " + vertices[i].getCantidadFeromona()); 
			
		}
	}
}
