public class dtCliente {
    private String nombre, nick, apellido, correo;
    private Date nacimiento;
    private File imagen;

    public dtCliente(String nombre, String nick, String apellido, String correo, Date nacimiento, File imagen) {
        this.nombre = nombre;
        this.nick = nick;
        this.apellido = apellido;
        this.correo = correo;
        this.nacimiento = nacimiento;
        this.imagen = imagen;
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
    public Date getNacimiento() {
        return nacimiento;
    }
    public File getImagen() {
        return imagen;
    }
}
