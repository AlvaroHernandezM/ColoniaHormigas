package coloniaHormigas;

public class Vertice implements Comparable<Vertice>{
	
	private String nombre;
	private int cantidadFeromona;
	private java.awt.Point posicion;
	
	public Vertice(String nombre){
		this.nombre = nombre;
		this.cantidadFeromona = 1;
	}
	
	public Vertice(String nombre, java.awt.Point posicion){
		this.nombre = nombre;
		this.cantidadFeromona = 1;
		this.posicion = posicion; 
	}
	
	
	public boolean esCasa(){
		return this.nombre.equals(Constantes.CASA);
	}
	
	public boolean esComida(){
		return this.nombre.equals(Constantes.COMIDA);
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void aumentarFeromona(int cantidadFeromona){
		this.cantidadFeromona += cantidadFeromona;
	}


	public int getCantidadFeromona() {
		return cantidadFeromona;
	}


	public void setCantidadFeromona(int cantidadFeromona) {
		this.cantidadFeromona = cantidadFeromona;
	}

	public java.awt.Point getPosicion() {
		return posicion;
	}

	public void setPosicion(java.awt.Point posicion) {
		this.posicion = posicion;
	}
	
//	@Override
//	public void run() {
//		while(true){
//			if(this.cantidadFeromona>1){
//				this.cantidadFeromona--;
//			}
//			try {
//				Thread.sleep(3000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}

	@Override
	public int compareTo(Vertice v2) {
		if(this.cantidadFeromona > v2.getCantidadFeromona()) {
			return 1;
		}
		if(this.cantidadFeromona < v2.getCantidadFeromona()) {
			return -1;
		}
		return 0;
	}
	
}
