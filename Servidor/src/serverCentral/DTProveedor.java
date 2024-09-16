package serverCentral;

import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;



public class DTProveedor {
	private Map<Integer, Producto> listaProductos;  
    private String compania, link, nombre, nick, apellido, correo;
    private DTFecha nacimiento;
    private ImageIcon imagen;
    // Constructor:
    public DTProveedor(String nombre, String nick, String apellido, String correo, DTFecha nacimiento, ImageIcon imagen, String compania, String link) {
    	   this.nombre = nombre;
           this.nick = nick;
           this.apellido = apellido;
           this.correo = correo;
           this.nacimiento = nacimiento;
           this.imagen = imagen;
           this.compania = compania;
           this.link = link;
           this.listaProductos = new HashMap<>();
    }
    // Gets sets
    public String getCompania() {
        return compania;
    }

    public void setCompania(String comp) {
        this.compania = comp;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String lin) {
        this.link = lin;
    }

    public Map<Integer, Producto> getProductos() {
        return listaProductos;//??????'
    }
    public void agregarProd(int id, Producto prod) {
        listaProductos.put(id, prod);
    }
    public Producto obtenerProd(int id) {
        return listaProductos.get(id);
    }
    public void eliminarProd(int id) {
        listaProductos.remove(id);
    }
    public boolean existeProd(int id) {
        return listaProductos.containsKey(id);
    }
    public int cantProd() {
        return listaProductos.size();
    }
	public String getNombre() {
		return this.nombre;
	}
	public Map<Integer, Producto> getListaProductos() {
		return listaProductos;
	}
	public void setListaProductos(Map<Integer, Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public DTFecha getNacimiento() {
		return nacimiento;
	}
	public void setNacimiento(DTFecha nacimiento) {
		this.nacimiento = nacimiento;
	}
	public ImageIcon getImagen() {
		return imagen;
	}
	public void setImagen(ImageIcon imagen) {
		this.imagen = imagen;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
