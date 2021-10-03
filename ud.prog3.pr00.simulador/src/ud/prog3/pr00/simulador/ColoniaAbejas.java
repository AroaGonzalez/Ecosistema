package ud.prog3.pr00.simulador;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ColoniaAbejas extends ElementoEcosistema implements Evolucionable{
	
	protected int poblacion;
	protected JPanel panel;

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
			JLabel uno = new JLabel("Colonia");
			JLabel dos = new JLabel(Integer.toString(this.poblacion));
			JLabel tres = new JLabel("Abejas");
			
			JPanel panelAbejas = new JPanel();
			panelAbejas.add(uno);
			panelAbejas.add(dos);
			panelAbejas.add(tres);
			
			panelAbejas.setBackground(Color.yellow);
			
			this.panel = panelAbejas;
			return (panelAbejas);
			
		}
		
		return (this.panel);
		
	}
	
	
	
	
}
