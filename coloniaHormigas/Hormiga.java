 package coloniaHormigas;

import gui.VentanaPrincipal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

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
	
	private int cantidadVeces(int cantidadFeromonas, ArrayList<Vertice> vertices){
		int numVece=0;
		for (int i = 0; i < vertices.size(); i++) {
			if(vertices.get(i).getCantidadFeromona()==cantidadFeromonas) numVece++;
		}
		return numVece;
	}
	
	private String rangosIguales(ArrayList<Vertice> vertices, int numFeromonas, double random){
		double delta= (double)vertices.get(0).getCantidadFeromona()/numFeromonas; double min=0;
		for (int i = 0; i < vertices.size(); i++) {
			double max = ((double)vertices.get(i).getCantidadFeromona()/numFeromonas)*(i+1);
			if(i==vertices.size()-1){ //el ultimo
				if(random>=min&&random<=max) return vertices.get(i).getNombre();
				else System.out.println("Caso imposible, random: "+random);
			} else {
				if(random>=min&&random<max)	return vertices.get(i).getNombre();		
				else min+=delta;
			}
		}
		return null;
	}
	
	private String probabilidadCaminos(ArrayList<String> posiblesCaminos, int numFeromonas){
		//double probabilidadAcumulada = 0;
		ArrayList<Vertice> vertices = new ArrayList<Vertice>();
		for (int i = 0; i < posiblesCaminos.size(); i++) {
			vertices.add(this.grafo.getVertice(posiblesCaminos.get(i)));
		}
		Collections.sort(vertices);
		//generamos el numero aleatorio
		double random = new Random().nextDouble();
		//en caso de que todos tenga lamisma probabilidad se debe generar rangos acumulados sencillo
		if(this.cantidadVeces(vertices.get(0).getCantidadFeromona(), vertices)==vertices.size()){
			return this.rangosIguales(vertices, numFeromonas, random);
		} else {
			double min=0;
			for (int j = 0; j < vertices.size(); j++) {
				double max = ((double)vertices.get(j).getCantidadFeromona()/numFeromonas);
				int cantidadVeces = this.cantidadVeces(vertices.get(j).getCantidadFeromona(), vertices);
				if(cantidadVeces>1){
					if(j+cantidadVeces == vertices.size()){
						if(random>=min&&random<1)	{
							return vertices.get((int)(Math.random()*((j+cantidadVeces-1)-j)+j)).getNombre();
						}
						else {
							System.err.println("No debe pasar "+random);
						}
					} else {
						if(random>=min&&random<=max){
							return vertices.get((int)(Math.random()*((j+cantidadVeces-1)-j)+j)).getNombre();
						} else {
							j+=(cantidadVeces-1);
							min=max;
						}						
					}
				} else {
					if(j == vertices.size()-1){
						if(random>=min&&random<1)	return vertices.get(j).getNombre();		
						else {
							System.err.println("No debe pasar "+random);
						}
					} else {
						if(random>=min&&random<max)	return vertices.get(j).getNombre();		
						else {
							min=max;
						}						
					}
				}				
			}
		}
		System.out.println("no debe pasar");
		return null;
//		for (int i = 0; i < posiblesCaminos.size(); i++) {
//			auxCaminos.add(String.valueOf((double)this.grafo.getVertice(posiblesCaminos.get(i)).getCantidadFeromona()/numFeromonas));
//			System.out.println("probabilidad: "+(double)this.grafo.getVertice(posiblesCaminos.get(i)).getCantidadFeromona()/numFeromonas );
//			
//			
//		}
//		return posiblesCaminos;
	}
	
	public String seleccionarCamino(ArrayList<String> posiblesCaminos){
		int numFeromonas = 0;
		for (int i = 0; i < posiblesCaminos.size(); i++) {
			numFeromonas += this.grafo.getVertice(posiblesCaminos.get(i)).getCantidadFeromona();
		}	
		return this.probabilidadCaminos(posiblesCaminos, numFeromonas);
		
//		if(auxCaminos.size()==1){
//			return auxCaminos.get(0); //cuando solo encuentra a uno con la mayor porbabilidad //System.out.println("solo encontro uno");
//		} else if(auxCaminos.size()>1){ // cuando es más de uno que tienen una probabilidad igual y mayor 			//aplciar probabilidad de las feromonas
//			//System.out.println("son: "+auxCaminos.size()+", por loque se hace al azar");
//			return auxCaminos.get((int)(Math.random() * auxCaminos.size()));
//		} else {
//			System.err.println("No debio pasar");
//			return "";
//		}
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
			//double intervalo = ((double)distancia / (double)arco.getTiempo() );//los convertimos a double para que haga la division
			double intervalo = 1/ (double)arco.getTiempo() ;//los convertimos a double para que haga la division
			if (intervalo < 1) {//el intervalo es cada cuanto va a cambiar de posición. depente de la distancia y tiempo
				intervalo = 1;
			}
			//System.out.println("i -d " + intervalo);
			for (int i = posicionOrigen.x; i < posicionDestino.x; i+= intervalo) {
				int x = i;
				int y = this.calcularY(this.posicion, destino, x);//calculamos la posición en y de acuerdo a la ecuación de la recta 
				//System.out.println("x: " + x + " y " + y);
				graficoVentana.clearRect(this.posicionGrafica.x, this.posicionGrafica.y, 10, 10);//borramos la posicion actual 
				this.posicionGrafica.x = x; 
				this.posicionGrafica.y = y;
				graficoVentana.fillOval(x, y, 10, 10);//dibujamos la nueva posicion
				this.dibujarFeromona();
				
				Thread.sleep((int)(arco.getTiempo()));
			}
		}else{//caminamos de derecha a izquierda
			int distancia = posicionOrigen.x - posicionDestino.x;
			double intervalo = 1 /(double)arco.getTiempo();//los convertimos a double para que haga la division
			if (intervalo < 1) {
				intervalo = 1;
			}
			//System.out.println(intervalo);
			
			for (int i = posicionOrigen.x; i > posicionDestino.x; i-= intervalo) {
				int x = i;
				int y = this.calcularY(this.posicion, destino, x);
				//System.out.println("x: " + x + " y " + y);
				graficoVentana.clearRect(this.posicionGrafica.x, this.posicionGrafica.y, 10, 10);
				this.posicionGrafica.x = x; 
				this.posicionGrafica.y = y;
				graficoVentana.fillOval(x, y, 10, 10);
				this.dibujarFeromona();
				
				Thread.sleep((int)(arco.getTiempo()));
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
