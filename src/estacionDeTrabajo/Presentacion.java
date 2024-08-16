package estacionDeTrabajo;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import javax.swing.*;

import serverCentral.*;

public class Presentacion {

    private JFrame frame;
    private JDesktopPane desktopPane;
    private static ISistema s = Factory.getSistema();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DTFecha fecha1 = new DTFecha(1, 1, 1990);
                    DTFecha fecha2 = new DTFecha(15, 6, 1985);

                    s.agregarCliente("juanito", "juanito@mail.com", "Juan", "Perez", fecha1);
                    s.agregarCliente("maria", "maria@mail.com", "Maria", "Gomez", fecha2);
                    s.agregarCliente("carlos", "carlos@mail.com", "Carlos", "Lopez", fecha1);


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
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        centerFrame(frame);

        // Crear el JDesktopPane
        desktopPane = new JDesktopPane();
        desktopPane.setBounds(0, 0, 800, 600);
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

                // Agregar la ventana secundaria al JDesktopPane
                desktopPane.add(ventanaSecundaria);
                
                // Opcional: Centrar la ventana secundaria
                ventanaSecundaria.setLocation(100, 100);
            }
        });

        // Crear opción "Mostrar Clientes"
        JMenuItem mntmMostrarClientes = new JMenuItem("Mostrar clientes");
        mntmMostrarClientes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarClientes();
            }
        });

        mnCasosDeUso.add(mntmMostrarClientes);
        mnCasosDeUso.add(mntmRegistrarUsuario);
    }

    private void mostrarClientes() {
    	// Crear e inicializar la ventana interna (JInternalFrame)
        JInternalFrame ventanaClientes = new JInternalFrame("Lista de Clientes", true, true, true, true);
        ventanaClientes.setSize(400, 200);

        ventanaClientes.setLayout(new BorderLayout());
        
        // Recuperar la lista de clientes
        List<DTCliente> clientes = s.listarClientes();
        
        // Crear un JPanel para contener los datos
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        // Agregar cada cliente al JPanel
        for (DTCliente cliente : clientes) {
            JPanel clientePanel = new JPanel();
            
            // Crear un JTextArea para mostrar la información del cliente
            JTextArea textArea = new JTextArea(cliente.toString());
            textArea.setEditable(false);
            textArea.setLineWrap(false);
            textArea.setWrapStyleWord(true);
            
           
            // Agregar JTextArea y JLabel al panel del cliente
            clientePanel.add(textArea, BorderLayout.NORTH);
            
            // Agregar el panel del cliente al panel principal
            panel.add(clientePanel);
        }
        
        // Agregar el panel al JScrollPane
        JScrollPane scrollPane = new JScrollPane(panel);
        
        // Agregar el JScrollPane a la ventana interna
        ventanaClientes.getContentPane().add(scrollPane, BorderLayout.CENTER);
        
        // Mostrar la ventana interna
        ventanaClientes.setVisible(true);
        
        // Agregar la ventana interna al JDesktopPane
        desktopPane.add(ventanaClientes);
        
        // Opcional: Centrar la ventana interna
        ventanaClientes.setLocation(100, 100);
    }

    // Método para centrar el JFrame en la pantalla
    private static void centerFrame(JFrame frame) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        Dimension frameSize = frame.getSize();
        int x = (screenSize.width - frameSize.width) / 2;
        int y = (screenSize.height - frameSize.height) / 2;
        frame.setLocation(x, y);
    }
}
