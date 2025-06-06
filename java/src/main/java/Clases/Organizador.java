package Clases;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import Funciones.Menu;

public class Organizador {
	private String nombre;
	private String correo;
	public Organizador(String nombre, String correo) {
		super();
		this.nombre = nombre;
		this.correo = correo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	@Override
	public String toString() {
		return  nombre + "/" + correo ;
	}
	
	public static Organizador crear_Organizador(ArrayList<Organizador>listaOrga) {
		Scanner sc = new Scanner(System.in);
		sc.nextLine();
		System.out.println("Escribe tu nombre");
		String nombre = sc.nextLine();
		System.out.println("Escribe tu correo");
		String correo = sc.nextLine();
		Organizador a = new Organizador(nombre, correo);
		listaOrga.add(a);
		System.out.println("\nCreacion completada\n");
		Menu.menu();
		return a;
	}
	
	
	public static void crear_Evento(ArrayList<Organizador>listaOrga, ArrayList<Evento>listaEven) {
		Scanner sc = new Scanner(System.in);
		sc.nextLine();
		System.out.println("Escibe el nombre");
		String nombre = sc.nextLine();
		System.out.println("Escibe la fecha");
		String fec = sc.nextLine();
		System.out.println("Escribe la duracion en minutos");
		int duracion = sc.nextInt();
		sc.nextLine();
		System.out.println("Escribe el lugar");
		String lugar = sc.nextLine();
		System.out.println("Escribe el nombre del Organizadoe que lo crea");
		String orga = sc.nextLine();
		Organizador or = null;
		boolean existeOr = false;
		Iterator<Organizador> i = listaOrga.iterator();
		while (i.hasNext()) {
			Organizador a = i.next();
			if (a.getNombre().equals(orga)) {
				existeOr = true;
				or = a;
				break;
			}
		}
		if (!existeOr) {
			System.out.println("\nEl nombre del Organizador no existe, Error\n");
			Menu.menu();
		}
		System.out.println("Escribe la categoria ");
		String cate = sc.nextLine();
		try {
			Categoria cat = Categoria.valueOf(cate);
			Evento eve = new Evento(nombre, fec, duracion, lugar, or, cat);
			listaEven.add(eve);
			System.out.println("\nCreacion completada\n");
		} catch (IllegalArgumentException e) {
			System.out.println("\nLa categoria no existe, Error\n");
		}
		Menu.menu();
	}
	
	public static void borrarOrganizador(ArrayList<Organizador>listaOrga) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Organizador:\n");
		Iterator<Organizador> or = listaOrga.iterator();
		while (or.hasNext()) {
			Organizador a = or.next();
			System.out.print(a);
		}
		sc.nextLine();
		System.out.println("\n¿Que Organizador quires borrar?");
		String nomb = sc.nextLine();
		boolean estao = false;
		Organizador o = null;
		Iterator<Organizador> org = listaOrga.iterator();
		while (org.hasNext()) {
			Organizador a = org.next();
			if (a.getNombre().equals(nomb)) {
				estao = true;
				o = a;
			}
		}
		if (estao) {
			listaOrga.remove(o);
			System.out.println("Organizador borrado\n");
			Menu.menu();
		} else {
			System.out.println("El organizador no existe\n");
			Menu.menu();
		}
	}
	
	public static void borrarEvento(ArrayList<Evento>listaEven) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Evento:\n");
		Iterator<Evento> ev = listaEven.iterator();
		while (ev.hasNext()) {
			Evento a = ev.next();
			System.out.print(a);
		}
		sc.nextLine();
		System.out.println("\n¿Que Evento quires borrar?");
		String nombr = sc.nextLine();
		boolean estae = false;
		Evento e = null;
		Iterator<Evento> eve = listaEven.iterator();
		while (eve.hasNext()) {
			Evento a = eve.next();
			if (a.getNombre().equals(nombr)) {
				estae = true;
				e = a;
			}
		}
		if (estae) {
			listaEven.remove(e);
			System.out.println("Evento borrado\n");
			Menu.menu();
		} else {
			System.out.println("El organizador no existe\n");
			Menu.menu();
		}
	}
}
