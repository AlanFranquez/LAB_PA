package serverCentral;

import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import java.awt.Image;
import java.io.File;

public class Sistema implements ISistema {
    private static Sistema instance = null;
    private Map<String, Usuario> usuarios;
    private Map<String, Categoria> categorias;
    private Map<Integer, OrdenDeCompra> ordenes;
    private Map<String, Categoria> arbolCategorias;

    private Sistema() {
        // Inicialización de colecciones
        this.usuarios = new HashMap<>();
        this.categorias = new HashMap<>();
        this.ordenes = new HashMap<>();
        this.arbolCategorias = new HashMap<>();
    }

    public static synchronized Sistema getInstance() {
        if (instance == null) {
            instance = new Sistema();
        }
        return instance;
    }

    // CASO DE USO 1: REGISTRAR USUARIO
    public boolean verificarUnicidad(String nick, String correo) {
    	Usuario u = this.usuarios.get(nick);
        if (u != null) {
       
            if (u.getCorreo().equals(correo)) {
                return false;
            }
        }
        
        for (Usuario usuario : this.usuarios.values()) {
            if (usuario.getCorreo().equals(correo)) {
                return false;
            }
        }
        
        return true;
    	
    	
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
    public void agregarProducto(String titulo, int numRef, String descripcion, String especificaciones, int precio, String p, int stock) {
        Proveedor proveedor = (Proveedor) usuarios.get(p);
        Producto prod = new Producto(titulo, descripcion, precio, numRef, especificaciones, proveedor, stock);
        proveedor.agregarProd(prod);
    }
    public DefaultMutableTreeNode arbolCategorias() {
      	 DefaultMutableTreeNode root = new DefaultMutableTreeNode("Cats");
      	 for(Categoria cat : arbolCategorias.values()) {
      		 DefaultMutableTreeNode child = arbolCategorias(cat);
      		 root.add(child);
      	 }
      	return root;
      }
    public DefaultMutableTreeNode arbolCategorias(Categoria cat) {
     	 	DefaultMutableTreeNode rama = new DefaultMutableTreeNode(cat.getNombre());
     	 	if(cat.getTipo() == "Padre") {
     	 		Map<String, Categoria> hijos = ((Cat_Padre) cat).getHijos();
     	 		if(hijos.size() >= 1) {
     	 			for(Categoria hijo : hijos.values()) {
     	 				DefaultMutableTreeNode child = arbolCategorias(hijo);
     	 				rama.add(child);
     	 			}
     	 		}else {
     	 			rama.add(new DefaultMutableTreeNode("Sin Elementos"));
     	 		}
     	 	}
     	return rama;
     }
    public boolean existeNombre(String nombre, int num) {
    	Map<String, Categoria> categorias = this.categorias;
    	for (Map.Entry<String, Categoria> entry : categorias.entrySet()) {
    		Categoria categ = entry.getValue();
    		if (categ instanceof Cat_Producto) {
    			Cat_Producto pcast = (Cat_Producto) categ;
    			Map<Integer, Producto> productos = pcast.getProductos();
    			for (Map.Entry<Integer, Producto> entry2 : productos.entrySet()) {
    				Producto prod = entry2.getValue();
    				if (prod.getNombre().equals(nombre) || prod.getNumRef() == num) {
    					return true;
    				}
    			}
    		}
    	}
    	return false;
    }
    public boolean esPadre(String nombre) {
    	Categoria cat = categorias.get(nombre);
    	return (cat instanceof Cat_Padre);
    }
    public void agregarProductoCategoria(String catName, int numRef) throws CategoriaException {
    	for (Usuario user : usuarios.values()) {
    		if (user instanceof Proveedor) {
    			Proveedor p = (Proveedor) user;
    			Producto prod = p.obtenerProd(numRef);
    			if(prod != null) {
    				Cat_Producto cat = (Cat_Producto) categorias.get(catName);
    				if(cat == null) {
    					throw new CategoriaException("Hubo un error en la Categoria, vuelva a intentarlo");
    				}
    				prod.agregarCategorias(cat);
    				cat.agregarProducto(prod);
    			}
    		}
    	}
    }
    
    public Icon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
    
    
    // CASO DE USO 3: ALTA DE CATEGORIA
    public void agregarCategoria(String nombre) throws CategoriaException {
	   if(existeCategoria(nombre)) {
		   throw new CategoriaException("El nombre de la categoria ya existe");
	   }
	   Cat_Padre nuevaCategoria = new Cat_Padre(nombre);
	   this.categorias.put(nombre, nuevaCategoria);
	   this.arbolCategorias.put(nombre, nuevaCategoria);
   }
    public void agregarCategoriaConProductos(String nombre) throws CategoriaException{
	   if(existeCategoria(nombre)) {
		   throw new CategoriaException("Esta categoria ya existe");
	   }
	   Cat_Producto nuevaCategoria = new Cat_Producto(nombre);
	   this.categorias.put(nombre, nuevaCategoria);
	   this.arbolCategorias.put(nombre, nuevaCategoria);
   }
    public void asignarlePadreCategoria(String nombrePadre, String nombre) throws CategoriaException {
	   Cat_Padre catPadre = (Cat_Padre) this.categorias.get(nombrePadre);
	   Cat_Padre cat = (Cat_Padre) this.categorias.get(nombre);
	   
	   if(catPadre.verificarSiYaEsHijo(nombre)) {
		   throw new CategoriaException("Esta categoria ya es su hijo");
	   }
	   
	   cat.setPadre(catPadre);
	   catPadre.agregarHijo(cat);
	   arbolCategorias.remove(cat.getNombre());
   }
    public void asignarlePadreACategoriaProds(String nombrePadre, String nombre) throws CategoriaException {

    	Cat_Padre catPadre = (Cat_Padre) this.categorias.get(nombrePadre);
    	Cat_Producto cat = (Cat_Producto) this.categorias.get(nombre);
 	   
 	   if(catPadre.verificarSiYaEsHijo(nombre)) {
 		   throw new CategoriaException("Esta categoria ya es su hijo");
 	   }
 	   
 	   cat.setPadre(catPadre);
 	   catPadre.agregarHijo(cat);
 	   arbolCategorias.remove(cat.getNombre());

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
    // Reutilizacion de listarClientes del caso de uso 5
    public DefaultMutableTreeNode arbolProductos() {
   	 DefaultMutableTreeNode root = new DefaultMutableTreeNode("Cats");
   	 for(Categoria cat : arbolCategorias.values()) {
   		 DefaultMutableTreeNode child = arbolProductos(cat);
   		 root.add(child);
   	 }
   	return root;
   }
    public DefaultMutableTreeNode arbolProductos(Categoria cat) {
  	 	DefaultMutableTreeNode rama = new DefaultMutableTreeNode(cat.getNombre());
  	 	if(cat.getTipo() == "Padre") {
  	 		Map<String, Categoria> hijos = ((Cat_Padre) cat).getHijos();
  	 		if(hijos.size() >= 1) {
  	 			for(Categoria hijo : hijos.values()) {
  	 				DefaultMutableTreeNode child = arbolProductos(hijo);
  	 				rama.add(child);
  	 			}
  	 		}else {
  	 			rama.add(new DefaultMutableTreeNode("Sin Elementos"));
  	 		}
  	 	}else {
  	 		Map<Integer, Producto> productos = ((Cat_Producto) cat).getProductos();
  	 		if(productos.size() >= 1) {
  	 			for(Producto prod : productos.values()) {
  	 				DefaultMutableTreeNode child = new DefaultMutableTreeNode(prod.getNombre() + " - " + prod.getNumRef());
  					rama.add(child);
  	 			}
  	 		}else {
  	 			rama.add(new DefaultMutableTreeNode("Sin Elementos"));
  	 		}
  	 	}
  	return rama;
  }
    public void CrearOrden() {
    	int maxKey = ordenes.keySet().stream().max(Integer::compare).orElse(0);
    	OrdenDeCompra orden = new OrdenDeCompra(maxKey + 1);
    	ordenes.put(orden.getNumero(), orden);
    }
    public void agregarProducto(int numRef, int cant) {
    	for (Usuario user : usuarios.values()) {
    		if (user instanceof Proveedor) {
    			Proveedor p = (Proveedor) user;
    			Producto prod = p.obtenerProd(numRef);
    			if(prod != null) {
    				ordenes.get(ordenes.size()).addItem(prod, cant);
    			}
    		}
    	}
    }
    public void asignarOrdenCliente(String cliente) {
    	System.out.println(cliente);
    	Cliente c = (Cliente) usuarios.get(cliente);
    	System.out.println(c.getNombre());
    	c.agregarCompra(ordenes.get(ordenes.size()));
    }
    
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
    public Cliente getClienteDeOrden(Integer orden) {
    	for (Usuario usuario : usuarios.values()) {
            if (usuario instanceof Cliente) {
                Cliente cliente = (Cliente) usuario;
                if (cliente.existeOrden(orden))
                    return cliente;
            }
        }
        return null;
    }

    public void eliminarOrdenDeCompra(int numero) throws OrdenDeCompraException {
    	OrdenDeCompra orden = this.ordenes.get(numero);
        if (orden == null) {
            throw new OrdenDeCompraException("La orden de compra no existe");
        }
        Cliente cliente = getClienteDeOrden(orden.getNumero());
        if (cliente == null) {
        	throw new OrdenDeCompraException("No se encontró un cliente");        	
        }
        cliente.eliminarOrden(numero);
        Map<Integer, Item> items = orden.getItems();
        items.clear();
        this.ordenes.remove(numero);
        
    }
    public boolean existeOrden(int num) {
    	return ordenes.containsKey(num);
    }
    
    // CASO DE USO 8: MODIFICAR DATOS DE PRODUCTO
    public boolean verificarNumero(int numero, String titulo) {
    	Map<String, Categoria> cats = categorias;
    	int cont = 0;
    	for (Map.Entry<String, Categoria> entry : cats.entrySet()) {
    	
    		Cat_Producto prodC = (Cat_Producto) entry.getValue();
    	    	
    	    	
    		for(Entry<Integer, Producto> entry1: prodC.getProductos().entrySet()) {
    			Producto p = entry1.getValue();
    			if(p.getNombre() == titulo && p.getNumRef() == numero) {
    				cont++;
    			}
    		}
    	}
    	if(cont > 1) {
    		return false;
    	}

    	return true;
    }
    
    public int editarProducto(String titulo, int numero, String deescripcion, float precio, Producto p) {
    	if(!verificarNumero(numero, titulo)) {
    		return 1;
    	}
    	
    	
    	p.setNombre(titulo);
    	p.setPrecio(precio);
    	p.setDescripcion(deescripcion);
    	p.setNumRef(numero);
    	
    	return 0;
    }
    
    // CASO DE USO 9: VER INFORMACION DE PRODUCTO
    public List<DtProducto> listarProductosPorCategoria(String cat) throws ProductoException {
    	List <DtProducto> listaProductos = new ArrayList<>();
    	
    	Cat_Producto prodC = (Cat_Producto) this.categorias.get(cat);
    	
    	if(prodC.getProductos().isEmpty()) {
    		throw new ProductoException("Esta categoría no cuenta con productos");
    	}
    	
    	for(Entry<Integer, Producto> entry: prodC.getProductos().entrySet()) {
    		Producto p = entry.getValue();
    		
    		listaProductos.add(p.crearDT());
    	}	
    	
    	return listaProductos;
    }
    
    public List<DtProducto> listarALLProductos() throws ProductoException {
    	List<DtProducto> listaProductos = new ArrayList<>();
    	
    	for(Map.Entry<String, Categoria> entry : this.categorias.entrySet()) {
    		Categoria c = entry.getValue();
    		
    		if(c.getTipo() == "Producto") {
    			Cat_Producto cProd = (Cat_Producto) c;
    			
    			List<DtProducto> listaPerProducto = cProd.listarProductos();
    			
    			if(listaPerProducto.isEmpty()) {
    				continue;
    			}
    			
    			for(DtProducto dt: listaPerProducto) {
    				listaProductos.add(dt);
    			}
    		}
    	}
    	if(listaProductos.isEmpty()) {
    		throw new ProductoException("No se ha encontrado ningun producto para listar");
    	}
    	
    	return listaProductos;
    	
    }
    
    
    
    
    public DtProducto getDtProducto(int numRef) {
    	for (Usuario user : usuarios.values()) {
    		if (user instanceof Proveedor) {
    			Proveedor p = (Proveedor) user;
    			Producto prod = p.obtenerProd(numRef);
    			if(prod != null) {
    				return prod.crearDT();
    			}
    		}
    	}
    	return null;
    }
    
    
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

 // Para probar lo de mostrar Ordenes

    public void addOrdenes(OrdenDeCompra o, String nickUsuario) {
    	Usuario us = this.usuarios.get(nickUsuario);
    	
    	Cliente cl = (Cliente) us;
    	ordenes.put(o.getNumero(), o);
    	cl.agregarCompra(o);
    }
    
    public boolean comprobarCat(String cat) throws CategoriaException {
    	if(this.categorias.get(cat) == null) {
    		throw new CategoriaException("Esta categoria no existe");
    	}
    	
    	return true;
    }
    
    public DTProveedor traerProveedorProducto(int numKey) {
        for (Map.Entry<String, Usuario> entry : this.usuarios.entrySet()) {
            Usuario u = entry.getValue();
            
            if (u instanceof Proveedor) {
                Proveedor proveedorBuscado = (Proveedor) u;
                DTProveedor provDT = proveedorBuscado.crearDt();
                if (provDT.existeProd(numKey)) {
                    return provDT;
                }
            }
        }
        
        return null;
    }
   
}

