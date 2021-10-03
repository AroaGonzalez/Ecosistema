package ud.prog3.pr00.simulador;

import java.awt.*;

import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class ElementoEcosistema extends JLabel{
	
	protected String titulo;
	protected Dimension dimension;
	protected Point posicion;

	
	public ElementoEcosistema(String titulo, java.awt.Dimension dimension, Point posicion) {
		super();
		this.titulo = titulo;
		this.dimension = dimension;
		this.posicion = posicion;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public Dimension getDimension() {
		return dimension;
	}


	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}


	public Point getPosicion() {
		return posicion;
	}


	public void setPosicion(Point posicion) {
		this.posicion = posicion;
	}

	public abstract JPanel getPanel();

	
}
