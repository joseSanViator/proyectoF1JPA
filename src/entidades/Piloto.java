package entidades;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Piloto implements Serializable{
	
	@Id
	private int numPiloto; //ID no autogenerado, lo metemos nosotros manualmente.
	private String nombre;
	private String escuderia;
	private int puntosClasificacion;
	//constructor sin parámetros OBLIGATORIO:
	public Piloto() {}
	
	public Piloto(int numPiloto, String nombre, String escuderia, int puntosClasificacion) {
		super();
		this.numPiloto = numPiloto;
		this.nombre = nombre;
		this.escuderia = escuderia;
		this.puntosClasificacion = puntosClasificacion;
	}
	
	public int getNumPiloto() {
		return numPiloto;
	}
	public void setNumPiloto(int numPiloto) {
		this.numPiloto = numPiloto;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEscuderia() {
		return escuderia;
	}
	public void setEscuderia(String escuderia) {
		this.escuderia = escuderia;
	}
	public int getPuntosClasificacion() {
		return puntosClasificacion;
	}
	public void setPuntosClasificacion(int puntosClasificacion) {
		this.puntosClasificacion = puntosClasificacion;
	}

	@Override
	public String toString() {
		return "Piloto [numPiloto=" + numPiloto + ", nombre=" + nombre + ", escuderia=" + escuderia
				+ ", puntosClasificacion=" + puntosClasificacion + "]";
	}
	
	
}
