package coloniaHormigas;

import java.util.ArrayList;

public class Grafo {
	
	ArrayList<Vertice> vertices;
	ArrayList<ArrayList<Arco>> arcos;
	
	public Grafo(ArrayList<Vertice> vertices, ArrayList<ArrayList<Arco>> arcos){
		if (vertices.size() == arcos.size() && arcos.size()  == arcos.get(0).size()) {
			this.vertices = vertices;
			this.arcos = arcos;
		}else{
			System.out.println("Existe un problema con las matrices dadas");
			this.vertices = null;
			this.arcos = null;
		}
		
	}
	
	
	public Grafo(){
		this.vertices = new ArrayList<Vertice>();
		this.arcos = new ArrayList<ArrayList<Arco>>();
	}

	public ArrayList<String> getPosiblesCamigos(Vertice posicion){
		int indexPosicion = -1;
		ArrayList<String> caminos = new ArrayList<String>();
		for (int i = 0; i < vertices.size(); i++) {
			if (vertices.get(i).getNombre().equals(posicion.getNombre())) {
				indexPosicion = i;
				break;
			}
		}
		
		for (int i = 0; i < arcos.get(0).size(); i++) {
			if (arcos.get(indexPosicion).get(i).getAdyacencia() == 1) {
				caminos.add(vertices.get(i).getNombre());
			}
		}
		
		return caminos;
	}
	

	
	public Vertice getVertice(String nombre){
		for (int i = 0; i < this.vertices.size(); i++) {
			if (vertices.get(i).getNombre().equals(nombre)) {
				return vertices.get(i); 
			}
		}
		return null;
	}
	
	public Arco getArco(String origen, String destino){
		int indexOrigen = -1;
		int indexDestino = -1;
		for (int i = 0; i < vertices.size(); i++) {
			if (vertices.get(i).getNombre().equals(origen)) {
				indexOrigen = i;
			}			
			if (vertices.get(i).getNombre().equals(destino)) {
				indexDestino = i;
			}
		}
		return this.arcos.get(indexOrigen).get(indexDestino);
	}
	
	public Vertice getVertice(int index){
		return this.vertices.get(index);
	}
	
	public void mostrarCaminos(){
		for (int i = 0; i < vertices.size(); i++) {
			System.out.println("Fermonoas en " + vertices.get(i).getNombre()+ ": " + vertices.get(i).getCantidadFeromona());			
		}
	}
	
	
	public void agregarVertice(Vertice vertice){
		this.vertices.add(vertice);
		
		for (int i = 0; i < arcos.size(); i++) {//agregamos la columa a las filas exitentes
			arcos.get(i).add(new Arco());
		}
		
		ArrayList<Arco> fila = new ArrayList<Arco>();
		for (int i = 0; i < vertices.size(); i++) {//agregamos la nueva fila
			fila.add(new Arco());
		}
		this.arcos.add(fila);
		
//		for (int i = 0; i < arcos.size(); i++) {
//			for (int j = 0; j < arcos.get(i).size(); j++) {
//				System.out.print(arcos.get(i).get(j).getAdyacencia());
//			}
//			System.out.println();
//		}
//		System.out.println();
	}


	public ArrayList<Vertice> getVertices() {
		return vertices;
	}


	public void setVertices(ArrayList<Vertice> vertices) {
		this.vertices = vertices;
	}
	
	public void agregarArco(String origen, String destino, double tiempo){
		int indexOrigen1 = -1;
		int indexDestino1= -1;
		int indexOrigen2 = -1;
		int indexDestino2= -1;
		
		for (int i = 0; i < this.vertices.size(); i++) {
			if (this.vertices.get(i).getNombre().equals(origen)) {
				indexOrigen1 = i;
				indexDestino2 = i;
			}
			if (this.vertices.get(i).getNombre().equals(destino)) {
				indexDestino1 = i;
				indexOrigen2 = i;
			}
		}		
		this.arcos.get(indexOrigen1).set(indexDestino1, new Arco(1,tiempo));//de origen a destino
		this.arcos.get(indexOrigen2).set(indexDestino2, new Arco(1,tiempo));//de destino a origen
		
//		for (int i = 0; i < arcos.size(); i++) {
//			for (int j = 0; j < arcos.get(i).size(); j++) {
//				System.out.print(arcos.get(i).get(j).getAdyacencia());
//			}
//			System.out.println();
//		}
//		System.out.println();
	}


	public ArrayList<ArrayList<Arco>> getArcos() {
		return arcos;
	}


	public void setArcos(ArrayList<ArrayList<Arco>> arcos) {
		this.arcos = arcos;
	}
	
	
}
