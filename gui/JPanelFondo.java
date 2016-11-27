package gui;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class JPanelFondo extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ImageIcon imagen;
	private String ruta;
	
	public JPanelFondo(String ruta) {
		super();
		this.ruta = ruta;
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		Dimension tamanio = this.getSize();
		imagen= new ImageIcon(getClass().getResource(ruta));
		g.drawImage(imagen.getImage(), 0, 0, tamanio.width,tamanio.height,null);
		setOpaque(false);
		super.paint(g);
	}
	
	

}
