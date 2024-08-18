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
    
    public Usuario getUsuario(String nickname) {
    	return this.usuarios.get(nickname);
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

        Proveedor nuevoProveedor = new Proveedor(nombre, nick, apellido, correo, fechaNacimiento, compania, link);
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
    
    
    // ListarCategorias
    public List<String> listarCategoria() {
        Map<String, Categoria> listaCategoria = this.categorias; // Suponiendo que tienes un atributo `categorias` en la clase
        List<String> nombresCat = new ArrayList<>(); // Inicializar la lista

        for (Map.Entry<String, Categoria> entry : listaCategoria.entrySet()) {
            Categoria cat = entry.getValue();
            if (cat instanceof Cat_Padre) {
                // Recorrer la categoría y sus hijos para obtener todos los nombres
                cat.recorrerCategorias(cat, nombresCat);
            }
        }
        return nombresCat;
    }
    
    //vincular CatProd auxiliar
    
    private void vincularProductoACategoria(Categoria cat, Producto p, String nombreCat) {
        if (cat.getNombre().equals(nombreCat)) {
            if (cat instanceof Cat_Producto) {
                ((Cat_Producto) cat).agregarProducto(p);
            }
        } else if (cat instanceof Cat_Padre) {
            for (Categoria hijo : ((Cat_Padre) cat).getHijos().values()) {
                vincularProductoACategoria(hijo, p, nombreCat);
            }
        }
    }
    
  //vincular CatProd
    public void vincularCatProd(Producto p, String nombre) {
    	for (Categoria cat : this.categorias.values()) {
    		vincularProductoACategoria(cat, p, nombre);
        }
    }

    
    
    // Crear Producto
    public void agregarProducto(String titulo, Integer numRef, String descripcion, String[] especificaciones, Float precio, Usuario proveedor) {
        if (!verificarUsuario(proveedor)) {
            // No existe el Proveedor
            return;
        }

        if (verificarNombre(titulo)) {
            // Existe un nombre igual
            return;
        }

        Producto p = new Producto(titulo, descripcion, precio, numRef, especificaciones);
        //Lista de strings para el tree en swing
        List<String> nombresCat = listarCategoria();
        
        //Agregar logica swing para seleccionar categorias
        while(true) {
        	String im = "";
        	vincularCatProd(p, im);
        }
        
        while(true) {
        	//Agregar imagen de swing
        	File img = null;
        	p.agregarImagen(img);
        }
    }
   
}










