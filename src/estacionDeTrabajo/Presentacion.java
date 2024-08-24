package estacionDeTrabajo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import com.toedter.calendar.JDateChooser;

import serverCentral.DTCliente;
import serverCentral.DTFecha;
import serverCentral.DTItem;
import serverCentral.DTOrdenDeCompra;
import serverCentral.DtProducto;
import serverCentral.Factory;
import serverCentral.ISistema;
import serverCentral.Item;
import serverCentral.OrdenDeCompra;
import serverCentral.Producto;
import serverCentral.Proveedor;
import serverCentral.Usuario;
import serverCentral.UsuarioRepetidoException;

public class Presentacion {

    private JFrame frame;
    private File imagenSeleccionada;
    private ImageIcon imagenSelecc;
    private JDesktopPane desktopPane;
    private static ISistema s = Factory.getSistema();
    private JFileChooser fileChooser;
    private Date fechaSeleccionada;
    Calendar calendar = Calendar.getInstance();
    
    /**
     * Launch the application.
     */
    
 
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
  
                try {
                    DTFecha fecha1 = new DTFecha(1, 1, 1990);
                    DTFecha fecha2 = new DTFecha(15, 6, 1985);
                    DTFecha fecha3 = new DTFecha(5, 6, 1990);

                    s.agregarCliente("Juan", "Juan123", "Perez", "Juan@gmail.com", fecha1);
                    s.agregarCliente("Alberto", "albert1341", "Hernandez", "Ahernandez@gmail.com", fecha2);
                    s.agregarCliente("Maria", "agusmari", "Agustina", "mariaagustina@gmail.com", fecha1);

                    s.agregarImagenUsuario("Juan123", new ImageIcon("./imagenes/p1.jpg"));
                    s.agregarImagenUsuario("albert1341", new ImageIcon("./imagenes/p2.jpg"));
                    s.agregarImagenUsuario("agusmari", new ImageIcon("./imagenes/p3.jpg"));
                    
                    s.agregarCategoria("Living");
                    s.agregarCategoria("Tecnología");
                    s.agregarCategoria("Estanterias");
                    
                    s.agregarCategoriaConProductos("Tecno");
                    s.asignarlePadreACategoriaProds("Living", "Tecno");
                    
                    
                    // SOlo una prueba
                    
                    
                   
                    Proveedor prov = new Proveedor("Bellizzi", "isracaballero@gmail.com", "Israel", "Bellizzi", fecha3 ,"Bamboo.inc" , "www.bamboo.com");
                    s.agregarProveedor("Bellizzi", "isracaballero@gmail.com", "Israel", "Bellizzi", fecha3 ,"Bamboo.inc" , "www.bamboo.com");
                    s.agregarImagenUsuario("Bellizzi", new ImageIcon("./imagenes/p1.jpg"));
                    
                    Producto p1 = new Producto("Pelota", "Pelota inflable ideal", 12, 1,"Lalala", prov);
                    OrdenDeCompra o1 = new OrdenDeCompra(1);
                    o1.addItem(p1, 3);
                    
                    s.addOrdenes(o1, "Juan123");

                    s.agregarImagenUsuario("Juan123", new ImageIcon("./imagenes/p1.jpg"));
                    s.agregarImagenUsuario("albert1341", new ImageIcon("./imagenes/p2.jpg"));
                    s.agregarImagenUsuario("agusmari", new ImageIcon("./imagenes/p3.jpg"));
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
    	//
    	
    	
    	fileChooser = new JFileChooser();
    	calendar = Calendar.getInstance();
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 900, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*frame.getContentPane().setLayout(null);*/
        frame.getContentPane().setLayout(new BorderLayout());
        centerFrame(frame);

        // Crear el JDesktopPane
        desktopPane = new JDesktopPane();
        desktopPane.setBounds(0, 0, 900, 700);
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
                ventanaSecundaria.setSize(400, 600); // Ajustar el tamaño
                ventanaSecundaria.setTitle("Alta al usuario");
                ventanaSecundaria.setVisible(true);
                

                // Panel del formulario
                JPanel panel = new JPanel();
                panel.setLayout(null); 
                

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
                
                

                // ComboBox
                JLabel tipoUsuarioLabel = new JLabel("Usuario: ");
                tipoUsuarioLabel.setBounds(20, 180, 100, 25);
                panel.add(tipoUsuarioLabel);

                JComboBox<String> tipoUsuarioComboBox = new JComboBox<>(new String[]{"Cliente", "Proveedor"});
                tipoUsuarioComboBox.setBounds(100, 180, 160, 25);
                panel.add(tipoUsuarioComboBox);


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
                webField.setEnabled(false);
                panel.add(webField);
                
             // JCalendar
               
//JCalendar tiene problemas por la ruta del archivo, habría que investigarlo mejor o encontrar otra alternativa
 
               
                JDateChooser chooser = new JDateChooser();
                chooser.setBounds(20, 300, 80, 25);
                panel.add(new JLabel("Fecha nacimiento: "));
                panel.add(chooser);
                
                
             // Escuchar cambios en la fecha seleccionada
                chooser.addPropertyChangeListener(new PropertyChangeListener() {
                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                        if ("date".equals(evt.getPropertyName())) {
                            Date selectedDate = (Date) evt.getNewValue();
                            if (selectedDate != null) {
                                calendar = Calendar.getInstance();
                                calendar.setTime(selectedDate);


                            } else {
                            	calendar = null;
                            }
                        }
                    }
                });
            

                JButton seleccionarImagenButton = new JButton("Seleccionar Imagen");
                seleccionarImagenButton.setBounds(20, 470, 240, 25);
                panel.add(seleccionarImagenButton);
                
               
                JLabel imagenLabel = new JLabel("No se ha seleccionado ninguna imagen");
                imagenLabel.setBounds(20, 500, 240, 25);
                panel.add(imagenLabel);
                
                

                JButton registrarButton = new JButton("Registrar");
                registrarButton.setBounds(20, 540, 240, 25);
                panel.add(registrarButton);
                
             // Inicializar JFileChooser
                fileChooser = new JFileChooser();

                // Acción del botón de seleccionar imagen
                seleccionarImagenButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e1) {
                        int returnValue = fileChooser.showOpenDialog(null);
                        if (returnValue == JFileChooser.APPROVE_OPTION) {
                            imagenSeleccionada = fileChooser.getSelectedFile();
                            // Verificar que imagenSeleccionada no sea null
                            if (imagenSeleccionada != null) {
                                String nombreArchivo = imagenSeleccionada.getName().toLowerCase();
                                if (nombreArchivo.endsWith(".jpg") || nombreArchivo.endsWith(".png")) {
                                    imagenLabel.setText(imagenSeleccionada.getName());
                                    imagenSelecc = new ImageIcon(imagenSeleccionada.getAbsolutePath());
                                } else {
                                    // Mostrar mensaje de error si el archivo no es válido
                                    JOptionPane.showMessageDialog(null, "Por favor, selecciona un archivo con extensión .jpg o .png", "Archivo no válido", JOptionPane.ERROR_MESSAGE);
                                }
                            } else {
                                // Mensaje si no se selecciona un archivo
                                JOptionPane.showMessageDialog(null, "No se seleccionó ningún archivo", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                });

                
                
                tipoUsuarioComboBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e1) {
                        boolean esProveedor = tipoUsuarioComboBox.getSelectedItem().equals("Proveedor");
                        
                        if(esProveedor) {
                        	webField.setEnabled(true);
                        	companiaField.setEnabled(true);
                        } else {
                        	webField.setEnabled(false);
                        	companiaField.setEnabled(false);
                        }
                    }
                });

               
                registrarButton.addActionListener(new ActionListener() {
					@Override
                    public void actionPerformed(ActionEvent e1) {
                        String nickname = nicknameField.getText();
                        String correo = correoField.getText();
                        String nombre = nombreField.getText();
                        String apellido = apellidoField.getText();
                        boolean esProveedor = tipoUsuarioComboBox.getSelectedItem().equals("Proveedor");
                        String compania = companiaField.getText();
                        String web = webField.getText();
                        
                        
                       int dia, mes, anio;
                        if(calendar.getTime() != null) {
                        	mes = calendar.get(Calendar.MONTH);
                        	dia = calendar.get(Calendar.DAY_OF_MONTH) + 1;
                        	anio = calendar.get(Calendar.YEAR);
                        } else {
                        	JOptionPane.showMessageDialog(null, "Debe seleccionar una fecha");
                        	return;
                        }
                         
                        
                        
                        if(nickname.isEmpty() || correo.isEmpty() || nombre.isEmpty() || apellido.isEmpty()) {
                        	JOptionPane.showMessageDialog(null, "Hay campos vacios.");
                        	return;
                        	
                        } else if(esProveedor && (compania.isEmpty() || web.isEmpty())) {
                        	JOptionPane.showMessageDialog(null, "Hay campos vacios.");
                        	return;
                        }
                        
                        if(!s.validarCorreo(correo)) {
                        	JOptionPane.showMessageDialog(null, "Mal formato de correo");
                        	return;
                        }
                        
                        

                        DTFecha fechaNacimiento = new DTFecha(dia, mes, anio);
                        
                        if(esProveedor) {
                        	try {
								s.agregarProveedor(nombre, nickname, apellido, correo, fechaNacimiento, compania, web);
								
							} catch (UsuarioRepetidoException e) {
								JOptionPane.showMessageDialog(null,e.getMessage());
							}
                        	Usuario u = s.getUsuario(nickname);
                        	
                        	System.out.println("Imagen seleccionada: " + (imagenSelecc != null));
                        	if(imagenSelecc != null) {
                        		u.setImagen(imagenSelecc);
                        	}
                        	
                        	
                        } else {
                        	try {
								s.agregarCliente(nombre, nickname, apellido, correo, fechaNacimiento);
							
							} catch (UsuarioRepetidoException e) {
								JOptionPane.showMessageDialog(null,e.getMessage());
							}
                        	
                        	Usuario u = s.getUsuario(nickname);
                        	u.setImagen(imagenSelecc);
                        	
                        }
                        
                        // Limpiar inputs
                        JOptionPane.showMessageDialog(null, "Usuario registrado con éxito.");
                        nicknameField.setText("");
                        correoField.setText("");
                        nombreField.setText("");
                        apellidoField.setText("");
                        tipoUsuarioComboBox.setSelectedIndex(0);
                        companiaField.setText("");
                        webField.setText("");
              //          chooser.setCalendar(null);
                  
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
        
        
        // Alta Categoria
        JMenuItem mntmAltaCategoria = new JMenuItem("Alta Categoria");
        mntmAltaCategoria.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Crear e inicializar la ventana secundaria (JInternalFrame)
                JInternalFrame ventanaSecundaria = new JInternalFrame("Registrar Usuario", true, true, true, true);
                ventanaSecundaria.setSize(400, 300); // Ajustar el tamaño
                ventanaSecundaria.setTitle("Alta de Categorias");
                ventanaSecundaria.setVisible(true);
                

                // Panel del formulario
                JPanel panel = new JPanel();
                panel.setLayout(null); 
                

                
                JLabel labelNombre = new JLabel("Nombre:");
                labelNombre.setBounds(20, 20, 80, 25);
                panel.add(labelNombre);

                JTextField categoriaField = new JTextField(15);
                categoriaField.setBounds(100, 20, 100, 25);
                panel.add(categoriaField);
                
                JLabel prods = new JLabel("Tiene prods: ");
                prods.setBounds(20, 60, 100, 25);
                panel.add(prods);
                

                JRadioButton prodsSI = new JRadioButton("SI");
                prodsSI.setBounds(120, 60, 60, 25);

                JRadioButton prodsNO = new JRadioButton("NO");
                prodsNO.setBounds(180, 60, 60, 25);
                
                ButtonGroup botonGrupal1 = new ButtonGroup();
                botonGrupal1.add(prodsSI);
                botonGrupal1.add(prodsNO);
                panel.add(prodsSI);
                panel.add(prodsNO);

                JLabel padreLabel = new JLabel("Tiene padre: ");
                padreLabel.setBounds(20, 100, 100, 25);
                panel.add(padreLabel);
                

                JRadioButton padreSI = new JRadioButton("SI");
                padreSI.setBounds(120, 100, 60, 25);

                JRadioButton padreNO = new JRadioButton("NO");
                padreNO.setBounds(180, 100, 60, 25);
                
                ButtonGroup botonGrupal = new ButtonGroup();
                botonGrupal.add(padreSI);
                botonGrupal.add(padreNO);
                panel.add(padreSI);
                panel.add(padreNO);
                
             // ComboBox
                JLabel CategoriasPadreLbael = new JLabel("Padres: ");
                CategoriasPadreLbael.setBounds(20, 140, 100, 25);
                panel.add(CategoriasPadreLbael);

                
                DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>(s.listarSoloNombresPadresCat().toArray(new String[0]));
                JComboBox<String> padresCategorias = new JComboBox<>(comboBoxModel);
                padresCategorias.setBounds(100, 140, 160, 25);
                padresCategorias.setEnabled(false);
                panel.add(padresCategorias);
                
                
                
                padreSI.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e1) {
                        boolean tienePadre = padreSI.isSelected();
                        
                        if(tienePadre) {
                        	padresCategorias.setEnabled(true);
                        } else {
                        	padresCategorias.setEnabled(false);
                        }
                    }
                });
                
                padreNO.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e1) {
                        boolean tienePadre = padreSI.isSelected();
                        
                        if(tienePadre) {
                        	padresCategorias.setEnabled(true);
                        } else {
                        	padresCategorias.setEnabled(false);
                        }
                    }
                });
                
                JButton registrarButton = new JButton("Registrar");
                registrarButton.setBounds(20, 200, 240, 25);
                panel.add(registrarButton);
                
                
                registrarButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						String textCat = categoriaField.getText();
						boolean tieneProds = prodsSI.isSelected();
						boolean tienePadre = padreSI.isSelected();
						String nombreCatPadre = padresCategorias.getSelectedItem().toString();
						
						if(textCat.isEmpty()) {
							JOptionPane.showMessageDialog(null, "El campo de nombre está vacio");
							return;
						}
						
						try {
							s.existeCategoria(textCat);
							
						} catch(Exception e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());
							return;
						}
						
						try {
							if(tieneProds) {
								s.agregarCategoriaConProductos(textCat);
								if(tienePadre) {
									s.asignarlePadreACategoriaProds(nombreCatPadre, textCat);
								}
								
							} else {
								s.agregarCategoria(textCat);
								if(tienePadre) {
									s.asignarlePadreCategoria(nombreCatPadre, textCat);
								}
							}
							
						}	catch(Exception e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());
							return;
						}
						
						
						
						
						JOptionPane.showMessageDialog(null, "Categoria agregada con exito");
						// Actualizar campos
						categoriaField.setText("");
						botonGrupal.clearSelection();
						botonGrupal1.clearSelection();
						padresCategorias.setEnabled(false);
						
						// Actualizar ComboBox
                        List<String> nuevosPadres = s.listarSoloNombresPadresCat();
                        comboBoxModel.removeAllElements();
                        for (String padre : nuevosPadres) {
                            comboBoxModel.addElement(padre);
                        }
						
						
						
					}
				} );


                // Mostrar la ventana interna
                ventanaSecundaria.getContentPane().add(panel);
                ventanaSecundaria.setVisible(true);

                // Agregar la ventana interna al JDesktopPane
                desktopPane.add(ventanaSecundaria);
                // Opcional: Centrar la ventana interna
                ventanaSecundaria.setLocation(0, 0);
        	}
        });
        mnCasosDeUso.add(mntmAltaCategoria);
        mnCasosDeUso.add(mntmRegistrarUsuario);
        
        
        JMenuItem mntmMostrarOrden = new JMenuItem("Mostrar Orden");
        mntmMostrarOrden.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		System.out.println("Existen órdenes para listar: " + s.existenOrdenesParaListar());
        		if(!s.existenOrdenesParaListar()) {
    	        	JOptionPane.showMessageDialog(null, "Todavia no hay ordenes para listar");
    	        	return;
    	        }
        		
        		 JInternalFrame ventanaOrdenes = new JInternalFrame("Lista de Ordenes", true, true, true, true);
        	        ventanaOrdenes.setSize(500, 300);
        	        ventanaOrdenes.getContentPane().setLayout(new BorderLayout());

        	        
        	        List<DTOrdenDeCompra> ordenes = s.listarOrdenes();
        	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        	        
        	        // Definir las columnas de la tabla
        	        String[] columnNames = {"Numero de Orden", "Fecha"};

        	        // Crear datos para la tabla
        	        Object[][] data = new Object[ordenes.size()][3];
        	        for (int i = 0; i < ordenes.size(); i++) {
        	            DTOrdenDeCompra o = ordenes.get(i);
        	            data[i][0] = o.getNumero();
        	            data[i][1] = o.getFecha().format(formatter).toString();
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
        	                    DTOrdenDeCompra o = ordenes.get(row);
        	                    mostrarDetallesOrden(o);
        	                }
        	            }
        	        });

        	        // Agregar la tabla al JScrollPane
        	        JScrollPane scrollPane = new JScrollPane(table);
        	        ventanaOrdenes.getContentPane().add(scrollPane, BorderLayout.CENTER);

        	        // Mostrar la ventana interna
        	        ventanaOrdenes.setVisible(true);

        	        // Agregar la ventana interna al JDesktopPane
        	        desktopPane.add(ventanaOrdenes);

        	        // Opcional: Centrar la ventana interna
        	        ventanaOrdenes.setLocation(100, 100);
        	}
        });
        
        mnCasosDeUso.add(mntmMostrarOrden);
        
        JMenuItem mntmRegistrarProducto = new JMenuItem("Registrar Producto");
        mntmRegistrarProducto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	RegistrarProducto prod = new RegistrarProducto();
            	desktopPane.add(prod);
        
            }
        });
        mnCasosDeUso.add(mntmRegistrarProducto);
        
        JMenuItem mntmOrdenCompra = new JMenuItem("Generar orden compra");
        mntmOrdenCompra.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	CrearOrdenCompra compra = new CrearOrdenCompra();
                	desktopPane.add(compra);
            	}
            });
        mnCasosDeUso.add(mntmOrdenCompra);}
        
    
    private void mostrarDetallesOrden(DTOrdenDeCompra orden) {
        JInternalFrame ventanaDetalles = new JInternalFrame("Detalles de la Orden", true, true, true, true);
        ventanaDetalles.setSize(400, 300);
        ventanaDetalles.getContentPane().setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        List<DTItem> lista = orden.listarItems();
        
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        System.out.print(orden.getFecha().format(formatter));
        panel.add(new JLabel("Numero de Orden: " + orden.getNumero()));
        panel.add(new JLabel("Fecha: " + orden.getFecha().format(formatter)));
        
        panel.add(new JLabel("============================="));
        
        if(lista.isEmpty()) {
        	panel.add(new JLabel("No se han añadido items"));
        } else {
        	for(DTItem l: lista) {
        		Producto p = l.getProducto();
        		DtProducto dtp = p.crearDT();
        		
        		panel.add(new JLabel("Nombre del producto: " + dtp.getNombre() + " - " + dtp.getPrecio()));
        		panel.add(new JLabel("Cantidad: " + l.getCant()));
        		panel.add(new JLabel("Subtotal: " + l.getSubTotal()));
        		panel.add(new JLabel("============================="));
        	}
        }
        
        panel.add(new JLabel("Precio total " + orden.getPrecioTotal()));
        
        
        
        
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
    
    

  

    private void mostrarClientes() {
        JInternalFrame ventanaClientes = new JInternalFrame("Lista de Clientes", true, true, true, true);
        ventanaClientes.setSize(500, 300);
        ventanaClientes.getContentPane().setLayout(new BorderLayout());

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
            data[i][2] = cliente.getNombre() + " " + cliente.getApellido();
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
        ventanaDetalles.getContentPane().setLayout(new BorderLayout());

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
        panel.add(new JLabel("Tipo de Usuario: " + cliente.getTipo()));
        panel.add(new JLabel("Mail: " + cliente.getCorreo()));
        panel.add(new JLabel("Nick: " + cliente.getNick()));
        panel.add(new JLabel("Nombre Completo: " + cliente.getNombre() + " " + cliente.getApellido()));
        panel.add(new JLabel("Fecha de Nacimiento: " + cliente.getNacimiento().getDia() + " - " + cliente.getNacimiento().getMes() + " - " + cliente.getNacimiento().getAnio()));
        panel.add(Box.createVerticalStrut(5));
       
        panel.add(new JLabel("Ordenes: "));
        if(cliente.getOrdenes().isEmpty()) {
        	panel.add(new JLabel("   Todavia no existen ordenes"));
        } else {
        	Map<Integer, OrdenDeCompra> ordenesCliente = cliente.getOrdenes();
        	
        	List<DTOrdenDeCompra> ordenesDT = new ArrayList<>();
        	
            for (OrdenDeCompra orden : ordenesCliente.values()) {
                DTOrdenDeCompra dtOrden = orden.crearDT();
                ordenesDT.add(dtOrden);
            }
            for (DTOrdenDeCompra dtOrden : ordenesDT) {
                panel.add(new JLabel(dtOrden.toString()));
            }
        }
        
    
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
