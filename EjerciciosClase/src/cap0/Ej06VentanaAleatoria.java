package cap0;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Timer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/** Código de base para una ventana de Swing, de cara a completar el ejercicio 0.6
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
@SuppressWarnings("serial")
public class Ej06VentanaAleatoria extends JFrame {
	private static Dimension TAMANYO_INICIAL_VENTANA = new Dimension( 400, 300 );
	//Componentes
	
	private JTable table;
	private DefaultTableModel tableModel;
	private JComboBox cbOpciones;
	private JButton bAleatorio;
	private Timer timer;
	
	public static void main(String[] args) {
		Ej06VentanaAleatoria v = new Ej06VentanaAleatoria();
		v.setVisible(true);
		
	}

	public Ej06VentanaAleatoria() {
		super( "Ventana aleatoria - ej. 0.6" );
		setSize( TAMANYO_INICIAL_VENTANA );
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		
		//Creacion de los componentes
		tableModel = new DefaultTableModel(2,2);
		table = new JTable(tableModel);
//		table.setShowHorizontalLines(true);
//		table.setShowGrid(true);
		String[] opciones = {"2x2", "3x3", "4x4", "5x5"};
		cbOpciones = new JComboBox<>(opciones);
		JPanel pSuperior = new JPanel();
		pSuperior.add(cbOpciones);
		
		cbOpciones.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				actualizarTabla();
			}
					
				});
				System.out.println("Ahora se cambia de tamaño");
//				String opcion = (String) cbOpciones.getSelectedItem();
//				String nFilas = opcion.substring(0,1);
//				String nColumnas = opcion.substring(2,3);
//				System.out.println(nFilas);
//				System.out.println(nColumnas);
				add(new JScrollPane(table), BorderLayout.CENTER);
				add(pSuperior, BorderLayout.SOUTH);
				
			}
			
		
		
		

	Vector<Thread> hiloEnCurso = new Vector<>();
	public void actualizarTabla() {
		Thread hiloActual = new Thread() {
		public void run() {
			Thread yo = this;
			hiloEnCurso.add(yo);
			while(hiloEnCurso.get(0) != yo) {
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					// TODO: handle exception
				}
				
			}
			String opcion = (String)cbOpciones.getSelectedItem();
			System.out.println(opcion);
			int nColumnas = Integer.parseInt(opcion.substring(0,1));
			int nFilas = Integer.parseInt(opcion.substring(2,3));
			tableModel.setColumnCount(nColumnas);
			tableModel.setRowCount(nFilas);
			table.repaint();
			hiloEnCurso.remove(0);
		}	
		
		};
		hiloActual.start();
	}

}
