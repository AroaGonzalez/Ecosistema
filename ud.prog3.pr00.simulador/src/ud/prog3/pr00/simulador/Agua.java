package ud.prog3.pr00.simulador;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Agua extends ElementoEcosistema {
	
	protected long cantidad;
	protected JPanel panel;

	public Agua(String titulo, Dimension dimension, Point posicion) {
		super(titulo, dimension, posicion);
		cantidad = (long) Math.sqrt(dimension.getWidth()*dimension.height);
	}

	public long getCantidad() {
		return cantidad;
	}

	public void setCantidad(long cantidad) {
		this.cantidad = cantidad;
	}

	
	public String toString() {
		return "Agua [cantidad=" + cantidad + "]";
	}


	public JPanel getPanel() {
		
		if(this.panel == null) {
			JLabel uno = new JLabel(this.getTitulo(),SwingConstants.CENTER);
			JLabel dos = new JLabel(Long.toString(this.cantidad), SwingConstants.CENTER);
			JLabel tres = new JLabel("Agua",SwingConstants.CENTER);
			
			JPanel panelAgua = new JPanel();
			panelAgua.setLayout(new BorderLayout());
			panelAgua.add(uno, BorderLayout.NORTH);
			panelAgua.add(dos,BorderLayout.CENTER);
			panelAgua.add(tres,BorderLayout.SOUTH);
			
			panelAgua.setBackground(Color.BLUE);
			
			this.panel = panelAgua;
			return (panelAgua);
		}
		
		return (this.panel);
		
	}
	
	

}
