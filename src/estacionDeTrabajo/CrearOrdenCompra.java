package estacionDeTrabajo;

import java.awt.Component;
import java.awt.Image;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import serverCentral.DTCliente;
import serverCentral.Factory;
import serverCentral.ISistema;

@SuppressWarnings("serial")
public class CrearOrdenCompra extends JInternalFrame{
	private static ISistema s = Factory.getSistema();
	public CrearOrdenCompra() {
		setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Crear Orden de Compra");
        setBounds(10, 40, 360, 150);
        setSize(412, 400);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel tituloLabel = new JLabel("Cliente:");
        tituloLabel.setBounds(20, 20, 80, 25);
        panel.add(tituloLabel);
        
        List<DTCliente> clientes = s.listarClientes();
        String[] nombres;
        nombres = new String[clientes.size()];
        for (int i = 0; i < clientes.size(); i++) {
            DTCliente cliente = clientes.get(i);
            nombres[i] = cliente.getNick();
        }
        
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>(nombres);
        JComboBox<String> padresCategorias = new JComboBox<>(comboBoxModel);
        padresCategorias.setBounds(73, 20, 160, 25);
        padresCategorias.setEnabled(true);
        panel.add(padresCategorias); 
        getContentPane().add(panel);
        
        
        JLabel lblCategoria = new JLabel("Categoria:");
        lblCategoria.setBounds(20, 56, 80, 25);
        panel.add(lblCategoria);
        DefaultMutableTreeNode root = s.arbolProductos();
        
        DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer() {
            // Íconos personalizados
        	Icon closedIcon = resizeIcon(new ImageIcon("./imagenes/sinElementos.png"), 16, 16);

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
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);
        tree.setCellRenderer(renderer);
        tree.setBounds(83, 60, 275, 244);
        
        panel.add(tree);
        
        
        JButton registrarButton = new JButton("Crear");
        registrarButton.setBounds(73, 315, 240, 25);
        panel.add(registrarButton);
        
        registrarButton.addActionListener(b -> {
            // Validar y registrar el producto en el sistema
            String cliente = (String) comboBoxModel.getSelectedItem();
            TreePath[] productos = tree.getSelectionPaths();
            if (productos != null) {
                for (TreePath path : productos) {
                	DefaultMutableTreeNode selectedNode =
                            (DefaultMutableTreeNode) path.getLastPathComponent();
                    System.out.println(" - " + selectedNode.getUserObject());
                }
            }


            // Validar campos vacíos
            if (cliente.isEmpty() ) {
                JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "El número de referencia debe ser un número entero válido y el precio debe ser un número decimal válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        
        });
    
        
        
        setVisible(true);
        toFront();
        
	}
	private static Icon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
}
