package serverCentral;

import java.io.File;
import java.util.List;
import java.util.ArrayList;

public class Usuario {
    private String nombre, nick, apellido, correo, tipo;
    private DTFecha nacimiento;
    private File imagen;
    // Constructor
    public Usuario(String nombre, String nick, String apellido, String correo, DTFecha nacimiento,String tipo) {
        this.nombre = nombre;
        this.nick = nick;
        this.apellido = apellido;
        this.correo = correo;
        this.nacimiento = nacimiento;
        this.imagen = null;
        this.tipo = tipo;
    }
    // gets, sets
    public String getNombre(){
        return nombre;
    }
    public String getNick(){
        return nick;
    }
    public String getApellido(){
        return apellido;
    }
    public String getCorreo(){
        return correo;
    }
    public String getTipo() {
    	return tipo;
    }
    public DTFecha getNacimiento(){
        return nacimiento;
    }
    public File getImagen() {
        return imagen;
    }
    
    
    public void setNombre(String Nombre){
        this.nombre = Nombre;
    }
    public void setNick(String Nick){
        this.nick = Nick;
    }
    public void setApellido(String Apellido){
        this.apellido = Apellido;
    }
    public void setCorreo(String Correo){
        this.correo = Correo;
    }
    public void setNacimiento(DTFecha nacimiento){
        this.nacimiento = nacimiento;
    }
    
    public void setImagen(File imagen) { 
        this.imagen = imagen;
        
        // Verificar si el archivo de imagen existe
        if (imagen != null) {
            if (imagen.exists() && imagen.isFile()) {
                System.out.println("Imagen cargada correctamente: " + imagen.getAbsolutePath());
            } else {
                System.out.println("El archivo de imagen no existe o no es un archivo válido.");
            }
        } else {
            System.out.println("La imagen es null.");
        }
    }
    
    // Método para depurar la clase Usuario
    public static void main(String[] args) {
        Usuario usuario = new Usuario("Juan", "juanito", "Pérez", "juanito@mail.com", new DTFecha(1, 1, 1990), "cliente");
        usuario.setImagen(new File("imagenes/p1.jpg")); // Ajusta la ruta según tu estructura
    }
}

