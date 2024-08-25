package serverCentral;

import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.tree.DefaultMutableTreeNode;


//CREO QUE ES ASI
public interface ISistema {
	 // Verificiar si el usuario está o no agregado al sistema
    public abstract boolean verificarUnicidad(String nick, String correo);
 
    
 // Validar correo electrónico
    public abstract boolean validarCorreo(String correo);
    
    // Agregar usuario a la coleccion
    public void agregarProveedor(String nick, String correo, String nombre, String apellido, DTFecha fechaNacimiento, String compania, String link) throws UsuarioRepetidoException;
    
    public void agregarCliente(String nick, String correo, String nombre, String apellido, DTFecha fechaNacimiento) throws UsuarioRepetidoException;
    
    public void agregarImagenUsuario(String nick, ImageIcon imageIcon);
    
    public List<DTCliente> listarClientes();
    
    public Usuario getUsuario(String nickname);
    
    public boolean existeCategoria(String nombre) throws CategoriaException;
    
    public List <DTCat_Padre> listarSoloPadres();
    
    public List <String> listarSoloNombresPadresCat();
    
    public void agregarCategoria(String nombre) throws CategoriaException;
    
    public void agregarCategoriaConProductos(String nombre) throws CategoriaException;
    
    public void asignarlePadreCategoria(String nombrePadre, String nombre) throws CategoriaException;
    

    public void asignarlePadreACategoriaProds(String nombrePadre, String nombre) throws CategoriaException;
    
    public List<DTOrdenDeCompra> listarOrdenes();
    
    public boolean existenOrdenesParaListar() ;


	public abstract DefaultMutableTreeNode arbolCategorias();

	public abstract DefaultMutableTreeNode arbolProductos();

	public List<DTProveedor> listarProveedores();
	
	// Para probar lo de mostrar Ordenes

    public void addOrdenes(OrdenDeCompra o, String nickUsuario);


	public abstract void agregarProductoCategoria(String catName, int numRef);


	public abstract boolean existeNombre(String cliente, int num);


	public abstract boolean esPadre(String catName);


	public abstract void agregarProducto(String titulo, int numRef, String descripcion, String especificaciones,
			int precio, String proveedor);


	public abstract void agregarProducto(int numRef, int cant);


	public abstract void CrearOrden();
	
	public List<DtProducto> listarALLProductos();



}
