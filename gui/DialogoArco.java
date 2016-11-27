package gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;

import javax.swing.JOptionPane;

import coloniaHormigas.Vertice;

public class DialogoArco extends JDialog implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JComboBox<String> origen;
	JComboBox<String> destino;
	JLabel labelOrigen;
	JLabel labelDestino;
	JButton aceptar = new JButton("Aceptar");
	VentanaPrincipal ventana;
	
	public DialogoArco(VentanaPrincipal ventana){
		this.ventana = ventana;
		this.setSize(new Dimension(450,50));
		this.setLocationRelativeTo(null);
		this.setLayout(new GridLayout());
		this.setResizable(false);
	}
	
	public void init(ArrayList<Vertice> vertices){
		labelOrigen = new JLabel("     Origen");
		this.add(labelOrigen);
		origen = new JComboBox<String>();
		for (int i = 0; i < vertices.size(); i++) {
			origen.addItem(vertices.get(i).getNombre());
		}
		this.add(origen);
		
		labelDestino = new JLabel("     Destino");
		this.add(labelDestino);
		destino = new JComboBox<String>();
		for (int i = 0; i < vertices.size(); i++) {
			destino.addItem(vertices.get(i).getNombre());
		}
		this.add(destino);
		
		aceptar = new JButton("Aceptar");
		aceptar.addActionListener(this);
		this.add(aceptar);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String origen = (String)this.origen.getSelectedItem();
		
		String destino = (String)this.destino.getSelectedItem();
		String pesoArco = JOptionPane.showInputDialog("Ingrese el peso del arco");
		if (pesoArco!=null){
			double tiempo = Double.parseDouble(pesoArco);
			//System.out.println(tiempo);
			this.ventana.agregarArco(origen, destino, tiempo);
			this.setVisible(false);			
		} else {
			JOptionPane.showMessageDialog(this,"No se ha agregado ningún peso entre los arcos seleccionados");
		}
	}

}
