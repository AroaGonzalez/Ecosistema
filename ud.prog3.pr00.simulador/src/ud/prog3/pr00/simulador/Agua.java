package ud.prog3.pr00.simulador;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JLabel;
import javax.swing.JPanel;

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
			JLabel uno = new JLabel("Lago");
			JLabel dos = new JLabel(Long.toString(this.cantidad));
			JLabel tres = new JLabel("Agua");
			
			JPanel panelAgua = new JPanel();
			panelAgua.add(uno);
			panelAgua.add(dos);
			panelAgua.add(tres);
			
			panelAgua.setBackground(Color.BLUE);
			
			this.panel = panelAgua;
			return (panelAgua);
		}
		
		return (this.panel);
		
	}
	
	

}
