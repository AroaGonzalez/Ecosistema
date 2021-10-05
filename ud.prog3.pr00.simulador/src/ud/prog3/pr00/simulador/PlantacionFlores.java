package ud.prog3.pr00.simulador;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PlantacionFlores extends ElementoEcosistema implements Evolucionable{

	protected long cantidad = (long) Math.sqrt(dimension.getWidth()*dimension.height);;
	protected JPanel panel;
	public JLabel dos = new JLabel("",SwingConstants.CENTER);
	
	public void editarTexto(String texto) {
		this.dos.setText(texto);
	}
	
	public JLabel getDos() {
		return this.dos;
	}

	public long getCantidad() {
		return cantidad;
	}

	public void setCantidad(long cantidad) {
		this.cantidad = cantidad;
	}

	public PlantacionFlores(String titulo, Dimension dimension, Point posicion, long cantidad) {
		super(titulo, dimension, posicion);
		this.cantidad = (long) Math.sqrt(dimension.getWidth()*dimension.height);
	}

	public void evoluciona(int dias) {
		double factorCrecimiento = 0.75;
		 for (ElementoEcosistema ee : Ecosistema.getMundo().getElementos()) {
		 int dist = Ecosistema.distancia( this, ee );
		 if (ee instanceof ColoniaAbejas) { // La cercanía de abejas beneficia
		 if (dist < 500) factorCrecimiento = factorCrecimiento / dist * 500;
		 } else if (ee instanceof Agua) { // La cercanía de agua beneficia
		 if (dist < 500) factorCrecimiento = factorCrecimiento / dist * 500;
		 }
		 }
		 cantidad = (long) (cantidad * factorCrecimiento * dias);
		 if (cantidad > 5000) cantidad = 5000;
		
	}

	public String toString() {
		return "PlantacionFlores [cantidad=" + cantidad + "]";
	}

	@Override
	public JPanel getPanel() {
		
		if(this.panel == null) {
			JLabel uno = new JLabel(this.getTitulo(),SwingConstants.CENTER);
			JLabel tres = new JLabel("Flores",SwingConstants.CENTER);
			
			JPanel panelFlores = new JPanel();
			panelFlores.setLayout(new BorderLayout());
			panelFlores.add(uno, BorderLayout.NORTH);
			panelFlores.add(tres, BorderLayout.SOUTH);
			
			Color verde = new Color(125,193,111);
			panelFlores.setBackground(verde);
			
			this.panel = panelFlores;
			return (panelFlores);
		}
		
		return (this.panel);
		
	}
	
	
	
}
