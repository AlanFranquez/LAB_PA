package serverCentral;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;

import java.awt.Image;
import java.io.File;

public class Sistema implements ISistema {
    private static Sistema instance = null;
    private Map<String, Usuario> usuarios;
    private Map<String, Categoria> categorias;

    private Sistema() {
        // Inicialización de colecciones
        this.usuarios = new HashMap<>();
        this.categorias = new HashMap<>();
    }

    public static synchronized Sistema getInstance() {
        if (instance == null) {
            instance = new Sistema();
        }
        return instance;
    }

    // Verificar si el usuario está o no agregado al sistema
    public boolean verificarUnicidad(String nick, String correo) {
        return !this.usuarios.containsKey(nick) && !this.usuarios.containsValue(correo);
    }

    // Validar correo electrónico
    public boolean validarCorreo(String correo) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(correo);
        return matcher.matches();
    }

    // Agregar usuario a la colección
    public void agregarProveedor(String nick, String correo, String nombre, String apellido, DTFecha fechaNacimiento, String compania, String link) throws UsuarioRepetidoException {
        if (!verificarUnicidad(nick, correo)) {
            throw new UsuarioRepetidoException("El usuario con el nick: " + nick + " ya existe");
        }

        Proveedor nuevoProveedor = new Proveedor(nick, correo, nombre, apellido, fechaNacimiento, compania, link);
        usuarios.put(nick, nuevoProveedor);
    }

    public void agregarCliente(String nombre, String nick, String apellido, String correo, DTFecha fecha) throws UsuarioRepetidoException {
        if (!verificarUnicidad(nick, correo)) {
            throw new UsuarioRepetidoException("El usuario con el nick: " + nick + " ya existe");
        }

        Cliente nuevoCliente = new Cliente(nombre, nick, apellido, correo, fecha);
        usuarios.put(nick, nuevoCliente);
    }

    public void agregarImagenes(String nick, ImageIcon imagen) {
        Usuario usuarioBuscado = this.usuarios.get(nick);
        if (usuarioBuscado == null) {
            System.out.println("Usuario con nick: " + nick + " no encontrado.");
            return;
        }

        usuarioBuscado.setImagen(imagen);
    }

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
}
