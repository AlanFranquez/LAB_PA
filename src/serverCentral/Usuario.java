package serverCentral;

public class Usuario {
    private String Nombre, Nick, Apellido, Correo;
    private Date Nacimiento;
    // Constructor
    public Usuario(String Nombre, String Nick, String Apellido, String Correo, Date Nacimiento) {
        this.Nombre = Nombre;
        this.Nick = Nick;
        this.Apellido = Apellido;
        this.Correo = Correo;
        this.Nacimiento = Nacimiento;
    }
    // gets, sets
    public String getNombre()
        return Nombre;

    public String getNick()
        return Nick;

    public String getApellido()
        return Apellido;

    public String getCorreo()
        return Correo;

    public Date getNacimiento()
        return Nacimiento;

    public void setNombre(String Nombre)
        this.Nombre = Nombre;

    public void setNick(String Nick)
        this.Nick = Nick;

    public void setApellido(String Apellido)
        this.Apellido = Apellido;

    public void setCorreo(String Correo)
        this.Correo = Correo;

    public void setNacimiento(Date Nacimiento)
        this.Nacimiento = Nacimiento;
}
