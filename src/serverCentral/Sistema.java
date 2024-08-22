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
    private Map<Integer, OrdenDeCompra> ordenes;

    private Sistema() {
        // Inicialización de colecciones
        this.usuarios = new HashMap<>();
        this.categorias = new HashMap<>();
        this.ordenes = new HashMap<>();
    }

    public static synchronized Sistema getInstance() {
        if (instance == null) {
            instance = new Sistema();
        }
        return instance;
    }

    // CASO DE USO 1: REGISTRAR USUARIO
    public boolean verificarUnicidad(String nick, String correo) {
    	if(!this.usuarios.containsKey(nick)) {
    		for (Usuario usuario : usuarios.values()) {
                if(usuario.getCorreo() == correo) {
                	return false;
                }
            }
    		return true;
    	}
    	return false;
    }
    public void agregarProveedor(String nick, String correo, String nombre, String apellido, DTFecha fechaNacimiento, String compania, String link) throws UsuarioRepetidoException {
    	if (!verificarUnicidad(nick, correo)) {
    		throw new UsuarioRepetidoException("Ya existe un usuario con nick: " + nick + " o email: " + correo);
    	}

    	Proveedor nuevoProveedor = new Proveedor(nombre, nick, apellido, correo, fechaNacimiento, compania, link);
    	usuarios.put(nick, nuevoProveedor);
    }
    public void agregarCliente(String nombre, String nick, String apellido, String correo, DTFecha fecha) throws UsuarioRepetidoException {
    	if (!verificarUnicidad(nick, correo)) {
    		throw new UsuarioRepetidoException("Ya existe un usuario con nick: " + nick + " o email: " + correo);
    	}

    	Cliente nuevoCliente = new Cliente(nombre, nick, apellido, correo, fecha);
    	usuarios.put(nick, nuevoCliente);
    }
    public void agregarImagenUsuario(String nick, ImageIcon imagen) {
    	Usuario usuarioBuscado = this.usuarios.get(nick);
    	if (usuarioBuscado == null) {
    		System.out.println("Usuario con nick: " + nick + " no encontrado.");
    		return;
    	}
    	usuarioBuscado.setImagen(imagen);
    }


    // CASO DE USO 1: FUNCIONES AUXILIARES
    public Usuario getUsuario(String nickname) {
    	return this.usuarios.get(nickname);
    }
    public boolean validarCorreo(String correo) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(correo);
        return matcher.matches();
    }

    
    
    // CASO DE USO 2: REGISTRAR PRODUCTO
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
    }
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
    // public void agregarImagenProducto(...){}
    
    //CASO DE USO 2: FUNCIONES AUXILIARES
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
    public boolean verificarNombre(String nombre) {
        Map<String, Categoria> categorias = this.categorias;
        for (Map.Entry<String, Categoria> entry : categorias.entrySet()) {
            Categoria categ = entry.getValue();
            if (categ instanceof Cat_Producto) {
                Cat_Producto pcast = (Cat_Producto) categ;
                Map<Integer, Producto> productos = pcast.darProductos();
                for (Map.Entry<Integer, Producto> entry2 : productos.entrySet()) {
                    Producto prod = entry2.getValue();
                    if (prod.getNombre().equals(nombre)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public void vincularCatProd(Producto p, String nombre) {
    	for (Categoria cat : this.categorias.values()) {
    		vincularProductoACategoria(cat, p, nombre);
        }
    }
    
    
    
    // CASO DE USO 3: ALTA DE CATEGORIA
    public void agregarCategoria(String nombre) throws CategoriaException {
	   if(existeCategoria(nombre)) {
		   throw new CategoriaException("El nombre de la categoria ya existe");
	   }
	   Cat_Padre nuevaCategoria = new Cat_Padre(nombre);
	   this.categorias.put(nombre, nuevaCategoria);
   }
    public void agregarCategoriaConProductos(String nombre) throws CategoriaException{
	   if(existeCategoria(nombre)) {
		   throw new CategoriaException("Esta categoria ya existe");
	   }
	   Cat_Producto nuevaCategoria = new Cat_Producto(nombre);
	   this.categorias.put(nombre, nuevaCategoria);
   }
    public void asignarlePadreCategoria(String nombrePadre, String nombre) throws CategoriaException {
	   Cat_Padre catPadre = (Cat_Padre) this.categorias.get(nombrePadre);
	   Cat_Padre cat = (Cat_Padre) this.categorias.get(nombre);
	   
	   if(catPadre.verificarSiYaEsHijo(nombre)) {
		   throw new CategoriaException("Esta categoria ya es su hijo");
	   }
	   
	   // Esto tira error
	   /*if((cat.obtenerPadre() != null)) {
		   throw new CategoriaException("Esta categoria ya es su padre");
	   }*/
	   cat.setPadre(catPadre);
   }
    public void asignarlePadreACategoriaProds(String nombrePadre, String nombre) throws CategoriaException {
	   Cat_Padre catPadre = (Cat_Padre) this.categorias.get(nombrePadre);
	   Cat_Producto cat = (Cat_Producto) this.categorias.get(nombre);
	   
	   if(catPadre.verificarSiYaEsHijo(nombre)) {
		   throw new CategoriaException("Esta categoria ya es su hijo");
	   }
	   if(cat.obtenerPadre() == nombrePadre) {
		   throw new CategoriaException("Esta categoria ya es su padre");
	   }
	   cat.setPadre(catPadre);;
   }
   
    // CASO DE USO 3: FUNCIONES AUXILIARES
    public List <String> listarSoloNombresPadresCat() {
    	List <String> listarPadres = new ArrayList<>();
    	for(Map.Entry<String, Categoria> entry : categorias.entrySet()) {
    		Categoria cat = entry.getValue();
    		if(cat.getTipo() == "Padre") {
    			Cat_Padre catPadre = (Cat_Padre) cat;
    			listarPadres.add(catPadre.getNombre());
    		}
    	}
    	return listarPadres;
    }
    public boolean existeCategoria(String nombre) {
        return this.categorias.containsKey(nombre);
    }
    
    
    
    // CASO DE USO 4: GENERAR ORDEN DE COMPRA
    
    
    
    // CASO DE USO 5: VER INFORMACION DE CLIENTE 
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
    
    
    
    // CASO DE USO 6: VER INFORMACION DE PROVEEDOR
    // Reutilización de la función listarProveedores del caso de uso 2
    public DTProveedor infoProveedor(String nick) {
    	Usuario usuario = usuarios.get(nick);
    	if (usuario == null || !usuario.getTipo().equals("proveedor")) {
            System.out.println("Proveedor con nick: " + nick + " no encontrado.");
            return null;
        }
    	
    	Proveedor proveedor = (Proveedor) usuario;
    	return proveedor.crearDt();
        }
    
    
    // CASO DE USO 7: CANCELAR ORDEN DE COMPRA
    
    
    
    // CASO DE USO 8: MODIFICAR DATOS DE PRODUCTO
    
    
    
    // CASO DE USO 9: VER INFORMACION DE PRODUCTO
    
    
    
    // CASO DE USO 10: VER INFORMACION DE ORDEN DE COMPRA
    
    public boolean existenOrdenesParaListar() {
        return !this.ordenes.isEmpty();
    }
    
    public List<DTOrdenDeCompra> listarOrdenes() {
    	List<DTOrdenDeCompra> lista = new ArrayList<>();
    	
    	for(Map.Entry<Integer, OrdenDeCompra> entry : this.ordenes.entrySet()) {
    		OrdenDeCompra o = entry.getValue();
    		
    		lista.add(o.crearDT());
    	}
    	
    	return lista;
    }
    
    public DTOrdenDeCompra elegirOrden(int clave) throws OrdenDeCompraException{
    	if(this.ordenes.get(clave) == null) {
    		throw new OrdenDeCompraException("No existe esta orden de compra");

    	}
    	
    	OrdenDeCompra or = this.ordenes.get(clave);
    	return or.crearDT();
    	
    }
    
    
    
    // NO SE DÓNDE SE USARÍAN:
    
    // Listar solo categorias padres
    public List <DTCat_Padre> listarSoloPadres() {
    	List <DTCat_Padre> listarPadres = new ArrayList<>();
    	for(Map.Entry<String, Categoria> entry : categorias.entrySet()) {
    		Categoria cat = entry.getValue();
    		
    		if(cat.getTipo() == "Padre") {
    			Cat_Padre catPadre = (Cat_Padre) cat;
    			listarPadres.add(catPadre.crearDT());
    		}
    	}
    	return listarPadres;
    }
    
   

    
   

    
    


    
    
    
   


   
   
   
}