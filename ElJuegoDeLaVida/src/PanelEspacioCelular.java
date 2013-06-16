
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JComponent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import javax.swing.JPanel;

public class PanelEspacioCelular extends JPanel implements Runnable,ActionListener{
	EspacioCelular ec;
	boolean estado = false;
	public PanelEspacioCelular(int ancho, int alto) {
		setPreferredSize(new Dimension(ancho, alto));
		
		String hexadecimal="AF72FA";
		
		int entero=Integer.parseInt(hexadecimal,16);
		
		Color color=new Color(entero);

		ec = new EspacioCelular(alto, ancho);
		setBackground(Color.LIGHT_GRAY);
		setForeground(color);

		EspacioCelular espacioCelular = new EspacioCelular(alto, ancho);
		
//Metodo para ponerle una funcion a la tecla izquierda del ratón para que cree celulas vivas y se pinten
		addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			
			public void mouseClicked(MouseEvent e) {

				ec.setViva(e.getX(), e.getY());

				repaint();

			}
		});
		
		//Método para que pinte células mientras mantengamos pulsado el boton izquierdo del ratón y arrastremos
		addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseDragged(MouseEvent e) {
				ec.setViva(e.getX(), e.getY());

				repaint();

			}
		});
//Usaremos las teclas de dirección para movernos por la cuadrícula y la barra espaciadora para ejecutar el método cambiarEstado
		registerKeyboardAction(this, "derecha",
				  KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0),
				  JComponent.WHEN_IN_FOCUSED_WINDOW);
		
		registerKeyboardAction(this, "arriba",
				  KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0),
				  JComponent.WHEN_IN_FOCUSED_WINDOW);
		
		registerKeyboardAction(this, "izquierda",
				  KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0),
				  JComponent.WHEN_IN_FOCUSED_WINDOW);
		
		registerKeyboardAction(this, "abajo",
				  KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0),
				  JComponent.WHEN_IN_FOCUSED_WINDOW);
		
		registerKeyboardAction(this, "cambiarEstado",
				  KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0),
				  JComponent.WHEN_IN_FOCUSED_WINDOW);
	}
	
	//Método paint para pintar las celulas y la cuadricula si lo precisamos
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		ec.paint(g, getForeground());

	}
//Método para pintar las celulas aleatorias creadas a partir del metodo setCelulasAleatorias de la clase EspacioCelular
	public void pintarCelulasAleatorias() {
		ec.setCelulasAleatorias();
		repaint();

	}
//Método para limpiar la cuadricula de células usando el metodo setLimpiar de la clase EspacioCelular
	public void limpiar() {

		ec.setLimpiar();

		repaint();
	}
//Método para pintar las celulas de la siguiente generacion celular creadas en la clase EspacioCelular en el método sgteGeneracion. 
//Usaremos un hilo para que se pinten las células mientras esté ejecutandose el método sgteGeneracion
	public void siguenteGeneracion() {
		ec.sgteGeneracion();

		repaint();
	}

	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(60);
			while(estado){
				
			ec.sgteGeneracion();
			repaint();
			Thread.sleep(60);
			}
			
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	//Este método en principio es para poder cambiar el estado de la céula que seleccionaremos recorriendo la cuadricula 
	//con las flechas de dirección, pero no funciona. De hecho, para que recorra la cuadrícula, hay que quitarle la barra de herramientas 
	//pero cuando seleccionas una celda para pintarla, la primera celda arriba a la izquierda si la pinta, pero cuando quieres pintar otra, 
	//pinta celdas al azar.
	public void setEstado(boolean b){
		
		estado = b ;
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("arriba")){
			ec.Direccion(0);
		}
			
			
		if(e.getActionCommand().equals("derecha")){
			ec.Direccion(1);
		}
				
		if(e.getActionCommand().equals("abajo")){
			ec.Direccion(2);
		}
					
		if(e.getActionCommand().equals("izquierda")){
			ec.Direccion(3);
		}
		
		if(e.getActionCommand().equals("cambiarEstado")){
			ec.setEspacioEstado();
		}
		
		
		
		repaint();
		}

}
