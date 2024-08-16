package serverCentral;

import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;

import java.awt.Image;
import java.io.File;

public class Sistema implements ISistema {
    private static Sistema instance = null;
    private Map<String, Usuario> usuarios;
    private Map<String, Categoria> categorias;

    private Sistema() {
        // Inicialización de colecciones
        this.usuarios = new HashMap<>();
        this.categorias = new HashMap<>();
    }

    public static synchronized Sistema getInstance() {
        if (instance == null) {
            instance = new Sistema();
        }
        return instance;
    }

    // Verificar si el usuario está o no agregado al sistema
    public boolean verificarUnicidad(String nick, String correo) {
        return !this.usuarios.containsKey(nick) && !this.usuarios.containsValue(correo);
    }

    // Validar correo electrónico
    public boolean validarCorreo(String correo) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(correo);
        return matcher.matches();
    }

    // Agregar usuario a la colección
    public void agregarProveedor(String nick, String correo, String nombre, String apellido, DTFecha fechaNacimiento, String compania, String link) throws UsuarioRepetidoException {
        if (!verificarUnicidad(nick, correo)) {
            throw new UsuarioRepetidoException("El usuario con el nick: " + nick + " ya existe");
        }

        Proveedor nuevoProveedor = new Proveedor(nick, correo, nombre, apellido, fechaNacimiento, compania, link);
        usuarios.put(nick, nuevoProveedor);
    }

    public void agregarCliente(String nombre, String nick, String apellido, String correo, DTFecha fecha) throws UsuarioRepetidoException {
        if (!verificarUnicidad(nick, correo)) {
            throw new UsuarioRepetidoException("El usuario con el nick: " + nick + " ya existe");
        }

        Cliente nuevoCliente = new Cliente(nombre, nick, apellido, correo, fecha);
        usuarios.put(nick, nuevoCliente);
    }

    public void agregarImagenes(String nick, ImageIcon imagen) {
        Usuario usuarioBuscado = this.usuarios.get(nick);
        if (usuarioBuscado == null) {
            System.out.println("Usuario con nick: " + nick + " no encontrado.");
            return;
        }

        usuarioBuscado.setImagen(imagen);
    }

    @Override
    public List<DTCliente> listarClientes() {
        List<DTCliente> listaClientes = new ArrayList<>();

        for (Map.Entry<String, Usuario> entry : usuarios.entrySet()) {
            Usuario usuario = entry.getValue();
            if (usuario.getTipo().equals("cliente")) {
                Cliente usuarioCliente = (Cliente) usuario;
                listaClientes.add(usuarioCliente.crearDt());
            }
        }

        return listaClientes;
    }
    
    
    
  //Agregar un producto
    
    //Listar Proveedores
    public List<DTProveedor> listarProveedores(){
    	List<DTProveedor> listaProveedor = new ArrayList<>();
    	for(Map.Entry<String, Usuario> entry : usuarios.entrySet()) {
    		Usuario usuario = entry.getValue();
    		if(usuario.getTipo().equals("proveedor")) {
    			Proveedor usuarioProveedor = (Proveedor) usuario;
    			listaProveedor.add(usuarioProveedor.crearDt());
    		}
    	}
    	return listaProveedor;
    }
    
    //Verificar si existe el proveedor
    public boolean verificarUsuario(Usuario Proveedor) {
    	Map<String, Usuario> usuarios = this.usuarios;
    	for(Map.Entry<String, Usuario> entry : usuarios.entrySet()) {
    		Usuario u = entry.getValue();
    		if(u.equals(Proveedor)) {
    			return true;
    		}
    	}
    	return false;
    }
    
    //Verificar Nombre
    
    public boolean verificarNombre(String nombre) {
    	Map<String, Categoria> categorias = this.categorias;
    	Cat_Producto ejemplo = null;
    	for(Map.Entry<String, Categoria> entry : categorias.entrySet()) {
    		Categoria categ = entry.getValue();
    		if(categ.getClass().equals(ejemplo)) {
    			Cat_Producto pcast = (Cat_Producto) categ;
    			Map<Integer, Producto> productos = pcast.darProductos();
    			for(Map.Entry<Integer, Producto> entry : productos.entrySet()) {
    	    		Producto prod = entry.getValue();
    	    		if(prod.getNombre().equals(nombre)){
    	    			return true;
    	    		}
    	    	}
    		}
    	}
    	return false;
    }
    
    //ListarCategorias
    public void listarCategoria(){
    	List<Categoria> listaCategoria = this.categorias;
    	Cat_Padre ejemplo = null;
    	for(Map.Entry<String, Categoria> entry : categorias.entrySet()) {
    		Categoria cat = entry.getValue();
    		if(cat.getClass().equals(ejemplo)) {
    			//Listar recursivo - no lo hice por la interfaz
    			
    			
    		}
    	}
    	
    }
    
    
    //Crear Producto
    
    public void agregarProducto(String titulo, Integer numRef, String Descripcion, String[] Especificaciones, Float precio, Usuario Proveedor) {
    	if(!verificarUsuario(Proveedor)) {
    		//No existe el Proveedor
    		return;
    	}
    	
    	if(verificarNombre(titulo)) {
    		//Existe un nombre igual
    		return;
    	}
    	
    	while(true) {
    		listarCategoria();
    		
    	}
    	
    	
    	Producto p = new Producto(titulo, Descripcion, precio, numRef, Especificaciones);
    	
    }
   
}










