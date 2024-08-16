package serverCentral;
import java.io.File;
import java.util.List;
import java.util.Map;

public class DTCliente {
    private String nombre, nick, apellido, correo;
    private DTFecha nacimiento;
    private Map<Integer, OrdenDeCompra> ordenes;
	private File imagen;
    
    public DTCliente(String nombre, String nick, String apellido, String correo, DTFecha nacimiento, File imagen, Map<Integer, OrdenDeCompra> ordenes) {
        this.nombre = nombre;
        this.nick = nick;
        this.apellido = apellido;
        this.correo = correo;
        this.nacimiento = nacimiento;
        this.imagen = imagen;
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
    @Override
    public String toString() {
        return "Mail: " + correo + " | " + "Nick: " + nick; 
               
    }
    
    public String toStringCompleto() {
    	return 	
    			"Imagen no agregada todavía" +
    			System.lineSeparator() + System.lineSeparator() +
    			
    			"Mail: " + correo + System.lineSeparator() +
    			"Nick: " + nick + System.lineSeparator() +
    			"Nombre Completo: " + nombre + " " + apellido + System.lineSeparator() + 
    			"Fecha de nacimiento " + nacimiento.getDia() + " - " + nacimiento.getMes() +  " - " + nacimiento.getAnio();
    			
    }
    
    public String getCorreo() {
        return correo;
    }
    public DTFecha getNacimiento() {
        return nacimiento;
    }
    public File getImagenes() {
        return imagen;
    }
    public Map<Integer, OrdenDeCompra> getOrdenes() {
        return ordenes;
    }
}
