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
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import serverCentral.DTCliente;
import serverCentral.DTOrdenDeCompra;
import serverCentral.Factory;
import serverCentral.ISistema;
import javax.swing.JTextField;
import javax.swing.JTable;

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
        
        
        JLabel lblCategoria = new JLabel("Producto:");
        lblCategoria.setBounds(20, 56, 80, 25);
        panel.add(lblCategoria);
        
        DefaultMutableTreeNode root = s.arbolProductos();
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setLocation(73, 56);

        scrollPane.setSize(266, 158);
        scrollPane.setVisible(true);
        panel.add(scrollPane);
        
        DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer() {
            // Íconos personalizados
        	Icon closedIcon = s.resizeIcon(new ImageIcon("./imagenes/sinElementos.png"), 16, 16);

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
        scrollPane.setViewportView(tree);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.setCellRenderer(renderer);
        tree.clearSelection();
        
        
        JButton registrarButton = new JButton("Crear");
        registrarButton.setBounds(73, 334, 240, 25);
        panel.add(registrarButton);
        
        JLabel lblCantidad = new JLabel("Cantidad:");
        lblCantidad.setBounds(20, 225, 80, 25);
        panel.add(lblCantidad);
        
        JTextField cantidad = new JTextField();
        cantidad.setBounds(82, 225, 96, 20);
        panel.add(cantidad);
        
        JButton productoButton = new JButton("Agregar Producto");
        productoButton.setBounds(224, 231, 134, 23);
        panel.add(productoButton);
        
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Numero de Referencia");
        model.addColumn("Cantidad");
        model.addRow(new Object[]{"Numero de Referencia", "Cantidad"});
        JTable lista = new JTable(model);
        lista.setBounds(52, 261, 287, 62);
    	panel.add(lista);

        
        productoButton.addActionListener(b -> { 
        	TreePath[] productos = tree.getSelectionPaths();
        	int cant = Integer.parseInt(cantidad.getText());
        	
        	if(cant > 0) {
        		for (TreePath path : productos) {
                	DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) path.getLastPathComponent();
                	String selection = (String) selectedNode.getUserObject();
                	String[] parts = selection.split(" - "); 
                	int numRef = Integer.parseInt(parts[1]);
                	
                	model.addRow(new Object[]{numRef, cant});
                	cantidad.setText("");
                }
        	}
        });
        registrarButton.addActionListener(b -> {
            // Validar y registrar el producto en el sistema
            String cliente = (String) comboBoxModel.getSelectedItem();
            // Validar campos vacíos
            if (cliente.isEmpty()) {
            	JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            	return;
            }

            s.CrearOrden();
            for (int i = 1; i < model.getRowCount(); i++) {

            	System.out.println(model.getValueAt(i, 0).toString());
            	int numRef = Integer.parseInt(model.getValueAt(i, 0).toString());
            	int cant = Integer.parseInt(model.getValueAt(i, 1).toString());
            	System.out.println("  )" + numRef);
            	s.agregarProducto(numRef, cant);
            }
            s.asignarOrdenCliente(cliente);
            model.setRowCount(1);
            try {
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "El número de referencia debe ser un número entero válido y el precio debe ser un número decimal válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        
        });
    
        
        
        setVisible(true);
        toFront();
	}
}
