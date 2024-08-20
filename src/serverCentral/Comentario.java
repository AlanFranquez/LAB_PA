package serverCentral;

import java.time.LocalDateTime;

public class Comentario {
	private String contenido;
	private LocalDateTime fecha;
	
	public Comentario (String cont) {
		fecha = LocalDateTime.now();
		contenido = cont;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

}
