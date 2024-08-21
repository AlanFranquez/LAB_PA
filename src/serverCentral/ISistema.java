package serverCentral;

import java.util.List;

import javax.swing.ImageIcon;

//CREO QUE ES ASI
public interface ISistema {
	 // Verificiar si el usuario está o no agregado al sistema
    public abstract boolean verificarUnicidad(String nick, String correo);
 
    
 // Validar correo electrónico
    public abstract boolean validarCorreo(String correo);
    
    // Agregar usuario a la coleccion
    public abstract void agregarProveedor(String nick, String correo, String nombre, String apellido, DTFecha fechaNacimiento, String compania, String link) throws UsuarioRepetidoException;
    
    public abstract void agregarCliente(String nick, String correo, String nombre, String apellido, DTFecha fechaNacimiento) throws UsuarioRepetidoException;
    
    public abstract void agregarImagenUsuario(String nick, ImageIcon imageIcon);
    
    public abstract List<DTCliente> listarClientes();
    
    public Usuario getUsuario(String nickname);
    
    public boolean existeCategoria(String nombre) throws CategoriaException;
    
    public List <DTCat_Padre> listarSoloPadres();
    
    public List <String> listarSoloNombresPadresCat();
    
    public void agregarCategoria(String nombre) throws CategoriaException;
    
    public void agregarCategoriaConProductos(String nombre) throws CategoriaException;
    
    public void asignarlePadreCategoria(String nombrePadre, String nombre) throws CategoriaException;
}
