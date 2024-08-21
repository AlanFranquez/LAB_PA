package serverCentral;

public class Administrador extends Usuario {
	public Administrador(String nom, String nick, String ape, String correo, DTFecha nacimiento) {
        super(nom, nick, ape, correo, nacimiento, "Admin");
    }
}
