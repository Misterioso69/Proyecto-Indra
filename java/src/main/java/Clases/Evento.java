package Clases;

public class Evento {
	private String nombre;
	private String fecha;
	private int duracion_min;
	private String lugar;
	private Organizador or;
	private Categoria ca;
	
	public Evento(String nombre, String fecha, int duracion_min, String lugar, Organizador or, Categoria ca) {
		super();
		this.nombre = nombre;
		this.fecha = fecha;
		this.duracion_min = duracion_min;
		this.lugar = lugar;
		this.or = or;
		this.ca = ca;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public int getDuracion_min() {
		return duracion_min;
	}
	public void setDuracion_min(int duracion_min) {
		this.duracion_min = duracion_min;
	}
	public String getLugar() {
		return lugar;
	}
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	public Organizador getOr() {
		return or;
	}
	public void setOr(Organizador or) {
		this.or = or;
	}
	public Categoria getCa() {
		return ca;
	}
	public void setCa(Categoria ca) {
		this.ca = ca;
	}
	@Override
	public String toString() {
		return " [nombre=" + nombre + ", fecha=" + fecha + ", duracion_min=" + duracion_min + ", lugar=" + lugar
				+ ", or=" + or + ", ca=" + ca + "]";
	}
	
	
	
	
}
