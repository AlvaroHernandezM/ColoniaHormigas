 package coloniaHormigas;

import java.util.ArrayList;

public class Hormiga extends Thread{
	
	Vertice posicion;
	boolean buscando;
	Grafo grafo;
	boolean trabajando;
	
	public Hormiga(Grafo grafo, Vertice posicion){
		this.grafo = grafo;
		this.buscando = true;
		this.trabajando = true;
		this.posicion = posicion;
	}
	
	public boolean isBuscando() {
		return buscando;
	}
	public void setBuscando(boolean buscando) {
		this.buscando = buscando;
	}
	
	private ArrayList<String> probabilidadCaminos(ArrayList<String> posiblesCaminos, int numFeromonas){
		double probabilidadMax = 0;
		ArrayList<String> auxCaminos = new ArrayList<>(); 
		for (int i = 0; i < posiblesCaminos.size(); i++) {
			System.out.println("probabilidad: "+(double)this.grafo.getVertice(posiblesCaminos.get(i)).getCantidadFeromona()/numFeromonas );
			if((double)this.grafo.getVertice(posiblesCaminos.get(i)).getCantidadFeromona()/numFeromonas >= probabilidadMax){
				probabilidadMax = (double)this.grafo.getVertice(posiblesCaminos.get(i)).getCantidadFeromona()/numFeromonas;
				for (int j = 0; j < auxCaminos.size(); j++) {
					if((double)this.grafo.getVertice(auxCaminos.get(j)).getCantidadFeromona()/numFeromonas < probabilidadMax){
						auxCaminos.remove(j);
					}
				}
				auxCaminos.add(posiblesCaminos.get(i));
			}
		}
		return auxCaminos;
	}
	
	public String seleccionarCamino(ArrayList<String> posiblesCaminos){
		int numFeromonas = 0;
		for (int i = 0; i < posiblesCaminos.size(); i++) {
			numFeromonas += this.grafo.getVertice(posiblesCaminos.get(i)).getCantidadFeromona();
		}	
		
		ArrayList<String> auxCaminos = this.probabilidadCaminos(posiblesCaminos, numFeromonas);
		
		if(auxCaminos.size()==1){
			return auxCaminos.get(0); //cuando solo encuentra a uno con la mayor porbabilidad //System.out.println("solo encontro uno");
		} else if(auxCaminos.size()>1){ // cuando es más de uno que tienen una probabilidad igual y mayor 			//aplciar probabilidad de las feromonas
			//System.out.println("son: "+auxCaminos.size()+", por loque se hace al azar");
			return auxCaminos.get((int)(Math.random() * auxCaminos.size()));
		} else {
			System.err.println("No debio pasar");
			return "";
		}
	}
	
	public String seleccionarRetorno(ArrayList<String> posiblesCaminos){
		int indexCamino = (int)(Math.random() * posiblesCaminos.size());
		System.out.println("FEROMOAS :: "+posiblesCaminos.get(indexCamino));
		return posiblesCaminos.get(indexCamino);
//		Vertice camino = this.grafo.getVertice(posiblesCaminos.get(indexCamino));
//		System.out.println("Feromona en " + camino.getNombre()+ ": " + camino.getCantidadFeromona());
//		if (camino.getCantidadFeromona() == 0) {
//			return posiblesCaminos.get(indexCamino);
//		}else{
//			return this.seleccionarRetorno(posiblesCaminos);
//		}
	}
	
	public void dejarFeromona(){
		this.posicion.aumentarFeromona(10);
	}
		
	public void buscar() throws InterruptedException{
		ArrayList<String> posiblesCaminos = this.grafo.getPosiblesCamigos(this.posicion);
		String nombreCamino = this.seleccionarCamino(posiblesCaminos);
		Vertice destino = this.grafo.getVertice(nombreCamino);
		System.out.println("caminando de " + this.posicion.getNombre() + " a " + destino.getNombre());
		this.caminar(destino);
		System.out.println("Llegamos a " + this.posicion.getNombre());		
		if (this.posicion.getNombre().equals(Constantes.COMIDA)) {	
			System.out.println("*****************Encontramos Comida****************");
			this.buscando = false;
		}
	}
	
	public void retornar() throws InterruptedException{
		this.dejarFeromona();
		ArrayList<String> posiblesCaminos = this.grafo.getPosiblesCamigos(this.posicion);
		String nombreDestino= this.seleccionarRetorno(posiblesCaminos);
		Vertice destino = this.grafo.getVertice(nombreDestino);
		System.out.println("Retornando de " + this.posicion.getNombre() + " a " + destino.getNombre());
		this.caminar(destino);
		System.out.println("Llegamos a " + this.posicion.getNombre());
		this.dejarFeromona();
		if (this.posicion.getNombre().equals(Constantes.CASA)) {
			this.trabajando = false;
		}
	}
	
	public void caminar(Vertice destino) throws InterruptedException{
		Arco arco = this.grafo.getArco(this.posicion.getNombre(), destino.getNombre());
		System.out.println(arco.getTiempo());
		
		for (int i = (int)arco.getTiempo(); i > 0  ; i--) {			
			//System.out.println("Caminando de " + this.posicion.getNombre() + " a " +  destino.getNombre() + " Falta " + i);
			Thread.sleep(50);
		}
		this.posicion = grafo.getVertice(destino.getNombre());
	}
	

	public void run() {
		while(this.trabajando){
			try {
				
				if (isBuscando() && !this.posicion.esComida()) {
					this.buscar();					
				}
				if (!this.isBuscando() && !this.posicion.esCasa()){
					this.retornar();
				}
			
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
		this.grafo.mostrarCaminos();
	}
	
}
