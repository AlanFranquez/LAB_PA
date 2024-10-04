package serverCentral;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class Comentario {
	private int numero;
	private String texto;
	private Cliente autor;
	private LocalDateTime fecha;
	List <Comentario> respuestas;
	
	public Comentario(int numero, String texto, Cliente autor, LocalDateTime fecha) {
		this.setNumero(numero);
		this.setTexto(texto);
		this.setAutor(autor);
		this.setFecha(fecha);
		this.respuestas = new ArrayList<Comentario>();
	}
	
	public void agregarRespuesta(Comentario r) {
		this.respuestas.add(r);
	}
	
	public List<Comentario> getRespuestas() {
		return this.respuestas;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Cliente getAutor() {
		return autor;
	}

	public void setAutor(Cliente autor) {
		this.autor = autor;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}


}
