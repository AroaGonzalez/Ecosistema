package ud.prog3.pr00.simulador;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ColoniaAbejas extends ElementoEcosistema implements Evolucionable{
	
	protected int poblacion = (int) Math.sqrt(dimension.getWidth()*dimension.height);
	protected JPanel panel;
	public JLabel dos = new JLabel("",SwingConstants.CENTER);
	
	public void editarTexto(String texto) {
		this.dos.setText(texto);
	}
	public JLabel getDos() {
		return this.dos;
	}
	

	public int getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(int poblacion) {
		this.poblacion = poblacion;
	}

	
	public ColoniaAbejas(String titulo, Dimension dimension, Point posicion, int poblacion) {
		super(titulo, dimension, posicion);
		this.poblacion = poblacion;
	}

	public void evoluciona(int dias) {
		double factorCrecimiento = 1.0;
		 int numFlores = 0;
		 for (ElementoEcosistema ee : Ecosistema.getMundo().getElementos()) {
		 int dist = Ecosistema.distancia( this, ee );
		 if (ee instanceof ColoniaAbejas && ee!=this) { // Otra colonia de abejas perjudica
		 if (dist < 500) factorCrecimiento = factorCrecimiento * dist / 500;
		 } else if (ee instanceof PlantacionFlores) { // La cercanía de flores beneficia
		 if (dist < 500) factorCrecimiento = factorCrecimiento / dist * 500;
		 numFlores += ((PlantacionFlores)ee).getCantidad();
		 }
		 }
		 if (numFlores < 50) factorCrecimiento *= 0.1; // Insuficientes flores mata
		 poblacion = (int) (poblacion * factorCrecimiento * dias);
		 if (poblacion > 5000) poblacion = 5000; // Límite de población
		
	}

	public String toString() {
		return "ColoniaAbejas [poblacion=" + poblacion + "]";
	}

	@Override
	public JPanel getPanel() {
		
		if(this.panel == null) {
			JLabel uno = new JLabel(this.getTitulo(),SwingConstants.CENTER);
			JLabel tres = new JLabel("Abejas", SwingConstants.CENTER);
			
			JPanel panelAbejas = new JPanel();
			panelAbejas.setLayout(new BorderLayout());
			panelAbejas.add(uno, BorderLayout.NORTH);
			panelAbejas.add(tres, BorderLayout.SOUTH);
			
			Color amarillo = new Color(254,246,98);
			panelAbejas.setBackground(amarillo);
			
			this.panel = panelAbejas;
			return panelAbejas;
			
		}
		
		return (this.panel);
		
	}
	
	
	
	
}
