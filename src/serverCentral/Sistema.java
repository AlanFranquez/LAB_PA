package serverCentral;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;


public class Sistema implements ISistema {
    private static Sistema instance = null;
    private Map <String, Usuario>usuarios;
    private Map<String, Categoria>categorias;

    private Sistema() {
        //  colecciones
    	this.usuarios = new HashMap<>();
    	this.categorias= new HashMap<>();
    }

    public static synchronized Sistema getInstance() {
        if (instance == null) {
            instance = new Sistema();
        }
        return instance;
    }
    
    
    // Verificiar si el usuario está o no agregado al sistema
    public boolean verificarUnicidad(String nick, String correo) {
    	if(!this.usuarios.containsKey(nick) && !this.usuarios.containsKey(correo)) {
    		return true;
    	}
    	
    	return false;
    }
    
 // Validar correo electrónico
    public boolean validarCorreo(String correo) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(correo);
        return matcher.matches();
    }
    
    // Agregar usuario a la coleccion
    public void agregarProveedor(String nick, String correo, String nombre, String apellido, DTFecha fechaNacimiento, String compania, String link) throws UsuarioRepetidoException {
    	if(verificarUnicidad(nick, correo) == false) {
    		throw new UsuarioRepetidoException("El usuario con el nick: " + nick + " ya existe");
    	}
    	
    	Proveedor nuevoProveedor = new Proveedor(nick, correo, nombre, apellido, fechaNacimiento, compania, link);
    	usuarios.put(nick, nuevoProveedor);
    }
    
    public void agregarCliente(String nick, String correo, String nombre, String apellido, DTFecha fechaNacimiento) throws UsuarioRepetidoException {
    	if(verificarUnicidad(nick, correo) == false) {
    		throw new UsuarioRepetidoException("El usuario con el nick: " + nick + " ya existe");
    	}
    	
    	Cliente nuevoCliente = new Cliente(nick, correo, nombre, apellido, fechaNacimiento);
    	usuarios.put(nick, nuevoCliente);
    }
    
    public void agregarImagenes(String nick, List<File>imagenes) {
    	Usuario usuarioBuscado = this.usuarios.get(nick);
    	
    	usuarioBuscado.setImagenes(imagenes);
    }
    
    
    

    //DESTRUCTOR¿?
    
    
}
