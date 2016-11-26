package coloniaHormigas;

public class Arco {
	
	public int adyacencia;
	
	double tiempo;
	//tiempo de costo
	public Arco(int adyacencia, double tiempo){
		this.adyacencia = adyacencia;
		this.tiempo = tiempo;
	}
	
	public Arco(){
		this.adyacencia = 0;
		this.tiempo = 0;
	}
	
	public int getAdyacencia() {
		return adyacencia;
	}
	public void setAdyacencia(int adyacencia) {
		this.adyacencia = adyacencia;
	}

	public double getTiempo() {
		return tiempo;
	}

	public void setTiempo(double tiempo) {
		this.tiempo = tiempo;
	}
	
	

	
}
