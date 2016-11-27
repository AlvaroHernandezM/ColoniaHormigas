 package coloniaHormigas;

import gui.VentanaPrincipal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class Hormiga extends Thread{
	
	Vertice posicion;
	boolean buscando;
	Grafo grafo;
	boolean trabajando;
	VentanaPrincipal ventana;
	Point posicionGrafica;
	
	public Hormiga(Grafo grafo, Vertice posicion, VentanaPrincipal ventana){
		this.grafo = grafo;
		this.buscando = true;
		this.trabajando = true;
		this.posicion = posicion;
		this.ventana = ventana;
		this.posicionGrafica = new Point(this.posicion.getPosicion().x, this.posicion.getPosicion().y);
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
		//System.out.println("FEROMOAS :: "+posiblesCaminos.get(indexCamino));
		//System.out.println("FEROMOAS :: "+this.posicion.getNombre());
		return posiblesCaminos.get(indexCamino);
	}
	
	public void dejarFeromona(){
		this.posicion.aumentarFeromona(1);	
	}
	
	public void dibujarFeromona(){
		Graphics graficos = this.ventana.getGraphics();
		graficos.setColor(Color.yellow);
		graficos.fillOval(this.posicionGrafica.x -3, this.posicionGrafica.y-3, this.posicion.getCantidadFeromona(), this.posicion.getCantidadFeromona());
	}
	
		
	public void buscar() throws InterruptedException{
		ArrayList<String> posiblesCaminos = this.grafo.getPosiblesCamigos(this.posicion);
		String nombreCamino = this.seleccionarCamino(posiblesCaminos);
		Vertice destino = this.grafo.getVertice(nombreCamino);
		
		//System.out.println("caminando de " + this.posicion.getNombre() + " a " + destino.getNombre());
		this.caminar(destino);		
		//System.out.println("Llegamos a " + this.posicion.getNombre());
		
		
		if (this.posicion.getNombre().equals(Constantes.COMIDA)) {	
			//System.out.println("*****************Encontramos Comida****************");
			this.buscando = false;			
		}
	}
	
	public void retornar() throws InterruptedException{
		this.dejarFeromona();
		
		ArrayList<String> posiblesCaminos = this.grafo.getPosiblesCamigos(this.posicion);
		String nombreDestino= this.seleccionarRetorno(posiblesCaminos);
		Vertice destino = this.grafo.getVertice(nombreDestino);
		
		//System.out.println("Retornando de " + this.posicion.getNombre() + " a " + destino.getNombre());
		this.caminar(destino);
		//System.out.println("Llegamos a " + this.posicion.getNombre());
		
		this.dejarFeromona();
		
		if (this.posicion.getNombre().equals(Constantes.CASA)) {
			this.trabajando = false;
		}
	}
	
	public int calcularY(Vertice origen, Vertice destino, int x){
		int x1 = origen.getPosicion().x;
		int y1 = -1*origen.getPosicion().y;
		
		int x2 = destino.getPosicion().x;
		int y2 = -1*destino.getPosicion().y;
		
		double m = ((double)y2 - (double)y1) / ((double)x2 - (double)x1);		
		
		double y = y1 + (m * x) - (m * x1);
		return (int)(-1*y);
	}
	
	public void caminar(Vertice destino) throws InterruptedException{
		Arco arco = this.grafo.getArco(this.posicion.getNombre(), destino.getNombre());
		//System.out.println(arco.getTiempo());
		
		Point posicionOrigen = this.posicion.getPosicion();
		Point posicionDestino = destino.getPosicion();
		
		Graphics graficoVentana = this.ventana.getGraphics();
		graficoVentana.setColor(Color.blue);
		graficoVentana.fillOval(posicionGrafica.x, posicionGrafica.y, 10, 10);
		
		if (posicionOrigen.x < posicionDestino.x) {//camina de izquierda a derecha
			int distancia = posicionDestino.x - posicionOrigen.x;
			double intervalo = ((double)distancia / (double)arco.getTiempo() );//los convertimos a double para que haga la division
			if (intervalo < 1) {//el intervalo es cada cuanto va a cambiar de posición. depente de la distancia y tiempo
				intervalo = 1;
			}
			for (int i = posicionOrigen.x; i < posicionDestino.x; i+= intervalo) {
				int x = i;
				int y = this.calcularY(this.posicion, destino, x);//calculamos la posición en y de acuerdo a la ecuación de la recta 
				//System.out.println("x: " + x + " y " + y);
				graficoVentana.clearRect(this.posicionGrafica.x, this.posicionGrafica.y, 10, 10);//borramos la posicion actual 
				this.posicionGrafica.x = x; 
				this.posicionGrafica.y = y;
				graficoVentana.fillOval(x, y, 10, 10);//dibujamos la nueva posicion
				this.dibujarFeromona();
				
				Thread.sleep(400);
			}
		}else{//caminamos de derecha a izquierda
			int distancia = posicionOrigen.x - posicionDestino.x;
			double intervalo = ((double)distancia / (double)arco.getTiempo() );//los convertimos a double para que haga la division
			if (intervalo < 1) {
				intervalo = 1;
			}
			for (int i = posicionOrigen.x; i > posicionDestino.x; i-= intervalo) {
				int x = i;
				int y = this.calcularY(this.posicion, destino, x);
				//System.out.println("x: " + x + " y " + y);
				graficoVentana.clearRect(this.posicionGrafica.x, this.posicionGrafica.y, 10, 10);
				this.posicionGrafica.x = x; 
				this.posicionGrafica.y = y;
				graficoVentana.fillOval(x, y, 10, 10);
				this.dibujarFeromona();
				
				Thread.sleep(400);
			}			
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
			this.ventana.dibuja();
		}
		this.grafo.mostrarCaminos();
		this.ventana.sumarHormiga();
	}
	
}
