package serverCentral;

import java.io.File;
import java.util.List;
import java.util.ArrayList;

public class Usuario {
    private String nombre, nick, apellido, correo;
    private DTFecha nacimiento;
    private List<File> imagenes;
    // Constructor
    public Usuario(String nombre, String nick, String apellido, String correo, DTFecha nacimiento) {
        this.nombre = nombre;
        this.nick = nick;
        this.apellido = apellido;
        this.correo = correo;
        this.nacimiento = nacimiento;
        this.imagenes = new ArrayList<>();
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
    public DTFecha getNacimiento(){
        return nacimiento;
    }
    public List<File> getImagenes() {
        return imagenes;
    }
    
    String funcionPrueba() {
    	return "Me tire un pedo";
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
    
    public void setImagenes(List<File> imagenes) {
        this.imagenes = imagenes;
    }
}

