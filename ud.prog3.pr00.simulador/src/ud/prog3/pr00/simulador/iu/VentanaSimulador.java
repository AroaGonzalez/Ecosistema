package ud.prog3.pr00.simulador.iu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import ud.prog3.pr00.simulador.Agua;
import ud.prog3.pr00.simulador.ColoniaAbejas;
import ud.prog3.pr00.simulador.Ecosistema;
import ud.prog3.pr00.simulador.ElementoEcosistema;
import ud.prog3.pr00.simulador.PlantacionFlores;

public class VentanaSimulador extends JFrame{
	
	public int contadorNumeros = 1;
	
	static int coordenadaX = 0;
	static int coordenadaY = 0;
	public int width = 0;
	public int height = 0;
	
	protected static Boolean vidaEstado = false;
	private JToggleButton mover;
	private JToggleButton crear;
	private JComboBox<String> cb;
	private JButton vida;
	private JPanel panelSur;
	private JPanel panelPrincipal;

	public VentanaSimulador() {
		
		this.setTitle("Ecosistema");
		this.setLocation(400,150);
		this.setSize(500,500);
		this.setLayout(new BorderLayout());
		
		mover = new JToggleButton("Mover");
		crear = new JToggleButton("Crear");
		cb = new JComboBox<String>();
		cb.addItem("Abejas");
		cb.addItem("Agua");
		cb.addItem("Flores");
		vida = new JButton("Vida");
		
		panelSur = new JPanel();
		panelPrincipal = new JPanel();
		panelPrincipal.setLayout(null);
		
		panelSur.add(mover);
		panelSur.add(crear);
		panelSur.add(cb);
		panelSur.add(vida);
		
		this.add(panelSur, BorderLayout.SOUTH);
		this.add(panelPrincipal, BorderLayout.CENTER);
		
		vida.addActionListener(new ActionListener() {
			protected Boolean vivoHilo;
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				mover.setEnabled(false);
				crear.setSelected(false);
				
				Thread hilo = new Thread(new Runnable() {
					@Override
					public void run() {
						vivoHilo = true;
						int contador = 7;
					
						while(vivoHilo == true|| contador !=1) {
							
							try {
								contador--;
								for (ElementoEcosistema ee : Ecosistema.getMundo().getElementos()) {
									if (ee instanceof ColoniaAbejas) {
										( (ColoniaAbejas) ee).evoluciona(1);
										((ColoniaAbejas) ee).editarTexto( Integer.toString(((ColoniaAbejas) ee).getPoblacion() ));
										panelPrincipal.revalidate();
										
									}else if (ee instanceof  PlantacionFlores) {
										( (PlantacionFlores) ee).evoluciona(1);
										((PlantacionFlores) ee).editarTexto( Long.toString(((PlantacionFlores) ee).getCantidad() ));
										panelPrincipal.revalidate();
									}
								}
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
								vivoHilo=true;
						}
						vivoHilo = false;
						vida.setText("Vida");
						mover.setEnabled(true);
						crear.setEnabled(true);
							
					}				
				});
				
				if(vida.getText()=="Vida") {
					hilo.start();
					vida.setText("Parar");
					mover.setEnabled(false);
					crear.setEnabled(false);
				}else {
					vivoHilo = true;
					vida.setText("Vida");
					mover.setEnabled(true);
					crear.setEnabled(true);
					try {
						Thread.sleep(500);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					
				}
			}			
		});
		
		
		mover.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(crear.isSelected() && mover.isSelected()) {
					crear.setSelected(false);
				} else if( !mover.isSelected()) {
					mover.setSelected(false);
				}else {
					crear.setSelected(false);
					mover.setSelected(true);
				}
				
			}
		});
		
		crear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(crear.isSelected() && mover.isSelected()) {
					mover.setSelected(false);
				} else if( !crear.isSelected()) {
					crear.setSelected(false);
				}else {
					crear.setSelected(true);
					mover.setSelected(false);
				}
			}
		});
		
		
		panelPrincipal.addMouseListener(new MouseListener() {
			

			public void mouseReleased(MouseEvent e) {
				boolean esNegativo = false;
				
				int ancho = e.getX() - coordenadaX;
				int alto = e.getY() - coordenadaY;
				
				if (ancho<0) {
					esNegativo = true;
					ancho = Math.abs(ancho);
				}
				if (alto<0) {
					esNegativo = true;
					alto = Math.abs(alto);
				}
				
				if (coordenadaX != e.getX() && coordenadaY != e.getY()){
					if (mover.isSelected()) {
						for (ElementoEcosistema ee : Ecosistema.getMundo().getElementos()) {
							if((ee.getPosicion().getX()<= coordenadaX )&&( coordenadaX <= (ee.getPosicion().getX() + ee.getDimension().width )) &&(ee.getPosicion().getY() <= coordenadaY )&&(coordenadaY <= (ee.getPosicion().getY() + ee.getDimension().height))) {
								
								ee.setPosicion(new Point(e.getX(),e.getY() ));
								ee.getPanel().setBounds(e.getX() , e.getY(), ee.getDimension().width, ee.getDimension().height);
								panelPrincipal.revalidate();
							}
						}
					}
					
				if (crear.isSelected()) {
					if (esNegativo) {
						coordenadaX = coordenadaX - ancho;
						coordenadaY = coordenadaY - alto;
					}
					
					if(cb.getSelectedItem().equals("Flores")){
						
						PlantacionFlores flores = new PlantacionFlores("Flores "+ contadorNumeros, new Dimension(ancho, alto), new Point(coordenadaX, coordenadaY), 20);
						contadorNumeros+=1;
						flores.getPanel().setPreferredSize(flores.getDimension());
						JPanel panel = flores.getPanel();
						flores.editarTexto(Long.toString(flores.getCantidad()));
						panel.add(flores.getDos());
						panelPrincipal.add(panel);
						
						flores.getPanel().setBounds(coordenadaX, coordenadaY, flores.getDimension().width, flores.getDimension().height);
						Ecosistema.getMundo().getElementos().add(flores);
						
						panelPrincipal.revalidate();
					}
					
					if(cb.getSelectedItem().equals( "Abejas")) {
						
						ColoniaAbejas abejas = new ColoniaAbejas("Colonia " + contadorNumeros, new Dimension(ancho, alto), new Point(coordenadaX, coordenadaY), 20);
						
						abejas.getPanel().setPreferredSize(abejas.getDimension());
						contadorNumeros+=1;
						JPanel panel = abejas.getPanel();
						abejas.editarTexto(Integer.toString(abejas.getPoblacion()));
						panel.add(abejas.getDos());
						panelPrincipal.add(panel);
						
						abejas.getPanel().setBounds(coordenadaX, coordenadaY, abejas.getDimension().width, abejas.getDimension().height);
						Ecosistema.getMundo().getElementos().add(abejas);
						
						panelPrincipal.revalidate();
					}
					
					if (cb.getSelectedItem().equals("Agua")){
						
						
						Agua agua = new Agua("Lago "+contadorNumeros, new Dimension(ancho, alto), new Point(coordenadaX, coordenadaY));
						
						agua.getPanel().setPreferredSize(agua.getDimension());
						contadorNumeros+=1;
						Ecosistema.getMundo().getElementos().add(agua);
						
						panelPrincipal.add(agua.getPanel());
						
						agua.getPanel().setBounds(coordenadaX, coordenadaY, agua.getDimension().width, agua.getDimension().height);
						
						panelPrincipal.revalidate();
					}

				}
			}
				
		}
			
			@Override
			public void mousePressed(MouseEvent e) {
				coordenadaX = e.getX();
				coordenadaY = e.getY();
				
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
	}
	
	public static void main(String[] args) {
		VentanaSimulador v1 = new VentanaSimulador();
		v1.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		v1.setVisible(true);
	}
}
