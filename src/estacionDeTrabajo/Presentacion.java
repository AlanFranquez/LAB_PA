package estacionDeTrabajo;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
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
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import com.toedter.calendar.JDateChooser;

import serverCentral.CategoriaException;
import serverCentral.DTCliente;
import serverCentral.DTFecha;
import serverCentral.DTOrdenDeCompra;
import serverCentral.DTProveedor;
import serverCentral.DtProducto;
import serverCentral.Factory;
import serverCentral.ISistema;
import serverCentral.OrdenDeCompra;
import serverCentral.Producto;
import serverCentral.ProductoException;
import serverCentral.Proveedor;
import serverCentral.Sistema;
import serverCentral.Usuario;
import serverCentral.UsuarioRepetidoException;

public class Presentacion {

    private JFrame frame;
    private File imagenSeleccionada;
    private ImageIcon imagenSelecc;
    private JDesktopPane desktopPane;
    private static ISistema s = Factory.getSistema();
    private JFileChooser fileChooser;
    Calendar calendar = Calendar.getInstance();
    private List<File> imagenesSeleccionadas = new ArrayList<>();
    
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
                    s.agregarCategoriaConProductos("Otros");
                    
                    // SOlo una prueba
                    
                    
                   
                    Proveedor prov = new Proveedor("Bellizzi", "isracaballero@gmail.com", "Israel", "Bellizzi", fecha3 ,"Bamboo.inc" , "www.bamboo.com");
                    s.agregarProveedor("Bellizzi", "isracaballero@gmail.com", "Israel", "Bellizzi", fecha3 ,"Bamboo.inc" , "www.bamboo.com");
                    s.agregarImagenUsuario("Bellizzi", new ImageIcon("./imagenes/p1.jpg"));
                    
                    Producto p1 = new Producto("Pelota", "Pelota inflable ideal", 120, 1,"Lalala", prov, 2);
                    s.agregarProducto("Pelota", 1, "Pelota inflable ideal", "Lalala", 120, "Bellizzi", 2);
                    s.agregarProductoCategoria("Tecno", 1);
                    s.agregarProductoCategoria("Otros", 1);
                    s.agregarProducto("Cargador", 2, "Cargador tipo c", "Lalala", 220, "Bellizzi", 20);
                    s.agregarProductoCategoria("Tecno", 2);
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
								s.agregarProveedor(nickname, correo, nombre, apellido, fechaNacimiento, compania, web);
								
							} catch (UsuarioRepetidoException e) {
								JOptionPane.showMessageDialog(null,e.getMessage());
								return;
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
								return;
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
        
        
        JMenuItem mntmMostrarOrden = new JMenuItem("Mostrar Ordenes");
        mntmMostrarOrden.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ListarOrdenes ords = new ListarOrdenes("Detalles");
        		desktopPane.add(ords);
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
        mnCasosDeUso.add(mntmOrdenCompra);
        

        
        //Opcion Mostrar Proveedor
        JMenuItem mntmMostrarProveedor = new JMenuItem("Mostrar Proveedor");
        mnCasosDeUso.add(mntmMostrarProveedor);
    	mntmMostrarProveedor.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            MostrarProveedor();
        	}
    	});
    	

        JMenuItem mntmListarProductos = new JMenuItem("Listar Productos");
        mntmListarProductos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JInternalFrame ventanaProductos = new JInternalFrame("Lista de Productos", true, true, true, true);
                ventanaProductos.setSize(400, 500);
                ventanaProductos.setLayout(new BorderLayout());

                JPanel panel = new JPanel();
                panel.setLayout(new BorderLayout());

                List<DtProducto> listaP = new ArrayList<>();
                try {
                    listaP = s.listarALLProductos();
                } catch (ProductoException e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage());
                    return;
                }

                JPanel productosPanel = new JPanel();
                
                // lo pasare a una funcion, 1 argumento lista P
                productosPanel.setLayout(new BoxLayout(productosPanel, BoxLayout.Y_AXIS));
                productosPanel.add(new JLabel("Listado de Productos"));
                for (DtProducto dt : listaP) {
                	JLabel productoDT = new JLabel(dt.getNombre() + " - " + dt.getPrecio());
                	productoDT.addMouseListener(new MouseListener() {
						@Override
						public void mouseClicked(MouseEvent e) {
							JInternalFrame ventanaDetalleProducto = new JInternalFrame("Detalle de Producto", true, true, true, true);
                            ventanaDetalleProducto.setSize(600, 400);
                            ventanaDetalleProducto.setLayout(new BorderLayout());

                            JPanel detallePanel = new JPanel();
                            detallePanel.setLayout(new BoxLayout(detallePanel, BoxLayout.Y_AXIS));
                            
                            detallePanel.add(new JLabel("Numero de Referencia: " + dt.getNumRef()));
                            detallePanel.add(new JLabel("Nombre: " + dt.getNombre()));
                            detallePanel.add(new JLabel("Descripción: " + dt.getDescripcion()));
                            detallePanel.add(new JLabel("Especificaciones: " + dt.getEspecs()));
                            detallePanel.add(new JLabel("Precio: " + dt.getPrecio()));
                            
                            detallePanel.add(new JLabel("Proveedor: " + dt.getNombreProveedor()));
                            
                            detallePanel.add(new JLabel("=================================================="));
                            detallePanel.add(new JLabel("<html>Categorias de los productos: " + dt.getCategorias()));
                            List<File> imagenes = dt.getImagenes();
                            if (imagenes != null && !imagenes.isEmpty()) {
                                for (File imagenFile : imagenes) {
                                    try {
                                        ImageIcon imageIcon = new ImageIcon(imagenFile.getAbsolutePath());
                                        Image imagenAjuste = imageIcon.getImage();
                                        Image reajuste = imagenAjuste.getScaledInstance(75, 75, 0);
                                        ImageIcon imagenIconAjustada = new ImageIcon(reajuste);
                                        JLabel imageLabel = new JLabel(imagenIconAjustada);
                                        detallePanel.add(imageLabel);
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            }
                            
                            ventanaDetalleProducto.getContentPane().add(detallePanel, BorderLayout.CENTER);
                            ventanaDetalleProducto.setVisible(true);
                            desktopPane.add(ventanaDetalleProducto);
                            ventanaDetalleProducto.setLocation(150, 150);
							
						}

						@Override
						public void mousePressed(MouseEvent e) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void mouseReleased(MouseEvent e) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void mouseEntered(MouseEvent e) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void mouseExited(MouseEvent e) {
							// TODO Auto-generated method stub
							
						}
					});
                    productosPanel.add(productoDT);
                }
                panel.add(productosPanel, BorderLayout.NORTH);

                JLabel lblCategoria = new JLabel("Categoría:");
                panel.add(lblCategoria, BorderLayout.WEST);
                
                DefaultMutableTreeNode root = s.arbolProductos();
                JScrollPane scrollPane = new JScrollPane();
                scrollPane.setLocation(73, 56);

                scrollPane.setSize(200, 100);
                scrollPane.setVisible(true);
                panel.add(scrollPane);
                
                DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer() {
					private static final long serialVersionUID = 1L;
					Icon closedIcon = s.resizeIcon(new ImageIcon("./imagenes/sinElementos.png"), 16, 16);

                    @Override
                    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel,
                                                                  boolean expanded, boolean leaf, int row, boolean hasFocus) {
                        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
                        if(value.toString() == "Sin Elementos") {
                        	setIcon(closedIcon);
                        }

                        return this;
                    }
                };
                JTree tree = new JTree(root);
                scrollPane.setViewportView(tree);
                tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
                tree.setCellRenderer(renderer);
                tree.clearSelection();
                

                // Listener para selección de nodos
                tree.addTreeSelectionListener((TreeSelectionListener) new TreeSelectionListener() {
                    public void valueChanged(TreeSelectionEvent event) {
                    	System.out.print("1");
                    	
                    	
                        DefaultMutableTreeNode seleccionado = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                        System.out.print(seleccionado);
                        
                        if(seleccionado == null || seleccionado.getUserObject().toString() == "Cats") {
                        	 productosPanel.removeAll();
                             productosPanel.add(new JLabel("Listado de Productos"));
                             List<DtProducto> listaPr = new ArrayList<>();
							try {
								listaPr = s.listarALLProductos();
							} catch (ProductoException e) {
								JOptionPane.showMessageDialog(null, "Ocurrio un error, volver a intentar");
							}
                             for (DtProducto dt : listaPr) {
                                 productosPanel.add(new JLabel(dt.getNombre() + " - " + dt.getPrecio()));
                             }
                            productosPanel.revalidate();
                         	productosPanel.repaint();
                        	return;
                        }
                        String nombreCategoria = seleccionado.getUserObject().toString();
                        List<DtProducto> prodsFiltrados = new ArrayList<>();
                        
                        if(!s.esPadre(nombreCategoria)) {
                        	
                        	try {
								s.comprobarCat(nombreCategoria);
							} catch (CategoriaException e) {
								return;
							}
                        	
                        		
                        	try {
								prodsFiltrados = s.listarProductosPorCategoria(nombreCategoria);
								productosPanel.removeAll();
	                            productosPanel.add(new JLabel("Listado de Productos"));
	                            for (DtProducto dt : prodsFiltrados) {
	                                 productosPanel.add(new JLabel(dt.getNombre() + " - " + dt.getPrecio()));
	                             }
							} catch (ProductoException e) {
								JOptionPane.showMessageDialog(null, e.getMessage());
								return;
							}
                        	
                        	
                        		
                        	
                        }
                        
                        productosPanel.revalidate();
                    	productosPanel.repaint();
                        
                    }
                    
                    
                });

                
                
                        tree.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                // Obtener la ruta del nodo donde se hizo clic
                                TreePath path = tree.getPathForLocation(e.getX(), e.getY());

                                if (path != null) {
                                    // Obtener el nodo seleccionado
                                    DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) path.getLastPathComponent();

                                    // Verificar si el nodo es una hoja
                                    if (selectedNode.isLeaf()) {
                                        // Disparar el evento deseado
                                    	String selection = (String) selectedNode.getUserObject();
                                    	String[] parts = selection.split(" - "); 
                                    	int numRef = 0;
                                    	
                                    	try {
                                    		numRef = Integer.parseInt(parts[1]);
                                    	} catch(ArrayIndexOutOfBoundsException e2) {
                                    		
                                    		return;
                                    	}
                                    	DtProducto dt = s.getDtProducto(numRef);
                                    	
                                    	System.out.print(numRef);
                                    	System.out.print(parts.length);
                                    	
                                    	JInternalFrame ventanaDetalleProducto = new JInternalFrame("Detalle de Producto", true, true, true, true);
                                        ventanaDetalleProducto.setSize(600, 400);
                                        ventanaDetalleProducto.setLayout(new BorderLayout());

                                        JPanel detallePanel = new JPanel();
                                        detallePanel.setLayout(new BoxLayout(detallePanel, BoxLayout.Y_AXIS));
                                        
                                        detallePanel.add(new JLabel("Numero de Referencia: " + dt.getNumRef()));
                                        detallePanel.add(new JLabel("Nombre: " + dt.getNombre()));
                                        detallePanel.add(new JLabel("Descripción: " + dt.getDescripcion()));
                                        detallePanel.add(new JLabel("Especificaciones: " + dt.getEspecs()));
                                        detallePanel.add(new JLabel("Precio: " + dt.getPrecio()));
                                        
                                        detallePanel.add(new JLabel("Proveedor: " + dt.getNombreProveedor()));
                                        
                                        detallePanel.add(new JLabel("=================================================="));
                                        detallePanel.add(new JLabel("<html>Categorias de los productos: " + dt.getCategorias()));
                                        
                                        
                                        ventanaDetalleProducto.getContentPane().add(detallePanel, BorderLayout.CENTER);
                                        ventanaDetalleProducto.setVisible(true);
                                        desktopPane.add(ventanaDetalleProducto);
                                        ventanaDetalleProducto.setLocation(150, 150);
                                    }
                                }
                            }
                        });
                        
                        productosPanel.revalidate();
                    	productosPanel.repaint();
                        

                ventanaProductos.getContentPane().add(panel, BorderLayout.CENTER);

                ventanaProductos.setVisible(true);
                desktopPane.add(ventanaProductos);
                ventanaProductos.setLocation(100, 100);
            }
        });
		
        mnCasosDeUso.add(mntmListarProductos);
        
        
        JMenuItem mntmModificarProductos = new JMenuItem("Modificar Productos");
        mntmModificarProductos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JInternalFrame ventanaProductos = new JInternalFrame("Modificar Productos", true, true, true, true);
                ventanaProductos.setSize(400, 600);
                ventanaProductos.setLayout(new BorderLayout());

                JPanel panel = new JPanel();
                panel.setLayout(new BorderLayout());

                JPanel productosPanel = new JPanel();
                

                JLabel lblCategoria = new JLabel("Categoría:");
                panel.add(lblCategoria, BorderLayout.WEST);
                
                DefaultMutableTreeNode root = s.arbolProductos();
                
                @SuppressWarnings("serial")
				DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer() {
                    // Íconos personalizados
                	ImageIcon icon = new ImageIcon("./imagenes/sinElementos.png");
                	Image img = icon.getImage();
                    Image resizedImage = img.getScaledInstance(16, 16,  java.awt.Image.SCALE_SMOOTH);
                	Icon closedIcon = new ImageIcon(resizedImage);

                    @Override
                    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel,
                                                                  boolean expanded, boolean leaf, int row, boolean hasFocus) {
                        // Llamar al método de la superclase para configurar el componente
                        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

                        // Modificar el ícono según el tipo de nodo
                        if(value.toString() == "Sin Elementos") {
                        	setIcon(closedIcon);
                        }

                        return this;
                    }
                };
                JTree tree = new JTree(root);
                tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
                tree.setCellRenderer(renderer);
                tree.setBounds(83, 50, 275, 170);
                
                panel.add(tree);
                JScrollPane treeScrollPane = new JScrollPane(tree);
                panel.add(treeScrollPane, BorderLayout.CENTER);
                
                tree.addMouseListener(new MouseAdapter() {
                      @Override
                      public void mouseClicked(MouseEvent e) {
                          // Obtener la ruta del nodo donde se hizo clic
                          TreePath path = tree.getPathForLocation(e.getX(), e.getY());

                          if (path != null) {
                             // Obtener el nodo seleccionado
                             DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) path.getLastPathComponent();

                             // Verificar si el nodo es una hoja
                             if (selectedNode.isLeaf()) {
                            	 // Disparar el evento deseado
                                 String selection = (String) selectedNode.getUserObject();
                                 if(selection == "Sin Elementos") {
                                	 return;
                                 }
                                 String[] parts = selection.split(" - "); 
                                 int numRef = Integer.parseInt(parts[1]);
                                 DtProducto dt = s.getDtProducto(numRef);
                                    	
                                JInternalFrame ventanaDetalleProducto = new JInternalFrame("Detalle de Producto", true, true, true, true);
                                ventanaDetalleProducto.setSize(600, 400);
                                ventanaDetalleProducto.setLayout(new BorderLayout());

                                JPanel detallePanel = new JPanel();
                                detallePanel.setLayout(new BoxLayout(detallePanel, BoxLayout.Y_AXIS));
                                        
                                detallePanel.add(new JLabel("Numero de Referencia: " + dt.getNumRef()));
                                detallePanel.add(new JLabel("Nombre: " + dt.getNombre()));
                                detallePanel.add(new JLabel("Descripción: " + dt.getDescripcion()));
                                detallePanel.add(new JLabel("Especificaciones: " + dt.getEspecs()));
                                detallePanel.add(new JLabel("Precio: " + dt.getPrecio()));
                                        
                                detallePanel.add(new JLabel("Proveedor: " + dt.getNombreProveedor()));
                                        
                                        
                                detallePanel.add(new JLabel("<html>Categorias: " + dt.getCategorias()));
                                
                                

                                ventanaDetalleProducto.getContentPane().add(detallePanel, BorderLayout.CENTER);
                                ventanaDetalleProducto.setVisible(true);
                                ventanaDetalleProducto.toFront();
                                desktopPane.add(ventanaDetalleProducto);
                                ventanaDetalleProducto.setLocation(150, 150);
                                JButton modificarButton = new JButton("Modificar");
                                modificarButton.setBounds(20, 540, 240, 25);
                                
                                
                                
                                modificarButton.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent z) {
                                    	
                                    	
                                    	
                                    	JInternalFrame modificarFrame = new JInternalFrame();

                                        modificarFrame.setResizable(true);
                                        modificarFrame.setIconifiable(true);
                                        modificarFrame.setMaximizable(true);
                                        modificarFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                                        modificarFrame.setClosable(true);
                                        modificarFrame.setTitle("Modificar Producto");
                                        modificarFrame.setBounds(10, 40, 360, 150);
                                        modificarFrame.setSize(440, 257);

                                        JPanel panel1 = new JPanel();
                                        panel1.setLayout(null);  // Se cambió "panel" por "panel1"

                                        JLabel tituloLabel = new JLabel("Título:");
                                        tituloLabel.setBounds(20, 20, 80, 25);
                                        panel1.add(tituloLabel);

                                        JTextField tituloField = new JTextField(20);
                                        tituloField.setBounds(100, 20, 200, 25);
                                        panel1.add(tituloField);

                                        JLabel referenciaLabel = new JLabel("Número de referencia:");
                                        referenciaLabel.setBounds(20, 50, 150, 25);
                                        panel1.add(referenciaLabel);

                                        JTextField referenciaField = new JTextField(20);
                                        referenciaField.setBounds(185, 50, 200, 25);
                                        panel1.add(referenciaField);

                                        JLabel descripcionLabel = new JLabel("Descripción:");
                                        descripcionLabel.setBounds(20, 80, 100, 25);
                                        panel1.add(descripcionLabel);

                                        JTextField descripcionField = new JTextField(20);
                                        descripcionField.setBounds(100, 80, 266, 25);
                                        panel1.add(descripcionField);

                                        JLabel precioLabel = new JLabel("Precio:");
                                        precioLabel.setBounds(20, 115, 80, 25);
                                        panel1.add(precioLabel);

                                        JTextField precioField = new JTextField(10);
                                        precioField.setBounds(100, 115, 100, 25);
                                        panel1.add(precioField);

                                        JButton registrarButton = new JButton("Guardar Cambios");
                                        registrarButton.setBounds(88, 172, 240, 25);
                                        panel1.add(registrarButton);
                                        
                                        JLabel imagenesLabel = new JLabel("Imágenes:");
                                        imagenesLabel.setBounds(20, 363, 100, 25);
                                        panel1.add(imagenesLabel);

                                        JButton seleccionarImagenButton = new JButton("Seleccionar Imágenes");
                                        seleccionarImagenButton.setBounds(100, 363, 200, 25);
                                        panel1.add(seleccionarImagenButton);

                                        JLabel imagenesSeleccionadasLabel = new JLabel("No se ha seleccionado ninguna imagen");
                                        imagenesSeleccionadasLabel.setBounds(100, 384, 300, 25);
                                        panel1.add(imagenesSeleccionadasLabel);
                                        
                                        JFileChooser fileChooser = new JFileChooser();
                                        fileChooser.setMultiSelectionEnabled(true);
                                        seleccionarImagenButton.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e1) {
                                                int returnValue = fileChooser.showOpenDialog(null);
                                                if (returnValue == JFileChooser.APPROVE_OPTION) {
                                                    File[] archivosSeleccionados = fileChooser.getSelectedFiles();
                                                    imagenesSeleccionadas.clear();
                                                    StringBuilder imagenesNombres = new StringBuilder();
                                                    
                                                    for (File archivo : archivosSeleccionados) {
                                                        String nombreArchivo = archivo.getName().toLowerCase();
                                                        if (nombreArchivo.endsWith(".jpg") || nombreArchivo.endsWith(".png")) {
                                                            imagenesSeleccionadas.add(archivo);
                                                            imagenesNombres.append(archivo.getName()).append("; ");
                                                        } else {
                                                            JOptionPane.showMessageDialog(null, "El archivo " + archivo.getName() + " no es válido. Seleccione archivos .jpg o .png", "Archivo no válido", JOptionPane.ERROR_MESSAGE);
                                                        }
                                                    }
                                                    
                                                    if (imagenesSeleccionadas.isEmpty()) {
                                                        imagenesSeleccionadasLabel.setText("No se ha seleccionado ninguna imagen");
                                                    } else {
                                                        imagenesSeleccionadasLabel.setText("Imágenes seleccionadas: " + imagenesNombres.toString());
                                                    }
                                                }
                                            }
                                        });
                                        
                                        modificarFrame.getContentPane().add(panel1);
                                        
                                        
                                        
                                        // Validar y registrar el producto en el sistema
                                        registrarButton.addActionListener(b -> {
                                            String titulo = tituloField.getText();
                                            String descripcion = descripcionField.getText();
                                            String precioStr = precioField.getText();
                                            

                                            if (titulo.isEmpty() || referenciaField.getText().isEmpty() || descripcion.isEmpty() || precioStr.isEmpty()) {
                                                JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                                                return;
                                            }
                                            
                                            int precio = 0;
                                            
                                            try {
                                            	precio = Integer.parseInt(precioStr);
                                            	
                                            } catch(NumberFormatException e1) {
                                            	JOptionPane.showMessageDialog(null, "El precio debe ser un numero", "Error", JOptionPane.ERROR_MESSAGE);
                                            	return;
                                            }
                                            
                                            int numRef = 0;
                                            try {
                                            	numRef = Integer.parseInt(referenciaField.getText());
                                            	
                                            } catch(NumberFormatException e1) {
                                            	JOptionPane.showMessageDialog(null, "El numero de referencia no puede ser un string", "Error", JOptionPane.ERROR_MESSAGE);
                                            	return;
                                            }

                                            try {
												s.editarProducto(titulo, numRef, descripcion, precio, imagenesSeleccionadas);
											} catch (ProductoException e) {
												JOptionPane.showMessageDialog(null, e.getMessage());
											}

                                            
                                        });

                                        modificarFrame.setVisible(true);
                                        modificarFrame.toFront();
                                        desktopPane.add(modificarFrame);
                                    }
                                });

                                
                                
                                detallePanel.add(modificarButton);
                             }
                          }
                      }
                });
                        
                productosPanel.revalidate();
                productosPanel.repaint();

                ventanaProductos.getContentPane().add(panel, BorderLayout.CENTER);

                ventanaProductos.setVisible(true);
                desktopPane.add(ventanaProductos);
                ventanaProductos.setLocation(100, 100);
            }
        });

        mnCasosDeUso.add(mntmModificarProductos);
        
        JMenuItem mntmCancelarOrden = new JMenuItem("Cancelar Orden de Compra");
        mntmCancelarOrden.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	ListarOrdenes cancelar = new ListarOrdenes("Eliminar");
            	desktopPane.add(cancelar);
            }
        });
        mnCasosDeUso.add(mntmCancelarOrden);
    
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
        	
        	
            for (OrdenDeCompra orden : ordenesCliente.values()) {
                DTOrdenDeCompra dtOrden = orden.crearDT();
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
    
    private void MostrarProveedor() {
    	JInternalFrame ventanaProveedores = new JInternalFrame("Lista de Proveedores", true, true, true, true);
        ventanaProveedores.setSize(500, 300);
        ventanaProveedores.getContentPane().setLayout(new BorderLayout());
        // Recuperar la lista de proveedores
        List<DTProveedor> proveedores = s.listarProveedores();

        // Definir las columnas de la tabla
        String[] columnNames = {"Nick", "Correo"};

     // Crear datos para la tabla
        Object[][] data = new Object[proveedores.size()][3];
        for (int i = 0; i < proveedores.size(); i++) {
            DTProveedor proveedor = proveedores.get(i);
            data[i][0] = proveedor.getNick();
            data[i][1] = proveedor.getCorreo();
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
                    DTProveedor proveedor = proveedores.get(row);
                    mostrarDetallesProveedor(proveedor);
                }
            }
        });

        // Agregar la tabla al JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        ventanaProveedores.getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Mostrar la ventana interna
        ventanaProveedores.setVisible(true);

        // Agregar la ventana interna al JDesktopPane
        desktopPane.add(ventanaProveedores);

        // Opcional: Centrar la ventana interna
        ventanaProveedores.setLocation(100, 100);
    }
    
    
    private void mostrarDetallesProveedor(DTProveedor proveedor) {
    	JInternalFrame ventanaDetalles = new JInternalFrame("Detalles del Proveedor", true, true, true, true);
        ventanaDetalles.setSize(400, 300);
        ventanaDetalles.getContentPane().setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Agregar la información del cliente al panel
        ImageIcon imagenIcon = proveedor.getImagen();
        
        
        
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
        panel.add(new JLabel("Tipo de Usuario: Proveedor"));
        panel.add(new JLabel("Mail: " + proveedor.getCorreo()));
        panel.add(new JLabel("Nick: " + proveedor.getNick()));
        panel.add(new JLabel("Nombre Completo: " + proveedor.getNombre() + " " + proveedor.getApellido()));
        panel.add(new JLabel("Fecha de Nacimiento: " + proveedor.getNacimiento().getDia() + " - " + proveedor.getNacimiento().getMes() + " - " + proveedor.getNacimiento().getAnio()));
        panel.add(new JLabel("Compañía: " + proveedor.getCompania()));
        panel.add(new JLabel("Link: " + proveedor.getLink()));
        panel.add(Box.createVerticalStrut(5));
       
        
        
    
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
