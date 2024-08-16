package estacionDeTrabajo;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import javax.swing.*;
import javax.swing.plaf.FileChooserUI;

import serverCentral.*;

public class Presentacion {

    private JFrame frame;
    private JDesktopPane desktopPane;
    private static ISistema s = Factory.getSistema();
    private JFileChooser fileChooser;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
            	
        
           

  
            	
                try {
                    DTFecha fecha1 = new DTFecha(1, 1, 1990);
                    DTFecha fecha2 = new DTFecha(15, 6, 1985);

                    s.agregarCliente("Juan", "Juan123", "Perez", "Juan@gmail.com", fecha1);
                    s.agregarCliente("Alberto", "albert1341", "Hernandez", "Ahernandez@gmail.com", fecha1);
                    s.agregarCliente("Maria", "agusmari", "Agustina", "mariaagustina@gmail.com", fecha1);

                    s.agregarImagenes("Juan123", new ImageIcon("./imagenes/p1.jpg"));
                    s.agregarImagenes("albert1341", new ImageIcon("./imagenes/p2.jpg"));
                    s.agregarImagenes("agusmari", new ImageIcon("./imagenes/p3.jpg"));
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
    	fileChooser = new JFileChooser();
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 1100, 900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        centerFrame(frame);

        // Crear el JDesktopPane
        desktopPane = new JDesktopPane();
        desktopPane.setBounds(0, 0, 1100, 900);
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
                ventanaSecundaria.setSize(400, 500); // Ajustar el tamaño
                ventanaSecundaria.setTitle("Alta al usuario");
                ventanaSecundaria.setVisible(true);

                // Panel del formulario
                JPanel panel = new JPanel();
                panel.setLayout(null); // Usar el layout null para posicionar componentes manualmente

                // Crear y agregar componentes al panel
                JLabel nicknameLabel = new JLabel("Nickname:");
                nicknameLabel.setBounds(20, 20, 80, 25);
                panel.add(nicknameLabel);

                JTextField nicknameField = new JTextField(15);
                nicknameField.setBounds(100, 20, 160, 25);
                panel.add(nicknameField);

                JLabel correoLabel = new JLabel("Correo:");
                correoLabel.setBounds(20, 60, 80, 25);
                panel.add(correoLabel);

                JTextField correoField = new JTextField(15);
                correoField.setBounds(100, 60, 160, 25);
                panel.add(correoField);

                JLabel nombreLabel = new JLabel("Nombre:");
                nombreLabel.setBounds(20, 100, 80, 25);
                panel.add(nombreLabel);

                JTextField nombreField = new JTextField(15);
                nombreField.setBounds(100, 100, 160, 25);
                panel.add(nombreField);

                JLabel apellidoLabel = new JLabel("Apellido:");
                apellidoLabel.setBounds(20, 140, 80, 25);
                panel.add(apellidoLabel);

                JTextField apellidoField = new JTextField(15);
                apellidoField.setBounds(100, 140, 160, 25);
                panel.add(apellidoField);

                // Radio buttons para tipo de usuario
                JRadioButton clienteRadioButton = new JRadioButton("Cliente");
                clienteRadioButton.setBounds(100, 180, 80, 25);
                clienteRadioButton.setSelected(true); 
                panel.add(clienteRadioButton);

                JRadioButton proveedorRadioButton = new JRadioButton("Proveedor");
                proveedorRadioButton.setBounds(180, 180, 100, 25);
                panel.add(proveedorRadioButton);

                ButtonGroup tipoUsuarioGroup = new ButtonGroup();
                tipoUsuarioGroup.add(clienteRadioButton);
                tipoUsuarioGroup.add(proveedorRadioButton);

                JLabel companiaLabel = new JLabel("Compañía:");
                companiaLabel.setBounds(20, 220, 80, 25);
                panel.add(companiaLabel);

                JTextField companiaField = new JTextField(20);
                companiaField.setBounds(100, 220, 160, 25);
                companiaField.setEnabled(false); 
                panel.add(companiaField);

                JLabel webLabel = new JLabel("Sitio Web:");
                webLabel.setBounds(20, 260, 80, 25);
                panel.add(webLabel);

                JTextField webField = new JTextField(20);
                webField.setBounds(100, 260, 160, 25);
                webField.setEnabled(false); // Deshabilitar por defecto
                panel.add(webField);

                JButton seleccionarImagenButton = new JButton("Seleccionar Imagen");
                seleccionarImagenButton.setBounds(20, 300, 240, 25);
                panel.add(seleccionarImagenButton);

                JLabel imagenLabel = new JLabel("No se ha seleccionado ninguna imagen");
                imagenLabel.setBounds(20, 340, 240, 25);
                panel.add(imagenLabel);

                JButton registrarButton = new JButton("Registrar");
                registrarButton.setBounds(20, 380, 240, 25);
                panel.add(registrarButton);
                
                
                seleccionarImagenButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e1) {
                        int returnValue = fileChooser.showOpenDialog(null);
                        if (returnValue == JFileChooser.APPROVE_OPTION) {
                            File imagenSeleccionada = fileChooser.getSelectedFile();
                            imagenLabel.setText(imagenSeleccionada.getName());
                        }
                    }
                });

                // Acción para habilitar o deshabilitar campos según tipo de usuario
                proveedorRadioButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e1) {
                        companiaField.setEnabled(true);
                        webField.setEnabled(true);
                    }
                });

                clienteRadioButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e1) {
                        companiaField.setEnabled(false);
                        webField.setEnabled(false);
                    }
                });
                
                
             // Acción del botón de registro
                registrarButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e1) {
                        String nickname = nicknameField.getText();
                        String correo = correoField.getText();
                        String nombre = nombreField.getText();
                        String apellido = apellidoField.getText();
                        boolean esProveedor = proveedorRadioButton.isSelected();
                        String compania = companiaField.getText();
                        String web = webField.getText();
                        
                        if(nickname.isEmpty() || correo.isEmpty() || nombre.isEmpty() || apellido.isEmpty()) {
                        	JOptionPane.showMessageDialog(null, "Hay campos vacios.");
                        	return;
                        	
                        } else if(esProveedor && (compania.isEmpty() || web.isEmpty())) {
                        	JOptionPane.showMessageDialog(null, "Hay campos vacios.");
                        	return;
                        }
                        
                        

                        // Sin completar
                        
                        JOptionPane.showMessageDialog(null, "Usuario registrado con éxito.");
                    }
                });

                // Añadir el panel a la ventana secundaria
                ventanaSecundaria.getContentPane().add(panel);
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
        ventanaClientes.setSize(500, 300);
        ventanaClientes.setLayout(new BorderLayout());

        // Recuperar la lista de clientes
        List<DTCliente> clientes = s.listarClientes();

        // Definir las columnas de la tabla
        String[] columnNames = {"Nick", "Correo", "Nombre Completo"};

        // Crear datos para la tabla
        Object[][] data = new Object[clientes.size()][3];
        for (int i = 0; i < clientes.size(); i++) {
            DTCliente cliente = clientes.get(i);
            data[i][0] = cliente.getNick();
            data[i][1] = cliente.getCorreo();
        }

        // Crear la tabla
        JTable table = new JTable(data, columnNames);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Agregar un listener para manejar clics en la tabla
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = table.rowAtPoint(evt.getPoint());
                if (row >= 0) {
                    DTCliente cliente = clientes.get(row);
                    mostrarDetallesCliente(cliente);
                }
            }
        });

        // Agregar la tabla al JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        ventanaClientes.getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Mostrar la ventana interna
        ventanaClientes.setVisible(true);

        // Agregar la ventana interna al JDesktopPane
        desktopPane.add(ventanaClientes);

        // Opcional: Centrar la ventana interna
        ventanaClientes.setLocation(100, 100);
    }
    
    private void mostrarDetallesCliente(DTCliente cliente) {
        JInternalFrame ventanaDetalles = new JInternalFrame("Detalles del Cliente", true, true, true, true);
        ventanaDetalles.setSize(400, 300);
        ventanaDetalles.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Agregar la información del cliente al panel
        ImageIcon imagenIcon = cliente.getImagenes();
        
        
        
        if (imagenIcon != null) {
        	Image imagen = imagenIcon.getImage();
            Image imagenRedimensionada = imagen.getScaledInstance(200, 150, Image.SCALE_SMOOTH);
            
            imagenIcon = new ImageIcon(imagenRedimensionada);
            // Crear nuevo ImageIcon con la imagen redimensionada
            JLabel imagenLabel = new JLabel(imagenIcon);
            
            panel.add(imagenLabel);
        } else {
            panel.add(new JLabel("No hay imagen disponible"));
        }
        panel.add(new JLabel("Mail: " + cliente.getCorreo()));
        panel.add(new JLabel("Nick: " + cliente.getNick()));
        panel.add(new JLabel("Nombre Completo: " + cliente.getNombre() + " " + cliente.getApellido()));
        panel.add(new JLabel("Fecha de Nacimiento: " + cliente.getNacimiento().getDia() + " - " + cliente.getNacimiento().getMes() + " - " + cliente.getNacimiento().getAnio()));
        panel.add(Box.createVerticalStrut(5));
       
        panel.add(new JLabel("Ordenes: "));
        if(cliente.getOrdenes().isEmpty()) {
        	panel.add(new JLabel("   Todavia no existen ordenes"));
        }
        
        // Falta agregar las ordenes y que al seleccionar una se muestren
    
        JScrollPane scrollPane = new JScrollPane(panel);

        
        ventanaDetalles.getContentPane().add(scrollPane, BorderLayout.CENTER);

    
        ventanaDetalles.setVisible(true);

     
        desktopPane.add(ventanaDetalles);

        
        try {
            ventanaDetalles.setSelected(true);
            ventanaDetalles.toFront();
        } catch (java.beans.PropertyVetoException e) {
            e.printStackTrace();
        }

        // Opcional: Centrar la ventana interna
        ventanaDetalles.setLocation(150, 150);
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
