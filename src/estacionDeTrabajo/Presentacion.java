package estacionDeTrabajo;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import serverCentral.*;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenu;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JDesktopPane;

import java.awt.event.ActionListener;
import javax.swing.JInternalFrame;
import java.awt.Button;

public class Presentacion {

	private JFrame frame;
	private JDesktopPane desktopPane;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					ISistema s = Factory.getSistema();
					
					Presentacion window = new Presentacion();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Presentacion() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	/**
     * Initialize the contents of the frame.
     */
	
	
	// Method to center a JFrame on the screen
    private static void centerFrame(JFrame frame) {
        // Get the size of the screen
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        // Get the size of the frame
        Dimension frameSize = frame.getSize();

        // Calculate the position of the frame
        int x = (screenSize.width - frameSize.width) / 2;
        int y = (screenSize.height - frameSize.height) / 2;

        // Set the position of the frame
        frame.setLocation(x, y);
    }
    
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 800, 600); // Tamaño más grande para ver el JDesktopPane
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        centerFrame(frame);

        // Crear el JDesktopPane
        desktopPane = new JDesktopPane();
        desktopPane.setBounds(0, 0, 800, 600); // Ajustar tamaño y ubicación del JDesktopPane
        frame.getContentPane().add(desktopPane);
        
        
        
        

        // Crear la barra de menú
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        // Crear menú "Casos de Uso"
        JMenu mnCasosDeUso = new JMenu("Casos de Uso");
        menuBar.add(mnCasosDeUso);
        
      

        // Crear opción "Registrar Usuario"
        JMenuItem mntmRegistrarUsuario = new JMenuItem("Registrar Usuario");
        mntmRegistrarUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Crear e inicializar la ventana secundaria (JInternalFrame)
                JInternalFrame ventanaSecundaria = new JInternalFrame("Registrar Usuario", true, true, true, true);
                ventanaSecundaria.setSize(400, 300);
                ventanaSecundaria.setVisible(true);
                Button button = new Button("New button");
                button.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                	}
                });
                button.setBounds(100, 50, 100, 30);
              
                
                
                // Agregar la ventana secundaria al JDesktopPane
                desktopPane.add(ventanaSecundaria);
                
                ventanaSecundaria.getContentPane().add(button);
                // Opcional: Centrar la ventana secundaria
                ventanaSecundaria.setLocation(100, 100);
            }
        });
        mnCasosDeUso.add(mntmRegistrarUsuario);
    }
}
