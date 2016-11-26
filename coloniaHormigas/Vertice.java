package coloniaHormigas;


public class Vertice {
	
	public String nombre;
	public int cantidadFeromona;
	
	public Vertice(String nombre){
		this.nombre = nombre;
		this.cantidadFeromona = 1;
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
	
	
}
