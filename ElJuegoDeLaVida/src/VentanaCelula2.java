import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;

import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.Random;
import java.util.RandomAccess;

import javax.swing.JMenuBar;
import javax.swing.JMenu;


public class VentanaCelula2 extends JFrame {
	
//Creamos un objeto de la clase PanelEspacioCelular para poder usar sus métodos
	static PanelEspacioCelular pec;
	
	public VentanaCelula2() {
		
		//Crearemos una ventana con la cuadricula y con unos botones para poder ejecutar los métodos que nos interesen
	
		super("El Juego de la Vida");
		pec = new PanelEspacioCelular(80, 80);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Creamos las dimensiones que queremos que tenga nuestra ventana y haremos que no se pueda expandir
		setMinimumSize(new Dimension(608, 666));
		setResizable(false);
		
		
		
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		//Crearemos una barra de herramientas que contendrá todos los botones que pongamos
		
		JToolBar toolBar = new JToolBar();
		getContentPane().add(toolBar, BorderLayout.NORTH);
		
		//A partir de aqui crearemos los botones y usaremos en cada uno de ellos los métodos de la clase PanelEspacioCelular que les 
		//correspondan mediante el objeto pec para poder usar sus clases.
		
		JButton btnCrearCelulas = new JButton("Crear C\u00E9lulas");
		btnCrearCelulas.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				pec.pintarCelulasAleatorias();
				    } 
		});
		toolBar.add(btnCrearCelulas);
		
		JButton btnLimpiar = new JButton("Limpiar");
		toolBar.add(btnLimpiar);
		
		btnLimpiar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
			pec.limpiar();
			}});
			
		
		JButton btnModificar = new JButton("Modificar");
		toolBar.add(btnModificar);
		
		JButton btnSiguenteGeneracin = new JButton("Siguente Generaci\u00F3n");
		toolBar.add(btnSiguenteGeneracin);
		
		
		btnSiguenteGeneracin.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
			pec.siguenteGeneracion();
			}});
		
		JButton btnIniciarGeneracion = new JButton("Iniciar Generacion");
		btnIniciarGeneracion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				pec.setEstado(true);
			}
		});
		toolBar.add(btnIniciarGeneracion);
		
		JButton btnParar = new JButton("Parar");
		btnParar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				pec.setEstado(false);
			}
		});
		toolBar.add(btnParar);
		
		JButton btnSalir = new JButton("Salir");
		toolBar.add(btnSalir);
		
		
		btnSalir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
			
			
		});
		
		getContentPane().add(pec, BorderLayout.CENTER);
		
		
		
		
		
		
		pec.setFocusable(true);
		pack();

		}
	
	public static void main(String[] args){
		
		new VentanaCelula2().setVisible(true);
		new Thread(pec).start();
		
	}
}