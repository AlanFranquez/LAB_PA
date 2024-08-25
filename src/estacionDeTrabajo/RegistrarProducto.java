package estacionDeTrabajo;

import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import serverCentral.Categoria;
import serverCentral.CategoriaException;
import serverCentral.DTProveedor;
import serverCentral.Factory;
import serverCentral.ISistema;

@SuppressWarnings("serial")
public class RegistrarProducto extends JInternalFrame{
	private static ISistema s = Factory.getSistema();
	private File imagenSeleccionada;
	private ImageIcon imagenSelecc;
	
	public RegistrarProducto() {
		setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Registrar Producto");
        setBounds(10, 40, 360, 150);
        setSize(440, 482);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel tituloLabel = new JLabel("Título:");
        tituloLabel.setBounds(20, 20, 80, 25);
        panel.add(tituloLabel);

        JTextField tituloField = new JTextField(20);
        tituloField.setBounds(100, 20, 200, 25);
        panel.add(tituloField);

        JLabel referenciaLabel = new JLabel("Número de referencia:");
        referenciaLabel.setBounds(20, 50, 150, 25);
        panel.add(referenciaLabel);

        
        
        JTextField referenciaField = new JTextField(20);
        referenciaField.setBounds(185, 50, 200, 25);
        panel.add(referenciaField);

        JLabel descripcionLabel = new JLabel("Descripción:");
        descripcionLabel.setBounds(20, 80, 100, 25);
        panel.add(descripcionLabel);

        JTextField descripcionField = new JTextField(20);
        descripcionField.setBounds(100, 80, 266, 25);
        panel.add(descripcionField);

        JLabel especificacionesLabel = new JLabel("Especificaciones:");
        especificacionesLabel.setBounds(20, 110, 150, 25);
        panel.add(especificacionesLabel);

        JTextArea especificacionesArea = new JTextArea();
        especificacionesArea.setBounds(20, 135, 394, 64);
        especificacionesArea.setLineWrap(true);
        panel.add(especificacionesArea);

        JLabel precioLabel = new JLabel("Precio:");
        precioLabel.setBounds(20, 205, 80, 25);
        panel.add(precioLabel);

        JTextField precioField = new JTextField(10);
        precioField.setBounds(100, 205, 100, 25);
        panel.add(precioField);

        JLabel proveedorLabel = new JLabel("Proveedor:");
        proveedorLabel.setBounds(20, 235, 100, 25);
        panel.add(proveedorLabel);
        
        List<DTProveedor> proveedores = s.listarProveedores();
        String[] nombres;
        nombres = new String[proveedores.size()];
        for (int i = 0; i < proveedores.size(); i++) {
            DTProveedor cliente = proveedores.get(i);
            nombres[i] = cliente.getNick();
        }
        
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>(nombres);
        JComboBox<String> padresCategorias = new JComboBox<>(comboBoxModel);
        padresCategorias.setBounds(100, 235, 160, 25);
        padresCategorias.setEnabled(true);
        panel.add(padresCategorias);
        
        
        JLabel lblCategoria = new JLabel("Categoria:");
        lblCategoria.setBounds(20, 263, 80, 25);
        panel.add(lblCategoria);
        DefaultMutableTreeNode root = s.arbolCategorias();
        
        DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer() {
            // Íconos personalizados
        	Icon closedIcon = resizeIcon(new ImageIcon("./imagenes/sinElementos.png"), 16, 16);

            @Override
            public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel,
                                                          boolean expanded, boolean leaf, int row, boolean hasFocus) {
                super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

                // Modificar el ícono según el tipo de nodo
                if(value.toString() == "Sin Elementos") {
                	setIcon(closedIcon);
                }

                return this;
            }
        };
        JTree tree = new JTree(root);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);
        tree.setCellRenderer(renderer);
        tree.setBounds(110, 263, 275, 94);
        
        panel.add(tree);
        
        JLabel imagenesLabel = new JLabel("Imágenes:");
        imagenesLabel.setBounds(20, 363, 100, 25);
        panel.add(imagenesLabel);

        JButton seleccionarImagenButton = new JButton("Seleccionar Imágenes");
        seleccionarImagenButton.setBounds(100, 363, 200, 25);
        panel.add(seleccionarImagenButton);

        JFileChooser fileChooser = new JFileChooser();
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
                        	imagenesLabel.setText(imagenSeleccionada.getName());
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
        
        JLabel imagenSeleccionadaLabel = new JLabel("No se ha seleccionado ninguna imagen");
        imagenSeleccionadaLabel.setBounds(100, 384, 300, 25);
        panel.add(imagenSeleccionadaLabel);
        
        
        JButton registrarButton = new JButton("Crear");
        registrarButton.setBounds(90, 420, 240, 25);
        panel.add(registrarButton);
        
        // Validar y registrar el producto en el sistema
        registrarButton.addActionListener(b -> {
            String proveedor = (String) comboBoxModel.getSelectedItem();
            String titulo = tituloField.getText();
            String descripcion = descripcionField.getText();
            String especificaciones = especificacionesArea.getText();
            String precioStr = precioField.getText();
            File[] imagenes = fileChooser.getSelectedFiles();
            TreePath[] categorias = tree.getSelectionPaths();
            
            
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

            
            
            if(categorias == null) {
            	JOptionPane.showMessageDialog(null, "Recuerde ingresar una Categoría", "Error", JOptionPane.ERROR_MESSAGE);
            	return;
            }
            
            if (titulo.isEmpty() || referenciaField.getText().isEmpty() || descripcion.isEmpty() || especificaciones.isEmpty() || precioStr.isEmpty() || proveedor.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if(s.existeNombre(proveedor, numRef)) {
            	JOptionPane.showMessageDialog(null, "Ya existe un producto con este nombre o numero referencia", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            s.agregarProducto(titulo, numRef, descripcion,especificaciones, precio, proveedor);
            
            
            if (categorias != null) {
                for (TreePath path : categorias) {
                	DefaultMutableTreeNode selectedNode =
                            (DefaultMutableTreeNode) path.getLastPathComponent();
                    System.out.println(" - " + selectedNode.getUserObject());
                    String catName = selectedNode.getUserObject().toString();
                    if(s.esPadre(catName)) {
                    	JOptionPane.showMessageDialog(null, "Alguna de las categorias seleccionadas no es una categoria válida", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    try {
                    	s.agregarProductoCategoria(catName, numRef);
                    	
                    } catch(CategoriaException e1) {
                    	JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    	return;
                    }
                }
            }
            
            JOptionPane.showMessageDialog(null, "Producto registrado con éxito.");
            
            tituloField.setText("");
            referenciaField.setText("");
            descripcionField.setText("");
            especificacionesArea.setText("");
            precioField.setText("");
            comboBoxModel.setSelectedItem(null);
            tree.clearSelection();
            imagenSeleccionadaLabel.setText("No se ha seleccionado ninguna imagen");
        
        });
    
        
        getContentPane().add(panel);
        setVisible(true);
        toFront();
        
	}
	private static Icon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
	
}
