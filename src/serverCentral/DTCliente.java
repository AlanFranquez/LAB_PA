package serverCentral;
import java.io.File;
import java.util.List;
import java.util.Map;

public class DTCliente {
    private String nombre, nick, apellido, correo;
    private DTFecha nacimiento;
    private Map<Integer, OrdenDeCompra> ordenes;
	private List<File> imagenes;
    
    public DTCliente(String nombre, String nick, String apellido, String correo, DTFecha nacimiento, List<File> imagenes, Map<Integer, OrdenDeCompra> ordenes) {
        this.nombre = nombre;
        this.nick = nick;
        this.apellido = apellido;
        this.correo = correo;
        this.nacimiento = nacimiento;
        this.imagenes = imagenes;
        this.ordenes = ordenes;
    }
    // Gets
    public String getNombre() {
        return nombre;
    }
    public String getNick() {
        return nick;
    }
    public String getApellido() {
        return apellido;
    }
    public String getCorreo() {
        return correo;
    }
    public DTFecha getNacimiento() {
        return nacimiento;
    }
    public List<File> getImagenes() {
        return imagenes;
    }
    public Map<Integer, OrdenDeCompra> getOrdenes() {
        return ordenes;
    }
}
