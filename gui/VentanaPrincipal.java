package gui;

import java.awt.BasicStroke;
import java.awt.BorderLayout;

import coloniaHormigas.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class VentanaPrincipal extends JFrame implements ActionListener, MouseListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	boolean dibujo;//false = arco, true = nodo
	final int TAMANO_NODO = 35; 
	Grafo grafo;
	
	JMenuBar barraMenu;
	JMenu menuDibujar;
	JMenuItem nodo;
	JMenuItem arco;
	
	JMenu menuColonia;
	JMenuItem menuBuscar;
	JPanelFondo pnlFondo; 
	
	JLabel lblHormigas;
	
	public VentanaPrincipal(){
		this.setTitle("COLONIA DE HORMIGAS - INV. OPERACIONES 2016-02");
		this.setSize(super.getToolkit().getScreenSize());
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addMouseListener(this);		
		this.dibujo = true;
		this.grafo = new Grafo();			
		this.init();	
	}
	
	
	public void init(){
		this.pnlFondo = new JPanelFondo("/img/fondo.jpg");
		this.add(this.pnlFondo);
		barraMenu = new JMenuBar();
		menuDibujar = new JMenu("Dibujar");
		nodo = new JMenuItem("Nodo");
		arco = new JMenuItem("Arco");
		arco.addActionListener(this);
		menuDibujar.add(arco);
		menuDibujar.add(nodo);
		barraMenu.add(menuDibujar);
		
		
		menuColonia = new JMenu("Acciones");
		menuBuscar = new JMenuItem("Buscar camino");
		menuBuscar.addActionListener(this);
		menuColonia.add(menuBuscar);
		barraMenu.add(menuColonia);
		
		this.setJMenuBar(barraMenu);
		this.lblHormigas = new JLabel("0");
		this.lblHormigas.setBounds(0, 0, 100, 30);
		this.pnlFondo.add(lblHormigas);
	}
	
	public void agregarNodo(int x, int y){
		try {
			String nombreNodo = JOptionPane.showInputDialog("Nombre del nodo");
			if(nombreNodo!=null && nombreNodo.length()>0){
				Vertice vertice = new Vertice(nombreNodo, new Point(x,y));
				vertice.start();
				this.grafo.agregarVertice(vertice);
				//Graphics grafico = this.getGraphics();
				Graphics2D grafico = (Graphics2D) this.getGraphics();
				grafico.setColor(Color.white);
				grafico.fillOval(x, y, TAMANO_NODO, TAMANO_NODO);		
				FontRenderContext frc = grafico.getFontRenderContext();
	            TextLayout textTl = new TextLayout(nombreNodo, getFont(), frc);
	            Shape outline = textTl.getOutline(null);
	            int x1=0;
	            int y1=0;
	            x1 = (x +(TAMANO_NODO/2))-Constantes.DELTA_NOMBRE;
	            y1 = (y+(TAMANO_NODO/2))-Constantes.DELTA_NOMBRE;
	            grafico.translate(x1, y1);;
				grafico.setColor(Color.black);
				grafico.draw(outline);
			} else {
				JOptionPane.showMessageDialog(this, "No se ha asignado ningún nombre al nodo");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void agregarArco(String origen, String destino, double tiempo){
		Vertice verticeOrigen = this.grafo.getVertice(origen);
		Vertice verticeDestino = this.grafo.getVertice(destino);		
		
		this.grafo.agregarArco(origen, destino, tiempo);
		
		Point centroArco = this.calcularCentroArco(verticeOrigen, verticeDestino);
		
		//Graphics graficos = this.getGraphics();
		Graphics2D graficos = (Graphics2D) this.getGraphics();
		Stroke stroke = new BasicStroke(2f);
		graficos.setColor(Color.black);
		graficos.setStroke(stroke);
		graficos.drawLine(verticeOrigen.getPosicion().x+(TAMANO_NODO/2), verticeOrigen.getPosicion().y+(TAMANO_NODO/2), verticeDestino.getPosicion().x+(TAMANO_NODO/2), verticeDestino.getPosicion().y+(TAMANO_NODO/2));
		FontRenderContext frc = graficos.getFontRenderContext();
        TextLayout textTl = new TextLayout(""+ tiempo, getFont(), frc);
        Shape outline = textTl.getOutline(null);
        int x1 = centroArco.x;
        int y1 = centroArco.y;	            	
        graficos.translate(x1, y1);;
		graficos.setColor(Color.black);
		graficos.draw(outline);
	}
	
	public Point calcularCentroArco(Vertice origen, Vertice destino){
		int x1 = origen.getPosicion().x;
		int y1 = -1*origen.getPosicion().y;
		
		int x2 = destino.getPosicion().x;
		int y2 = -1*destino.getPosicion().y;
		
		double m = ((double)y2 - (double)y1) / ((double)x2 - (double)x1);
		
		int x = 0;
		if (x1 > x2) {
			x = ((x1 - x2) / 2) + x2;
		}else{
			x = ((x2 - x1) / 2) + x1;
		}
		double y = y1 + (m * x) - (m * x1);
		return new Point(x, (int)(-1*y));
	}
	
	public void sumarHormiga(){
		this.lblHormigas.setText(String.valueOf(Integer.parseInt(this.lblHormigas.getText())+1));	
	}
	
	
	public void calcular(Vertice origen, Vertice destino){
		int x1 = origen.getPosicion().x;
		int y1 = -1*origen.getPosicion().y;
		
		int x2 = destino.getPosicion().x;
		int y2 = -1*destino.getPosicion().y;
		
		double m = ((double)y2 - (double)y1) / ((double)x2 - (double)x1);
		//System.out.println((y2 - y1) + " " + (x2 - x1));
		
		int inicioBucle = 0;
		int finBucle = 0;
		if (x1 > x2) {
			inicioBucle = x2;
			finBucle = x1;
		}else{
			inicioBucle = x1;
			finBucle = x2;
		}
		//System.out.println("x1: " + x1 + "y1: " + y1);
		//System.out.println("x2: " + x2 + "y2: " + y2);
		Graphics2D graficos = (Graphics2D) this.getGraphics();
		for (int i = inicioBucle; i < finBucle; i++) {
			double y = y1 + (m * i) - (m * x1);
			graficos.fillOval(i, -1*(int)y, 10, 10);			
			//System.out.println("x: "+ i + " y: " +y + " m :" + m);
		}
		
	}
	
	public void dibuja(){
		Graphics g = this.getGraphics();
		for (int i = 0; i < grafo.getVertices().size(); i++) {
			int x = grafo.getVertices().get(i).getPosicion().x;
			int y = grafo.getVertices().get(i).getPosicion().y;
			String nombreNodo = grafo.getVertices().get(i).getNombre();
			g.setColor(Color.white);
			g.fillOval(x, y, TAMANO_NODO, TAMANO_NODO);		
			g.setColor(Color.black);
			g.drawString(nombreNodo, (x +(TAMANO_NODO/2))-Constantes.DELTA_NOMBRE, (y+(TAMANO_NODO/2))-Constantes.DELTA_NOMBRE);
		}
		
		for (int i = 0; i < grafo.getArcos().size(); i++) {
			for (int j = 0; j < grafo.getArcos().get(i).size(); j++) {
				if (grafo.getArcos().get(i).get(j).adyacencia == 1) {
					Vertice origen = grafo.getVertice(i);
					Vertice destino = grafo.getVertice(j);
					double tiempo = grafo.getArcos().get(i).get(j).getTiempo();
					Point centroArco = this.calcularCentroArco(origen , destino);
					
					Graphics graficos = this.getGraphics();
					graficos.setColor(Color.black);
					graficos.drawLine(origen.getPosicion().x+(TAMANO_NODO/2), origen.getPosicion().y+(TAMANO_NODO/2), destino.getPosicion().x+(TAMANO_NODO/2), destino.getPosicion().y+(TAMANO_NODO/2));
					graficos.drawString(""+ tiempo, centroArco.x, centroArco.y);
				}
			}			
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Arco")) {			
			DialogoArco dialogo = new DialogoArco(this);
			dialogo.init(this.grafo.getVertices());
			dialogo.setVisible(true);
		}
		if (e.getActionCommand().equals("Buscar camino")) {
			Colonia colonia = new Colonia(this.grafo, this, Integer.parseInt(JOptionPane.showInputDialog("Número de hormigas")));
			colonia.buscarCamino();
			this.dibujo=false;
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (this.dibujo) {
			this.agregarNodo(e.getX(), e.getY());
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
