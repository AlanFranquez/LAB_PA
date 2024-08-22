package estacionDeTrabajo;

import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import serverCentral.DTCliente;
import serverCentral.Factory;
import serverCentral.ISistema;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import java.awt.Component;
import java.awt.Image;

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
        setSize(600, 700);

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
        
        DefaultMutableTreeNode root = s.arbolCategorias();
        
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
        
        
//        JButton registrarButton = new JButton("Registrar");
//        registrarButton.setBounds(20, 200, 240, 25);
//        panel.add(registrarButton);
        
        JTree tree = new JTree(root);
        tree.setCellRenderer(renderer);
        tree.setBounds(83, 60, 160, 151);
        panel.add(tree);
        setVisible(true);
        toFront();
        
	}
	private static Icon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
}
