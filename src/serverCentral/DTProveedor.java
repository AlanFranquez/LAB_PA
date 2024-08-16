package serverCentral;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class DTProveedor {
	private Map<Integer, Producto> listaProductos;  
    private String compania, link, nombre, nick, apellido, correo;
    private DTFecha nacimiento;
    private File imagen;
    // Constructr
    public DTProveedor(String nombre, String nick, String apellido, String correo, DTFecha nacimiento, File imagen, String compania, String link) {
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
}
